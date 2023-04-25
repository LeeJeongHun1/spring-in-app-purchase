package com.springinapppurchase.service;

import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.springinapppurchase.component.AppleInAppPurchaseProvider;
import com.springinapppurchase.component.JwtProvider;
import com.springinapppurchase.component.MessageManager;
import com.springinapppurchase.dto.ApiResponse;
import com.springinapppurchase.dto.AppleIAPResponseDto;
import com.springinapppurchase.dto.AppleInAppPurchaseDto;
import com.springinapppurchase.dto.ReceiptDto;
import com.springinapppurchase.entity.AppleInAppPurchase;
import com.springinapppurchase.entity.Subscription;
import com.springinapppurchase.entity.SubscriptionPlan;
import com.springinapppurchase.entity.user.User;
import com.springinapppurchase.enumeration.PurchaseStatus;
import com.springinapppurchase.exception.NotFoundException;
import com.springinapppurchase.repository.RepositoryWrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class InAppPurchaseService {
    private final AppleInAppPurchaseProvider appleInAppPurchaseProvider;
    private final RepositoryWrapper repositoryWrapper;
    private final MessageManager messageManager;
//    private final ModelMapper modelMapper;
    private final JwtProvider jwtProvider;


    /**
     * apple 관리자 페이지에서 개발자 실시간 알림 환경에 알림 서버 endpoint 등록
     * 해당 endpoint 로 token data가 넘어 옴.
     * token 디코딩하여 originalTransactionId 에 해당하는 상품 환불 처리
     * @param token
     */
    public void refund(String token) {
        // iap response token decode
        DecodedJWT decodedJWT = jwtProvider.GetDecodedJWT(token);
        Map<String, Claim> claims = decodedJWT.getClaims();
        String transactionId = String.valueOf(claims.get("originalTransactionId"));

        // originalTransactionId 통해 구매 내역 조회
        AppleInAppPurchaseDto appleInAppPurchaseDto = repositoryWrapper.appleInAppPurchase
                .findByOriginalTransactionId(transactionId).orElseThrow(() -> new NotFoundException(""));
        // db 환불 처리
        appleInAppPurchaseDto.setStatus(PurchaseStatus.REFUNDED);
//        appleInAppPurchaseDto.updateRevocationDate();
//        appleInAppPurchaseDto.updateRevocationReason("");
        repositoryWrapper.appleInAppPurchase.updateStatus(appleInAppPurchaseDto);

        // 구독 상품 만료 기간 수정
        Subscription subscription = repositoryWrapper.subscription.findByUserId(appleInAppPurchaseDto.getUser().getId())
                .orElseThrow(() -> new NotFoundException("SUBSCRIPTION.NOT_FOUND"));

        long until = appleInAppPurchaseDto.getPurchaseDate()
                .until(appleInAppPurchaseDto.getExpirationDate(), ChronoUnit.DAYS);
        subscription.updateExpirationDate(subscription.getExpirationDate().minus(Duration.ofDays(until)));

        if (subscription.getExpirationDate().isBefore(Instant.now())) {
            subscription.updateExpire(true);
        }
    }

    /**
     * client 에서 결제 후 영수증 데이터 검증.
     * @param userId
     * @param receiptDto
     * @return
     */
    public ApiResponse verifyReceipt(Long userId, ReceiptDto receiptDto) {

        AppleIAPResponseDto appleIAPResponseDto = appleInAppPurchaseProvider.verifyReceipt(receiptDto);

        findLatestReceipt(appleIAPResponseDto.getReceipt());

        if (appleIAPResponseDto.getReceipt().getInApp().isEmpty()) {
            return ApiResponse.error(messageManager.getMessage("IAP.RECEIPT_REQUIRED"), HttpStatus.BAD_REQUEST);
        }

//         정상적인 구매일 경우 subscription, appleInAppPurchase 생성
        createSubscription(userId, appleIAPResponseDto.getReceipt(), receiptDto);
        Subscription subscription = repositoryWrapper.subscription.findByUserId(userId)
                .orElseThrow(() -> new NotFoundException("SUBSCRIPTION.NOT_FOUND"));
//        return modelMapper.map(subscription, SubscriptionDto.class);
        return ApiResponse.success(subscription);
    }

    /**
     * receipt data를 통해 verify 후 inApp Array에 TransactionId를 db에 조회하여
     * 가장 최신 구매 영수증 find
     * @param receipt
     */
    private void findLatestReceipt(AppleIAPResponseDto.Receipt receipt) {
        List<String> receiptTransactionIds = receipt.getInApp().stream().map(AppleIAPResponseDto.InApp::getTransactionId)
                .collect(Collectors.toList());

        // 결제 transaction id 로 구매 내역 조회
        List<AppleInAppPurchaseDto> iapDtoList = repositoryWrapper.appleInAppPurchase
                .findAllByTransactionIds(receiptTransactionIds);
        List<String> transactionIds = iapDtoList.stream().map(AppleInAppPurchaseDto::getTransactionId)
                .collect(Collectors.toList());
//
        List<AppleIAPResponseDto.InApp> purchaseReceipt = receipt.getInApp().stream()
                .filter(inApp -> !transactionIds.contains(inApp.getTransactionId()))
                .collect(Collectors.toList());
        receipt.setInApp(purchaseReceipt);
    }

    /**
     * 구매한 사용자의 구독권을 확인하고 결제내역 저장.
     * 구독권이 존재하면 기간 연장
     * 구독권이 존재하지 않으면 신규 구독권 생성
     * @param userId
     * @param receipt
     * @param receiptDto
     */
    private void createSubscription(Long userId, AppleIAPResponseDto.Receipt receipt, ReceiptDto receiptDto) {
        for (AppleIAPResponseDto.InApp inApp : receipt.getInApp()) {
            SubscriptionPlan subscriptionPlan = repositoryWrapper.subscriptionPlan.findByProductId(inApp.getProductId());
            Subscription subscription = repositoryWrapper.subscription.findByUserId(userId)
                    .orElse(null);
            if (subscription == null) {
                subscription = Subscription.builder()
                        .subscriptionPlan(subscriptionPlan)
                        .purchaseDate(inApp.getPurchaseDate())
                        .expirationDate(inApp.getPurchaseDate()
                                .plus(Duration.ofDays(getDuration(subscriptionPlan.getDuration()))))
                        .isExpire(false)
                        .build();
            } else {
                subscription.updateSubscriptionPlan(subscriptionPlan);
                subscription.updatePurchaseDate(inApp.getPurchaseDate());
                subscription.updateExpirationDate(subscription.getExpirationDate()
                        .plus(Duration.ofDays(getDuration(subscriptionPlan.getDuration()))));
                subscription.updateExpire(false);
            }
            Subscription savedSubscription = repositoryWrapper.subscription.save(subscription);

            AppleInAppPurchase appleInAppPurchase = AppleInAppPurchase.builder()
                    .user(User.of().id(userId).build())
                    .appItemId(receipt.getAppItemId())
                    .productId(inApp.getProductId())
                    .transactionId(inApp.getTransactionId())
                    .originalTransactionId(inApp.getOriginalTransactionId())
                    .price(receiptDto.getPrice())
                    .currency(receiptDto.getCurrency())
                    .receiptData(receiptDto.getReceiptData())
                    .status(PurchaseStatus.COMPLETED)
                    .purchaseDate(inApp.getPurchaseDate())
                    .expirationDate(inApp.getPurchaseDate()
                            .plus(Duration.ofDays(getDuration(subscriptionPlan.getDuration()))))
                    .build();
            repositoryWrapper.appleInAppPurchase.save(appleInAppPurchase);
        }
    }

    /**
     * apple 에 등록한 정기결제 상품의 사용 기한.
     * @param duration
     * @return
     */
    private Long getDuration(String duration) {
        switch (duration) {
            case "P3M":
                return 90L;
            case "P6M":
                return 180L;
            default:
                return 30L;
        }
    }

}
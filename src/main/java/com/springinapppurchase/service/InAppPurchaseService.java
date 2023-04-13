package com.springinapppurchase.service;

import com.springinapppurchase.component.AppleInAppPurchaseProvider;
import com.springinapppurchase.dto.AppleIAPResponseDto;
import com.springinapppurchase.dto.ReceiptDto;
import com.springinapppurchase.dto.SubscriptionDto;
import com.springinapppurchase.repository.RepositoryWrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class InAppPurchaseService {
    private final AppleInAppPurchaseProvider appleInAppPurchaseProvider;
    private final RepositoryWrapper repositoryWrapper;
//    private final ModelMapper modelMapper;
//    private final JwtProvider jwtProvider;

    public void refund(String token) {
        // iap response token decode
//        DecodedJWT decodedJWT = jwtProvider.GetDecodedJWT(token);
//        Map<String, Claim> claims = decodedJWT.getClaims();
        String transactionId = "2000000305368959";
        // originalTransactionId 통해 구매 내역 조회
////        AppleInAppPurchaseDto appleInAppPurchaseDto = repositoryWrapper.appleInAppPurchase
////                .findByOriginalTransactionId(transactionId).orElseThrow(() -> new NotFoundException(""));
//        // db 환불 처리
//        appleInAppPurchaseDto.setStatus(PurchaseStatus.REFUNDED);
////        appleInAppPurchase.updateRevocationDate();
////        appleInAppPurchase.updateRevocationReason("");
////        repositoryWrapper.appleInAppPurchase.updateStatus(appleInAppPurchaseDto);
//
//        // 구독 상품 만료 기간 수정
////        Subscription subscription = repositoryWrapper.subscription.findByChannelId(appleInAppPurchaseDto.getChannel().getId())
////                .orElseThrow(() -> new NotFoundException("SUBSCRIPTION.NOT_FOUND"));
//
//        long until = appleInAppPurchaseDto.getPurchaseDate()
//                .until(appleInAppPurchaseDto.getExpirationDate(), ChronoUnit.DAYS);
//        subscription.updateExpirationDate(subscription.getExpirationDate().minus(Duration.ofDays(until)));
//
//        if (subscription.getExpirationDate().isBefore(Instant.now())) {
//            subscription.updateExpire(true);
//        }
    }


    public SubscriptionDto verifyReceipt(ReceiptDto receiptDto) {

        AppleIAPResponseDto appleIAPResponseDto = appleInAppPurchaseProvider.verifyReceipt(receiptDto);

        findLatestReceipt(appleIAPResponseDto.getReceipt());

//        if (appleIAPResponseDto.getReceipt().getInApp().isEmpty()) {
//            throw new BadRequestException("IAP.RECEIPT_REQUIRED");
//        }

//         정상적인 구매일 경우 subscription, appleInAppPurchase 생성
//        Subscription subscription = createSubscription(loginUserId, channelId, appleIAPResponseDto.getReceipt(), receiptDto);
        createSubscription(appleIAPResponseDto.getReceipt(), receiptDto);
//        Subscription subscription = repositoryWrapper.subscription.findByChannelId(1l)
//                .orElseThrow(() -> new NotFoundException("SUBSCRIPTION.NOT_FOUND"));
//        return modelMapper.map(subscription, SubscriptionDto.class);
        return null;
    }

    private void findLatestReceipt(AppleIAPResponseDto.Receipt receipt) {
        List<String> receiptTransactionIds = receipt.getInApp().stream().map(AppleIAPResponseDto.InApp::getTransactionId)
                .collect(Collectors.toList());

        // 결제 transaction id 로 구매 내역 조회
//        List<AppleInAppPurchaseDto> iapDtoList = repositoryWrapper.appleInAppPurchase
//                .findAllByTransactionIds(receiptTransactionIds);
//        List<String> transactionIds = iapDtoList.stream().map(AppleInAppPurchaseDto::getTransactionId)
//                .collect(Collectors.toList());
//
//        List<AppleIAPResponseDto.InApp> purchaseReceipt = receipt.getInApp().stream()
//                .filter(inApp -> !transactionIds.contains(inApp.getTransactionId()))
//                .collect(Collectors.toList());
//        receipt.setInApp(purchaseReceipt);
    }

    private void createSubscription(AppleIAPResponseDto.Receipt receipt, ReceiptDto receiptDto) {
//        for (AppleIAPResponseDto.InApp inApp : receipt.getInApp()) {
////            AppleIAPResponseDto.InApp inApp = receipt.getInApp().get(0);
//            SubscriptionPlan subscriptionPlan = repositoryWrapper.subscriptionPlan.findByProductId(inApp.getProductId());
//            Subscription subscription = repositoryWrapper.subscription.findByChannelId(channelId)
//                    .orElse(null);
//            if (subscription == null) {
//                subscription = Subscription.builder()
//                        .channel(Channel.of().id(channelId).build())
//                        .subscriptionPlan(subscriptionPlan)
//                        .purchaseDate(inApp.getPurchaseDate())
//                        .expirationDate(inApp.getPurchaseDate()
//                                .plus(Duration.ofDays(getDuration(subscriptionPlan.getDuration()))))
//                        .isExpire(false)
//                        .build();
//            } else {
//                subscription.updateSubscriptionPlan(subscriptionPlan);
//                subscription.updatePurchaseDate(inApp.getPurchaseDate());
//                subscription.updateExpirationDate(subscription.getExpirationDate()
//                        .plus(Duration.ofDays(getDuration(subscriptionPlan.getDuration()))));
//                subscription.updateExpire(false);
//            }
//            Subscription savedSubscription = repositoryWrapper.subscription.save(subscription);
//
//            AppleInAppPurchase appleInAppPurchase = AppleInAppPurchase.builder()
//                    .user(User.of().id(userId).build())
//                    .channel(Channel.of().id(channelId).build())
////                .subscription(savedSubscription)
//                    .appItemId(receipt.getAppItemId())
//                    .productId(inApp.getProductId())
//                    .transactionId(inApp.getTransactionId())
//                    .originalTransactionId(inApp.getOriginalTransactionId())
//                    .price(receiptDto.getPrice())
//                    .currency(receiptDto.getCurrency())
//                    .receiptData(receiptDto.getReceiptData())
//                    .status(PurchaseStatus.COMPLETED)
//                    .purchaseDate(inApp.getPurchaseDate())
//                    .expirationDate(inApp.getPurchaseDate()
//                            .plus(Duration.ofDays(getDuration(subscriptionPlan.getDuration()))))
//                    .build();
//            repositoryWrapper.appleInAppPurchase.save(appleInAppPurchase);
//        }
//        return savedSubscription;
    }

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
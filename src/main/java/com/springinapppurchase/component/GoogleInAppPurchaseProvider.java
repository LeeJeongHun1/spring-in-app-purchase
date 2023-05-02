package com.springinapppurchase.component;

import com.google.api.services.androidpublisher.AndroidPublisher;
import com.google.api.services.androidpublisher.model.SubscriptionPurchaseV2;
import com.google.auth.oauth2.AccessToken;
import com.springinapppurchase.config.GoogleCredentialsConfig;
import com.springinapppurchase.dto.iap.google.AcknowledgementState;
import com.springinapppurchase.dto.iap.google.GoogleIAPResponseDto;
import com.springinapppurchase.dto.iap.google.SubscriptionState;
import com.springinapppurchase.exception.BadRequestException;
import com.springinapppurchase.exception.InternalServerErrorException;
import com.springinapppurchase.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.time.Instant;

@Slf4j
@Component
@RequiredArgsConstructor
public class GoogleInAppPurchaseProvider {
    private final GoogleCredentialsConfig googleCredentialsConfig;

    @Value("${iap.android.package-name}")
    private String packageName;

    /**
     * 영수증 데이터(purchase token)으로 api 호출하여 정상적인 응답이 내려올 경우 responseDto에 매핑
     * @param token
     * @return
     */
    public GoogleIAPResponseDto verifyReceipt(String token) {

        AndroidPublisher publisher = null;
        GoogleIAPResponseDto googleIAPResponseDto;
        try {
            publisher = googleCredentialsConfig.androidPublisher();
            AccessToken accessToken = googleCredentialsConfig.getAccessToken();
            AndroidPublisher.Purchases.Subscriptionsv2.Get get = publisher.purchases().subscriptionsv2()
                    .get(packageName, token);
            get.setAccessToken(accessToken.getTokenValue());
            SubscriptionPurchaseV2 subscriptionPurchaseV2 = get.execute();

            googleIAPResponseDto = GoogleIAPResponseDto.builder()
                    .kind(subscriptionPurchaseV2.getKind())
                    .latestOrderId(subscriptionPurchaseV2.getLatestOrderId())
                    .acknowledgementState(AcknowledgementState.valueOf(subscriptionPurchaseV2.getAcknowledgementState()))
                    .externalAccountIdentifiers(subscriptionPurchaseV2.getExternalAccountIdentifiers())
                    .subscriptionState(SubscriptionState.valueOf(subscriptionPurchaseV2.getSubscriptionState()))
                    .linkedPurchaseToken(subscriptionPurchaseV2.getLinkedPurchaseToken())
                    .regionCode(subscriptionPurchaseV2.getRegionCode())
                    .startTime(Instant.parse(subscriptionPurchaseV2.getStartTime()))
                    .pausedStateContext(subscriptionPurchaseV2.getPausedStateContext())
                    .testPurchase(subscriptionPurchaseV2.getTestPurchase())
                    .Items(subscriptionPurchaseV2.getLineItems())
                    .subscribeWithGoogleInfo(subscriptionPurchaseV2.getSubscribeWithGoogleInfo())
                    .canceledStateContext(subscriptionPurchaseV2.getCanceledStateContext())
                    .build();
        } catch (IOException | GeneralSecurityException e) {
            throw new InternalServerErrorException("IAP.VALIDATE_RECEIPT_FAIL");
        }
        return googleIAPResponseDto;
    }

    public void verifySubscriptionState(SubscriptionState subscriptionState) {
        switch (subscriptionState) {
            case SUBSCRIPTION_STATE_UNSPECIFIED:
                throw new NotFoundException("SUBSCRIPTION_PLAN.NOT_FOUND");
            case SUBSCRIPTION_STATE_EXPIRED:
                throw new BadRequestException("SUBSCRIPTION.PERIOD_EXPIRED");
            case SUBSCRIPTION_STATE_ACTIVE: break;
//            case SUBSCRIPTION_STATE_PAUSED: break;
//            case SUBSCRIPTION_STATE_CANCELED: break;
//            case SUBSCRIPTION_STATE_IN_GRACE_PERIOD: break;
//            case SUBSCRIPTION_STATE_ON_HOLD: break;
//            case SUBSCRIPTION_STATE_PENDING: break;
        }
    }

    public void verifyAcknowledgementState(AcknowledgementState acknowledgementState) {
        switch (acknowledgementState) {
            case ACKNOWLEDGEMENT_STATE_PENDING:
                throw new BadRequestException("IAP.PURCHASE_FAIL");
            case ACKNOWLEDGEMENT_STATE_UNSPECIFIED:
                throw new NotFoundException("SUBSCRIPTION_PLAN.NOT_FOUND");
        }
    }


}

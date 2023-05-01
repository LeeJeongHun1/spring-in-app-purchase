package com.springinapppurchase.dto.iap.google;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.api.services.androidpublisher.model.*;
import lombok.*;

import java.time.Instant;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Builder
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GoogleIAPResponseDto {

    private String kind;
    private String regionCode;
    private String latestOrderId;
    private List<SubscriptionPurchaseLineItem> Items;
    private Instant startTime;
    private SubscriptionState subscriptionState;
    private String linkedPurchaseToken;
    private PausedStateContext pausedStateContext;
    private CanceledStateContext canceledStateContext;
    private TestPurchase testPurchase;
    private AcknowledgementState acknowledgementState;
    private ExternalAccountIdentifiers externalAccountIdentifiers;
    private SubscribeWithGoogleInfo subscribeWithGoogleInfo;

}

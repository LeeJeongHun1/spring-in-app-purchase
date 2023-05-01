package com.springinapppurchase.dto.iap.google;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@ToString
@AllArgsConstructor
@Getter
public enum AcknowledgementState {
    ACKNOWLEDGEMENT_STATE_UNSPECIFIED,
    ACKNOWLEDGEMENT_STATE_PENDING,
    ACKNOWLEDGEMENT_STATE_ACKNOWLEDGED
}

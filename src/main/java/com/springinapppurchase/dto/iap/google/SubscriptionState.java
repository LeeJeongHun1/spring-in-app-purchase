package com.springinapppurchase.dto.iap.google;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@ToString
@AllArgsConstructor
@Getter
public enum SubscriptionState {

    SUBSCRIPTION_STATE_UNSPECIFIED, // 구독 상태가 지정되지 않았습니다.
    SUBSCRIPTION_STATE_PENDING, //	가입이 생성되었지만 가입 과정에서 결제를 기다리는 중입니다. 이 상태에서는 모든 항목이 결제 대기 중입니다.
    SUBSCRIPTION_STATE_ACTIVE, //	구독이 활성 상태입니다. - (1) 정기 결제가 자동 갱신 요금제인 경우 하나 이상의 항목이 autoRenewEnabled이며 만료되지 않았습니다. - (2) 정기 결제가 선불 요금제라면 하나 이상의 항목이 만료되지 않습니다.
    SUBSCRIPTION_STATE_PAUSED, //	정기 결제가 일시중지되었습니다. 이 상태는 구독이 자동 갱신 요금제인 경우에만 사용할 수 있습니다. 이 상태에서 모든 항목은 일시중지 상태입니다.
    SUBSCRIPTION_STATE_IN_GRACE_PERIOD, //	구독은 유예 기간입니다. 이 상태는 구독이 자동 갱신 요금제인 경우에만 사용할 수 있습니다. 이 상태에서는 모든 항목이 유예 기간 상태가 됩니다.
    SUBSCRIPTION_STATE_ON_HOLD, //	구독이 보류 중입니다 (정지됨). 이 상태는 구독이 자동 갱신 요금제인 경우에만 사용할 수 있습니다. 이 상태에서는 모든 항목이 보존 조치됩니다.
    SUBSCRIPTION_STATE_CANCELED, //	정기 결제가 취소되었지만 아직 만료되지 않았습니다. 이 상태는 구독이 자동 갱신 요금제인 경우에만 사용할 수 있습니다. 모든 항목의 autoRenewEnabled가 false로 설정되었습니다.
    SUBSCRIPTION_STATE_EXPIRED, //	구독이 만료되었습니다. 모든 항목의 만료 시간이 과거입니다.
}

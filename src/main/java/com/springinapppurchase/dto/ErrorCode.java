package com.springinapppurchase.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    USER_NOT_FOUND(404, "존재하지 않는 이메일입니다."),
    PRODUCT_NOT_FOUND(404, "존재하지 않는 상품입니다.."),

    NOT_FOUND(404, "존재하지 않는 페이지입니다."),
    ORDER_NOT_FOUND(404, "존재하지 않는 주문입니다."),
    STOCK_SHORT(403, "주문하신 수량에 비해 재고가 부족합니다."),
    SOLD_OUT(403, "주문하신 상품은 품절되었습니다."),
    ALREADY_ORDER(403, "이미 주문완료된 주문입니다."),
    CANCEL_ORDER(403, "이미 취소된 주문입니다."),

    UNAUTHORIZED_USER(401, "인증되지 않은 사용자입니다."),

    ELSE_ERROR(501, "알 수 없는 이유로 오류가 발생했습니다.");

    private final int code;
    private final String message;
}

package com.springinapppurchase.dto.iap.google;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.springinapppurchase.dto.ChannelDto;
import com.springinapppurchase.dto.UserDto;
import com.springinapppurchase.enumeration.PurchaseStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;

@NoArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GoogleInAppPurchaseDto {

    private Long id;
    private UserDto user;
    private ChannelDto channel;
    private String quantity;
    private Long appItemId;
    private String productId;
    private String transactionId;
    private String originalTransactionId;
    private PurchaseStatus status;
    private BigDecimal price;
    private String currency;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss VV")
    private Instant purchaseDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss VV")
    private Instant expirationDate;

}

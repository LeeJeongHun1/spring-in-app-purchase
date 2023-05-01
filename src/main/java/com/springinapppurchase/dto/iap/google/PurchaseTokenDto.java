package com.springinapppurchase.dto.iap.google;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@NoArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PurchaseTokenDto {

    @NotNull
    private BigDecimal price;
    @NotNull
    private String currency;
    @NotNull
    private String purchaseToken;

}

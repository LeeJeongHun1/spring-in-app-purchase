package com.springinapppurchase.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AppleIAPResponseDto {

    private Receipt receipt;
    private String environment;
    private Integer status;

    @NoArgsConstructor
    @Getter
    @Setter
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
    public static class Receipt {
        private String receiptType;
        private Long adamId;
        private Long appItemId;
        private String bundleId;
        private String applicationVersion;
        private Long downloadId;
        private Long versionExternalIdentifier;

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss VV")
        private Instant receiptCreationDate;
        private String receiptCreationDateMs;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss VV")
        private Instant receiptCreationDatePst;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss VV")
        private Instant requestDate;
        private String requestDateMs;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss VV")
        private Instant requestDatePst;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss VV")
        private Instant originalPurchaseDate;
        private String originalPurchaseDateMs;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss VV")
        private Instant originalPurchaseDatePst;
        private String originalApplicationVersion;
        private List<InApp> inApp;
    }

    @NoArgsConstructor
    @Getter
    @Setter
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
    public static class InApp {
        private String quantity;
        private String productId;
        private String transactionId;
        private String originalTransactionId;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss VV")
        private Instant purchaseDate;
        private String purchaseDateMs;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss VV")
        private Instant purchaseDatePst;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss VV")
        private Instant originalPurchaseDate;
        private String originalPurchaseDateMs;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss VV")
        private Instant originalPurchaseDatePst;
        private String isTrialPeriod;
    }

}

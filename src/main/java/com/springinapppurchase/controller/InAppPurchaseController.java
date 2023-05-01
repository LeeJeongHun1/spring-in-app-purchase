package com.springinapppurchase.controller;

import com.springinapppurchase.dto.api.ApiResponse;
import com.springinapppurchase.dto.ReceiptDto;
import com.springinapppurchase.dto.RefundDto;
import com.springinapppurchase.dto.iap.google.GoogleInAppPurchaseDto;
import com.springinapppurchase.dto.iap.google.PurchaseTokenDto;
import com.springinapppurchase.service.InAppPurchaseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Tag(name = "in-app-purchase", description = "in-app-purchase")
@RestController
@RequestMapping
@RequiredArgsConstructor
public class InAppPurchaseController {
    private final InAppPurchaseService inAppPurchaseService;

    @Operation(summary = "영수증 검증", description = "영수증 검증")
    @PostMapping("/users/{userId}/apple/in-app-purchases/verify-receipt")
    public ResponseEntity<ApiResponse> verifyReceiptForApple(
            @Valid @RequestBody ReceiptDto receiptDto,
            @PathVariable Long userId) {
        return ResponseEntity.ok(inAppPurchaseService.verifyReceipt(userId, receiptDto));
    }

    @Operation(summary = "환불 검증", description = "환불 검증")
    @PostMapping("/apple/in-app-purchases/refund")
    public ResponseEntity refund(
            @RequestBody RefundDto refundDto) {
        inAppPurchaseService.refund(refundDto.getSignedPayload());
        return ResponseEntity.ok().build();
    }


    @Operation(summary = "영수증 검증", description = "영수증 검증")
    @PostMapping("/users/{userId}/apple/in-app-purchases/verify-receipt")
    public ResponseEntity<ApiResponse> verifyReceiptForGoogle(
            @Valid @RequestBody PurchaseTokenDto purchaseTokenDto,
            @PathVariable Long userId) {
        return ResponseEntity.ok(inAppPurchaseService.verifyGoogleReceipt(userId, purchaseTokenDto));
    }
}

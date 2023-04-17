package com.springinapppurchase.component;

import com.google.gson.JsonObject;
import com.springinapppurchase.dto.AppleIAPResponseDto;
import com.springinapppurchase.dto.ReceiptDto;
import com.springinapppurchase.exception.BadRequestException;
import com.springinapppurchase.exception.InternalServerErrorException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
@RequiredArgsConstructor
public class AppleInAppPurchaseProvider {
    @Value("${iap.ios.sandbox.verify-receipt-url}")
    private String verifySandboxUrl;

    @Value("${iap.ios.production.verify-receipt-url}")
    private String verifyProdUrl;

    private final RestTemplate restTemplate;

    public AppleIAPResponseDto verifyReceipt(ReceiptDto receiptDto) {

        JsonObject payload = new JsonObject();
        payload.addProperty("receipt-data", receiptDto.getReceiptData());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        try {
            ResponseEntity<AppleIAPResponseDto> response = restTemplate.exchange(verifyProdUrl, HttpMethod.POST, new HttpEntity<>(payload.toString(), headers), new ParameterizedTypeReference<>() {});
            AppleIAPResponseDto appleIAPResponseDto = response.getBody();
            if (appleIAPResponseDto.getStatus() == 21007) {
                ResponseEntity<AppleIAPResponseDto> responseEntity = restTemplate.exchange(verifySandboxUrl, HttpMethod.POST, new HttpEntity<>(payload.toString(), headers), new ParameterizedTypeReference<>() {});
                return responseEntity.getBody();
            } else if (appleIAPResponseDto.getStatus() != 0) {
                verifyStatusCode(appleIAPResponseDto.getStatus());
            }
            return appleIAPResponseDto;
        } catch (HttpStatusCodeException ex) {
            throw new InternalServerErrorException("IAP.VALIDATE_RECEIPT_FAIL");
        }
    }

    private void verifyStatusCode(int statusCode) {
        switch (statusCode) {
            case 21000:
                log.error("[Status code: " + statusCode + "] The request to the App Store was not made using the HTTP POST request method.");
                break;
            case 21001:
                log.error("[Status code: " + statusCode + "] This status code is no longer sent by the App Store.");
                break;
            case 21002:
                log.error("[Status code: " + statusCode + "] The data in the receipt-data property was malformed or the service experienced a temporary issue. Try again.");
                break;
            case 21003:
                log.error("[Status code: " + statusCode + "] The receipt could not be authenticated.");
                break;
            case 21004:
                log.error("[Status code: " + statusCode + "] The shared secret you provided does not match the shared secret on file for your account.");
                break;
            case 21005:
                log.error("[Status code: " + statusCode + "] The receipt server was temporarily unable to provide the receipt. Try again.");
                break;
            case 21006:
                log.error("[Status code: " + statusCode + "] This receipt is valid but the subscription has expired. When this status code is returned to your server, the receipt data is also decoded and returned as part of the response. Only returned for iOS 6-style transaction receipts for auto-renewable subscriptions.");
                break;
            case 21008:
                log.error("[Status code: " + statusCode + "] This receipt is from the production environment, but it was sent to the test environment for verification.");
                break;
            case 21009:
                log.error("[Status code: " + statusCode + "] Internal data access error. Try again later.");
                break;
            case 21010:
                log.error("[Status code: " + statusCode + "] The user account cannot be found or has been deleted.");
                break;
            default:
                log.error("[Status code: " + statusCode + "] The receipt for the App Store is incorrect.");
                break;
        }
        throw new BadRequestException("IAP.RECEIPT_INCORRECT");
    }

}

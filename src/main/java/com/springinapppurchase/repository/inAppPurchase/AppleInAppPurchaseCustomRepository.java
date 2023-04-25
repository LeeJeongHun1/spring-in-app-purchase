package com.springinapppurchase.repository.inAppPurchase;

import com.springinapppurchase.dto.AppleInAppPurchaseDto;

import java.util.List;
import java.util.Optional;

//
//import com.springinapppurchase.dto.AppleInAppPurchaseDto;
//import org.springframework.data.domain.Page;
//
//import java.util.List;
//import java.util.Optional;
//
public interface AppleInAppPurchaseCustomRepository {
//
    List<AppleInAppPurchaseDto> findAllByTransactionIds(List<String> transactionIds);
//
//    Page<AppleInAppPurchaseDto> findAll(Long userId, Long channelId);
//    Optional<AppleInAppPurchaseDto> findById(Long userId, Long channelId, Long id);
    Optional<AppleInAppPurchaseDto> findByOriginalTransactionId(String originalTransactionId);
//
    void updateStatus(AppleInAppPurchaseDto appleInAppPurchaseDto);
}
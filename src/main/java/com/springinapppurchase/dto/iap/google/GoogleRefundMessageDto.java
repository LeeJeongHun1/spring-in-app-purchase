package com.springinapppurchase.dto.iap.google;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@NoArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GoogleRefundMessageDto {

    private Message message;
    private String subscription;


    @NoArgsConstructor
    @Getter
    @Setter
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Message {
        private Map<String, String> attributes;
        private String data;
        private String messageId;
    }
}

package com.springinapppurchase.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.*;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.util.StreamUtils;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Configuration
public class RestTemplateConfig {

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
        ClientHttpRequestFactory factory = new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory());
        return restTemplateBuilder
                .requestFactory(() -> factory)
                .additionalMessageConverters(new StringHttpMessageConverter(StandardCharsets.UTF_8))
                .interceptors(new RestTemplateLoggingInterceptor())
                .build();
    }

    @Slf4j
    static class RestTemplateLoggingInterceptor implements ClientHttpRequestInterceptor {
        @Override
        public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
            logRequest(request, body);
            ClientHttpResponse response = execution.execute(request, body);
            logResponse(response);
            return response;
        }

        private void logRequest(HttpRequest request, byte[] body) throws IOException {
            if (log.isDebugEnabled()) {
                log.debug("===========================request begin================================================");
                log.debug("URI         : {}", request.getURI());
                log.debug("Method      : {}", request.getMethod());
                log.debug("Headers     : {}", request.getHeaders());
                log.debug("Request body: {}", new String(body, StandardCharsets.UTF_8));
                log.debug("==========================request end================================================");
            }
        }

        private void logResponse(ClientHttpResponse response) throws IOException {
            if (log.isDebugEnabled()) {
                log.debug("============================response begin==========================================");
                log.debug("Status code  : {}", response.getStatusCode());
                log.debug("Status text  : {}", response.getStatusText());
                log.debug("Headers      : {}", response.getHeaders());
                log.debug("Response body: {}", StreamUtils.copyToString(response.getBody(), StandardCharsets.UTF_8));
                log.debug("=======================response end=================================================");
            }
        }
    }
}

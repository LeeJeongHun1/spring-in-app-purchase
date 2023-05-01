package com.springinapppurchase.config;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.androidpublisher.AndroidPublisher;
import com.google.api.services.androidpublisher.AndroidPublisherScopes;
import com.google.auth.http.HttpCredentialsAdapter;
import com.google.auth.oauth2.AccessToken;
import com.google.auth.oauth2.GoogleCredentials;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;

@Component
public class GoogleCredentialsConfig {
    @Value("classpath:google-api.json")
    private Resource credentialsResource;
    @Value("${iap.android.package-name}")
    private String packageName;
    private GoogleCredentials credentials;

    public AndroidPublisher androidPublisher () throws IOException, GeneralSecurityException {
        InputStream inputStream = credentialsResource.getInputStream();
        credentials = GoogleCredentials
                .fromStream(inputStream)
                .createScoped(AndroidPublisherScopes.ANDROIDPUBLISHER);
        return new AndroidPublisher.Builder(
                GoogleNetHttpTransport.newTrustedTransport(),
                GsonFactory.getDefaultInstance(),
                new HttpCredentialsAdapter(credentials)
        ).setApplicationName(packageName).build();
    }

    public AccessToken getAccessToken() throws IOException {
        return credentials.refreshAccessToken();
    }


}

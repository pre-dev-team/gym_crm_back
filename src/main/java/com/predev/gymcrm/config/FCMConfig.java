package com.predev.gymcrm.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import javax.annotation.PostConstruct;
import java.io.IOException;

@Configuration
public class FCMConfig {

    @Value("${fcm.certification}")
    private String FIREBASE_CONFIG_PATH;

    @PostConstruct
    public FirebaseApp fcmInitialize() throws IOException {
        try {
            GoogleCredentials googleCredentials = GoogleCredentials.fromStream(new ClassPathResource(FIREBASE_CONFIG_PATH).getInputStream());
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(googleCredentials)
                    .build();

            if (FirebaseApp.getApps().isEmpty()) {
                FirebaseApp firebaseApp = FirebaseApp.initializeApp(options);
                System.out.println("파이어베이스 앱 시작");
                return firebaseApp;
            } else {
                return FirebaseApp.getInstance(); // 이미 초기화된 경우 기존 인스턴스 반환
            }
        } catch (IOException e) {
            System.out.println("파이어베이스 앱 시작 실패: " + e.getMessage());
            throw new IllegalStateException("파이어베이스 앱 시작 실패", e);
        }
    }

    @Bean
    public FirebaseMessaging firebaseMessaging() {
        return FirebaseMessaging.getInstance(FirebaseApp.getInstance());
    }

}

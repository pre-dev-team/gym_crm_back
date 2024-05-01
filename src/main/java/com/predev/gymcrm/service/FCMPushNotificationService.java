package com.predev.gymcrm.service;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.WebpushConfig;
import com.google.firebase.messaging.WebpushNotification;
import com.predev.gymcrm.entity.NotificationMessage;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Service
public class FCMPushNotificationService {

    @Autowired
    private FirebaseMessaging firebaseMessaging;

    @Getter
    private final Map<Integer, String> tokenMap = new HashMap<>(); // 서버에 저장(실제 서비스에서는 저장공간을 redis로 바꾸는 듯함)

    public void register(final int accountId, final String token) {
        System.out.println(tokenMap);
        tokenMap.put(accountId, token);
    }

    public void logout(final int accountId) {
        tokenMap.remove(accountId);
    }

    public void send(NotificationMessage notificationMessage) throws ExecutionException, InterruptedException {

        Message message = Message.builder()
                .setToken(notificationMessage.getToken())
                .setWebpushConfig(WebpushConfig.builder()
                        .setNotification(new WebpushNotification(notificationMessage.getTitle(), notificationMessage.getMessage()))
                        .build())
                .build();
        System.out.println(message.toString());
        firebaseMessaging.sendAsync(message).get();
    }

}

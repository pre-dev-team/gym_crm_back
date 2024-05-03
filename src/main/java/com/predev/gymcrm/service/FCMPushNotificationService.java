package com.predev.gymcrm.service;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.WebpushConfig;
import com.google.firebase.messaging.WebpushNotification;
import com.predev.gymcrm.entity.NotificationMessage;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Service
public class FCMPushNotificationService {

    @Autowired
    private FirebaseMessaging firebaseMessaging;

    @Getter
    private final Map<Integer, String> tokenMap = new HashMap<>(); // 서버에 저장(실제 서비스에서는 저장공간을 redis로 바꾸는 듯함)

    public int register(final int accountId, final String token) {
        System.out.println(tokenMap);
        tokenMap.put(accountId, token);
        return tokenMap.size();
    }

    public void logout(final int accountId) {
        tokenMap.remove(accountId);
    }

    public String getToken(final int accountId) {
        return tokenMap.get(accountId);
    }

    public void sendFCMOneToOne(int accountId, String title, String message) {
        System.out.println(tokenMap);
        String token = getToken(accountId);
        if(token != null) {
            send(NotificationMessage.builder()
                    .token(getToken(accountId))
                    .title(title)
                    .message(message)
                    .build());
        }
    }

    @Async
    public void sendFCMFromOneToMany(List<Integer> accountIds, String title, String message) {
        accountIds.forEach(accountId -> {
            send(NotificationMessage.builder()
                    .token(getToken(accountId))
                    .title(title)
                    .message(message)
                    .build());
        });
    }

    public void send(NotificationMessage notificationMessage) {

        String token = notificationMessage.getToken();

        if (token == null || token.isEmpty()) {
            System.out.println(token);
            throw new IllegalArgumentException("Token은 null이거나 비어있을 수 없습니다.");
        }

        Message message = Message.builder()
                .setToken(token)
                .setWebpushConfig(WebpushConfig.builder()
                        .setNotification(new WebpushNotification(notificationMessage.getTitle(), notificationMessage.getMessage()))
                        .build())
                .build();

        System.out.println(message.toString());
        try {
            firebaseMessaging.sendAsync(message).get();
            System.out.println("메세지전송 완료");
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

}

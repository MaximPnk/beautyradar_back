package ru.beautyradar.pushservice.service;

import com.google.firebase.messaging.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.beautyradar.pushservice.dto.wrap.InitResp;
import ru.beautyradar.pushservice.dto.wrap.Resp;
import ru.beautyradar.pushservice.model.PrivatePush;
import ru.beautyradar.pushservice.service.inter.PushService;

@Service
@Slf4j
@RequiredArgsConstructor
public class PushServiceImpl implements PushService {

    private final FirebaseMessaging firebaseMessaging;

    @Override
    public Resp<?> send(PrivatePush privatePush, String token) {
        Notification notification = Notification
                .builder()
                .setTitle(privatePush.getTitle())
                .setBody(privatePush.getMsg())
//                .setImage("")
                .build();

        Message message = Message
                .builder()
                .setToken(token)
                .setNotification(notification)
//                .putAllData()
                .build();

        try {
            return new InitResp<>().ok(firebaseMessaging.send(message));
        } catch (FirebaseMessagingException e) {
            log.error(e.getMessage());
            return new InitResp<>().exc(1, e.getMessage());
        }
    }
}

package ru.beautyradar.pushservice.service;

import com.google.firebase.messaging.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.beautyradar.pushservice.dto.wrap.InitResp;
import ru.beautyradar.pushservice.dto.wrap.Resp;
import ru.beautyradar.pushservice.model.PrivateMsg;
import ru.beautyradar.pushservice.service.inter.FirebaseService;

@Service
@Slf4j
@RequiredArgsConstructor
public class FirebaseServiceImpl implements FirebaseService {

    private final FirebaseMessaging firebaseMessaging;

    @Override
    public Resp<?> send(PrivateMsg privateMsg) {
        Notification notification = Notification
                .builder()
                .setTitle(privateMsg.getTitle())
                .setBody(privateMsg.getMsg())
//                .setImage("")
                .build();

        Message message = Message
                .builder()
                .setToken(privateMsg.getToken())
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

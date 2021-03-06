package ru.beautyradar.pushservice.service;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.beautyradar.pushservice.dto.wrap.Resp;
import ru.beautyradar.pushservice.dto.wrap.RespBuilder;
import ru.beautyradar.pushservice.model.PrivatePush;
import ru.beautyradar.pushservice.service.inter.PushService;
import ru.beautyradar.pushservice.service.inter.UserService;

@Service
@Slf4j
@RequiredArgsConstructor
public class PushServiceImpl implements PushService {

    private final FirebaseMessaging firebaseMessaging;
    private final UserService userService;

    @Override
    public Resp<?> send(PrivatePush privatePush) {

        String token = userService.getTokenById(privatePush.getUserId());



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
            return new RespBuilder<>().setCode(0).setBody(firebaseMessaging.send(message)).build();
        } catch (FirebaseMessagingException e) {
            log.error(e.getMessage());
            return new RespBuilder<>().setCode(1).setMessage(e.getMessage()).build();
        }
    }
}

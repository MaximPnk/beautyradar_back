package ru.beautyradar.pushservice.service.inter;

import ru.beautyradar.pushservice.dto.wrap.Resp;
import ru.beautyradar.pushservice.model.PrivatePush;

public interface PushService {

    Resp<?> send(PrivatePush privatePush, String token);
}

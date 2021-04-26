package ru.beautyradar.pushservice.service.inter;

import ru.beautyradar.pushservice.dto.wrap.Resp;
import ru.beautyradar.pushservice.model.PrivateMsg;

public interface FirebaseService {

    Resp<?> send(PrivateMsg privateMsg);
}

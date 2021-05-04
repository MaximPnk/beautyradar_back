package ru.beautyradar.pushservice.service.inter;

import ru.beautyradar.pushservice.dto.wrap.Resp;

public interface UserService {

    Resp<?> getTokenByUpn(Long id);

}

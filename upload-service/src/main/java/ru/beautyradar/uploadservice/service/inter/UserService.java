package ru.beautyradar.uploadservice.service.inter;

import ru.beautyradar.uploadservice.wrap.Resp;

public interface UserService {

    Resp<?> findUserById(Long id);
}

package ru.beautyradar.uploadservice.service.inter;

import ru.beautyradar.uploadservice.entity.UserEntity;

public interface UserService {

    UserEntity findUserById(Long id);
}

package ru.beautyradar.frontgateway.service.inter;

import org.springframework.scheduling.annotation.Async;
import ru.beautyradar.frontgateway.dto.UserDto;

public interface UserService {

    UserDto getUserByUpn(String upn);

    @Async
    void saveUser(UserDto userDto);
}

package ru.beautyradar.frontgateway.service.inter;

import ru.beautyradar.frontgateway.dto.UserDto;
import ru.beautyradar.frontgateway.dto.wrap.Resp;

public interface UserService {

    Resp<?> getUsers();

    Resp<?> getUserByUpn(String upn);

    Resp<?> existsUserByUpn(String upn);

    Resp<?> saveUser(UserDto userDto);

    Resp<?> updateUser(UserDto userDto);

    Resp<?> deleteUserByUpn(String upn);

}

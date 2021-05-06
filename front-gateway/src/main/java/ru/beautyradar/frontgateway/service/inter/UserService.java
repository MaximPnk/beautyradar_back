package ru.beautyradar.frontgateway.service.inter;

import ru.beautyradar.frontgateway.dto.UserDto;
import ru.beautyradar.frontgateway.dto.wrap.Resp;
import ru.beautyradar.frontgateway.entity.UserEntity;

public interface UserService {

    Resp<?> getAllUsersDto();

    Resp<?> getUserDtoById(Long id);

    Resp<?> getUserDtoByUpn(String upn);

    Resp<?> existsUserByUpn(String upn);

    Resp<?> saveUser(UserDto userDto);

    Resp<?> updateUser(Long id, UserDto userDto);

    Resp<?> deleteUserById(Long id);

    UserEntity getUserEntityById(Long id);

    UserEntity getUserEntityByUpn(String upn);
}

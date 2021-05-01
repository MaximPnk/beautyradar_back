package ru.beautyradar.frontgateway.service.inter;

import ru.beautyradar.frontgateway.dto.UserDto;
import ru.beautyradar.frontgateway.dto.wrap.Resp;
import ru.beautyradar.frontgateway.entity.RoleEntity;
import ru.beautyradar.frontgateway.entity.UserEntity;

import java.util.List;
import java.util.Optional;

public interface RoleService {

    List<RoleEntity> getRolesByUserDto(UserDto userDto);
    List<RoleEntity> getRolesByUser(UserEntity user);


}

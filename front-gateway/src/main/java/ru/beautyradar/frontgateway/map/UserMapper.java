package ru.beautyradar.frontgateway.map;

import org.springframework.stereotype.Component;
import ru.beautyradar.frontgateway.dto.UserDto;
import ru.beautyradar.frontgateway.entity.UserEntity;

@Component
public class UserMapper {

    public UserDto map(UserEntity entity) {
        UserDto dto = new UserDto();
        dto.setId(entity.getId());
        dto.setUpn(entity.getUpn());
        dto.setName(entity.getName());
        dto.setLogin(entity.getLogin());
        dto.setImg(entity.getImg());
        return dto;
    }

    public UserEntity map(UserDto dto) {
        UserEntity entity = new UserEntity();
        entity.setId(dto.getId());
        entity.setUpn(dto.getUpn());
        entity.setName(dto.getName());
        entity.setLogin(dto.getLogin());
        entity.setImg(dto.getImg());
        return entity;
    }
}

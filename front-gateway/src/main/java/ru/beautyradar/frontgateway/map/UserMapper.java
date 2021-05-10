package ru.beautyradar.frontgateway.map;

import org.springframework.stereotype.Component;
import ru.beautyradar.frontgateway.dto.UserDto;
import ru.beautyradar.frontgateway.entity.UserEntity;

@Component
public class UserMapper {

    public UserDto mapEntityToDto(UserEntity entity) {
        if (entity == null) {
            return null;
        }
        UserDto dto = new UserDto();
        dto.setId(entity.getId());
        dto.setUpn(entity.getUpn());
        dto.setToken(entity.getToken());
        dto.setLogin(entity.getLogin());
        dto.setName(entity.getName());
        dto.setEmail(entity.getEmail());
        dto.setPhone(entity.getPhone());
        dto.setImg(entity.getImg());
        return dto;
    }

    public UserEntity mapDtoToEntity(UserDto dto) {
        UserEntity entity = new UserEntity();
        updateEntityByDto(entity, dto);
        return entity;
    }

    public void updateEntityByDto(UserEntity entity, UserDto dto) {
        entity.setToken(dto.getToken());
        entity.setUpn(dto.getUpn());
        entity.setLogin(dto.getLogin());
        entity.setName(dto.getName());
        entity.setEmail(dto.getEmail());
        entity.setPhone(dto.getPhone());
        entity.setImg(dto.getImg());
    }
}

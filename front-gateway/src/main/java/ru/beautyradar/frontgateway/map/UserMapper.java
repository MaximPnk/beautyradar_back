package ru.beautyradar.frontgateway.map;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.beautyradar.frontgateway.dto.UserDto;
import ru.beautyradar.frontgateway.entity.UserEntity;

@Component
@RequiredArgsConstructor
public class UserMapper {

    private final MasterMapper masterMapper = new MasterMapper();

    public UserDto mapEntityToDto(UserEntity entity) {
        UserDto dto = new UserDto();
        dto.setId(entity.getId());
        dto.setUpn(entity.getUpn());
        dto.setLogin(entity.getLogin());
        dto.setName(entity.getName());
        dto.setEmail(entity.getEmail());
        dto.setPhone(entity.getPhone());
        dto.setImg(entity.getImg());
        return dto;
    }

    public UserEntity mapDtoToEntity(UserDto dto) {
        UserEntity entity = new UserEntity();
        entity.setId(dto.getId());
        entity.setUpn(dto.getUpn());
        entity.setToken(dto.getToken());
        mapDtoToEntity(dto, entity);
        return entity;
    }

    public void mapDtoToEntity(UserDto dto, UserEntity entity) {
        entity.setLogin(dto.getLogin());
        entity.setName(dto.getName());
        entity.setEmail(dto.getEmail());
        entity.setPhone(dto.getPhone());
        entity.setImg(dto.getImg());
    }
}

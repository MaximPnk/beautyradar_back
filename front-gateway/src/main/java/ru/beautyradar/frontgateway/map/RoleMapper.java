package ru.beautyradar.frontgateway.map;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.beautyradar.frontgateway.dto.RoleDto;
import ru.beautyradar.frontgateway.dto.UserDto;
import ru.beautyradar.frontgateway.entity.RoleEntity;
import ru.beautyradar.frontgateway.entity.UserEntity;

@Component
@RequiredArgsConstructor
public class RoleMapper {

    private final RoleMapper roleMapper = new RoleMapper();

    public RoleDto mapEntityToDto(RoleEntity entity) {
        if (entity == null) {
            return null;
        }
        RoleDto dto = new RoleDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        //todo - надо доделать
        return dto;
    }

    public RoleEntity mapDtoToEntity(RoleDto dto) {
        RoleEntity entity = new RoleEntity();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        return entity;
    }

}

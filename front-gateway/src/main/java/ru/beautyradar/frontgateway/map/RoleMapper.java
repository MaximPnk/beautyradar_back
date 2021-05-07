package ru.beautyradar.frontgateway.map;

import org.springframework.stereotype.Component;
import ru.beautyradar.frontgateway.dto.RoleDto;
import ru.beautyradar.frontgateway.entity.RoleEntity;

@Component
public class RoleMapper {

    public RoleDto mapEntityToDto(RoleEntity entity) {
        if (entity == null) {
            return null;
        }
        RoleDto dto = new RoleDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        return dto;
    }

    public RoleEntity mapDtoToEntity(RoleDto dto) {
        RoleEntity entity = new RoleEntity();
        updateEntityByDto(entity, dto);
        return entity;
    }

    public void updateEntityByDto(RoleEntity entity, RoleDto dto) {
        entity.setName(dto.getName());
    }
}

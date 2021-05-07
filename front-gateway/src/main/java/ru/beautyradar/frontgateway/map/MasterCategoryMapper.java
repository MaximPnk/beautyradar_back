package ru.beautyradar.frontgateway.map;

import org.springframework.stereotype.Component;
import ru.beautyradar.frontgateway.dto.MasterCategoryDto;
import ru.beautyradar.frontgateway.entity.MasterCategoryEntity;

@Component
public class MasterCategoryMapper {

    public MasterCategoryDto mapEntityToDto(MasterCategoryEntity entity) {
        if (entity == null) {
            return null;
        }
        MasterCategoryDto dto = new MasterCategoryDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        return dto;
    }

    public MasterCategoryEntity mapDtoToEntity(MasterCategoryDto dto) {
        MasterCategoryEntity entity = new MasterCategoryEntity();
        updateEntityByDto(entity, dto);
        return entity;
    }

    public void updateEntityByDto(MasterCategoryEntity entity, MasterCategoryDto dto) {
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
    }

}

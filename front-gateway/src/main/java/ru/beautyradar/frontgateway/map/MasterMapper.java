package ru.beautyradar.frontgateway.map;

import ru.beautyradar.frontgateway.dto.MasterDto;
import ru.beautyradar.frontgateway.entity.MasterEntity;

public class MasterMapper {

    public MasterDto mapEntityToDto(MasterEntity entity) {
        if (entity == null) {
            return null;
        }
        MasterDto dto = new MasterDto();
        dto.setId(entity.getId());
        dto.setAddress(entity.getAddress());
        dto.setRating(entity.getRating());
        return dto;
    }

    public MasterEntity mapDtoToEntity(MasterDto dto) {
        MasterEntity entity = new MasterEntity();
        entity.setId(dto.getId());
        entity.setAddress(dto.getAddress());
        entity.setRating(dto.getRating());
        return entity;
    }
}

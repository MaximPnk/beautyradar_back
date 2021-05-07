package ru.beautyradar.frontgateway.map;

import org.springframework.stereotype.Component;
import ru.beautyradar.frontgateway.dto.GalleryDto;
import ru.beautyradar.frontgateway.entity.GalleryEntity;

@Component
public class GalleryMapper {

    public GalleryDto mapEntityToDto(GalleryEntity entity) {
        if (entity == null) {
            return null;
        }
        GalleryDto dto = new GalleryDto();
        dto.setId(entity.getId());
        dto.setMaster(entity.getMaster().getId());
        dto.setImage(entity.getImage());
        return dto;
    }

}

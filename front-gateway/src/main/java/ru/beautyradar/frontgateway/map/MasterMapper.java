package ru.beautyradar.frontgateway.map;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.beautyradar.frontgateway.dto.MasterDto;
import ru.beautyradar.frontgateway.entity.MasterEntity;
import ru.beautyradar.frontgateway.service.inter.UserService;

@Component
@RequiredArgsConstructor
public class MasterMapper {

    private final UserService userService;

    public MasterDto mapEntityToDto(MasterEntity entity) {
        if (entity == null) {
            return null;
        }
        MasterDto dto = new MasterDto();
        dto.setId(entity.getId());
        dto.setUserId(entity.getUser().getId());
        dto.setAddress(entity.getAddress());
        dto.setLatitude(entity.getLatitude());
        dto.setLongitude(entity.getLongitude());
        dto.setRating(entity.getRating());
        return dto;
    }

    public MasterEntity mapDtoToEntity(MasterDto dto) {
        MasterEntity entity = new MasterEntity();
        updateEntityByDto(entity, dto);
        return entity;
    }

    public void updateEntityByDto(MasterEntity entity, MasterDto dto) {
        entity.setRating(dto.getRating());
        entity.setAddress(dto.getAddress());
        entity.setLatitude(dto.getLatitude());
        entity.setLongitude(dto.getLongitude());
        entity.setUser(userService.getUserEntityById(dto.getUserId()));
    }

}

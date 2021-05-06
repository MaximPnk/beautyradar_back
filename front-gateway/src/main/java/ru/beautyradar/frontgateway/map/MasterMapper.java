package ru.beautyradar.frontgateway.map;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.beautyradar.frontgateway.dto.ClientDto;
import ru.beautyradar.frontgateway.dto.MasterDto;
import ru.beautyradar.frontgateway.entity.ClientEntity;
import ru.beautyradar.frontgateway.entity.MasterEntity;

@Component
@RequiredArgsConstructor
public class MasterMapper {


    public MasterDto mapEntityToDto(MasterEntity entity) {
        MasterDto dto = new MasterDto();
        dto.setId(entity.getId());
        dto.setUserId(entity.getUser().getId());
        dto.setAddress(entity.getAddress());
        dto.setRating(entity.getRating());
        dto.setCoordinates(entity.getCoordinates());
        return dto;
    }




}

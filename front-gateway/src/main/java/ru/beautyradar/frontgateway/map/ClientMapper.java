package ru.beautyradar.frontgateway.map;

import org.springframework.stereotype.Component;
import ru.beautyradar.frontgateway.dto.ClientDto;
import ru.beautyradar.frontgateway.entity.ClientEntity;

@Component
public class ClientMapper {

    public ClientDto mapEntityToDto(ClientEntity entity) {
        if (entity == null) {
            return null;
        }
        ClientDto dto = new ClientDto();
        dto.setUserId(entity.getId());
        dto.setRating(entity.getRating());
        return dto;
    }

}

package ru.beautyradar.frontgateway.map;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.beautyradar.frontgateway.dao.ClientRepository;
import ru.beautyradar.frontgateway.dto.ClientDto;
import ru.beautyradar.frontgateway.dto.UserDto;
import ru.beautyradar.frontgateway.entity.ClientEntity;
import ru.beautyradar.frontgateway.entity.UserEntity;
import ru.beautyradar.frontgateway.exc.ResourceNotFoundException;

@Component
@RequiredArgsConstructor
public class ClientMapper {


    public ClientDto mapEntityToDto(ClientEntity entity) {
        ClientDto dto = new ClientDto();
        dto.setUserId(entity.getId());
        dto.setRating(entity.getRating());
        return dto;
    }




}

package ru.beautyradar.frontgateway.map;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.beautyradar.frontgateway.dto.ClientReviewDto;
import ru.beautyradar.frontgateway.entity.ClientReviewEntity;
import ru.beautyradar.frontgateway.service.inter.ClientService;
import ru.beautyradar.frontgateway.service.inter.MasterService;

@Component
@RequiredArgsConstructor
public class ClientReviewMapper {

    private final ClientService clientService;
    private final MasterService masterService;

    public ClientReviewDto mapEntityToDto(ClientReviewEntity entity) {
        if (entity == null) {
            return null;
        }
        ClientReviewDto dto = new ClientReviewDto();
        dto.setId(entity.getId());
        dto.setClientId(entity.getClient().getId());
        dto.setMasterId(entity.getMaster().getId());
        dto.setRating(entity.getRating());
        dto.setDescription(entity.getDescription());
        return dto;
    }

    public ClientReviewEntity mapDtoToEntity(ClientReviewDto dto) {
        ClientReviewEntity entity = new ClientReviewEntity();
        updateEntityByDto(entity, dto);
        return entity;
    }

    public void updateEntityByDto(ClientReviewEntity entity, ClientReviewDto dto) {
        entity.setClient(clientService.getClientEntityById(dto.getClientId()));
        entity.setMaster(masterService.getMasterEntityById(dto.getMasterId()));
        entity.setRating(dto.getRating());
        entity.setDescription(dto.getDescription());
    }

}

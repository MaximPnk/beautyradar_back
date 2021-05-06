package ru.beautyradar.frontgateway.map;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.beautyradar.frontgateway.dto.MasterReviewDto;
import ru.beautyradar.frontgateway.entity.MasterReviewEntity;
import ru.beautyradar.frontgateway.service.inter.ClientService;
import ru.beautyradar.frontgateway.service.inter.MasterService;

@Component
@RequiredArgsConstructor
public class MasterReviewMapper {
    
    private final MasterService masterService;
    private final ClientService clientService;

    public MasterReviewDto mapEntityToDto(MasterReviewEntity entity) {
        MasterReviewDto dto = new MasterReviewDto();
        dto.setId(entity.getId());
        dto.setMasterId(entity.getMaster().getId());
        dto.setClientId(entity.getClient().getId());
        dto.setRating(entity.getRating());
        dto.setDescription(entity.getDescription());
        return dto;
    }

    public MasterReviewEntity mapDtoToEntity(MasterReviewDto dto) {
        MasterReviewEntity entity = new MasterReviewEntity();
        updateEntityByDto(entity, dto);
        return entity;
    }

    public void updateEntityByDto(MasterReviewEntity entity, MasterReviewDto dto) {
        entity.setMaster(masterService.getMasterEntityById(dto.getMasterId()));
        entity.setClient(clientService.getClientEntityById(dto.getClientId()));
        entity.setRating(dto.getRating());
        entity.setDescription(dto.getDescription());
    }

}

package ru.beautyradar.frontgateway.map;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.beautyradar.frontgateway.dto.MasterWorkdayDto;
import ru.beautyradar.frontgateway.entity.MasterWorkdayEntity;
import ru.beautyradar.frontgateway.service.inter.MasterService;

@Component
@RequiredArgsConstructor
public class MasterWorkdayMapper {

    private final MasterService masterService;

    public MasterWorkdayDto mapEntityToDto(MasterWorkdayEntity entity) {
        if (entity == null) {
            return null;
        }
        MasterWorkdayDto dto = new MasterWorkdayDto();
        dto.setId(entity.getId());
        dto.setMasterId(entity.getMaster().getId());
        dto.setDate(entity.getDate());
        dto.setStartTime(entity.getStartTime());
        dto.setEndTime(entity.getEndTime());
        return dto;
    }

    public MasterWorkdayEntity mapDtoToEntity(MasterWorkdayDto dto) {
        MasterWorkdayEntity entity = new MasterWorkdayEntity();
        updateEntityByDto(entity, dto);
        return entity;
    }

    public void updateEntityByDto(MasterWorkdayEntity entity, MasterWorkdayDto dto) {
        entity.setMaster(masterService.getMasterEntityById(dto.getMasterId()));
        entity.setDate(dto.getDate());
        entity.setStartTime(dto.getStartTime());
        entity.setEndTime(dto.getEndTime());
    }

}

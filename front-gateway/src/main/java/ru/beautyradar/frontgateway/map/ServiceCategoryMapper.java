package ru.beautyradar.frontgateway.map;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.beautyradar.frontgateway.dto.ServiceCategoryDto;
import ru.beautyradar.frontgateway.entity.ServiceCategoryEntity;
import ru.beautyradar.frontgateway.service.inter.MasterCategoryService;

@Component
@RequiredArgsConstructor
public class ServiceCategoryMapper {

    private final MasterCategoryService masterCategoryService;

    public ServiceCategoryDto mapEntityToDto(ServiceCategoryEntity entity) {
        if (entity == null) {
            return null;
        }
        ServiceCategoryDto dto = new ServiceCategoryDto();
        dto.setId(entity.getId());
        dto.setMasterCategoryId(entity.getMasterCategory().getId());
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        return dto;
    }

    public ServiceCategoryEntity mapDtoToEntity(ServiceCategoryDto dto) {
        ServiceCategoryEntity entity = new ServiceCategoryEntity();
        updateEntityByDto(entity, dto);
        return entity;
    }

    public void updateEntityByDto(ServiceCategoryEntity entity, ServiceCategoryDto dto) {
        entity.setMasterCategory(masterCategoryService.getMasterCategoryEntityById(dto.getMasterCategoryId()));
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
    }

}

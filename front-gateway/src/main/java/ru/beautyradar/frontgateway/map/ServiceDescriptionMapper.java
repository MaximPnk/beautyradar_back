package ru.beautyradar.frontgateway.map;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.beautyradar.frontgateway.dto.ServiceDescriptionDto;
import ru.beautyradar.frontgateway.entity.ServiceDescriptionEntity;
import ru.beautyradar.frontgateway.service.inter.MasterService;
import ru.beautyradar.frontgateway.service.inter.ServiceCategoryService;

@Component
@RequiredArgsConstructor
public class ServiceDescriptionMapper {

    private final MasterService masterService;
    private final ServiceCategoryService serviceCategoryService;

    public ServiceDescriptionDto mapEntityToDto(ServiceDescriptionEntity entity) {
        if (entity == null) {
            return null;
        }
        ServiceDescriptionDto dto = new ServiceDescriptionDto();
        dto.setId(entity.getId());
        dto.setMasterId(entity.getMaster().getId());
        dto.setServiceCategoryId(entity.getServiceCategory().getId());
        dto.setDuration(entity.getDuration());
        dto.setPrice(entity.getPrice());
        return dto;
    }

    public ServiceDescriptionEntity mapDtoToEntity(ServiceDescriptionDto dto) {
        ServiceDescriptionEntity entity = new ServiceDescriptionEntity();
        updateEntityByDto(entity, dto);
        return entity;
    }

    public void updateEntityByDto(ServiceDescriptionEntity entity, ServiceDescriptionDto dto) {
        entity.setMaster(masterService.getMasterEntityById(dto.getMasterId()));
        entity.setServiceCategory(serviceCategoryService.getServiceCategoryEntityById(dto.getServiceCategoryId()));
        entity.setDuration(dto.getDuration());
        entity.setPrice(dto.getPrice());
    }

}

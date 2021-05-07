package ru.beautyradar.frontgateway.service.inter;


import ru.beautyradar.frontgateway.dto.ServiceDescriptionDto;
import ru.beautyradar.frontgateway.dto.wrap.Resp;
import ru.beautyradar.frontgateway.entity.ServiceDescriptionEntity;

public interface ServiceDescriptionService {

    Resp<?> getServiceDescriptionsDtoByServiceCategoryId(Long serviceCategoryId);

    Resp<?> getServiceDescriptionsDtoByMasterId(Long masterId);

    Resp<?> getServiceDescriptionDtoById(Long id);

    Resp<?> createServiceDescription(ServiceDescriptionDto serviceDescriptionDto);

    Resp<?> updateServiceDescription(Long id, ServiceDescriptionDto serviceDescriptionDto);

    Resp<?> deleteServiceDescriptionById(Long id);

    ServiceDescriptionEntity getServiceDescriptionEntityById(Long id);
}

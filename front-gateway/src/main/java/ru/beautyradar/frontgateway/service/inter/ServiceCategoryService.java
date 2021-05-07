package ru.beautyradar.frontgateway.service.inter;


import ru.beautyradar.frontgateway.dto.ServiceCategoryDto;
import ru.beautyradar.frontgateway.dto.wrap.Resp;
import ru.beautyradar.frontgateway.entity.ServiceCategoryEntity;

public interface ServiceCategoryService {

    Resp<?> getAllServiceCategoriesDtoByMasterCategoryId(Long masterCategoryId);

    Resp<?> getServiceCategoryDtoById(Long id);

    Resp<?> createServiceCategory(ServiceCategoryDto serviceCategoryDto);

    Resp<?> updateServiceCategory(Long id, ServiceCategoryDto serviceCategoryDto);

    Resp<?> deleteServiceCategoryById(Long id);

    ServiceCategoryEntity getServiceCategoryEntityById(Long id);

}

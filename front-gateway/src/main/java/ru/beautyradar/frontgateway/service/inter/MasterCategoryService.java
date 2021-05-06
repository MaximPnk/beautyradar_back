package ru.beautyradar.frontgateway.service.inter;


import org.springframework.transaction.annotation.Transactional;
import ru.beautyradar.frontgateway.dto.MasterCategoryDto;
import ru.beautyradar.frontgateway.dto.wrap.Resp;
import ru.beautyradar.frontgateway.entity.MasterCategoryEntity;

public interface MasterCategoryService {


    Resp<?> getAllMasterCategoriesDto();

    Resp<?> getMasterCategoryDtoById(Long categoryId);

    Resp<?> getAllMasterCategoriesByMasterId(Long masterId);

    @Transactional
    Resp<?> createMasterCategory(MasterCategoryDto masterCategoryDto);

    @Transactional
    Resp<?> updateMasterCategory(Long id, MasterCategoryDto masterCategoryDto);

    Resp<?> deleteMasterCategoryById(Long id);

    MasterCategoryEntity getMasterCategoryById(Long id);
}

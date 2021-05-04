package ru.beautyradar.frontgateway.service.inter;


import ru.beautyradar.frontgateway.dto.MasterCategoryResponseDTO;
import ru.beautyradar.frontgateway.dto.UpdateMasterCategoryResponseDTO;
import ru.beautyradar.frontgateway.dto.wrap.Resp;
import ru.beautyradar.frontgateway.entity.UserEntity;
import ru.beautyradar.frontgateway.event.SaveClientEvent;

public interface MasterCategoryService {

   Resp<?> findByCategoryId(Long id);

   Resp<?> findCategoriesByMasterID(Long id);

   void  saveNewMasterCategory(MasterCategoryResponseDTO dto);

   String updateMasterCategory(UpdateMasterCategoryResponseDTO dto);
//update
}

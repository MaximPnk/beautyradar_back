package ru.beautyradar.frontgateway.service.inter;

import ru.beautyradar.frontgateway.dto.MasterDto;
import ru.beautyradar.frontgateway.dto.wrap.Resp;
import ru.beautyradar.frontgateway.entity.MasterEntity;
import ru.beautyradar.frontgateway.entity.UserEntity;
import ru.beautyradar.frontgateway.event.UpdateMasterRatingEvent;

public interface MasterService {

    Resp<?> getAllMastersDto();

    Resp<?> getAllMastersDtoByMasterCategoryId(Long id);

    Resp<?> getMasterDtoById(Long id);

    Resp<?> getMasterDtoByUserId(Long id);

    Resp<?> createMaster(MasterDto masterDto);

    Resp<?> updateMaster(Long id, MasterDto masterDto);

    Resp<?> deleteMasterById(Long id);

    Resp<?> addMasterCategory(Long masterId, Long masterCategoryId);

    Resp<?> removeMasterCategory(Long masterId, Long masterCategoryId);

    void updateRating(UpdateMasterRatingEvent event);

    MasterEntity getMasterEntityById(Long id);

    MasterEntity getMasterEntityByUser(UserEntity userEntity);
}

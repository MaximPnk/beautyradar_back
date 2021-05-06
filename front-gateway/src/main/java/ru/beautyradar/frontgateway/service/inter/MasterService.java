package ru.beautyradar.frontgateway.service.inter;

import org.springframework.context.event.EventListener;
import org.springframework.transaction.annotation.Transactional;
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

    @Transactional
    Resp<?> addMasterCategory(Long masterId, Long masterCategoryId);

    @Transactional
    Resp<?> removeMasterCategory(Long masterId, Long masterCategoryId);

    @EventListener
    void updateRating(UpdateMasterRatingEvent event);

    //service methods
    MasterEntity getMasterEntityById(Long id);

    MasterEntity getMasterEntityByUser(UserEntity userEntity);
}

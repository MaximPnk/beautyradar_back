package ru.beautyradar.frontgateway.service.inter;

import org.springframework.transaction.annotation.Transactional;
import ru.beautyradar.frontgateway.dto.MasterWorkdayDto;
import ru.beautyradar.frontgateway.dto.wrap.Resp;
import ru.beautyradar.frontgateway.entity.MasterWorkdayEntity;

import java.time.LocalDate;

public interface MasterWorkdayService {
    @Transactional
    Resp<?> getAllMasterWorkdaysDtoByMasterId(Long masterId);

    @Transactional
    Resp<?> getAllMasterWorkdaysDtoByDate(LocalDate date);

    @Transactional
    Resp<?> getMasterWorkdayDtoById(Long id);

    @Transactional
    Resp<?> createMasterWorkday(MasterWorkdayDto masterWorkdayDto);

    @Transactional
    Resp<?> updateMasterWorkday(Long id, MasterWorkdayDto masterWorkdayDto);

    @Transactional
    Resp<?> deleteMasterWorkdayById(Long id);

    MasterWorkdayEntity getMasterWorkdayEntityById(Long id);
}

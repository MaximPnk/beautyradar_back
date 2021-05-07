package ru.beautyradar.frontgateway.service.inter;

import ru.beautyradar.frontgateway.dto.MasterWorkdayDto;
import ru.beautyradar.frontgateway.dto.wrap.Resp;
import ru.beautyradar.frontgateway.entity.MasterWorkdayEntity;

import java.time.LocalDate;

public interface MasterWorkdayService {

    Resp<?> getAllMasterWorkdaysDtoByMasterId(Long masterId);

    Resp<?> getAllMasterWorkdaysDtoByDate(LocalDate date);

    Resp<?> getMasterWorkdayDtoById(Long id);

    Resp<?> createMasterWorkday(MasterWorkdayDto masterWorkdayDto);

    Resp<?> updateMasterWorkday(Long id, MasterWorkdayDto masterWorkdayDto);

    Resp<?> deleteMasterWorkdayById(Long id);

    MasterWorkdayEntity getMasterWorkdayEntityById(Long id);

}

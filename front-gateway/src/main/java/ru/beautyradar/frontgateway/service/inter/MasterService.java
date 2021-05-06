package ru.beautyradar.frontgateway.service.inter;

import ru.beautyradar.frontgateway.dto.MasterDto;
import ru.beautyradar.frontgateway.dto.UserDto;
import ru.beautyradar.frontgateway.dto.wrap.Resp;
import ru.beautyradar.frontgateway.entity.MasterEntity;

public interface MasterService {

    Resp<?> getMasters();

    Resp<?> getMasterById(Long id);

    MasterEntity getMasterEntityById(Long id);

    MasterDto getMasterInfoByUserId(Long userId);

    Resp<?> setMasterByUserId(Long userId);

    Resp<?>  setAddressByMasterId(Long masterId, String address);

    Resp<?> setCoordinatesByMasterId(Long masterId, String coordinates);
}

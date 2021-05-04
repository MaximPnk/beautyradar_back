package ru.beautyradar.uploadservice.service.inter;

import ru.beautyradar.uploadservice.wrap.Resp;

public interface MasterService {

    Resp<?> findMasterById(Long id);
}

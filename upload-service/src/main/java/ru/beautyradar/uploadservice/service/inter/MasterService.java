package ru.beautyradar.uploadservice.service.inter;

import ru.beautyradar.uploadservice.entity.MasterEntity;

public interface MasterService {

    MasterEntity findMasterById(Long id);
}

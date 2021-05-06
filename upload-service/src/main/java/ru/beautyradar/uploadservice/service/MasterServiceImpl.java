package ru.beautyradar.uploadservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.beautyradar.uploadservice.dao.MasterRepository;
import ru.beautyradar.uploadservice.entity.MasterEntity;
import ru.beautyradar.uploadservice.exc.ResourceNotFoundException;
import ru.beautyradar.uploadservice.service.inter.MasterService;

@Service
@RequiredArgsConstructor
@Slf4j
public class MasterServiceImpl implements MasterService {

    private final MasterRepository masterRepository;

    @Override
    public MasterEntity findMasterById(Long id) {
        return masterRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Мастер с таким id не существует"));
    }
}

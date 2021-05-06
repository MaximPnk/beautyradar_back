package ru.beautyradar.frontgateway.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.beautyradar.frontgateway.dao.MasterCategoryRepository;
import ru.beautyradar.frontgateway.dto.MasterCategoryDto;
import ru.beautyradar.frontgateway.dto.wrap.InitResp;
import ru.beautyradar.frontgateway.dto.wrap.Resp;
import ru.beautyradar.frontgateway.entity.MasterCategoryEntity;
import ru.beautyradar.frontgateway.entity.MasterEntity;
import ru.beautyradar.frontgateway.exc.ResourceNotFoundException;
import ru.beautyradar.frontgateway.map.MasterCategoryMapper;
import ru.beautyradar.frontgateway.service.inter.MasterCategoryService;
import ru.beautyradar.frontgateway.service.inter.MasterService;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class MasterCategoryServiceImpl implements MasterCategoryService {

    private final MasterCategoryRepository repository;
    private final MasterCategoryMapper mapper;
    private final MasterService masterService;

    @Override
    public Resp<?> getAllMasterCategoriesDto() {
        try {
            List<MasterCategoryEntity> entities = repository.findAll();
            return new InitResp<>().ok(entities.stream().map(mapper::mapEntityToDto));
        } catch (Exception e) {
            log.error(e.getMessage());
            return new InitResp<>().exc(1, e.getMessage());
        }
    }

    @Override
    public Resp<?> getMasterCategoryDtoById(Long categoryId) {
        try {
            MasterCategoryEntity entity = getMasterCategoryById(categoryId);
            return new InitResp<>().ok(mapper.mapEntityToDto(entity));
        } catch (Exception e) {
            log.error(e.getMessage());
            return new InitResp<>().exc(1, e.getMessage());
        }
    }

    @Override
    public Resp<?> getAllMasterCategoriesByMasterId(Long masterId) {
        try {
            MasterEntity master = masterService.getMasterEntityById(masterId);
            List<MasterCategoryEntity> masterCategories = repository.findAllByMaster(master);
            return new InitResp<>().ok(masterCategories.stream().map(mapper::mapEntityToDto));
        } catch (Exception e) {
            log.error(e.getMessage());
            return new InitResp<>().exc(1, e.getMessage());
        }
    }

    @Override
    @Transactional
    public Resp<?> createMasterCategory(MasterCategoryDto masterCategoryDto) {
        try {
            MasterCategoryEntity entity = repository.save(mapper.mapDtoToEntity(masterCategoryDto));
            return new InitResp<>().ok(mapper.mapEntityToDto(entity));
        } catch (Exception e) {
            log.error(e.getMessage());
            return new InitResp<>().exc(1, e.getMessage());
        }
    }

    @Override
    @Transactional
    public Resp<?> updateMasterCategory(Long id, MasterCategoryDto masterCategoryDto) {
        try {
            MasterCategoryEntity entity = getMasterCategoryById(id);
            mapper.updateEntityByDto(entity, masterCategoryDto);
            return new InitResp<>().ok(mapper.mapEntityToDto(entity));
        } catch (Exception e) {
            log.error(e.getMessage());
            return new InitResp<>().exc(1, e.getMessage());
        }
    }

    @Override
    public Resp<?> deleteMasterCategoryById(Long id) {
        try {
            repository.deleteById(id);
            return new InitResp<>().ok(null);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new InitResp<>().exc(1, e.getMessage());
        }
    }

    //service methods

    @Override
    public MasterCategoryEntity getMasterCategoryById(Long id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Категория с таким ID не существует"));
    }
}

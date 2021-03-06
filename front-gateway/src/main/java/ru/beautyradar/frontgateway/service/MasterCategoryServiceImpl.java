package ru.beautyradar.frontgateway.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.beautyradar.frontgateway.dao.MasterCategoryRepository;
import ru.beautyradar.frontgateway.dto.MasterCategoryDto;
import ru.beautyradar.frontgateway.dto.wrap.Resp;
import ru.beautyradar.frontgateway.dto.wrap.RespBuilder;
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
    private MasterService masterService;

    @Override
    public Resp<?> getAllMasterCategoriesDto() {
        try {
            List<MasterCategoryEntity> entities = repository.findAll();
            return new RespBuilder<>().setCode(0).setBody(entities.stream().map(mapper::mapEntityToDto)).build();
        } catch (Exception e) {
            log.error(e.getMessage());
            return new RespBuilder<>().setCode(1).setMessage(e.getMessage()).build();
        }
    }

    @Override
    public Resp<?> getMasterCategoryDtoById(Long categoryId) {
        try {
            MasterCategoryEntity entity = getMasterCategoryEntityById(categoryId);
            return new RespBuilder<>().setCode(0).setBody(mapper.mapEntityToDto(entity)).build();
        } catch (Exception e) {
            log.error(e.getMessage());
            return new RespBuilder<>().setCode(1).setMessage(e.getMessage()).build();
        }
    }

    @Override
    public Resp<?> getAllMasterCategoriesDtoByMasterId(Long masterId) {
        try {
            MasterEntity master = masterService.getMasterEntityById(masterId);
            List<MasterCategoryEntity> masterCategories = repository.findAllByMaster(master);
            return new RespBuilder<>().setCode(0).setBody(masterCategories.stream().map(mapper::mapEntityToDto)).build();
        } catch (Exception e) {
            log.error(e.getMessage());
            return new RespBuilder<>().setCode(1).setMessage(e.getMessage()).build();
        }
    }

    @Override
    @Transactional
    public Resp<?> createMasterCategory(MasterCategoryDto masterCategoryDto) {
        try {
            MasterCategoryEntity entity = repository.save(mapper.mapDtoToEntity(masterCategoryDto));
            return new RespBuilder<>().setCode(0).setBody(mapper.mapEntityToDto(entity)).build();
        } catch (Exception e) {
            log.error(e.getMessage());
            return new RespBuilder<>().setCode(1).setMessage(e.getMessage()).build();
        }
    }

    @Override
    @Transactional
    public Resp<?> updateMasterCategory(Long id, MasterCategoryDto masterCategoryDto) {
        try {
            MasterCategoryEntity entity = getMasterCategoryEntityById(id);
            mapper.updateEntityByDto(entity, masterCategoryDto);
            return new RespBuilder<>().setCode(0).setBody(mapper.mapEntityToDto(entity)).build();
        } catch (Exception e) {
            log.error(e.getMessage());
            return new RespBuilder<>().setCode(1).setMessage(e.getMessage()).build();
        }
    }

    @Override
    public Resp<?> deleteMasterCategoryById(Long id) {
        try {
            repository.deleteById(id);
            return new RespBuilder<>().setCode(0).build();
        } catch (Exception e) {
            log.error(e.getMessage());
            return new RespBuilder<>().setCode(1).setMessage(e.getMessage()).build();
        }
    }

    //service methods

    @Override
    public MasterCategoryEntity getMasterCategoryEntityById(Long id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("?????????????????? ?? ?????????? ID ???? ????????????????????"));
    }

    //cyclic

    @Autowired
    public void setMasterService(MasterService masterService) {
        this.masterService = masterService;
    }
}

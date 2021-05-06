package ru.beautyradar.frontgateway.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.beautyradar.frontgateway.dao.MasterRepository;
import ru.beautyradar.frontgateway.dto.MasterDto;
import ru.beautyradar.frontgateway.dto.wrap.InitResp;
import ru.beautyradar.frontgateway.dto.wrap.Resp;
import ru.beautyradar.frontgateway.entity.MasterCategoryEntity;
import ru.beautyradar.frontgateway.entity.MasterEntity;
import ru.beautyradar.frontgateway.entity.UserEntity;
import ru.beautyradar.frontgateway.exc.ResourceNotFoundException;
import ru.beautyradar.frontgateway.map.MasterMapper;
import ru.beautyradar.frontgateway.service.inter.MasterCategoryService;
import ru.beautyradar.frontgateway.service.inter.MasterService;
import ru.beautyradar.frontgateway.service.inter.UserService;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class MasterServiceImpl implements MasterService {

    private final MasterRepository repository;
    private final MasterMapper mapper;
    private final UserService userService;
    private MasterCategoryService masterCategoryService;

    @Override
    public Resp<?> getAllMastersDto() {
        try {
            return new InitResp<>().ok(repository.findAll().stream().map(mapper::mapEntityToDto));
        } catch (Exception e) {
            log.error(e.getMessage());
            return new InitResp<>().exc(1, e.getMessage());
        }
    }

    @Override
    @Transactional
    public Resp<?> getAllMastersDtoByMasterCategoryId(Long id) {
        try {
            MasterCategoryEntity masterCategoryEntity = masterCategoryService.getMasterCategoryById(id);
            List<MasterEntity> masterEntities = repository.findMasterEntitiesByMasterCategories(masterCategoryEntity);
            return new InitResp<>().ok(masterEntities.stream().map(mapper::mapEntityToDto));
        } catch (Exception e) {
            log.error(e.getMessage());
            return new InitResp<>().exc(1, e.getMessage());
        }
    }

    @Override
    public Resp<?> getMasterDtoById(Long id) {
        try {
            return new InitResp<>().ok(mapper.mapEntityToDto(getMasterEntityById(id)));
        } catch (Exception e) {
            log.error(e.getMessage());
            return new InitResp<>().exc(1, e.getMessage());
        }
    }

    @Override
    @Transactional
    public Resp<?> getMasterDtoByUserId(Long id) {
        try {
            UserEntity user = userService.getUserEntityById(id);
            MasterEntity masterEntity = getMasterEntityByUser(user);
            return new InitResp<>().ok(mapper.mapEntityToDto(masterEntity));
        } catch (Exception e) {
            log.error(e.getMessage());
            return new InitResp<>().exc(1, e.getMessage());
        }
    }

    @Override
    public Resp<?> createMaster(MasterDto masterDto) {
        try {
            MasterEntity master = repository.save(mapper.mapDtoToEntity(masterDto));
            return new InitResp<>().ok(mapper.mapEntityToDto(master));
        } catch (Exception e) {
            log.error(e.getMessage());
            return new InitResp<>().exc(1, e.getMessage());
        }
    }

    @Override
    @Transactional
    public Resp<?> updateMaster(Long id, MasterDto masterDto) {
        try {
            MasterEntity masterEntity = getMasterEntityById(id);
            mapper.updateEntityByDto(masterEntity, masterDto);
            return new InitResp<>().ok(mapper.mapEntityToDto(masterEntity));
        } catch (Exception e) {
            log.error(e.getMessage());
            return new InitResp<>().exc(1, e.getMessage());
        }
    }

    @Override
    public Resp<?> deleteMasterById(Long id) {
        try {
            repository.deleteById(id);
            return new InitResp<>().ok(null);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new InitResp<>().exc(1, e.getMessage());
        }
    }

    @Override
    @Transactional
    public Resp<?> addMasterCategory(Long masterId, Long masterCategoryId) {
        try {
            MasterCategoryEntity masterCategoryEntity = masterCategoryService.getMasterCategoryById(masterCategoryId);
            MasterEntity masterEntity = getMasterEntityById(masterId);
            masterEntity.getMasterCategories().add(masterCategoryEntity);
            return new InitResp<>().ok(mapper.mapEntityToDto(masterEntity));
        } catch (Exception e) {
            log.error(e.getMessage());
            return new InitResp<>().exc(1, e.getMessage());
        }
    }

    @Override
    @Transactional
    public Resp<?> removeMasterCategory(Long masterId, Long masterCategoryId) {
        try {
            MasterCategoryEntity masterCategoryEntity = masterCategoryService.getMasterCategoryById(masterCategoryId);
            MasterEntity masterEntity = getMasterEntityById(masterId);
            masterEntity.getMasterCategories().remove(masterCategoryEntity);
            return new InitResp<>().ok(mapper.mapEntityToDto(masterEntity));
        } catch (Exception e) {
            log.error(e.getMessage());
            return new InitResp<>().exc(1, e.getMessage());
        }
    }

    //service methods
    @Override
    public MasterEntity getMasterEntityById(Long id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Мастер с таким id не найден"));
    }

    @Override
    public MasterEntity getMasterEntityByUser(UserEntity userEntity) {
        return repository.findFirstByUser(userEntity).orElseThrow(() -> new ResourceNotFoundException("Пользователь не является мастером"));
    }

    //cyclic

    @Autowired
    public void setMasterCategoryService(MasterCategoryService masterCategoryService) {
        this.masterCategoryService = masterCategoryService;
    }
}

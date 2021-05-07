package ru.beautyradar.frontgateway.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.beautyradar.frontgateway.dao.ServiceCategoryRepository;
import ru.beautyradar.frontgateway.dto.ServiceCategoryDto;
import ru.beautyradar.frontgateway.dto.wrap.Resp;
import ru.beautyradar.frontgateway.dto.wrap.RespBuilder;
import ru.beautyradar.frontgateway.entity.MasterCategoryEntity;
import ru.beautyradar.frontgateway.entity.ServiceCategoryEntity;
import ru.beautyradar.frontgateway.exc.ResourceNotFoundException;
import ru.beautyradar.frontgateway.map.ServiceCategoryMapper;
import ru.beautyradar.frontgateway.service.inter.MasterCategoryService;
import ru.beautyradar.frontgateway.service.inter.ServiceCategoryService;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ServiceCategoryServiceImpl implements ServiceCategoryService {

    private final ServiceCategoryRepository repository;
    private final MasterCategoryService masterCategoryService;
    private final ServiceCategoryMapper mapper;

    @Override
    @Transactional
    public Resp<?> getAllServiceCategoriesDtoByMasterCategoryId(Long masterCategoryId) {
        try {
            MasterCategoryEntity masterCategory = masterCategoryService.getMasterCategoryEntityById(masterCategoryId);
            List<ServiceCategoryEntity> serviceCategories = repository.findAllByMasterCategory(masterCategory);
            return new RespBuilder<>().setCode(0).setBody(serviceCategories.stream().map(mapper::mapEntityToDto)).build();
        } catch (Exception e) {
            log.error(e.getMessage());
            return new RespBuilder<>().setCode(1).setMessage(e.getMessage()).build();
        }
    }

    @Override
    @Transactional
    public Resp<?> getServiceCategoryDtoById(Long id) {
        try {
            ServiceCategoryEntity serviceCategory = getServiceCategoryEntityById(id);
            return new RespBuilder<>().setCode(0).setBody(mapper.mapEntityToDto(serviceCategory)).build();
        } catch (Exception e) {
            log.error(e.getMessage());
            return new RespBuilder<>().setCode(1).setMessage(e.getMessage()).build();
        }
    }

    @Override
    @Transactional
    public Resp<?> createServiceCategory(ServiceCategoryDto serviceCategoryDto) {
        try {
            ServiceCategoryEntity entity = repository.save(mapper.mapDtoToEntity(serviceCategoryDto));
            return new RespBuilder<>().setCode(0).setBody(mapper.mapEntityToDto(entity)).build();
        } catch (Exception e) {
            log.error(e.getMessage());
            return new RespBuilder<>().setCode(1).setMessage(e.getMessage()).build();
        }
    }

    @Override
    @Transactional
    public Resp<?> updateServiceCategory(Long id, ServiceCategoryDto serviceCategoryDto) {
        try {
            ServiceCategoryEntity entity = getServiceCategoryEntityById(id);
            mapper.updateEntityByDto(entity, serviceCategoryDto);
            return new RespBuilder<>().setCode(0).setBody(mapper.mapEntityToDto(entity)).build();
        } catch (Exception e) {
            log.error(e.getMessage());
            return new RespBuilder<>().setCode(1).setMessage(e.getMessage()).build();
        }
    }

    @Override
    @Transactional
    public Resp<?> deleteServiceCategoryById(Long id) {
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
    @Transactional
    public ServiceCategoryEntity getServiceCategoryEntityById(Long id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Категория сервиса с таким id не существует"));
    }

}

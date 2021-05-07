package ru.beautyradar.frontgateway.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.beautyradar.frontgateway.dao.ServiceDescriptionRepository;
import ru.beautyradar.frontgateway.dto.ServiceDescriptionDto;
import ru.beautyradar.frontgateway.dto.wrap.Resp;
import ru.beautyradar.frontgateway.dto.wrap.RespBuilder;
import ru.beautyradar.frontgateway.entity.MasterEntity;
import ru.beautyradar.frontgateway.entity.ServiceCategoryEntity;
import ru.beautyradar.frontgateway.entity.ServiceDescriptionEntity;
import ru.beautyradar.frontgateway.exc.ResourceNotFoundException;
import ru.beautyradar.frontgateway.map.ServiceDescriptionMapper;
import ru.beautyradar.frontgateway.service.inter.MasterService;
import ru.beautyradar.frontgateway.service.inter.ServiceCategoryService;
import ru.beautyradar.frontgateway.service.inter.ServiceDescriptionService;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ServiceDescriptionServiceImpl implements ServiceDescriptionService {

    private final ServiceDescriptionRepository repository;
    private final MasterService masterService;
    private final ServiceCategoryService serviceCategoryService;
    private final ServiceDescriptionMapper mapper;

    //todo get by master
    //todo get by service category
    //todo create
    //todo update
    //todo delete

    @Override
    @Transactional
    public Resp<?> getServiceDescriptionsDtoByServiceCategoryId(Long serviceCategoryId) {
        try {
            ServiceCategoryEntity serviceCategory = serviceCategoryService.getServiceCategoryEntityById(serviceCategoryId);
            List<ServiceDescriptionEntity> serviceDescriptions = repository.findByServiceCategory(serviceCategory);
            return new RespBuilder<>().setCode(0).setBody(serviceDescriptions.stream().map(mapper::mapEntityToDto)).build();
        } catch (Exception e) {
            log.error(e.getMessage());
            return new RespBuilder<>().setCode(1).setMessage(e.getMessage()).build();
        }
    }

    @Override
    @Transactional
    public Resp<?> getServiceDescriptionsDtoByMasterId(Long masterId) {
        try {
            MasterEntity master = masterService.getMasterEntityById(masterId);
            List<ServiceDescriptionEntity> serviceDescriptions = repository.findByMaster(master);
            return new RespBuilder<>().setCode(0).setBody(serviceDescriptions.stream().map(mapper::mapEntityToDto)).build();
        } catch (Exception e) {
            log.error(e.getMessage());
            return new RespBuilder<>().setCode(1).setMessage(e.getMessage()).build();
        }
    }

    @Override
    @Transactional
    public Resp<?> getServiceDescriptionDtoById(Long id) {
        try {
            ServiceDescriptionEntity serviceDescription = getServiceDescriptionEntityById(id);
            return new RespBuilder<>().setCode(0).setBody(mapper.mapEntityToDto(serviceDescription)).build();
        } catch (Exception e) {
            log.error(e.getMessage());
            return new RespBuilder<>().setCode(1).setMessage(e.getMessage()).build();
        }
    }

    @Override
    @Transactional
    public Resp<?> createServiceDescription(ServiceDescriptionDto serviceDescriptionDto) {
        try {
            ServiceDescriptionEntity entity = repository.save(mapper.mapDtoToEntity(serviceDescriptionDto));
            return new RespBuilder<>().setCode(0).setBody(mapper.mapEntityToDto(entity)).build();
        } catch (Exception e) {
            log.error(e.getMessage());
            return new RespBuilder<>().setCode(1).setMessage(e.getMessage()).build();
        }
    }

    @Override
    @Transactional
    public Resp<?> updateServiceDescription(Long id, ServiceDescriptionDto serviceDescriptionDto) {
        try {
            ServiceDescriptionEntity entity = getServiceDescriptionEntityById(id);
            mapper.updateEntityByDto(entity, serviceDescriptionDto);
            return new RespBuilder<>().setCode(0).setBody(mapper.mapEntityToDto(entity)).build();
        } catch (Exception e) {
            log.error(e.getMessage());
            return new RespBuilder<>().setCode(1).setMessage(e.getMessage()).build();
        }
    }

    @Override
    @Transactional
    public Resp<?> deleteServiceDescriptionById(Long id) {
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
    public ServiceDescriptionEntity getServiceDescriptionEntityById(Long id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Услуга с таким id не существует"));
    }

}

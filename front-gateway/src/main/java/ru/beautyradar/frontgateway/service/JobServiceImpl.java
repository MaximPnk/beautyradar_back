package ru.beautyradar.frontgateway.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.beautyradar.frontgateway.dao.JobRepository;
import ru.beautyradar.frontgateway.dto.JobDto;
import ru.beautyradar.frontgateway.dto.wrap.Resp;
import ru.beautyradar.frontgateway.dto.wrap.RespBuilder;
import ru.beautyradar.frontgateway.entity.*;
import ru.beautyradar.frontgateway.exc.ResourceNotFoundException;
import ru.beautyradar.frontgateway.map.JobMapper;
import ru.beautyradar.frontgateway.service.inter.*;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class JobServiceImpl implements JobService {

    private final JobRepository repository;
    private final JobMapper mapper;
    private final MasterService masterService;
    private final MasterWorkdayService masterWorkdayService;
    private final ClientService clientService;
    private final ServiceDescriptionService serviceDescriptionService;

    @Override
    @Transactional
    public Resp<?> getAllJobsDto() {
        try {
            List<JobEntity> jobs = repository.findAll();
            return new RespBuilder<>().setCode(0).setBody(jobs.stream().map(mapper::mapEntityToDto)).build();
        } catch (Exception e) {
            log.error(e.getMessage());
            return new RespBuilder<>().setCode(1).setMessage(e.getMessage()).build();
        }
    }

    @Override
    @Transactional
    public Resp<?> getAllJobsDtoByMasterId(Long masterId) {
        try {
            MasterEntity master = masterService.getMasterEntityById(masterId);
            List<JobEntity> jobs = repository.findAllByMasterWorkdayMaster(master);
            return new RespBuilder<>().setCode(0).setBody(jobs.stream().map(mapper::mapEntityToDto)).build();
        } catch (Exception e) {
            log.error(e.getMessage());
            return new RespBuilder<>().setCode(1).setMessage(e.getMessage()).build();
        }
    }

    @Override
    @Transactional
    public Resp<?> getAllJobsDtoByClientId(Long clientId) {
        try {
            ClientEntity client = clientService.getClientEntityById(clientId);
            List<JobEntity> jobs = repository.findAllByClient(client);
            return new RespBuilder<>().setCode(0).setBody(jobs.stream().map(mapper::mapEntityToDto)).build();
        } catch (Exception e) {
            log.error(e.getMessage());
            return new RespBuilder<>().setCode(1).setMessage(e.getMessage()).build();
        }
    }

    @Override
    @Transactional
    public Resp<?> getAllJobsDtoByMasterWorkdayId(Long masterWorkdayId) {
        try {
            MasterWorkdayEntity masterWorkday = masterWorkdayService.getMasterWorkdayEntityById(masterWorkdayId);
            List<JobEntity> jobs = repository.findAllByMasterWorkday(masterWorkday);
            return new RespBuilder<>().setCode(0).setBody(jobs.stream().map(mapper::mapEntityToDto)).build();
        } catch (Exception e) {
            log.error(e.getMessage());
            return new RespBuilder<>().setCode(1).setMessage(e.getMessage()).build();
        }
    }

    @Override
    @Transactional
    public Resp<?> getAllJobsDtoByServiceDescriptionId(Long serviceDescriptionId) {
        try {
            ServiceDescriptionEntity serviceDescription = serviceDescriptionService.getServiceDescriptionEntityById(serviceDescriptionId);
            List<JobEntity> jobs = repository.findAllByServiceDescription(serviceDescription);
            return new RespBuilder<>().setCode(0).setBody(jobs.stream().map(mapper::mapEntityToDto)).build();
        } catch (Exception e) {
            log.error(e.getMessage());
            return new RespBuilder<>().setCode(1).setMessage(e.getMessage()).build();
        }
    }

    @Override
    @Transactional
    public Resp<?> getJobDtoById(Long id) {
        try {
            JobEntity jobEntity = getJobEntityById(id);
            return new RespBuilder<>().setCode(0).setBody(mapper.mapEntityToDto(jobEntity)).build();
        } catch (Exception e) {
            log.error(e.getMessage());
            return new RespBuilder<>().setCode(1).setMessage(e.getMessage()).build();
        }
    }

    @Override
    @Transactional
    public Resp<?> createJob(JobDto jobDto) {
        //todo согласовать логику создания с фронтом и переделать по параметрам с добавлением логики
        try {
            JobEntity jobEntity = mapper.mapDtoToEntity(jobDto);
            repository.save(jobEntity);
            return new RespBuilder<>().setCode(0).setBody(mapper.mapEntityToDto(jobEntity)).build();
        } catch (Exception e) {
            log.error(e.getMessage());
            return new RespBuilder<>().setCode(1).setMessage(e.getMessage()).build();
        }
    }

    @Override
    @Transactional
    public Resp<?> updateJob(Long id, JobDto jobDto) {
        try {
            JobEntity jobEntity = getJobEntityById(id);
            mapper.updateEntityByDto(jobEntity, jobDto);
            return new RespBuilder<>().setCode(0).setBody(mapper.mapEntityToDto(jobEntity)).build();
        } catch (Exception e) {
            log.error(e.getMessage());
            return new RespBuilder<>().setCode(1).setMessage(e.getMessage()).build();
        }
    }

    @Override
    @Transactional
    public Resp<?> deleteJobById(Long id) {
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
    public JobEntity getJobEntityById(Long id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Запись с таким id не существует"));
    }

}

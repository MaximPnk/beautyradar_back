package ru.beautyradar.frontgateway.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.beautyradar.frontgateway.dao.JobStatusRepository;
import ru.beautyradar.frontgateway.dto.JobStatusDto;
import ru.beautyradar.frontgateway.dto.wrap.Resp;
import ru.beautyradar.frontgateway.dto.wrap.RespBuilder;
import ru.beautyradar.frontgateway.entity.JobStatusEntity;
import ru.beautyradar.frontgateway.exc.ResourceNotFoundException;
import ru.beautyradar.frontgateway.map.JobStatusMapper;
import ru.beautyradar.frontgateway.service.inter.JobStatusService;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class JobStatusServiceImpl implements JobStatusService {

    private final JobStatusRepository repository;
    private final JobStatusMapper mapper;

    @Override
    @Transactional
    public Resp<?> getAllJobStatusesDto() {
        //todo чекаем на флаг
        try {
            List<JobStatusEntity> jobStatuses = repository.findAll();
            return new RespBuilder<>().setCode(0).setBody(jobStatuses.stream().map(mapper::mapEntityToDto)).build();
        } catch (Exception e) {
            log.error(e.getMessage());
            return new RespBuilder<>().setCode(1).setMessage(e.getMessage()).build();
        }
    }

    @Override
    @Transactional
    public Resp<?> getJobStatusDtoById(Long id) {
        //todo чекаем на флаг
        try {
            JobStatusEntity jobStatusEntity = getJobStatusEntityById(id);
            return new RespBuilder<>().setCode(0).setBody(mapper.mapEntityToDto(jobStatusEntity)).build();
        } catch (Exception e) {
            log.error(e.getMessage());
            return new RespBuilder<>().setCode(1).setMessage(e.getMessage()).build();
        }
    }

    @Override
    @Transactional
    public Resp<?> createJobStatus(JobStatusDto jobStatusDto) {
        //todo чекаем на поля, если совпадают, то disabled = false
        try {
            JobStatusEntity jobStatusEntity = mapper.mapDtoToEntity(jobStatusDto);
            repository.save(jobStatusEntity);
            return new RespBuilder<>().setCode(0).setBody(mapper.mapEntityToDto(jobStatusEntity)).build();
        } catch (Exception e) {
            log.error(e.getMessage());
            return new RespBuilder<>().setCode(1).setMessage(e.getMessage()).build();
        }
    }

    @Override
    @Transactional
    public Resp<?> updateJobStatus(Long id, JobStatusDto jobStatusDto) {
        //todo чекаем на статус, и выдаём ошибку
        try {
            JobStatusEntity jobStatusEntity = getJobStatusEntityById(id);
            mapper.updateEntityByDto(jobStatusEntity, jobStatusDto);
            return new RespBuilder<>().setCode(0).setBody(mapper.mapEntityToDto(jobStatusEntity)).build();
        } catch (Exception e) {
            log.error(e.getMessage());
            return new RespBuilder<>().setCode(1).setMessage(e.getMessage()).build();
        }
    }

    @Override
    @Transactional
    public Resp<?> deleteJobStatusById(Long id) {
        //todo ставим флаг disable
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
    public JobStatusEntity getJobStatusEntityById(Long id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Отзыв клиента с таким id не существует"));
    }

}

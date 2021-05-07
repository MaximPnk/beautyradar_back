package ru.beautyradar.frontgateway.service.inter;

import org.springframework.transaction.annotation.Transactional;
import ru.beautyradar.frontgateway.dto.JobDto;
import ru.beautyradar.frontgateway.dto.wrap.Resp;
import ru.beautyradar.frontgateway.entity.JobEntity;

public interface JobService {

    Resp<?> getAllJobsDto();

    Resp<?> getAllJobsDtoByMasterId(Long masterId);

    Resp<?> getAllJobsDtoByClientId(Long clientId);

    Resp<?> getAllJobsDtoByMasterWorkdayId(Long masterWorkdayId);

    Resp<?> getAllJobsDtoByServiceDescriptionId(Long serviceDescriptionId);

    Resp<?> getJobDtoById(Long id);

    Resp<?> createJob(JobDto jobDto);

    Resp<?> updateJob(Long id, JobDto jobDto);

    Resp<?> deleteJobById(Long id);

    JobEntity getJobEntityById(Long id);

}

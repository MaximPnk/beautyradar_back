package ru.beautyradar.frontgateway.service.inter;

import ru.beautyradar.frontgateway.dto.JobStatusDto;
import ru.beautyradar.frontgateway.dto.wrap.Resp;
import ru.beautyradar.frontgateway.entity.JobStatusEntity;

public interface JobStatusService {

    Resp<?> getAllJobStatusesDto();

    Resp<?> getJobStatusDtoById(Long id);

    Resp<?> createJobStatus(JobStatusDto jobStatusDto);

    Resp<?> updateJobStatus(Long id, JobStatusDto jobStatusDto);

    Resp<?> deleteJobStatusById(Long id);

    JobStatusEntity getJobStatusEntityById(Long id);

}

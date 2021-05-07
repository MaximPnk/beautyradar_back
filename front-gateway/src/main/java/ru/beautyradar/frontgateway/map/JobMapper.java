package ru.beautyradar.frontgateway.map;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.beautyradar.frontgateway.dto.JobDto;
import ru.beautyradar.frontgateway.entity.JobEntity;
import ru.beautyradar.frontgateway.service.inter.ClientService;
import ru.beautyradar.frontgateway.service.inter.JobStatusService;
import ru.beautyradar.frontgateway.service.inter.MasterWorkdayService;
import ru.beautyradar.frontgateway.service.inter.ServiceDescriptionService;

@Component
@RequiredArgsConstructor
public class JobMapper {

    private final MasterWorkdayService masterWorkdayService;
    private final ClientService clientService;
    private final ServiceDescriptionService serviceDescriptionService;
    private final JobStatusService jobStatusService;

    public JobDto mapEntityToDto(JobEntity entity) {
        if (entity == null) {
            return null;
        }
        JobDto dto = new JobDto();
        dto.setId(entity.getId());
        dto.setMasterWorkdayId(entity.getMasterWorkday().getId());
        dto.setClientId(entity.getClient().getId());
        dto.setServiceDescriptionId(entity.getServiceDescription().getId());
        dto.setJobStatusId(entity.getJobStatus().getId());
        dto.setStartTime(entity.getStartTime());
        dto.setEndTime(entity.getEndTime());
        return dto;
    }

    public JobEntity mapDtoToEntity(JobDto dto) {
        JobEntity entity = new JobEntity();
        updateEntityByDto(entity, dto);
        return entity;
    }

    public void updateEntityByDto(JobEntity entity, JobDto dto) {
        entity.setMasterWorkday(masterWorkdayService.getMasterWorkdayEntityById(dto.getMasterWorkdayId()));
        entity.setClient(clientService.getClientEntityById(dto.getClientId()));
        entity.setServiceDescription(serviceDescriptionService.getServiceDescriptionEntityById(dto.getServiceDescriptionId()));
        entity.setJobStatus(jobStatusService.getJobStatusEntityById(dto.getJobStatusId()));
        entity.setStartTime(dto.getStartTime());
        entity.setEndTime(dto.getEndTime());
    }
}

package ru.beautyradar.frontgateway.map;

import org.springframework.stereotype.Component;
import ru.beautyradar.frontgateway.dto.JobStatusDto;
import ru.beautyradar.frontgateway.entity.JobStatusEntity;

@Component
public class JobStatusMapper {

    public JobStatusDto mapEntityToDto(JobStatusEntity entity) {
        JobStatusDto dto = new JobStatusDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        return dto;
    }

    public JobStatusEntity mapDtoToEntity(JobStatusDto dto) {
        JobStatusEntity entity = new JobStatusEntity();
        updateEntityByDto(entity, dto);
        return entity;
    }

    public void updateEntityByDto(JobStatusEntity entity, JobStatusDto dto) {
        entity.setName(dto.getName());
    }
}

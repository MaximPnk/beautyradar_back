package ru.beautyradar.frontgateway.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobDto {
    private Long id;
    private Long masterWorkdayId;
    private Long clientId;
    private Long serviceDescriptionId;
    private Long jobStatusId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}

package ru.beautyradar.frontgateway.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MasterWorkdayDto {
    private Long id;
    private Long masterId;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
}

package ru.beautyradar.frontgateway.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServiceDescriptionDto {
    private Long id;
    private Long masterId;
    private Long serviceCategoryId;
    private Integer duration;
    private BigDecimal price;
}

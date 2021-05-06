package ru.beautyradar.frontgateway.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MasterReviewDto {
    Long id;
    Long masterId;
    Long clientId;
    Integer rating;
    String description;
}

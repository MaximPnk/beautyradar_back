package ru.beautyradar.frontgateway.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientReviewDto {
    Long id;
    Long clientId;
    Long masterId;
    Integer rating;
    String description;
}

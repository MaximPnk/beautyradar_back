package ru.beautyradar.frontgateway.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MasterDto {
    private Long id;
    private Long userId;
    private String address;
    private Double latitude;
    private Double longitude;
    private Double rating;
}

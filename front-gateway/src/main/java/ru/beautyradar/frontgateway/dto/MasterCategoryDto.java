package ru.beautyradar.frontgateway.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MasterCategoryDto {
    private Long id;
    private String name;
    private String description;
}

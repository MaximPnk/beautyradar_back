package ru.beautyradar.frontgateway.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateMasterCategoryResponseDTO {
    private Long categoryID;
    private String masterCategory;
    private String description;
}

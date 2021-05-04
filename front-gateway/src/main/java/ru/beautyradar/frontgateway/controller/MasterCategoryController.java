package ru.beautyradar.frontgateway.controller;

import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.beautyradar.frontgateway.dto.MasterCategoryResponseDTO;
import ru.beautyradar.frontgateway.dto.UpdateMasterCategoryResponseDTO;
import ru.beautyradar.frontgateway.dto.wrap.swagger.ClientListResponse;
import ru.beautyradar.frontgateway.service.inter.MasterCategoryService;

@Api(value = "MasterCategoryController", tags = {"Master Category"})
@SwaggerDefinition(tags = {@Tag(name = "Master Category Controller", description = "Контроллер для работы с категориями мастеров")})
@RestController
@RequiredArgsConstructor
@RequestMapping("/master_cat")
@Slf4j
public class MasterCategoryController {
    @Autowired
    private MasterCategoryService MCategoryService;

    @ApiOperation(value = "Get master category list by ID", httpMethod = "GET", notes = "Получение списка категорий мастера по ID", response = ClientListResponse.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success")})
    @GetMapping("/{id}")
    public ResponseEntity<?> getCategories(@PathVariable Long id) {
        return ResponseEntity.ok(MCategoryService.findByCategoryId(id));
    }

    @ApiOperation(value = "Create new master category", httpMethod = "POST", notes = "Создание новой категории мастера", response = String.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success")})
    @PostMapping("/")
    public ResponseEntity<?> saveNewCategory(@PathVariable MasterCategoryResponseDTO dto) {
        MCategoryService.saveNewMasterCategory(dto);
        return ResponseEntity.ok("New master category added");
    }

    @ApiOperation(value = "Update name & description master category", httpMethod = "PUT", notes = "Обновление названия и описания категории мастера", response = String.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success")})
    @PutMapping("/")
    public ResponseEntity<?> saveNewCategory(@PathVariable UpdateMasterCategoryResponseDTO dto) {
        return ResponseEntity.ok(MCategoryService.updateMasterCategory(dto));
    }


}

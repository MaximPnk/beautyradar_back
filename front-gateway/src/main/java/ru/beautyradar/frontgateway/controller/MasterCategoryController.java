package ru.beautyradar.frontgateway.controller;

import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.beautyradar.frontgateway.dto.MasterCategoryDto;
import ru.beautyradar.frontgateway.dto.wrap.Resp;
import ru.beautyradar.frontgateway.dto.wrap.swagger.MasterCategoryListResponse;
import ru.beautyradar.frontgateway.dto.wrap.swagger.MasterCategoryResponse;
import ru.beautyradar.frontgateway.service.inter.MasterCategoryService;

@Api(value = "MasterCategoryController", tags = {"Master Category"})
@SwaggerDefinition(tags = {@Tag(name = "Master Category Controller", description = "Контроллер для работы с категориями мастеров")})
@RestController
@RequiredArgsConstructor
@RequestMapping("/master-category")
@Slf4j
public class MasterCategoryController {

    private final MasterCategoryService masterCategoryService;

    @ApiOperation(value = "Get master category list", httpMethod = "GET", notes = "Получение списка категорий", response = MasterCategoryListResponse.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success")})
    @GetMapping("/")
    public ResponseEntity<?> getAllMasterCategories() {
        return ResponseEntity.ok(masterCategoryService.getAllMasterCategoriesDto());
    }

    @ApiOperation(value = "Get master category list by master ID", httpMethod = "GET", notes = "Получение списка категорий мастера", response = MasterCategoryListResponse.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success")})
    @GetMapping("/master/{masterId}")
    public ResponseEntity<?> getAllMasterCategoriesByMasterId(@PathVariable("masterId") Long masterId) {
        return ResponseEntity.ok(masterCategoryService.getAllMasterCategoriesDtoByMasterId(masterId));
    }

    @ApiOperation(value = "Get master category by ID", httpMethod = "GET", notes = "Получение категории по id", response = MasterCategoryResponse.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success")})
    @GetMapping("/{masterCategoryId}")
    public ResponseEntity<?> getMasterCategoryById(@PathVariable("masterCategoryId") Long masterCategoryId) {
        return ResponseEntity.ok(masterCategoryService.getMasterCategoryDtoById(masterCategoryId));
    }

    @ApiOperation(value = "Create master category", httpMethod = "POST", notes = "Создание категории мастера", response = MasterCategoryResponse.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success")})
    @PostMapping("/")
    public ResponseEntity<?> createMasterCategory(@RequestBody MasterCategoryDto masterCategoryDto) {
        return ResponseEntity.ok(masterCategoryService.createMasterCategory(masterCategoryDto));
    }

    @ApiOperation(value = "Update master category", httpMethod = "PUT", notes = "Изменение категории мастера", response = MasterCategoryResponse.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success")})
    @PutMapping("/{masterCategoryId}")
    public ResponseEntity<?> updateMasterCategory(@PathVariable("masterCategoryId") Long masterCategoryId, @RequestBody MasterCategoryDto masterCategoryDto) {
        return ResponseEntity.ok(masterCategoryService.updateMasterCategory(masterCategoryId, masterCategoryDto));
    }

    @ApiOperation(value = "Delete master category by id", httpMethod = "DELETE", notes = "Удаление категории мастера по id", response = Resp.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success")})
    @DeleteMapping("/{masterCategoryId}")
    public ResponseEntity<?> deleteMasterCategoryById(@PathVariable("masterCategoryId") Long masterCategoryId) {
        return ResponseEntity.ok(masterCategoryService.deleteMasterCategoryById(masterCategoryId));
    }
}

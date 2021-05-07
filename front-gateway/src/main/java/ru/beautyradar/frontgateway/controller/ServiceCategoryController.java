package ru.beautyradar.frontgateway.controller;

import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.beautyradar.frontgateway.dto.ServiceCategoryDto;
import ru.beautyradar.frontgateway.dto.wrap.Resp;
import ru.beautyradar.frontgateway.dto.wrap.swagger.ServiceCategoryListResponse;
import ru.beautyradar.frontgateway.dto.wrap.swagger.ServiceCategoryResponse;
import ru.beautyradar.frontgateway.service.inter.ServiceCategoryService;

@Api(value = "ServiceCategoryController", tags = {"Service Category"})
@SwaggerDefinition(tags = {@Tag(name = "Service Category Controller", description = "Контроллер для работы с категориями сервисов")})
@RestController
@RequiredArgsConstructor
@RequestMapping("/service-category")
@Slf4j
public class ServiceCategoryController {

    private final ServiceCategoryService serviceCategoryService;

    @ApiOperation(value = "Get service category list by master category", httpMethod = "GET", notes = "Получение списка категорий сервиса по категории мастера", response = ServiceCategoryListResponse.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success")})
    @GetMapping("/master-category/{masterCategoryId}")
    public ResponseEntity<?> getAllServiceCategoriesByMasterCategoryId(@PathVariable("masterCategoryId") Long masterCategoryId) {
        return ResponseEntity.ok(serviceCategoryService.getAllServiceCategoriesDtoByMasterCategoryId(masterCategoryId));
    }

    @ApiOperation(value = "Get service category by ID", httpMethod = "GET", notes = "Получение категории по id", response = ServiceCategoryResponse.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success")})
    @GetMapping("/{serviceCategoryId}")
    public ResponseEntity<?> getServiceCategoryById(@PathVariable("serviceCategoryId") Long serviceCategoryId) {
        return ResponseEntity.ok(serviceCategoryService.getServiceCategoryDtoById(serviceCategoryId));
    }

    @ApiOperation(value = "Create service category", httpMethod = "POST", notes = "Создание категории сервиса", response = ServiceCategoryResponse.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success")})
    @PostMapping("/")
    public ResponseEntity<?> createServiceCategory(@RequestBody ServiceCategoryDto serviceCategoryDto) {
        return ResponseEntity.ok(serviceCategoryService.createServiceCategory(serviceCategoryDto));
    }

    @ApiOperation(value = "Update service category", httpMethod = "PUT", notes = "Изменение категории сервиса", response = ServiceCategoryResponse.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success")})
    @PutMapping("/{serviceCategoryId}")
    public ResponseEntity<?> updateServiceCategory(@PathVariable("serviceCategoryId") Long serviceCategoryId, @RequestBody ServiceCategoryDto serviceCategoryDto) {
        return ResponseEntity.ok(serviceCategoryService.updateServiceCategory(serviceCategoryId, serviceCategoryDto));
    }

    @ApiOperation(value = "Delete master category by id", httpMethod = "DELETE", notes = "Удаление категории сервиса по id", response = Resp.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success")})
    @DeleteMapping("/{serviceCategoryId}")
    public ResponseEntity<?> deleteServiceCategoryById(@PathVariable("serviceCategoryId") Long serviceCategoryId) {
        return ResponseEntity.ok(serviceCategoryService.deleteServiceCategoryById(serviceCategoryId));
    }

}

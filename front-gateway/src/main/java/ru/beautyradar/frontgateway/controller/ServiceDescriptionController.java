package ru.beautyradar.frontgateway.controller;

import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.beautyradar.frontgateway.dto.ServiceDescriptionDto;
import ru.beautyradar.frontgateway.dto.wrap.Resp;
import ru.beautyradar.frontgateway.dto.wrap.swagger.ServiceDescriptionListResponse;
import ru.beautyradar.frontgateway.dto.wrap.swagger.ServiceDescriptionResponse;
import ru.beautyradar.frontgateway.service.inter.ServiceDescriptionService;

@Api(value = "ServiceDescriptionController", tags = {"Service Description"})
@SwaggerDefinition(tags = {@Tag(name = "Service Description Controller", description = "Контроллер услуг мастеров")})
@RestController
@RequiredArgsConstructor
@RequestMapping("/service-description")
@Slf4j
public class ServiceDescriptionController {

    private final ServiceDescriptionService serviceDescriptionService;

    @ApiOperation(value = "Get service description list by service category", httpMethod = "GET", notes = "Получение списка услуг категории", response = ServiceDescriptionListResponse.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success")})
    @GetMapping("/service-category/{serviceCategoryId}")
    public ResponseEntity<?> getAllServiceDescriptionsByServiceCategoryId(@PathVariable("serviceCategoryId") Long serviceCategoryId) {
        return ResponseEntity.ok(serviceDescriptionService.getServiceDescriptionsDtoByServiceCategoryId(serviceCategoryId));
    }

    @ApiOperation(value = "Get service description list by master", httpMethod = "GET", notes = "Получение списка услуг мастера", response = ServiceDescriptionListResponse.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success")})
    @GetMapping("/master/{masterId}")
    public ResponseEntity<?> getAllServiceDescriptionsByMasterId(@PathVariable("masterId") Long masterId) {
        return ResponseEntity.ok(serviceDescriptionService.getServiceDescriptionsDtoByMasterId(masterId));
    }

    @ApiOperation(value = "Get service description by ID", httpMethod = "GET", notes = "Получение услуги мастера по id", response = ServiceDescriptionResponse.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success")})
    @GetMapping("/{serviceDescriptionId}")
    public ResponseEntity<?> getServiceDescriptionById(@PathVariable("serviceDescriptionId") Long serviceDescriptionId) {
        return ResponseEntity.ok(serviceDescriptionService.getServiceDescriptionDtoById(serviceDescriptionId));
    }

    @ApiOperation(value = "Create service description", httpMethod = "POST", notes = "Создание услуги мастера", response = ServiceDescriptionResponse.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success")})
    @PostMapping("/")
    public ResponseEntity<?> createServiceDescription(@RequestBody ServiceDescriptionDto serviceDescriptionDto) {
        return ResponseEntity.ok(serviceDescriptionService.createServiceDescription(serviceDescriptionDto));
    }

    @ApiOperation(value = "Update service description", httpMethod = "PUT", notes = "Изменение услуги мастера", response = ServiceDescriptionResponse.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success")})
    @PutMapping("/{serviceDescriptionId}")
    public ResponseEntity<?> updateServiceDescription(@PathVariable("serviceDescriptionId") Long serviceDescriptionId, @RequestBody ServiceDescriptionDto serviceDescriptionDto) {
        return ResponseEntity.ok(serviceDescriptionService.updateServiceDescription(serviceDescriptionId, serviceDescriptionDto));
    }

    @ApiOperation(value = "Delete service description by id", httpMethod = "DELETE", notes = "Удаление услуги мастера по id", response = Resp.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success")})
    @DeleteMapping("/{serviceDescriptionId}")
    public ResponseEntity<?> deleteServiceDescriptionById(@PathVariable("serviceDescriptionId") Long serviceDescriptionId) {
        return ResponseEntity.ok(serviceDescriptionService.deleteServiceDescriptionById(serviceDescriptionId));
    }

}

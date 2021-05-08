package ru.beautyradar.frontgateway.controller;

import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.beautyradar.frontgateway.dto.MasterDto;
import ru.beautyradar.frontgateway.dto.wrap.Resp;
import ru.beautyradar.frontgateway.dto.wrap.swagger.MasterListResponse;
import ru.beautyradar.frontgateway.dto.wrap.swagger.MasterResponse;
import ru.beautyradar.frontgateway.service.inter.MasterService;

@Api(value = "MasterController", tags = {"Master"})
@SwaggerDefinition(tags = {@Tag(name = "Master Controller", description = "Контроллер мастеров")})
@RestController
@RequiredArgsConstructor
@RequestMapping("/master")
@Slf4j
public class MasterController {

    private final MasterService masterService;

    //todo список мастеров отсортированный по дальности/рейтингу с фильтром по цене

    @ApiOperation(value = "Get master list", httpMethod = "GET", notes = "Получить список мастеров", response = MasterListResponse.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success")})
    @GetMapping("/")
    public ResponseEntity<?> getAllMasters() {
        return ResponseEntity.ok(masterService.getAllMastersDto());
    }

    @ApiOperation(value = "Get master list by master category id", httpMethod = "GET", notes = "Получить список мастеров по категории", response = MasterListResponse.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success")})
    @GetMapping("/master-category/{masterCategoryId}")
    public ResponseEntity<?> getAllMastersByMasterCategoryId(@PathVariable("masterCategoryId") Long masterCategoryId) {
        return ResponseEntity.ok(masterService.getAllMastersDtoByMasterCategoryId(masterCategoryId));
    }

    @ApiOperation(value = "Get master by ID", httpMethod = "GET", notes = "Получить мастера по ID", response = MasterResponse.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success")})
    @GetMapping("/{masterId}")
    public ResponseEntity<?> getMasterById(@PathVariable("masterId") Long masterId) {
        return ResponseEntity.ok(masterService.getMasterDtoById(masterId));
    }

    @ApiOperation(value = "Get master by user ID", httpMethod = "GET", notes = "Получить мастера по пользователю", response = MasterResponse.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success")})
    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getMasterByUserId(@PathVariable("userId") Long userId) {
        return ResponseEntity.ok(masterService.getMasterDtoByUserId(userId));
    }

    @ApiOperation(value = "Save master", httpMethod = "POST", notes = "Создать мастера", response = MasterResponse.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success")})
    @PostMapping("/")
    public ResponseEntity<?> createMaster(@RequestBody MasterDto masterDto) {
        return ResponseEntity.ok(masterService.createMaster(masterDto));
    }

    @ApiOperation(value = "Update master", httpMethod = "PUT", notes = "Изменить мастера", response = MasterResponse.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success")})
    @PutMapping("/{masterId}")
    public ResponseEntity<?> createMaster(@PathVariable("masterId") Long masterId, @RequestBody MasterDto masterDto) {
        return ResponseEntity.ok(masterService.updateMaster(masterId, masterDto));
    }

    @ApiOperation(value = "Delete master", httpMethod = "DELETE", notes = "Удалить мастера", response = Resp.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success")})
    @DeleteMapping("/{masterId}")
    public ResponseEntity<?> deleteMaster(@PathVariable("masterId") Long masterId) {
        return ResponseEntity.ok(masterService.deleteMasterById(masterId));
    }

    @ApiOperation(value = "Add master category to master", httpMethod = "PUT", notes = "Добавить мастеру новую категорию", response = MasterResponse.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success")})
    @PutMapping("/{masterId}/{masterCategoryId}")
    public ResponseEntity<?> addMasterCategoryToMaster(@PathVariable("masterId") Long masterId, @PathVariable("masterCategoryId") Long masterCategoryId) {
        return ResponseEntity.ok(masterService.addMasterCategory(masterId, masterCategoryId));
    }

    @ApiOperation(value = "Remove master category from master", httpMethod = "DELETE", notes = "Удалить у мастера категорию", response = MasterResponse.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success")})
    @DeleteMapping("/{masterId}/{masterCategoryId}")
    public ResponseEntity<?> removeMasterCategoryFromMaster(@PathVariable("masterId") Long masterId, @PathVariable("masterCategoryId") Long masterCategoryId) {
        return ResponseEntity.ok(masterService.removeMasterCategory(masterId, masterCategoryId));
    }

}

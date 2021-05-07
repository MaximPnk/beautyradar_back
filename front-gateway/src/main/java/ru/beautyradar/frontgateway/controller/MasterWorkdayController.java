package ru.beautyradar.frontgateway.controller;

import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.beautyradar.frontgateway.dto.MasterWorkdayDto;
import ru.beautyradar.frontgateway.dto.wrap.Resp;
import ru.beautyradar.frontgateway.dto.wrap.swagger.MasterWorkdayListResponse;
import ru.beautyradar.frontgateway.dto.wrap.swagger.MasterWorkdayResponse;
import ru.beautyradar.frontgateway.service.inter.MasterWorkdayService;

import java.time.LocalDate;

@Api(value = "MasterWorkdayController", tags = {"MasterWorkday"})
@SwaggerDefinition(tags = {@Tag(name = "Master Workday Controller", description = "Контроллер календаря мастеров")})
@RestController
@RequiredArgsConstructor
@RequestMapping("/master-workday")
@Slf4j
public class MasterWorkdayController {

    private final MasterWorkdayService masterWorkdayService;

    @ApiOperation(value = "Get master workday list by master id", httpMethod = "GET", notes = "Получение календаря мастера", response = MasterWorkdayListResponse.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success")})
    @GetMapping("/master/{masterId}")
    public ResponseEntity<?> getAllMasterWorkdaysByMasterId(@PathVariable("masterId") Long masterId) {
        return ResponseEntity.ok(masterWorkdayService.getAllMasterWorkdaysDtoByMasterId(masterId));
    }

    @ApiOperation(value = "Get master workday list by date", httpMethod = "GET", notes = "Получение календаря по дате", response = MasterWorkdayListResponse.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success")})
    @GetMapping("/date/{date}")
    public ResponseEntity<?> getAllMasterWorkdaysByMasterId(@PathVariable("date") LocalDate date) {
        return ResponseEntity.ok(masterWorkdayService.getAllMasterWorkdaysDtoByDate(date));
    }

    @ApiOperation(value = "Get master workday by id", httpMethod = "GET", notes = "Получение расписания по id", response = MasterWorkdayResponse.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success")})
    @GetMapping("/{masterWorkdayId}")
    public ResponseEntity<?> getMasterWorkdayById(@PathVariable("masterWorkdayId") Long masterWorkdayId) {
        return ResponseEntity.ok(masterWorkdayService.getMasterWorkdayDtoById(masterWorkdayId));
    }

    @ApiOperation(value = "Create master workday", httpMethod = "POST", notes = "Создание расписания", response = MasterWorkdayResponse.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success")})
    @PostMapping("/")
    public ResponseEntity<?> createMasterWorkday(@RequestBody MasterWorkdayDto masterWorkdayDto) {
        return ResponseEntity.ok(masterWorkdayService.createMasterWorkday(masterWorkdayDto));
    }

    @ApiOperation(value = "Update master workday", httpMethod = "PUT", notes = "Изменение расписания", response = MasterWorkdayResponse.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success")})
    @PutMapping("/{masterWorkdayId}")
    public ResponseEntity<?> updateMasterWorkday(@PathVariable("masterWorkdayId") Long masterWorkdayId, @RequestBody MasterWorkdayDto masterWorkdayDto) {
        return ResponseEntity.ok(masterWorkdayService.updateMasterWorkday(masterWorkdayId, masterWorkdayDto));
    }

    @ApiOperation(value = "Delete master workday by id", httpMethod = "DELETE", notes = "Удаление расписания по id", response = Resp.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success")})
    @DeleteMapping("/{masterWorkdayId}")
    public ResponseEntity<?> deleteMasterWorkdayById(@PathVariable("masterWorkdayId") Long masterWorkdayId) {
        return ResponseEntity.ok(masterWorkdayService.deleteMasterWorkdayById(masterWorkdayId));
    }

}

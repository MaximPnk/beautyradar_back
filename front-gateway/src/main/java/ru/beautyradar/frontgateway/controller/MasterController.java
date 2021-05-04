package ru.beautyradar.frontgateway.controller;

import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.beautyradar.frontgateway.dto.MasterDto;
import ru.beautyradar.frontgateway.service.inter.MasterService;

@Api(value = "MasterController", tags = {"Master"})
@SwaggerDefinition(tags = {@Tag(name = "Master Controller", description = "Контроллер мастеров")})
@RestController
@RequiredArgsConstructor
@RequestMapping("/master")
@Slf4j
public class MasterController {
    @Autowired
    private MasterService masterService;


    @ApiOperation(value = "Check is userID master", httpMethod = "GET", notes = "Проверить является ли user с id мастером", response = MasterDto.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success")})
    @GetMapping("/{userId}")
    public ResponseEntity<?> getMasterInfoByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(masterService.getMasterInfoByUserId(userId));
    }

    @ApiOperation(value = "Set master for userID", httpMethod = "POST", notes = "Установить user мастером", response = MasterDto.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success")})
    @PostMapping("/{userId}")
    public ResponseEntity<?> setMasterByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(masterService.setMasterByUserId(userId));
    }

    @ApiOperation(value = "Update master address by master ID", httpMethod = "POST", notes = "Установить адресс мастра по его ID", response = MasterDto.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success")})
    @PostMapping("/address/{masterId}")
    public ResponseEntity<?> setAddressByMasterId(@PathVariable Long masterId,@RequestBody String address) {
        return ResponseEntity.ok(masterService.setAddressByMasterId(masterId,address));
    }

    @ApiOperation(value = "Update coordinates by master ID", httpMethod = "POST", notes = "Установить координаты мастра по его ID", response = MasterDto.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success")})
    @PostMapping("/coordinates/{masterId}")
    public ResponseEntity<?> setCoordinatesByMasterId(@PathVariable Long masterId,@RequestBody String coordinates) {
        return ResponseEntity.ok(masterService.setCoordinatesByMasterId(masterId,coordinates));
    }


}

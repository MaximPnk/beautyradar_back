package ru.beautyradar.frontgateway.controller;

import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.beautyradar.frontgateway.dto.MasterReviewDto;
import ru.beautyradar.frontgateway.dto.wrap.Resp;
import ru.beautyradar.frontgateway.dto.wrap.swagger.MasterReviewListResponse;
import ru.beautyradar.frontgateway.dto.wrap.swagger.MasterReviewResponse;
import ru.beautyradar.frontgateway.service.inter.MasterReviewService;

@Api(value = "MasterReviewController", tags = {"MasterReview"})
@SwaggerDefinition(tags = {@Tag(name = "Master Review Controller", description = "Контроллер отзывов мастеров")})
@RestController
@RequiredArgsConstructor
@RequestMapping("/master-review")
@Slf4j
public class MasterReviewController {

    private final MasterReviewService masterReviewService;

    @ApiOperation(value = "Get master reviews by client id", httpMethod = "GET", notes = "Получение списка отзывов мастеров о клиенте", response = MasterReviewListResponse.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success")})
    @GetMapping("/client/{clientId}")
    public ResponseEntity<?> getAllMasterReviewsByClientId(@PathVariable("clientId") Long clientId) {
        return ResponseEntity.ok(masterReviewService.getAllMasterReviewsDtoByClientId(clientId));
    }

    @ApiOperation(value = "Get master reviews by master id", httpMethod = "GET", notes = "Получение списка своих отзывов мастера", response = MasterReviewListResponse.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success")})
    @GetMapping("/master/{masterId}")
    public ResponseEntity<?> getAllMasterReviewsByMasterId(@PathVariable("masterId") Long masterId) {
        return ResponseEntity.ok(masterReviewService.getAllMasterReviewsDtoByMasterId(masterId));
    }

    @ApiOperation(value = "Get master review by id", httpMethod = "GET", notes = "Получение отзыва мастера", response = MasterReviewResponse.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success")})
    @GetMapping("/{masterReviewId}")
    public ResponseEntity<?> getMasterReviewById(@PathVariable("masterReviewId") Long masterReviewId) {
        return ResponseEntity.ok(masterReviewService.getMasterReviewDtoById(masterReviewId));
    }

    @ApiOperation(value = "Get master review by master id and client id", httpMethod = "GET", notes = "Получение конкретного отзыва мастера", response = MasterReviewResponse.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success")})
    @GetMapping("/{masterId}/{clientId}")
    public ResponseEntity<?> getClientReviewByClientIdAndMasterId(@PathVariable("masterId") Long masterId, @PathVariable("clientId") Long clientId) {
        return ResponseEntity.ok(masterReviewService.getMasterReviewDtoByMasterIdAndClientId(clientId, masterId));
    }

    @ApiOperation(value = "Create master review", httpMethod = "POST", notes = "Создание отзыва мастера", response = MasterReviewResponse.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success")})
    @PostMapping("/")
    public ResponseEntity<?> createMasterReview(@RequestBody MasterReviewDto masterReviewDto) {
        return ResponseEntity.ok(masterReviewService.createMasterReview(masterReviewDto));
    }

    @ApiOperation(value = "Update master review", httpMethod = "PUT", notes = "Изменение отзыва мастера", response = MasterReviewResponse.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success")})
    @PutMapping("/{masterReviewId}")
    public ResponseEntity<?> updateMasterReview(@PathVariable("masterReviewId") Long masterReviewId, @RequestBody MasterReviewDto masterReviewDto) {
        return ResponseEntity.ok(masterReviewService.updateMasterReview(masterReviewId, masterReviewDto));
    }

    @ApiOperation(value = "Delete master review by id", httpMethod = "DELETE", notes = "Удаление отзыва мастера по id", response = Resp.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success")})
    @DeleteMapping("/{masterReviewId}")
    public ResponseEntity<?> deleteMasterReviewById(@PathVariable("masterReviewId") Long masterReviewId) {
        return ResponseEntity.ok(masterReviewService.deleteMasterReviewById(masterReviewId));
    }

}

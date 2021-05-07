package ru.beautyradar.frontgateway.controller;

import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.beautyradar.frontgateway.dto.ClientReviewDto;
import ru.beautyradar.frontgateway.dto.wrap.Resp;
import ru.beautyradar.frontgateway.dto.wrap.swagger.ClientReviewListResponse;
import ru.beautyradar.frontgateway.dto.wrap.swagger.ClientReviewResponse;
import ru.beautyradar.frontgateway.service.inter.ClientReviewService;

@Api(value = "ClientReviewController", tags = {"ClientReview"})
@SwaggerDefinition(tags = {@Tag(name = "Client Review Controller", description = "Контроллер отзывов клиентов")})
@RestController
@RequiredArgsConstructor
@RequestMapping("/client-review")
@Slf4j
public class ClientReviewController {

    private final ClientReviewService clientReviewService;

    @ApiOperation(value = "Get client reviews by master id", httpMethod = "GET", notes = "Получение списка отзывов клиентов о мастере", response = ClientReviewListResponse.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success")})
    @GetMapping("/master/{masterId}")
    public ResponseEntity<?> getAllClientReviewsByMasterId(@PathVariable("masterId") Long masterId) {
        return ResponseEntity.ok(clientReviewService.getAllClientReviewsDtoByMasterId(masterId));
    }

    @ApiOperation(value = "Get client reviews by client id", httpMethod = "GET", notes = "Получение списка своих отзывов клиента", response = ClientReviewListResponse.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success")})
    @GetMapping("/client/{clientId}")
    public ResponseEntity<?> getAllClientReviewsByClientId(@PathVariable("clientId") Long clientId) {
        return ResponseEntity.ok(clientReviewService.getAllClientReviewsDtoByClientId(clientId));
    }

    @ApiOperation(value = "Get client review by id", httpMethod = "GET", notes = "Получение отзыва клиента", response = ClientReviewResponse.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success")})
    @GetMapping("/{clientReviewId}")
    public ResponseEntity<?> getClientReviewById(@PathVariable("clientReviewId") Long clientReviewId) {
        return ResponseEntity.ok(clientReviewService.getClientReviewDtoById(clientReviewId));
    }

    @ApiOperation(value = "Get client review by client id and master id", httpMethod = "GET", notes = "Получение конкретного отзыва клиента", response = ClientReviewResponse.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success")})
    @GetMapping("/{clientId}/{masterId}")
    public ResponseEntity<?> getClientReviewByClientIdAndMasterId(@PathVariable("clientId") Long clientId, @PathVariable("masterId") Long masterId) {
        return ResponseEntity.ok(clientReviewService.getClientReviewDtoByClientIdAndMasterId(clientId, masterId));
    }

    @ApiOperation(value = "Create client review", httpMethod = "POST", notes = "Создание отзыва клиента", response = ClientReviewResponse.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success")})
    @PostMapping("/")
    public ResponseEntity<?> createClientReview(@RequestBody ClientReviewDto clientReviewDto) {
        return ResponseEntity.ok(clientReviewService.createClientReview(clientReviewDto));
    }

    @ApiOperation(value = "Update client review", httpMethod = "PUT", notes = "Изменение отзыва клиента", response = ClientReviewResponse.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success")})
    @PutMapping("/{clientReviewId}")
    public ResponseEntity<?> updateClientReview(@PathVariable("clientReviewId") Long clientReviewId, @RequestBody ClientReviewDto clientReviewDto) {
        return ResponseEntity.ok(clientReviewService.updateClientReview(clientReviewId, clientReviewDto));
    }

    @ApiOperation(value = "Delete client review by id", httpMethod = "DELETE", notes = "Удаление отзыва клиента по id", response = Resp.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success")})
    @DeleteMapping("/{clientReviewId}")
    public ResponseEntity<?> deleteClientReviewById(@PathVariable("clientReviewId") Long clientReviewId) {
        return ResponseEntity.ok(clientReviewService.deleteClientReviewById(clientReviewId));
    }

}

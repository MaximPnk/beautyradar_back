package ru.beautyradar.frontgateway.controller;

import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.beautyradar.frontgateway.dto.wrap.Resp;
import ru.beautyradar.frontgateway.dto.wrap.swagger.MasterListResponse;
import ru.beautyradar.frontgateway.service.inter.FavoriteService;

@Api(value = "FavoriteController", tags = {"Favorite"})
@SwaggerDefinition(tags = {@Tag(name = "Favorite Controller", description = "Контроллер избранных мастеров")})
@RestController
@RequiredArgsConstructor
@RequestMapping("/favorite")
@Slf4j
public class FavoriteController {

    private final FavoriteService favoriteService;

    @ApiOperation(value = "Get favorite master list by client id", httpMethod = "GET", notes = "Получение списка избранных мастеров", response = MasterListResponse.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success")})
    @GetMapping("/client/{clientId}")
    public ResponseEntity<?> getAllFavoriteMastersByClientId(@PathVariable("clientId") Long clientId) {
        return ResponseEntity.ok(favoriteService.getAllFavoriteMastersByClientId(clientId));
    }

    @ApiOperation(value = "Add favorite master", httpMethod = "PUT", notes = "Добавление мастера в избранное клиента", response = Resp.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success")})
    @PutMapping("/{clientId}/{masterId}")
    public ResponseEntity<?> addFavoriteMaster(@PathVariable("clientId") Long clientId, @PathVariable("masterId") Long masterId) {
        return ResponseEntity.ok(favoriteService.addFavoriteMaster(clientId, masterId));
    }

    @ApiOperation(value = "Remove favorite master", httpMethod = "DELETE", notes = "Удаление мастера из избранного клиента", response = Resp.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success")})
    @DeleteMapping("/{clientId}/{masterId}")
    public ResponseEntity<?> removeFavoriteMaster(@PathVariable("clientId") Long clientId, @PathVariable("masterId") Long masterId) {
        return ResponseEntity.ok(favoriteService.removeFavoriteMaster(clientId, masterId));
    }

}

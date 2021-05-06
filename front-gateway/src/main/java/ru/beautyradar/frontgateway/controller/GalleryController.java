package ru.beautyradar.frontgateway.controller;

import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ru.beautyradar.frontgateway.dto.wrap.swagger.GalleryListResponse;
import ru.beautyradar.frontgateway.dto.wrap.swagger.GalleryResponse;
import ru.beautyradar.frontgateway.service.inter.GalleryService;


@Api(value = "GalleryController", tags = {"Gallery"})
@SwaggerDefinition(tags = {@Tag(name = "Gallery Controller", description = "Контроллер галереи фотографий")})
@RestController
@RequiredArgsConstructor
@RequestMapping("/gallery")
@Slf4j
public class GalleryController {

    private final GalleryService galleryService;

    @ApiOperation(value = "Get gallery list by master ID", httpMethod = "GET", notes = "Получить список изображений мастера", response = GalleryListResponse.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success")})
    @GetMapping("/master/{masterId}")
    public ResponseEntity<?> getAllGalleriesByMasterId(@PathVariable("masterId") Long masterId) {
        return ResponseEntity.ok(galleryService.getAllGalleriesDtoByMasterId(masterId));
    }

    @ApiOperation(value = "Get gallery by ID", httpMethod = "GET", notes = "Получить изображение по ID", response = GalleryResponse.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success")})
    @GetMapping("/{galleryId}")
    public ResponseEntity<?> getMasterById(@PathVariable("galleryId") Long galleryId) {
        return ResponseEntity.ok(galleryService.getGalleryDtoById(galleryId));
    }

}

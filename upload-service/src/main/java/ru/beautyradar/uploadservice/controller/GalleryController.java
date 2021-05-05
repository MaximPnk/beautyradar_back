package ru.beautyradar.uploadservice.controller;

import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.beautyradar.uploadservice.service.inter.GalleryService;
import ru.beautyradar.uploadservice.wrap.Resp;

@Api(value = "GalleryController", tags = {"Gallery"})
@SwaggerDefinition(tags = {@Tag(name = "Gallery Controller", description = "Контроллер галереи мастеров")})
@RestController
@RequiredArgsConstructor
@RequestMapping("/gallery")
public class GalleryController {

    private final GalleryService galleryService;

    @ApiOperation(value = "Upload gallery", httpMethod = "PUT", notes = "Загрузка изображения в галерею мастера", response = Resp.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success")})
    @PutMapping("/{masterId}")
    public Resp<?> upload(@RequestParam("file") MultipartFile multipartFile, @PathVariable("masterId") Long masterId) {
        return galleryService.uploadImage(masterId, multipartFile);
    }

    @ApiOperation(value = "Delete gallery", httpMethod = "DELETE", notes = "Удаление изображения из галереи мастера", response = Resp.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success")})
    @DeleteMapping("/{galleryId}")
    public Resp<?> delete(@PathVariable("galleryId") Long galleryId) {
        return galleryService.deleteImage(galleryId);
    }

}

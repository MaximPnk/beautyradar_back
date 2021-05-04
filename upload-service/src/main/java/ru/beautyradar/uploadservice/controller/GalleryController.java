package ru.beautyradar.uploadservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.beautyradar.uploadservice.service.inter.GalleryService;
import ru.beautyradar.uploadservice.wrap.Resp;

@RestController
@RequiredArgsConstructor
@RequestMapping("/gallery")
public class GalleryController {

    private final GalleryService galleryService;

    @PutMapping("/{masterId}")
    public Resp<?> upload(@RequestParam("file") MultipartFile multipartFile, @PathVariable("masterId") Long masterId) {
        return galleryService.uploadImage(masterId, multipartFile);
    }

    @DeleteMapping("/{galleryId}")
    public Resp<?> delete(@PathVariable("galleryId") Long galleryId) {
        return galleryService.deleteImage(galleryId);
    }

}

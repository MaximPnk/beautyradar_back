package ru.beautyradar.frontgateway.controller;

import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import ru.beautyradar.frontgateway.service.inter.GalleryService;


@Api(value = "GalleryController", tags = {"Gallery"})
@SwaggerDefinition(tags = {@Tag(name = "Gallery Controller", description = "Контроллер галлереи фотографий")})
@RestController
@RequiredArgsConstructor
@RequestMapping("/gallery")
@Slf4j
public class GalleryController {
    @Autowired
    private GalleryService service;


}

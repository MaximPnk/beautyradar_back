package ru.beautyradar.frontgateway.controller;

import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.beautyradar.frontgateway.dto.ClientDto;
import ru.beautyradar.frontgateway.dto.wrap.swagger.ClientListResponse;
import ru.beautyradar.frontgateway.dto.wrap.swagger.ClientResponse;
import ru.beautyradar.frontgateway.dto.wrap.swagger.ClientResponseID;
import ru.beautyradar.frontgateway.dto.wrap.swagger.SavePhotoResponse;
import ru.beautyradar.frontgateway.service.GalleryServiceImpl;
import ru.beautyradar.frontgateway.service.inter.ClientService;
import ru.beautyradar.frontgateway.service.inter.GalleryService;

import java.io.IOException;

@Api(value = "GalleryController", tags = {"Gallery"})
@SwaggerDefinition(tags = {@Tag(name = "Gallery Controller", description = "Контроллер галлиреии фотографий")})
@RestController
@RequiredArgsConstructor
@RequestMapping("/gallery")
@Slf4j
public class GalleryController {
    @Autowired
    private GalleryService service;


}

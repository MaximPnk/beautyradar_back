package ru.beautyradar.frontgateway.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.beautyradar.frontgateway.dao.GalleryRepository;
import ru.beautyradar.frontgateway.dao.MasterRepository;
import ru.beautyradar.frontgateway.dto.wrap.Resp;
import ru.beautyradar.frontgateway.service.inter.GalleryService;

@Service
@RequiredArgsConstructor
@Slf4j
public class GalleryServiceImpl implements GalleryService {
    @Autowired
    GalleryRepository repository;
    @Autowired
    MasterRepository masterRepository; // todo - так не правильно, нужно через сервис, но там ответом Resp


    @Override
    public Resp<?> findPhoto(Long id) {
        return null;
    }

}

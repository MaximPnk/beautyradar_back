package ru.beautyradar.frontgateway.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.beautyradar.frontgateway.dao.GalleryRepository;
import ru.beautyradar.frontgateway.dto.wrap.InitResp;
import ru.beautyradar.frontgateway.dto.wrap.Resp;
import ru.beautyradar.frontgateway.entity.GalleryEntity;
import ru.beautyradar.frontgateway.entity.MasterEntity;
import ru.beautyradar.frontgateway.exc.ResourceNotFoundException;
import ru.beautyradar.frontgateway.map.GalleryMapper;
import ru.beautyradar.frontgateway.service.inter.GalleryService;
import ru.beautyradar.frontgateway.service.inter.MasterService;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class GalleryServiceImpl implements GalleryService {

    private final GalleryRepository repository;
    private final GalleryMapper mapper;
    private final MasterService masterService;

    @Override
    @Transactional
    public Resp<?> getAllGalleriesDtoByMasterId(Long id) {
        try {
            MasterEntity masterEntity = masterService.getMasterEntityById(id);
            List<GalleryEntity> galleryEntities = repository.findGalleryEntitiesByMaster(masterEntity);
            return new InitResp<>().ok(galleryEntities.stream().map(mapper::mapEntityToDto));
        } catch (Exception e) {
            log.error(e.getMessage());
            return new InitResp<>().exc(1, e.getMessage());
        }
    }

    @Override
    @Transactional
    public Resp<?> getGalleryDtoById(Long id) {
        try {
            GalleryEntity galleryEntity = getGalleryEntityById(id);
            return new InitResp<>().ok(mapper.mapEntityToDto(galleryEntity));
        } catch (Exception e) {
            log.error(e.getMessage());
            return new InitResp<>().exc(1, e.getMessage());
        }
    }

    //service methods

    @Override
    public GalleryEntity getGalleryEntityById(Long id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Изображение с таким id не найдено"));
    }

}

package ru.beautyradar.frontgateway.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.beautyradar.frontgateway.dao.GalleryRepository;
import ru.beautyradar.frontgateway.dto.wrap.Resp;
import ru.beautyradar.frontgateway.dto.wrap.RespBuilder;
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
            return new RespBuilder<>().setCode(0).setBody(galleryEntities.stream().map(mapper::mapEntityToDto)).build();
        } catch (Exception e) {
            log.error(e.getMessage());
            return new RespBuilder<>().setCode(1).setMessage(e.getMessage()).build();
        }
    }

    @Override
    @Transactional
    public Resp<?> getGalleryDtoById(Long id) {
        try {
            GalleryEntity galleryEntity = getGalleryEntityById(id);
            return new RespBuilder<>().setCode(0).setBody(mapper.mapEntityToDto(galleryEntity)).build();
        } catch (Exception e) {
            log.error(e.getMessage());
            return new RespBuilder<>().setCode(1).setMessage(e.getMessage()).build();
        }
    }

    //service methods

    @Override
    public GalleryEntity getGalleryEntityById(Long id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Изображение с таким id не найдено"));
    }

}

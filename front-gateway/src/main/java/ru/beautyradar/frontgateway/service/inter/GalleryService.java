package ru.beautyradar.frontgateway.service.inter;

import org.springframework.transaction.annotation.Transactional;
import ru.beautyradar.frontgateway.dto.wrap.Resp;
import ru.beautyradar.frontgateway.entity.GalleryEntity;

public interface GalleryService {


    @Transactional
    Resp<?> getAllGalleriesDtoByMasterId(Long id);

    @Transactional
    Resp<?> getGalleryDtoById(Long id);

    GalleryEntity getGalleryEntityById(Long id);
}

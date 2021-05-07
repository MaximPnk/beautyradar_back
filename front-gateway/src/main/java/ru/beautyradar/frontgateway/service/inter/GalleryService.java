package ru.beautyradar.frontgateway.service.inter;

import ru.beautyradar.frontgateway.dto.wrap.Resp;
import ru.beautyradar.frontgateway.entity.GalleryEntity;

public interface GalleryService {

    Resp<?> getAllGalleriesDtoByMasterId(Long id);

    Resp<?> getGalleryDtoById(Long id);

    GalleryEntity getGalleryEntityById(Long id);
}

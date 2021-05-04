package ru.beautyradar.uploadservice.service.inter;

import org.springframework.web.multipart.MultipartFile;
import ru.beautyradar.uploadservice.entity.GalleryEntity;
import ru.beautyradar.uploadservice.wrap.Resp;

public interface GalleryService {

    Resp<?> uploadImage(Long masterId, MultipartFile multipartFile);

    Resp<?> deleteImage(Long galleryId);

}

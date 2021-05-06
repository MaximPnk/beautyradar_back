package ru.beautyradar.uploadservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.beautyradar.uploadservice.dao.GalleryRepository;
import ru.beautyradar.uploadservice.entity.GalleryEntity;
import ru.beautyradar.uploadservice.entity.MasterEntity;
import ru.beautyradar.uploadservice.exc.ResourceNotFoundException;
import ru.beautyradar.uploadservice.service.inter.FileService;
import ru.beautyradar.uploadservice.service.inter.GalleryService;
import ru.beautyradar.uploadservice.service.inter.MasterService;
import ru.beautyradar.uploadservice.wrap.Resp;
import ru.beautyradar.uploadservice.wrap.RespBuilder;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class GalleryServiceImpl implements GalleryService {

    private final MasterService masterService;
    private final FileService fileService;
    private final GalleryRepository galleryRepository;

    @Override
    @Transactional
    public Resp<?> uploadImage(Long masterId, MultipartFile multipartFile) {
        try {
            MasterEntity master = masterService.findMasterById(masterId);
            String url = fileService.upload(multipartFile);
            galleryRepository.save(new GalleryEntity(master, url));
            return new RespBuilder<>().setCode(0).setBody(url).build();
        } catch (Exception e) {
            log.error(e.getMessage());
            return new RespBuilder<>().setCode(1).setMessage(e.getMessage()).build();
        }
    }

    @Override
    @Transactional
    public Resp<?> deleteImage(Long galleryId) {
        try {
            GalleryEntity entity = getGalleryById(galleryId);
            fileService.delete(entity.getImage());
            galleryRepository.delete(entity);
            return new RespBuilder<>().setCode(0).build();
        } catch (Exception e) {
            log.error(e.getMessage());
            return new RespBuilder<>().setCode(1).setMessage(e.getMessage()).build();
        }
    }

    // service methods

    public GalleryEntity getGalleryById(Long id) {
        return galleryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Изображение с таким id не существует"));
    }
}

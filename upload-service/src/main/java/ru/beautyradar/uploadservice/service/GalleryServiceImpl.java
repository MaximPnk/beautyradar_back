package ru.beautyradar.uploadservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.beautyradar.uploadservice.dao.GalleryRepository;
import ru.beautyradar.uploadservice.entity.GalleryEntity;
import ru.beautyradar.uploadservice.entity.MasterEntity;
import ru.beautyradar.uploadservice.service.inter.FileService;
import ru.beautyradar.uploadservice.service.inter.GalleryService;
import ru.beautyradar.uploadservice.service.inter.MasterService;
import ru.beautyradar.uploadservice.wrap.InitResp;
import ru.beautyradar.uploadservice.wrap.Resp;

import javax.transaction.Transactional;
import java.sql.SQLException;
import java.util.Optional;

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

            Resp<?> masterResp = masterService.findMasterById(masterId);
            if (masterResp.getCode() != 0) {
                return masterResp;
            }
            MasterEntity master = (MasterEntity) masterResp.getBody();
            if (master == null) {
                return new InitResp<>().exc(1, "Master not found");
            }

            Resp<?> uploadResp = fileService.upload(multipartFile);
            String url = uploadResp.getBody().toString();
            if (uploadResp.getCode() != 0) {
                return uploadResp;
            }

            galleryRepository.save(new GalleryEntity(master, url));
            return new InitResp<>().ok(url);
        } catch (DataAccessException e) {
            log.error(e.getMessage());
            if (e.getRootCause() instanceof SQLException) {
                SQLException sqlEx = (SQLException) e.getRootCause();
                return new InitResp<>().exc(Integer.parseInt(sqlEx.getSQLState()), sqlEx.getMessage());
            }
            return new InitResp<>().exc(1, e.getMessage());
        } catch (Exception e) {
            log.error(e.getMessage());
            return new InitResp<>().exc(1, e.getMessage());
        }
    }

    @Override
    @Transactional
    public Resp<?> deleteImage(Long galleryId) {
        try {
            Optional<GalleryEntity> optGallery = galleryRepository.findById(galleryId);
            if (!optGallery.isPresent()) {
                return new InitResp<>().exc(1, "Gallery not found");
            }
            GalleryEntity entity = optGallery.get();
            fileService.delete(entity.getImage());
            galleryRepository.delete(entity);
            return new InitResp<>().ok(null);
        } catch (DataAccessException e) {
            log.error(e.getMessage());
            if (e.getRootCause() instanceof SQLException) {
                SQLException sqlEx = (SQLException) e.getRootCause();
                return new InitResp<>().exc(Integer.parseInt(sqlEx.getSQLState()), sqlEx.getMessage());
            }
            return new InitResp<>().exc(1, e.getMessage());
        } catch (Exception e) {
            log.error(e.getMessage());
            return new InitResp<>().exc(1, e.getMessage());
        }
    }
}

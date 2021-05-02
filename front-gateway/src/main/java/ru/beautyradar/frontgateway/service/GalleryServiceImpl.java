package ru.beautyradar.frontgateway.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import ru.beautyradar.frontgateway.dao.ClientRepository;
import ru.beautyradar.frontgateway.dao.GalleryRepository;
import ru.beautyradar.frontgateway.dao.MasterRepository;
import ru.beautyradar.frontgateway.dto.ClientDto;
import ru.beautyradar.frontgateway.dto.wrap.InitResp;
import ru.beautyradar.frontgateway.dto.wrap.Resp;
import ru.beautyradar.frontgateway.entity.AppImage;
import ru.beautyradar.frontgateway.entity.GalleryEntity;
import ru.beautyradar.frontgateway.entity.MasterEntity;
import ru.beautyradar.frontgateway.entity.UserEntity;
import ru.beautyradar.frontgateway.exc.ResourceNotFoundException;
import ru.beautyradar.frontgateway.map.ClientMapper;
import ru.beautyradar.frontgateway.service.inter.ClientService;
import ru.beautyradar.frontgateway.service.inter.GalleryService;
import ru.beautyradar.frontgateway.service.inter.MasterService;

import java.sql.SQLException;

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

    @Override
    public Resp<?> savePhoto(byte[] photo, Long idMaster) {
        GalleryEntity gallery = new GalleryEntity();
        gallery.setImage(photo);
        gallery.setMaster(masterRepository.findById(idMaster).orElseThrow(()-> new ResourceNotFoundException("Can not found Master id for saving photo")));
        try {
            return new InitResp<>().ok(repository.save(gallery));
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

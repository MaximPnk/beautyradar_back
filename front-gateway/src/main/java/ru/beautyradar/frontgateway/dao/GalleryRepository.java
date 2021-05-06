package ru.beautyradar.frontgateway.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.beautyradar.frontgateway.entity.GalleryEntity;
import ru.beautyradar.frontgateway.entity.MasterEntity;

import java.util.List;

@Repository
public interface GalleryRepository extends JpaRepository<GalleryEntity, Long> {

    List<GalleryEntity> findGalleryEntitiesByMaster(MasterEntity masterEntity);

}

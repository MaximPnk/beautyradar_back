package ru.beautyradar.uploadservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.beautyradar.uploadservice.entity.GalleryEntity;

@Repository
public interface GalleryRepository extends JpaRepository<GalleryEntity, Long> {

}

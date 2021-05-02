package ru.beautyradar.frontgateway.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.beautyradar.frontgateway.entity.GalleryEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface GalleryRepository extends JpaRepository<GalleryEntity, Long> {
    Optional<GalleryEntity> findById(Long id);
    Optional<List<GalleryEntity>> findByMaster_Id(Long id);

    @Override
    <S extends GalleryEntity> S save(S s);
}

package ru.beautyradar.frontgateway.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.beautyradar.frontgateway.entity.MasterCategoryEntity;
import ru.beautyradar.frontgateway.entity.MasterEntity;
import ru.beautyradar.frontgateway.entity.UserEntity;

import java.util.Optional;

@Repository
public interface MasterCategoryRepository extends JpaRepository<MasterCategoryEntity, Long> {

    Optional<MasterCategoryEntity> findById(Long id);
    Optional<MasterCategoryEntity> findByName(String categoryName);
}

package ru.beautyradar.frontgateway.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.beautyradar.frontgateway.entity.ClientEntity;
import ru.beautyradar.frontgateway.entity.ServiceCategoryEntity;
import ru.beautyradar.frontgateway.entity.UserEntity;

import java.util.Optional;

@Repository
public interface ServiceCategoryRepository extends JpaRepository<ServiceCategoryEntity, Long> {

    Optional<ServiceCategoryEntity> findById(Long id);
    Optional<ServiceCategoryEntity> findByName(String name);
}

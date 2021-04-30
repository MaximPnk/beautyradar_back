package ru.beautyradar.frontgateway.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.beautyradar.frontgateway.entity.ClientEntity;
import ru.beautyradar.frontgateway.entity.MasterEntity;
import ru.beautyradar.frontgateway.entity.UserEntity;

import java.util.Optional;

@Repository
public interface MasterRepository extends JpaRepository<MasterEntity, Long> {

    Optional<MasterEntity> findById(Long id);
    Optional<MasterEntity> findFirstByUser(UserEntity entity);
}

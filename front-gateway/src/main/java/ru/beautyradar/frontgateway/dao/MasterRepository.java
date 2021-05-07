package ru.beautyradar.frontgateway.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.beautyradar.frontgateway.entity.MasterCategoryEntity;
import ru.beautyradar.frontgateway.entity.MasterEntity;
import ru.beautyradar.frontgateway.entity.UserEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface MasterRepository extends JpaRepository<MasterEntity, Long> {

    Optional<MasterEntity> findFirstByUser(UserEntity entity);

    List<MasterEntity> findMasterEntitiesByMasterCategories(MasterCategoryEntity masterCategoryEntity);

}

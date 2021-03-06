package ru.beautyradar.frontgateway.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.beautyradar.frontgateway.entity.MasterCategoryEntity;
import ru.beautyradar.frontgateway.entity.MasterEntity;

import java.util.List;

@Repository
public interface MasterCategoryRepository extends JpaRepository<MasterCategoryEntity, Long> {

    List<MasterCategoryEntity> findAllByMaster(MasterEntity entity);

}

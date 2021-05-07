package ru.beautyradar.frontgateway.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.beautyradar.frontgateway.entity.MasterEntity;
import ru.beautyradar.frontgateway.entity.ServiceCategoryEntity;
import ru.beautyradar.frontgateway.entity.ServiceDescriptionEntity;

import java.util.List;

@Repository
public interface ServiceDescriptionRepository extends JpaRepository<ServiceDescriptionEntity, Long> {

    List<ServiceDescriptionEntity> findByMaster(MasterEntity master);

    List<ServiceDescriptionEntity> findByServiceCategory(ServiceCategoryEntity category);

}

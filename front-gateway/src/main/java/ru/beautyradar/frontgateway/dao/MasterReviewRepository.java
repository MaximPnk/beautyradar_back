package ru.beautyradar.frontgateway.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.beautyradar.frontgateway.entity.ClientEntity;
import ru.beautyradar.frontgateway.entity.MasterEntity;
import ru.beautyradar.frontgateway.entity.MasterReviewEntity;

import java.util.List;

@Repository
public interface MasterReviewRepository extends JpaRepository<MasterReviewEntity, Long> {

   List<MasterReviewEntity> findAllByMaster(MasterEntity master);

   List<MasterReviewEntity> findAllByClient(ClientEntity client);
}

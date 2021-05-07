package ru.beautyradar.frontgateway.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.beautyradar.frontgateway.entity.ClientEntity;
import ru.beautyradar.frontgateway.entity.ClientReviewEntity;
import ru.beautyradar.frontgateway.entity.MasterEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientReviewRepository extends JpaRepository<ClientReviewEntity, Long> {

    List<ClientReviewEntity> findAllByClient(ClientEntity clientEntity);

    List<ClientReviewEntity> findAllByMaster(MasterEntity masterEntity);

    Optional<ClientReviewEntity> findFirstByClientAndMaster(ClientEntity clientEntity, MasterEntity masterEntity);

}

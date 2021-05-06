package ru.beautyradar.frontgateway.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.beautyradar.frontgateway.entity.ClientReviewEntity;

@Repository
public interface ClientReviewRepository extends JpaRepository<ClientReviewEntity, Long> {
}

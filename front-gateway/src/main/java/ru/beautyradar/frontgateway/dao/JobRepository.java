package ru.beautyradar.frontgateway.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.beautyradar.frontgateway.entity.JobEntity;

import java.util.Optional;

@Repository
public interface JobRepository extends JpaRepository<JobEntity, Long> {

    Optional<JobEntity> findById(Long id);
}

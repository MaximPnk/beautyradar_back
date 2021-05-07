package ru.beautyradar.frontgateway.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.beautyradar.frontgateway.entity.JobStatusEntity;

import java.util.Optional;

@Repository
public interface JobStatusRepository extends JpaRepository<JobStatusEntity, Long> {

}

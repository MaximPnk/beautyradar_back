package ru.beautyradar.frontgateway.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.beautyradar.frontgateway.entity.JobEntity;

@Repository
public interface JobRepository extends JpaRepository<JobEntity, Long> {

}

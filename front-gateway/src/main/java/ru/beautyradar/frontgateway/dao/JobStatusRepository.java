package ru.beautyradar.frontgateway.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.beautyradar.frontgateway.entity.JobStatusEntity;
import ru.beautyradar.frontgateway.entity.RoleEntity;

import java.util.Optional;

@Repository
public interface JobStatusRepository extends JpaRepository<JobStatusEntity, Long> {

      Optional<JobStatusEntity> findById(Long id);
      Optional<JobStatusEntity> findByName(String name);


}

package ru.beautyradar.uploadservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.beautyradar.uploadservice.entity.MasterEntity;

@Repository
public interface MasterRepository extends JpaRepository<MasterEntity, Long> {
}

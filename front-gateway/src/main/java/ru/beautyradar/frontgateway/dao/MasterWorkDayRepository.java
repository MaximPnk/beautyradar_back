package ru.beautyradar.frontgateway.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.beautyradar.frontgateway.entity.MasterEntity;
import ru.beautyradar.frontgateway.entity.MasterWorkDayEntity;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface MasterWorkDayRepository extends JpaRepository<MasterWorkDayEntity, Long> {

   List<MasterWorkDayEntity> findAllByMaster(MasterEntity master);

   List<MasterWorkDayEntity> findAllByDate(LocalDateTime date);
}

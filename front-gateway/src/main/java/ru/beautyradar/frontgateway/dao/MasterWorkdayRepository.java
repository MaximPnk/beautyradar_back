package ru.beautyradar.frontgateway.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.beautyradar.frontgateway.entity.MasterEntity;
import ru.beautyradar.frontgateway.entity.MasterWorkdayEntity;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MasterWorkdayRepository extends JpaRepository<MasterWorkdayEntity, Long> {

   List<MasterWorkdayEntity> findAllByMaster(MasterEntity master);

   List<MasterWorkdayEntity> findAllByDate(LocalDate date);

}

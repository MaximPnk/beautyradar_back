package ru.beautyradar.frontgateway.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.beautyradar.frontgateway.entity.*;

import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<JobEntity, Long> {

    List<JobEntity> findAllByClient(ClientEntity clientEntity);

    List<JobEntity> findAllByMasterWorkday(MasterWorkdayEntity masterWorkdayEntity);

    List<JobEntity> findAllByServiceDescription(ServiceDescriptionEntity serviceDescriptionEntity);

    List<JobEntity> findAllByMasterWorkdayMaster(MasterEntity masterEntity);

}

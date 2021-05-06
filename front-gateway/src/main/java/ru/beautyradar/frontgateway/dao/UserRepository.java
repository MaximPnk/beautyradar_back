package ru.beautyradar.frontgateway.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.beautyradar.frontgateway.entity.UserEntity;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findFirstByUpn(String upn);

    boolean existsByUpn(String upn);

}

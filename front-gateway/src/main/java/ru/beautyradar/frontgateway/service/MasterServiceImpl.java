package ru.beautyradar.frontgateway.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import ru.beautyradar.frontgateway.dao.MasterRepository;
import ru.beautyradar.frontgateway.dao.UserRepository;
import ru.beautyradar.frontgateway.dto.UserDto;
import ru.beautyradar.frontgateway.dto.wrap.InitResp;
import ru.beautyradar.frontgateway.dto.wrap.Resp;
import ru.beautyradar.frontgateway.entity.UserEntity;
import ru.beautyradar.frontgateway.exc.ResourceNotFoundException;
import ru.beautyradar.frontgateway.map.UserMapper;
import ru.beautyradar.frontgateway.service.inter.MasterService;
import ru.beautyradar.frontgateway.service.inter.UserService;

import javax.transaction.Transactional;
import java.sql.SQLException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class MasterServiceImpl implements MasterService {

   @Autowired
    MasterRepository repository;


    @Override
    public Resp<?> getMasters() {
        try {
            return new InitResp<>().ok(repository.findAll());
        } catch (DataAccessException e) {
            log.error(e.getMessage());
            if (e.getRootCause() instanceof SQLException) {
                SQLException sqlEx = (SQLException) e.getRootCause();
                return new InitResp<>().exc(Integer.parseInt(sqlEx.getSQLState()), sqlEx.getMessage());
            }
            return new InitResp<>().exc(1, e.getMessage());
        } catch (Exception e) {
            log.error(e.getMessage());
            return new InitResp<>().exc(1, e.getMessage());
        }
    }

    @Override
    public Resp<?> getMasterById(Long id) {
        try {
            return new InitResp<>().ok(repository.findById(id));
        } catch (DataAccessException e) {
            log.error(e.getMessage());
            if (e.getRootCause() instanceof SQLException) {
                SQLException sqlEx = (SQLException) e.getRootCause();
                return new InitResp<>().exc(Integer.parseInt(sqlEx.getSQLState()), sqlEx.getMessage());
            }
            return new InitResp<>().exc(1, e.getMessage());
        } catch (Exception e) {
            log.error(e.getMessage());
            return new InitResp<>().exc(1, e.getMessage());
        }
    }


}

package ru.beautyradar.frontgateway.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.beautyradar.frontgateway.dao.MasterRepository;
import ru.beautyradar.frontgateway.dto.MasterDto;
import ru.beautyradar.frontgateway.dto.wrap.InitResp;
import ru.beautyradar.frontgateway.dto.wrap.Resp;
import ru.beautyradar.frontgateway.entity.MasterEntity;
import ru.beautyradar.frontgateway.entity.UserEntity;
import ru.beautyradar.frontgateway.exc.ResourceNotFoundException;
import ru.beautyradar.frontgateway.map.MasterMapper;
import ru.beautyradar.frontgateway.service.inter.MasterService;
import ru.beautyradar.frontgateway.service.inter.UserService;

import java.sql.SQLException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class MasterServiceImpl implements MasterService {

    @Autowired
    MasterRepository repository;
    @Autowired
    MasterMapper mapper;
    @Autowired
    UserService userService;


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

    @Override
    public MasterEntity getMasterEntityById(Long id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No such master by id"));
    }

    @Override
    public MasterDto getMasterInfoByUserId(Long id) {
        MasterDto dto = new MasterDto();
        UserEntity user = userService.findUserById(id);
        if (user != null) {
            dto.setId(user.getMaster().getId());
            dto.setUserId(user.getId());
            dto.setAddress(user.getMaster().getAddress());
            dto.setRating(user.getMaster().getRating());
        }
        return dto;
    }

    @Override
    @Transactional
    public Resp<?> setMasterByUserId(Long userId) {
        try {
            MasterDto dto = getMasterInfoByUserId(userId);
            if (dto.getUserId().equals(userId)) {
                return new InitResp<>().ok(dto);
            } else {
                MasterEntity master = new MasterEntity();
                repository.saveAndFlush(master);
                return new InitResp<>().ok(getMasterInfoByUserId(userId));
            }
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
    public Resp<?> setAddressByMasterId(Long masterId, String address) {
        try {
            Optional<MasterEntity> master = repository.findById(masterId);
            if (master.isPresent()) {
                master.get().setAddress(address);
                return new InitResp<>().ok(repository.findById(masterId));
            } else {
                return new InitResp<>().ok("Updating address rejected by reason - No such master ID");
            }
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
    public Resp<?> setCoordinatesByMasterId(Long masterId, String coordinates) {
        try {
            Optional<MasterEntity> master = repository.findById(masterId);
            if (master.isPresent()) {
                master.get().setCoordinates(coordinates);
                return new InitResp<>().ok(repository.findById(masterId));
            } else {
                return new InitResp<>().ok("Updating coordinates rejected by reason - No such master ID");
            }
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

package ru.beautyradar.uploadservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import ru.beautyradar.uploadservice.dao.MasterRepository;
import ru.beautyradar.uploadservice.entity.MasterEntity;
import ru.beautyradar.uploadservice.service.inter.MasterService;
import ru.beautyradar.uploadservice.wrap.InitResp;
import ru.beautyradar.uploadservice.wrap.Resp;

import java.sql.SQLException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class MasterServiceImpl implements MasterService {

    private final MasterRepository masterRepository;

    @Override
    public Resp<?> findMasterById(Long id) {
        try {
            Optional<MasterEntity> entity = masterRepository.findById(id);
            if (entity.isPresent()) {
                return new InitResp<>().ok(entity.get());
            } else {
                return new InitResp<>().exc(1, "Master with this id not found");
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

package ru.beautyradar.uploadservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import ru.beautyradar.uploadservice.dao.UserRepository;
import ru.beautyradar.uploadservice.entity.UserEntity;
import ru.beautyradar.uploadservice.service.inter.UserService;
import ru.beautyradar.uploadservice.wrap.InitResp;
import ru.beautyradar.uploadservice.wrap.Resp;

import java.sql.SQLException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public Resp<?> findUserById(Long id) {
        try {
            Optional<UserEntity> entity = userRepository.findById(id);
            if (entity.isPresent()) {
                return new InitResp<>().ok(entity.get());
            } else {
                return new InitResp<>().exc(1, "User with this id not found");
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

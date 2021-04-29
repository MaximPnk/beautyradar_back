package ru.beautyradar.pushservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import ru.beautyradar.pushservice.dao.UserRepository;
import ru.beautyradar.pushservice.dto.wrap.InitResp;
import ru.beautyradar.pushservice.dto.wrap.Resp;
import ru.beautyradar.pushservice.entity.UserEntity;
import ru.beautyradar.pushservice.service.inter.UserService;

import java.sql.SQLException;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public Resp<?> getTokenByUpn(String upn) {
        try {
            Optional<UserEntity> optionalUser = userRepository.findFirstByUpn(upn);
            if (optionalUser.isPresent()) {
                return new InitResp<>().ok(optionalUser.get().getToken());
            }
            return new InitResp<>().exc(1, "User with this UPN not found");
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

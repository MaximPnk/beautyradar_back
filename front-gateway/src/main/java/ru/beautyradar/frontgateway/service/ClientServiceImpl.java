package ru.beautyradar.frontgateway.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import ru.beautyradar.frontgateway.dao.ClientRepository;
import ru.beautyradar.frontgateway.dao.RoleRepository;
import ru.beautyradar.frontgateway.dto.ClientDto;
import ru.beautyradar.frontgateway.dto.UserDto;
import ru.beautyradar.frontgateway.dto.wrap.InitResp;
import ru.beautyradar.frontgateway.dto.wrap.Resp;
import ru.beautyradar.frontgateway.entity.ClientEntity;
import ru.beautyradar.frontgateway.entity.RoleEntity;
import ru.beautyradar.frontgateway.entity.UserEntity;
import ru.beautyradar.frontgateway.exc.ResourceNotFoundException;
import ru.beautyradar.frontgateway.map.ClientMapper;
import ru.beautyradar.frontgateway.service.inter.ClientService;
import ru.beautyradar.frontgateway.service.inter.RoleService;

import java.sql.SQLException;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ClientServiceImpl implements ClientService {

    private final ClientRepository repository;
    private final ClientMapper clientMapper;


    @Override
    public Resp<?> getAllClients() {
        try {
            return new InitResp<>().ok(repository.findAll().stream().map(clientMapper::mapEntityToDto));
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
    public Resp<?> findById(Long id) {
        try{
            return new InitResp<>().ok( repository.findById(id).orElseThrow(
                    ()->new ResourceNotFoundException("No such client")));
        }catch (DataAccessException e) {
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
    public Resp<?> findByUser(UserEntity entity) {
        try{
            return new InitResp<>().ok(repository.findByUser(entity).orElseThrow(
                    ()->new ResourceNotFoundException("No such client")));
        }catch (DataAccessException e) {
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
    public Resp<?> saveClient(ClientDto dto) {
        return null;// todo нужно решить по каким параметрам будем создавать клиента. т.к. он должен быть связан с User
    }

//    @Override
//    public Resp<?> saveClient(ClientDto dto) {
//        try {
//            UserEntity entity = userRepository.save(userMapper.mapDtoToEntity(userDto));
//            return new InitResp<>().ok(userMapper.mapEntityToDto(entity));
//        } catch (DataAccessException e) {
//            log.error(e.getMessage());
//            if (e.getRootCause() instanceof SQLException) {
//                SQLException sqlEx = (SQLException) e.getRootCause();
//                return new InitResp<>().exc(Integer.parseInt(sqlEx.getSQLState()), sqlEx.getMessage());
//            }
//            return new InitResp<>().exc(1, e.getMessage());
//        } catch (Exception e) {
//            log.error(e.getMessage());
//            return new InitResp<>().exc(1, e.getMessage());
//        }
//    }

}

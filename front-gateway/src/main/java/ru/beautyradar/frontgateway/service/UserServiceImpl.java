package ru.beautyradar.frontgateway.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import ru.beautyradar.frontgateway.dao.UserRepository;
import ru.beautyradar.frontgateway.dto.UserDto;
import ru.beautyradar.frontgateway.dto.wrap.InitResp;
import ru.beautyradar.frontgateway.dto.wrap.Resp;
import ru.beautyradar.frontgateway.entity.UserEntity;
import ru.beautyradar.frontgateway.event.SaveClientEvent;
import ru.beautyradar.frontgateway.exc.ResourceNotFoundException;
import ru.beautyradar.frontgateway.map.UserMapper;
import ru.beautyradar.frontgateway.service.inter.ClientService;
import ru.beautyradar.frontgateway.service.inter.UserService;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final UserMapper mapper;
    private final ApplicationEventPublisher publisher;
    private ClientService clientService;

    @Override
    public Resp<?> getAllUsersDto() {
        try {
            return new InitResp<>().ok(repository.findAll().stream().map(mapper::mapEntityToDto));
        } catch (Exception e) {
            log.error(e.getMessage());
            return new InitResp<>().exc(1, e.getMessage());
        }
    }

    @Override
    public Resp<?> getUserDtoById(Long id) {
        try {
            UserEntity userEntity = getUserEntityById(id);
            return new InitResp<>().ok(mapper.mapEntityToDto(userEntity));
        } catch (Exception e) {
            log.error(e.getMessage());
            return new InitResp<>().exc(1, e.getMessage());
        }
    }

    @Override
    public Resp<?> getUserDtoByUpn(String upn) {
        try {
            UserEntity userEntity = getUserEntityByUpn(upn);
            return new InitResp<>().ok(mapper.mapEntityToDto(userEntity));
        } catch (Exception e) {
            log.error(e.getMessage());
            return new InitResp<>().exc(1, e.getMessage());
        }
    }

    @Override
    public Resp<?> existsUserByUpn(String upn) {
        try {
            return new InitResp<>().ok(repository.existsByUpn(upn));
        } catch (Exception e) {
            log.error(e.getMessage());
            return new InitResp<>().exc(1, e.getMessage());
        }
    }

    @Override
    @Transactional
    public Resp<?> saveUser(UserDto userDto) {
        try {
            UserEntity entity = repository.save(mapper.mapDtoToEntity(userDto));
            publisher.publishEvent(new SaveClientEvent(clientService, entity));
            return new InitResp<>().ok(mapper.mapEntityToDto(entity));
        } catch (Exception e) {
            log.error(e.getMessage());
            return new InitResp<>().exc(1, e.getMessage());
        }
    }

    @Override
    @Transactional
    public Resp<?> updateUser(Long id, UserDto userDto) {
        try {
            UserEntity userEntity = getUserEntityById(id);
            mapper.updateEntityByDto(userEntity, userDto);
            return new InitResp<>().ok(mapper.mapEntityToDto(userEntity));
        } catch (Exception e) {
            log.error(e.getMessage());
            return new InitResp<>().exc(1, e.getMessage());
        }
    }

    @Override
    @Transactional
    public Resp<?> deleteUserById(Long id) {
        try {
            repository.deleteById(id);
            return new InitResp<>().ok(null);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new InitResp<>().exc(1, e.getMessage());
        }
    }

    // service methods

    @Override
    public UserEntity getUserEntityById(Long id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Пользователь с таким id не найден"));
    }

    @Override
    public UserEntity getUserEntityByUpn(String upn) {
        return repository.findFirstByUpn(upn).orElseThrow(() -> new ResourceNotFoundException("Пользователь с таким uid не найден"));
    }

    //cyclic

    @Autowired
    public void setClientService(ClientService clientService) {
        this.clientService = clientService;
    }
}

package ru.beautyradar.frontgateway.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import ru.beautyradar.frontgateway.dao.UserRepository;
import ru.beautyradar.frontgateway.dto.UserDto;
import ru.beautyradar.frontgateway.dto.wrap.Resp;
import ru.beautyradar.frontgateway.dto.wrap.RespBuilder;
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

    @Override
    public Resp<?> getAllUsersDto() {
        try {
            return new RespBuilder<>().setCode(0).setBody(repository.findAll().stream().map(mapper::mapEntityToDto)).build();
        } catch (Exception e) {
            log.error(e.getMessage());
            return new RespBuilder<>().setCode(1).setMessage(e.getMessage()).build();
        }
    }

    @Override
    public Resp<?> getUserDtoById(Long id) {
        try {
            UserEntity userEntity = getUserEntityById(id);
            return new RespBuilder<>().setCode(0).setBody(mapper.mapEntityToDto(userEntity)).build();
        } catch (Exception e) {
            log.error(e.getMessage());
            return new RespBuilder<>().setCode(1).setMessage(e.getMessage()).build();
        }
    }

    @Override
    public Resp<?> getUserDtoByUpn(String upn) {
        try {
            UserEntity userEntity = getUserEntityByUpn(upn);
            return new RespBuilder<>().setCode(0).setBody(mapper.mapEntityToDto(userEntity)).build();
        } catch (Exception e) {
            log.error(e.getMessage());
            return new RespBuilder<>().setCode(1).setMessage(e.getMessage()).build();
        }
    }

    @Override
    public Resp<?> existsUserByUpn(String upn) {
        try {
            return new RespBuilder<>().setCode(0).setBody(repository.existsByUpn(upn)).build();
        } catch (Exception e) {
            log.error(e.getMessage());
            return new RespBuilder<>().setCode(1).setMessage(e.getMessage()).build();
        }
    }

    @Override
    @Transactional
    public Resp<?> saveUser(UserDto userDto) {
        try {
            UserEntity entity = repository.save(mapper.mapDtoToEntity(userDto));
            publisher.publishEvent(new SaveClientEvent(ClientService.class, entity));
            return new RespBuilder<>().setCode(0).setBody(mapper.mapEntityToDto(entity)).build();
        } catch (Exception e) {
            log.error(e.getMessage());
            return new RespBuilder<>().setCode(1).setMessage(e.getMessage()).build();
        }
    }

    @Override
    @Transactional
    public Resp<?> updateUser(Long id, UserDto userDto) {
        try {
            UserEntity userEntity = getUserEntityById(id);
            mapper.updateEntityByDto(userEntity, userDto);
            return new RespBuilder<>().setCode(0).setBody(mapper.mapEntityToDto(userEntity)).build();
        } catch (Exception e) {
            log.error(e.getMessage());
            return new RespBuilder<>().setCode(1).setMessage(e.getMessage()).build();
        }
    }

    @Override
    @Transactional
    public Resp<?> deleteUserById(Long id) {
        try {
            repository.deleteById(id);
            return new RespBuilder<>().setCode(0).build();
        } catch (Exception e) {
            log.error(e.getMessage());
            return new RespBuilder<>().setCode(1).setMessage(e.getMessage()).build();
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

}

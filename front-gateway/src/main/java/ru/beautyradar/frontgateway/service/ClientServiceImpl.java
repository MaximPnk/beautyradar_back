package ru.beautyradar.frontgateway.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.beautyradar.frontgateway.dao.ClientRepository;
import ru.beautyradar.frontgateway.dto.wrap.InitResp;
import ru.beautyradar.frontgateway.dto.wrap.Resp;
import ru.beautyradar.frontgateway.entity.ClientEntity;
import ru.beautyradar.frontgateway.entity.UserEntity;
import ru.beautyradar.frontgateway.event.SaveClientEvent;
import ru.beautyradar.frontgateway.exc.ResourceNotFoundException;
import ru.beautyradar.frontgateway.map.ClientMapper;
import ru.beautyradar.frontgateway.service.inter.ClientService;
import ru.beautyradar.frontgateway.service.inter.UserService;

@Service
@RequiredArgsConstructor
@Slf4j
public class ClientServiceImpl implements ClientService {

    private final ClientRepository repository;
    private final ClientMapper mapper;
    private UserService userService;


    @Override
    public Resp<?> getAllClientsDto() {
        try {
            return new InitResp<>().ok(repository.findAll().stream().map(mapper::mapEntityToDto));
        } catch (Exception e) {
            log.error(e.getMessage());
            return new InitResp<>().exc(1, e.getMessage());
        }
    }

    @Override
    public Resp<?> getClientDtoById(Long id) {
        try{
            ClientEntity clientEntity = getClientEntityById(id);
            return new InitResp<>().ok(mapper.mapEntityToDto(clientEntity));
        } catch (Exception e) {
            log.error(e.getMessage());
            return new InitResp<>().exc(1, e.getMessage());
        }

    }

    @Override
    @Transactional
    public Resp<?> getClientDtoByUserId(Long id) {
        try{
            UserEntity userEntity = userService.getUserEntityById(id);
            ClientEntity clientEntity = getClientEntityByUser(userEntity);
            return new InitResp<>().ok(mapper.mapEntityToDto(clientEntity));
        } catch (Exception e) {
            log.error(e.getMessage());
            return new InitResp<>().exc(1, e.getMessage());
        }
    }

    @Override
    @EventListener
    public void saveClient(SaveClientEvent event) {
        repository.save(new ClientEntity(event.getUserEntity()));
    }

    //service methods

    @Override
    public ClientEntity getClientEntityById(Long id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Клиент с таким ID не существует"));
    }

    @Override
    public ClientEntity getClientEntityByUser(UserEntity userEntity) {
        return repository.findByUser(userEntity).orElseThrow(() -> new ResourceNotFoundException("Пользователь не является клиентом"));
    }

    //cyclic

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}

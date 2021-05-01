package ru.beautyradar.frontgateway.service.inter;


import ru.beautyradar.frontgateway.dto.ClientDto;
import ru.beautyradar.frontgateway.dto.wrap.Resp;
import ru.beautyradar.frontgateway.entity.ClientEntity;
import ru.beautyradar.frontgateway.entity.UserEntity;

import java.util.List;

public interface ClientService {

   Resp<?> getAllClients();

   Resp<?> findById(Long id);

   Resp<?> findByUser(UserEntity entity);

    Resp<?> saveClient(ClientDto dto);
}

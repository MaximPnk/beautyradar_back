package ru.beautyradar.frontgateway.service.inter;


import ru.beautyradar.frontgateway.dto.wrap.Resp;
import ru.beautyradar.frontgateway.entity.UserEntity;
import ru.beautyradar.frontgateway.event.SaveClientEvent;

public interface ClientService {

   Resp<?> getAllClients();

   Resp<?> findById(Long id);

   Resp<?> findByUser(UserEntity entity);

   void saveClient(SaveClientEvent event);
}

package ru.beautyradar.frontgateway.service.inter;


import org.springframework.context.event.EventListener;
import ru.beautyradar.frontgateway.dto.wrap.Resp;
import ru.beautyradar.frontgateway.entity.ClientEntity;
import ru.beautyradar.frontgateway.entity.UserEntity;
import ru.beautyradar.frontgateway.event.SaveClientEvent;

public interface ClientService {


   Resp<?> getAllClientsDto();

   Resp<?> getClientDtoById(Long id);

   Resp<?> getClientDtoByUserId(Long id);

   @EventListener
   void saveClient(SaveClientEvent event);

   ClientEntity getClientEntityById(Long id);

   ClientEntity getClientEntityByUser(UserEntity userEntity);
}

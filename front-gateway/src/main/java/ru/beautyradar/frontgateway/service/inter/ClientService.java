package ru.beautyradar.frontgateway.service.inter;


import ru.beautyradar.frontgateway.dto.wrap.Resp;
import ru.beautyradar.frontgateway.entity.ClientEntity;
import ru.beautyradar.frontgateway.entity.UserEntity;
import ru.beautyradar.frontgateway.event.SaveClientEvent;
import ru.beautyradar.frontgateway.event.UpdateClientRatingEvent;

public interface ClientService {

    Resp<?> getAllClientsDto();

    Resp<?> getClientDtoById(Long id);

    Resp<?> getClientDtoByUserId(Long id);

    void saveClient(SaveClientEvent event);

    void updateRating(UpdateClientRatingEvent event);

    ClientEntity getClientEntityById(Long id);

    ClientEntity getClientEntityByUser(UserEntity userEntity);
}

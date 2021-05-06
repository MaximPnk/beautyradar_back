package ru.beautyradar.frontgateway.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.beautyradar.frontgateway.dto.wrap.Resp;
import ru.beautyradar.frontgateway.dto.wrap.RespBuilder;
import ru.beautyradar.frontgateway.entity.ClientEntity;
import ru.beautyradar.frontgateway.entity.MasterEntity;
import ru.beautyradar.frontgateway.map.MasterMapper;
import ru.beautyradar.frontgateway.service.inter.ClientService;
import ru.beautyradar.frontgateway.service.inter.FavoriteService;
import ru.beautyradar.frontgateway.service.inter.MasterService;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class FavoriteServiceImpl implements FavoriteService {

    private final ClientService clientService;
    private final MasterService masterService;
    private final MasterMapper masterMapper;

    @Override
    @Transactional
    public Resp<?> getAllFavoriteMastersDtoByClientId(Long id) {
        try {
            ClientEntity client = clientService.getClientEntityById(id);
            List<MasterEntity> masters = client.getFavoriteMasters();
            return new RespBuilder<>().setCode(0).setBody(masters.stream().map(masterMapper::mapEntityToDto)).build();
        } catch (Exception e) {
            log.error(e.getMessage());
            return new RespBuilder<>().setCode(1).setMessage(e.getMessage()).build();
        }
    }

    @Override
    @Transactional
    public Resp<?> addFavoriteMaster(Long clientId, Long masterId) {
        try {
            ClientEntity client = clientService.getClientEntityById(clientId);
            MasterEntity master = masterService.getMasterEntityById(masterId);
            client.getFavoriteMasters().add(master);
            return new RespBuilder<>().setCode(0).build();
        } catch (Exception e) {
            log.error(e.getMessage());
            return new RespBuilder<>().setCode(1).setMessage(e.getMessage()).build();
        }
    }

    @Override
    @Transactional
    public Resp<?> removeFavoriteMaster(Long clientId, Long masterId) {
        try {
            ClientEntity client = clientService.getClientEntityById(clientId);
            MasterEntity master = masterService.getMasterEntityById(masterId);
            client.getFavoriteMasters().remove(master);
            return new RespBuilder<>().setCode(0).build();
        } catch (Exception e) {
            log.error(e.getMessage());
            return new RespBuilder<>().setCode(1).setMessage(e.getMessage()).build();
        }
    }
}

package ru.beautyradar.frontgateway.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.beautyradar.frontgateway.dao.ClientReviewRepository;
import ru.beautyradar.frontgateway.dto.ClientReviewDto;
import ru.beautyradar.frontgateway.dto.wrap.Resp;
import ru.beautyradar.frontgateway.dto.wrap.RespBuilder;
import ru.beautyradar.frontgateway.entity.ClientEntity;
import ru.beautyradar.frontgateway.entity.ClientReviewEntity;
import ru.beautyradar.frontgateway.entity.MasterEntity;
import ru.beautyradar.frontgateway.event.UpdateMasterRatingEvent;
import ru.beautyradar.frontgateway.exc.ResourceNotFoundException;
import ru.beautyradar.frontgateway.map.ClientReviewMapper;
import ru.beautyradar.frontgateway.service.inter.ClientReviewService;
import ru.beautyradar.frontgateway.service.inter.ClientService;
import ru.beautyradar.frontgateway.service.inter.MasterService;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ClientReviewServiceImpl implements ClientReviewService {

    private final MasterService masterService;
    private final ClientService clientService;
    private final ClientReviewRepository repository;
    private final ClientReviewMapper mapper;
    private final ApplicationEventPublisher publisher;

    @Override
    @Transactional
    public Resp<?> getAllClientReviewsDtoByMasterId(Long masterId) {
        try {
            MasterEntity master = masterService.getMasterEntityById(masterId);
            List<ClientReviewEntity> clientReviews = repository.findAllByMaster(master);
            return new RespBuilder<>().setCode(0).setBody(clientReviews.stream().map(mapper::mapEntityToDto)).build();
        } catch (Exception e) {
            log.error(e.getMessage());
            return new RespBuilder<>().setCode(1).setMessage(e.getMessage()).build();
        }
    }

    @Override
    @Transactional
    public Resp<?> getAllClientReviewsDtoByClientId(Long clientId) {
        try {
            ClientEntity client = clientService.getClientEntityById(clientId);
            List<ClientReviewEntity> clientReviews = repository.findAllByClient(client);
            return new RespBuilder<>().setCode(0).setBody(clientReviews.stream().map(mapper::mapEntityToDto)).build();
        } catch (Exception e) {
            log.error(e.getMessage());
            return new RespBuilder<>().setCode(1).setMessage(e.getMessage()).build();
        }
    }

    @Override
    @Transactional
    public Resp<?> getClientReviewDtoById(Long id) {
        try {
            ClientReviewEntity clientReviewEntity = getClientReviewEntityById(id);
            return new RespBuilder<>().setCode(0).setBody(mapper.mapEntityToDto(clientReviewEntity)).build();
        } catch (Exception e) {
            log.error(e.getMessage());
            return new RespBuilder<>().setCode(1).setMessage(e.getMessage()).build();
        }
    }

    @Override
    @Transactional
    public Resp<?> getClientReviewDtoByClientIdAndMasterId(Long clientId, Long masterId) {
        try {
            ClientEntity client = clientService.getClientEntityById(clientId);
            MasterEntity master = masterService.getMasterEntityById(masterId);
            ClientReviewEntity clientReviewEntity = getClientReviewEntityByClientAndMaster(client, master);
            return new RespBuilder<>().setCode(0).setBody(mapper.mapEntityToDto(clientReviewEntity)).build();
        } catch (Exception e) {
            log.error(e.getMessage());
            return new RespBuilder<>().setCode(1).setMessage(e.getMessage()).build();
        }
    }

    @Override
    @Transactional
    public Resp<?> createClientReview(ClientReviewDto clientReviewDto) {
        try {
            ClientReviewEntity clientReviewEntity = mapper.mapDtoToEntity(clientReviewDto);
            repository.save(clientReviewEntity);
            publisher.publishEvent(new UpdateMasterRatingEvent(MasterServiceImpl.class, clientReviewEntity.getMaster()));
            return new RespBuilder<>().setCode(0).setBody(mapper.mapEntityToDto(clientReviewEntity)).build();
        } catch (Exception e) {
            log.error(e.getMessage());
            return new RespBuilder<>().setCode(1).setMessage(e.getMessage()).build();
        }
    }

    @Override
    @Transactional
    public Resp<?> updateClientReview(Long id, ClientReviewDto clientReviewDto) {
        try {
            ClientReviewEntity clientReviewEntity = getClientReviewEntityById(id);
            mapper.updateEntityByDto(clientReviewEntity, clientReviewDto);
            publisher.publishEvent(new UpdateMasterRatingEvent(MasterServiceImpl.class, clientReviewEntity.getMaster()));
            return new RespBuilder<>().setCode(0).setBody(mapper.mapEntityToDto(clientReviewEntity)).build();
        } catch (Exception e) {
            log.error(e.getMessage());
            return new RespBuilder<>().setCode(1).setMessage(e.getMessage()).build();
        }
    }

    @Override
    @Transactional
    public Resp<?> deleteClientReviewById(Long id) {
        try {
            ClientReviewEntity clientReviewEntity = getClientReviewEntityById(id);
            MasterEntity master = clientReviewEntity.getMaster();
            repository.delete(clientReviewEntity);
            repository.flush();
            publisher.publishEvent(new UpdateMasterRatingEvent(MasterServiceImpl.class, master));
            return new RespBuilder<>().setCode(0).build();
        } catch (Exception e) {
            log.error(e.getMessage());
            return new RespBuilder<>().setCode(1).setMessage(e.getMessage()).build();
        }
    }

    //service methods

    public ClientReviewEntity getClientReviewEntityById(Long id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Отзыв клиента с таким id не существует"));
    }

    public ClientReviewEntity getClientReviewEntityByClientAndMaster(ClientEntity clientEntity, MasterEntity masterEntity) {
        return repository.findFirstByClientAndMaster(clientEntity, masterEntity).orElseThrow(() -> new ResourceNotFoundException("Отзыв клиента с таким id не существует"));
    }

}

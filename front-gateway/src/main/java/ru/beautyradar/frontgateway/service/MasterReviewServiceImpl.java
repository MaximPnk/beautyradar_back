package ru.beautyradar.frontgateway.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.beautyradar.frontgateway.dao.MasterReviewRepository;
import ru.beautyradar.frontgateway.dto.MasterReviewDto;
import ru.beautyradar.frontgateway.dto.wrap.Resp;
import ru.beautyradar.frontgateway.dto.wrap.RespBuilder;
import ru.beautyradar.frontgateway.entity.ClientEntity;
import ru.beautyradar.frontgateway.entity.MasterReviewEntity;
import ru.beautyradar.frontgateway.entity.MasterEntity;
import ru.beautyradar.frontgateway.exc.ResourceNotFoundException;
import ru.beautyradar.frontgateway.map.MasterReviewMapper;
import ru.beautyradar.frontgateway.service.inter.MasterReviewService;
import ru.beautyradar.frontgateway.service.inter.ClientService;
import ru.beautyradar.frontgateway.service.inter.MasterService;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class MasterReviewServiceImpl implements MasterReviewService {

    private final ClientService clientService;
    private final MasterService masterService;
    private final MasterReviewRepository repository;
    private final MasterReviewMapper mapper;

    @Override
    @Transactional
    public Resp<?> getAllMasterReviewsDtoByClientId(Long clientId) {
        try {
            ClientEntity client = clientService.getClientEntityById(clientId);
            List<MasterReviewEntity> masterReviews = repository.findAllByClient(client);
            return new RespBuilder<>().setCode(0).setBody(masterReviews.stream().map(mapper::mapEntityToDto)).build();
        } catch (Exception e) {
            log.error(e.getMessage());
            return new RespBuilder<>().setCode(1).setMessage(e.getMessage()).build();
        }
    }

    @Override
    @Transactional
    public Resp<?> getAllMasterReviewsDtoByMasterId(Long masterId) {
        try {
            MasterEntity master = masterService.getMasterEntityById(masterId);
            List<MasterReviewEntity> masterReviews = repository.findAllByMaster(master);
            return new RespBuilder<>().setCode(0).setBody(masterReviews.stream().map(mapper::mapEntityToDto)).build();
        } catch (Exception e) {
            log.error(e.getMessage());
            return new RespBuilder<>().setCode(1).setMessage(e.getMessage()).build();
        }
    }

    @Override
    @Transactional
    public Resp<?> getMasterReviewDtoById(Long id) {
        try {
            MasterReviewEntity masterReviewEntity = getMasterReviewEntityById(id);
            return new RespBuilder<>().setCode(0).setBody(mapper.mapEntityToDto(masterReviewEntity)).build();
        } catch (Exception e) {
            log.error(e.getMessage());
            return new RespBuilder<>().setCode(1).setMessage(e.getMessage()).build();
        }
    }

    @Override
    @Transactional
    public Resp<?> createMasterReview(MasterReviewDto masterReviewDto) {
        try {
            MasterReviewEntity masterReviewEntity = mapper.mapDtoToEntity(masterReviewDto);
            repository.save(masterReviewEntity);            
            return new RespBuilder<>().setCode(0).setBody(mapper.mapEntityToDto(masterReviewEntity)).build();
        } catch (Exception e) {
            log.error(e.getMessage());
            return new RespBuilder<>().setCode(1).setMessage(e.getMessage()).build();
        }
    }

    @Override
    @Transactional
    public Resp<?> updateMasterReview(Long id, MasterReviewDto masterReviewDto) {
        try {
            MasterReviewEntity masterReviewEntity = getMasterReviewEntityById(id);
            mapper.updateEntityByDto(masterReviewEntity, masterReviewDto);
            return new RespBuilder<>().setCode(0).setBody(mapper.mapEntityToDto(masterReviewEntity)).build();
        } catch (Exception e) {
            log.error(e.getMessage());
            return new RespBuilder<>().setCode(1).setMessage(e.getMessage()).build();
        }
    }

    @Override
    @Transactional
    public Resp<?> deleteMasterReviewById(Long id) {
        try {
            repository.deleteById(id);
            return new RespBuilder<>().setCode(0).build();
        } catch (Exception e) {
            log.error(e.getMessage());
            return new RespBuilder<>().setCode(1).setMessage(e.getMessage()).build();
        }
    }
    
    //service methods
    
    public MasterReviewEntity getMasterReviewEntityById(Long id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Отзыв клиента с таким id не существует"));
    }
    
}

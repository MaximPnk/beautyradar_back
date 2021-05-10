package ru.beautyradar.frontgateway.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.beautyradar.frontgateway.dao.MasterWorkdayRepository;
import ru.beautyradar.frontgateway.dto.MasterWorkdayDto;
import ru.beautyradar.frontgateway.dto.wrap.Resp;
import ru.beautyradar.frontgateway.dto.wrap.RespBuilder;
import ru.beautyradar.frontgateway.entity.MasterEntity;
import ru.beautyradar.frontgateway.entity.MasterWorkdayEntity;
import ru.beautyradar.frontgateway.exc.ResourceNotFoundException;
import ru.beautyradar.frontgateway.map.MasterWorkdayMapper;
import ru.beautyradar.frontgateway.service.inter.MasterService;
import ru.beautyradar.frontgateway.service.inter.MasterWorkdayService;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class MasterWorkdayServiceImpl implements MasterWorkdayService {

    private final MasterService masterService;
    private final MasterWorkdayRepository repository;
    private final MasterWorkdayMapper mapper;

    @Override
    @Transactional
    //todo по не disabled
    public Resp<?> getAllMasterWorkdaysDtoByMasterId(Long masterId) {
        try {
            MasterEntity master = masterService.getMasterEntityById(masterId);
            List<MasterWorkdayEntity> masterWorkdays = repository.findAllByMaster(master);
            return new RespBuilder<>().setCode(0).setBody(masterWorkdays.stream().map(mapper::mapEntityToDto)).build();
        } catch (Exception e) {
            log.error(e.getMessage());
            return new RespBuilder<>().setCode(1).setMessage(e.getMessage()).build();
        }
    }

    //todo выдавать список свободного времени после согласования с руководством и заливки тестовых данных

    @Override
    @Transactional
    public Resp<?> getAllMasterWorkdaysDtoByDate(LocalDate date) {
        //todo по не disabled
        try {
            List<MasterWorkdayEntity> masterWorkdays = repository.findAllByDate(date);
            return new RespBuilder<>().setCode(0).setBody(masterWorkdays.stream().map(mapper::mapEntityToDto)).build();
        } catch (Exception e) {
            log.error(e.getMessage());
            return new RespBuilder<>().setCode(1).setMessage(e.getMessage()).build();
        }
    }

    @Override
    @Transactional
    public Resp<?> getMasterWorkdayDtoById(Long id) {
        //todo по не disabled
        try {
            MasterWorkdayEntity masterWorkdayEntity = getMasterWorkdayEntityById(id);
            return new RespBuilder<>().setCode(0).setBody(mapper.mapEntityToDto(masterWorkdayEntity)).build();
        } catch (Exception e) {
            log.error(e.getMessage());
            return new RespBuilder<>().setCode(1).setMessage(e.getMessage()).build();
        }
    }

    @Override
    @Transactional
    public Resp<?> createMasterWorkday(MasterWorkdayDto masterWorkdayDto) {
        //todo согласовать логику создания согласно заранее обговорённому бизнес-процессу

        //todo создаём в любом случае новый
        try {
            MasterWorkdayEntity masterWorkdayEntity = mapper.mapDtoToEntity(masterWorkdayDto);
            repository.save(masterWorkdayEntity);
            return new RespBuilder<>().setCode(0).setBody(mapper.mapEntityToDto(masterWorkdayEntity)).build();
        } catch (Exception e) {
            log.error(e.getMessage());
            return new RespBuilder<>().setCode(1).setMessage(e.getMessage()).build();
        }
    }

    @Override
    @Transactional
    public Resp<?> updateMasterWorkday(Long id, MasterWorkdayDto masterWorkdayDto) {

        //todo чекаем на disabled, выкидываем ошибку
        try {
            MasterWorkdayEntity masterWorkdayEntity = getMasterWorkdayEntityById(id);
            mapper.updateEntityByDto(masterWorkdayEntity, masterWorkdayDto);
            return new RespBuilder<>().setCode(0).setBody(mapper.mapEntityToDto(masterWorkdayEntity)).build();
        } catch (Exception e) {
            log.error(e.getMessage());
            return new RespBuilder<>().setCode(1).setMessage(e.getMessage()).build();
        }
    }

    @Override
    @Transactional
    public Resp<?> deleteMasterWorkdayById(Long id) {
        //todo ставим disabled, job переводим в статус "отменено мастером"
        //todo добавляем в push таблицу для каждой job уведомление с текстом
        try {
            repository.deleteById(id);
            return new RespBuilder<>().setCode(0).build();
        } catch (Exception e) {
            log.error(e.getMessage());
            return new RespBuilder<>().setCode(1).setMessage(e.getMessage()).build();
        }
    }

    //service methods

    @Override
    public MasterWorkdayEntity getMasterWorkdayEntityById(Long id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Отзыв клиента с таким id не существует"));
    }

}

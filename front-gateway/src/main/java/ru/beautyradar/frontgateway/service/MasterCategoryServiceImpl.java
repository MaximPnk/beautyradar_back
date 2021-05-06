package ru.beautyradar.frontgateway.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.beautyradar.frontgateway.dao.ClientRepository;
import ru.beautyradar.frontgateway.dao.MasterCategoryRepository;
import ru.beautyradar.frontgateway.dto.MasterCategoryResponseDTO;
import ru.beautyradar.frontgateway.dto.UpdateMasterCategoryResponseDTO;
import ru.beautyradar.frontgateway.dto.wrap.InitResp;
import ru.beautyradar.frontgateway.dto.wrap.Resp;
import ru.beautyradar.frontgateway.entity.ClientEntity;
import ru.beautyradar.frontgateway.entity.MasterCategoryEntity;
import ru.beautyradar.frontgateway.entity.MasterEntity;
import ru.beautyradar.frontgateway.entity.UserEntity;
import ru.beautyradar.frontgateway.event.SaveClientEvent;
import ru.beautyradar.frontgateway.exc.ResourceNotFoundException;
import ru.beautyradar.frontgateway.map.ClientMapper;
import ru.beautyradar.frontgateway.service.inter.ClientService;
import ru.beautyradar.frontgateway.service.inter.MasterCategoryService;
import ru.beautyradar.frontgateway.service.inter.MasterService;

import java.sql.SQLException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class MasterCategoryServiceImpl implements MasterCategoryService {

    private final MasterCategoryRepository categoryRepository;
    private final MasterService masterService;

    @Override
    public Resp<?> findByCategoryId(Long categoryId) {
        try {
            return new InitResp<>().ok(categoryRepository.findById(categoryId));
        } catch (DataAccessException e) {
            log.error(e.getMessage());
            if (e.getRootCause() instanceof SQLException) {
                SQLException sqlEx = (SQLException) e.getRootCause();
                return new InitResp<>().exc(Integer.parseInt(sqlEx.getSQLState()), sqlEx.getMessage());
            }
            return new InitResp<>().exc(1, e.getMessage());
        } catch (Exception e) {
            log.error(e.getMessage());
            return new InitResp<>().exc(1, e.getMessage());
        }
    }

    @Override
    public Resp<?> findCategoriesByMasterID(Long masterId) {
        try {
            MasterEntity master = masterService.getMasterEntityById(masterId);
            return new InitResp<>().ok(categoryRepository.findAllByMaster(master));
        } catch (DataAccessException e) {
            log.error(e.getMessage());
            if (e.getRootCause() instanceof SQLException) {
                SQLException sqlEx = (SQLException) e.getRootCause();
                return new InitResp<>().exc(Integer.parseInt(sqlEx.getSQLState()), sqlEx.getMessage());
            }
            return new InitResp<>().exc(1, e.getMessage());
        } catch (Exception e) {
            log.error(e.getMessage());
            return new InitResp<>().exc(1, e.getMessage());
        }
    }

    @Override
    @Transactional
    public void saveNewMasterCategory(MasterCategoryResponseDTO dto) {
        MasterCategoryEntity mc = new MasterCategoryEntity();
        mc.setName(dto.getMasterCategory());
        mc.setDescription(dto.getDescription());
        mc.addMaster(masterService.getMasterEntityById(dto.getMasterID()));
        categoryRepository.saveAndFlush(mc);
    }

    @Override
    @Transactional
    public String updateMasterCategory(Long id,UpdateMasterCategoryResponseDTO dto) {
        Optional<MasterCategoryEntity> mc = categoryRepository.findById(id);
        if (mc.isPresent()){
            mc.get().setName(dto.getMasterCategory());
            mc.get().setDescription(dto.getDescription());
            mc.get().addMaster(masterService.getMasterEntityById(id));
            return "Update master category successes";
        }
        else return "Error for update master category";
    }

}

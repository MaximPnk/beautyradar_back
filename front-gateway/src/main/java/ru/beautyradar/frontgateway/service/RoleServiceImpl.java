package ru.beautyradar.frontgateway.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.beautyradar.frontgateway.dao.RoleRepository;
import ru.beautyradar.frontgateway.dto.RoleDto;
import ru.beautyradar.frontgateway.dto.wrap.Resp;
import ru.beautyradar.frontgateway.dto.wrap.RespBuilder;
import ru.beautyradar.frontgateway.entity.RoleEntity;
import ru.beautyradar.frontgateway.exc.ResourceNotFoundException;
import ru.beautyradar.frontgateway.map.RoleMapper;
import ru.beautyradar.frontgateway.service.inter.RoleService;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class RoleServiceImpl implements RoleService {

    private final RoleRepository repository;
    private final RoleMapper mapper;

    @Override
    @Transactional
    public Resp<?> getAllRolesDto() {
        try {
            List<RoleEntity> roles = repository.findAll();
            return new RespBuilder<>().setCode(0).setBody(roles.stream().map(mapper::mapEntityToDto)).build();
        } catch (Exception e) {
            log.error(e.getMessage());
            return new RespBuilder<>().setCode(1).setMessage(e.getMessage()).build();
        }
    }

    @Override
    @Transactional
    public Resp<?> getRoleDtoById(Long id) {
        try {
            RoleEntity roleEntity = getRoleEntityById(id);
            return new RespBuilder<>().setCode(0).setBody(mapper.mapEntityToDto(roleEntity)).build();
        } catch (Exception e) {
            log.error(e.getMessage());
            return new RespBuilder<>().setCode(1).setMessage(e.getMessage()).build();
        }
    }

    @Override
    @Transactional
    public Resp<?> createRole(RoleDto roleDto) {
        try {
            RoleEntity roleEntity = mapper.mapDtoToEntity(roleDto);
            repository.save(roleEntity);
            return new RespBuilder<>().setCode(0).setBody(mapper.mapEntityToDto(roleEntity)).build();
        } catch (Exception e) {
            log.error(e.getMessage());
            return new RespBuilder<>().setCode(1).setMessage(e.getMessage()).build();
        }
    }

    @Override
    @Transactional
    public Resp<?> updateRole(Long id, RoleDto roleDto) {
        try {
            RoleEntity roleEntity = getRoleEntityById(id);
            mapper.updateEntityByDto(roleEntity, roleDto);
            return new RespBuilder<>().setCode(0).setBody(mapper.mapEntityToDto(roleEntity)).build();
        } catch (Exception e) {
            log.error(e.getMessage());
            return new RespBuilder<>().setCode(1).setMessage(e.getMessage()).build();
        }
    }

    @Override
    @Transactional
    public Resp<?> deleteRoleById(Long id) {
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
    public RoleEntity getRoleEntityById(Long id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Роль с таким id не существует"));
    }

}

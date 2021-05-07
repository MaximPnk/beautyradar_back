package ru.beautyradar.frontgateway.service.inter;

import org.springframework.transaction.annotation.Transactional;
import ru.beautyradar.frontgateway.dto.RoleDto;
import ru.beautyradar.frontgateway.dto.wrap.Resp;
import ru.beautyradar.frontgateway.entity.RoleEntity;

public interface RoleService {

    @Transactional
    Resp<?> getAllRolesDto();

    @Transactional
    Resp<?> getRoleDtoById(Long id);

    @Transactional
    Resp<?> createRole(RoleDto roleDto);

    @Transactional
    Resp<?> updateRole(Long id, RoleDto roleDto);

    @Transactional
    Resp<?> deleteRoleById(Long id);

    RoleEntity getRoleEntityById(Long id);
}

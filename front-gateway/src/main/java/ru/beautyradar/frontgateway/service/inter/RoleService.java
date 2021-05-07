package ru.beautyradar.frontgateway.service.inter;

import ru.beautyradar.frontgateway.dto.RoleDto;
import ru.beautyradar.frontgateway.dto.wrap.Resp;
import ru.beautyradar.frontgateway.entity.RoleEntity;

public interface RoleService {

    Resp<?> getAllRolesDto();

    Resp<?> getRoleDtoById(Long id);

    Resp<?> createRole(RoleDto roleDto);

    Resp<?> updateRole(Long id, RoleDto roleDto);

    Resp<?> deleteRoleById(Long id);

    RoleEntity getRoleEntityById(Long id);
}

package ru.beautyradar.frontgateway.controller;

import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.beautyradar.frontgateway.dto.RoleDto;
import ru.beautyradar.frontgateway.dto.wrap.Resp;
import ru.beautyradar.frontgateway.dto.wrap.swagger.RoleListResponse;
import ru.beautyradar.frontgateway.dto.wrap.swagger.RoleResponse;
import ru.beautyradar.frontgateway.service.inter.RoleService;

@Api(value = "RoleController", tags = {"Role"})
@SwaggerDefinition(tags = {@Tag(name = "Role Controller", description = "Контроллер ролей пользователей")})
@RestController
@RequiredArgsConstructor
@RequestMapping("/role")
@Slf4j
public class RoleController {

    private final RoleService roleService;

    @ApiOperation(value = "Get role list", httpMethod = "GET", notes = "Получение списка ролей", response = RoleListResponse.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success")})
    @GetMapping("/")
    public ResponseEntity<?> getAllRoles() {
        return ResponseEntity.ok(roleService.getAllRolesDto());
    }

    @ApiOperation(value = "Get role by id", httpMethod = "GET", notes = "Получение роли по id", response = RoleResponse.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success")})
    @GetMapping("/{roleId}")
    public ResponseEntity<?> getRoleById(@PathVariable("roleId") Long roleId) {
        return ResponseEntity.ok(roleService.getRoleDtoById(roleId));
    }

    @ApiOperation(value = "Create role", httpMethod = "POST", notes = "Создание роли", response = RoleResponse.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success")})
    @PostMapping("/")
    public ResponseEntity<?> createRole(@RequestBody RoleDto roleDto) {
        return ResponseEntity.ok(roleService.createRole(roleDto));
    }

    @ApiOperation(value = "Update role", httpMethod = "PUT", notes = "Изменение роли", response = RoleResponse.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success")})
    @PutMapping("/{roleId}")
    public ResponseEntity<?> updateRole(@PathVariable("roleId") Long roleId, @RequestBody RoleDto roleDto) {
        return ResponseEntity.ok(roleService.updateRole(roleId, roleDto));
    }

    @ApiOperation(value = "Delete role by id", httpMethod = "DELETE", notes = "Удаление роли по id", response = Resp.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success")})
    @DeleteMapping("/{roleId}")
    public ResponseEntity<?> deleteRoleById(@PathVariable("roleId") Long roleId) {
        return ResponseEntity.ok(roleService.deleteRoleById(roleId));
    }

}

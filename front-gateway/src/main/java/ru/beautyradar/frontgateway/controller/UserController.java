package ru.beautyradar.frontgateway.controller;

import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.beautyradar.frontgateway.dto.UserDto;
import ru.beautyradar.frontgateway.dto.wrap.Resp;
import ru.beautyradar.frontgateway.dto.wrap.swagger.BooleanResponse;
import ru.beautyradar.frontgateway.dto.wrap.swagger.UserListResponse;
import ru.beautyradar.frontgateway.dto.wrap.swagger.UserResponse;
import ru.beautyradar.frontgateway.service.inter.UserService;


@Api(value = "UserController", tags = {"User"})
@SwaggerDefinition(tags = {@Tag(name = "User Controller", description = "Контроллер пользователей")})
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
@Slf4j
public class UserController {

    private final UserService userService;

    @ApiOperation(value = "Get user list", httpMethod = "GET", notes = "Получение списка пользователей", response = UserListResponse.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success")})
    @GetMapping("/")
    public ResponseEntity<?> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsersDto());
    }

    @ApiOperation(value = "Get user by id", httpMethod = "GET", notes = "Получение пользователя по id", response = UserResponse.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success")})
    @GetMapping("/{id}")
    public ResponseEntity<?> getUserByUpn(@PathVariable("id") Long id) {
        return ResponseEntity.ok(userService.getUserDtoById(id));
    }

    @ApiOperation(value = "Get user by UID", httpMethod = "GET", notes = "Получение пользователя по UID", response = UserResponse.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success")})
    @GetMapping("/upn/{upn}")
    public ResponseEntity<?> getUserByUpn(@PathVariable("upn") String upn) {
        return ResponseEntity.ok(userService.getUserDtoByUpn(upn));
    }

    @ApiOperation(value = "Exists user by UID", httpMethod = "GET", notes = "Существует ли пользователь по UID", response = BooleanResponse.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success")})
    @GetMapping("/{upn}/exists")
    public ResponseEntity<?> existsUserByUpn(@PathVariable("upn") String upn) {
        return ResponseEntity.ok(userService.existsUserByUpn(upn));
    }

    @ApiOperation(value = "Create new user", httpMethod = "POST", notes = "Создание пользователя", response = UserResponse.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success")})
    @PostMapping("/")
    public ResponseEntity<?> saveUser(@RequestBody UserDto userDto) {
        return ResponseEntity.ok(userService.saveUser(userDto));
    }

    @ApiOperation(value = "Update user", httpMethod = "PUT", notes = "Изменение пользователя", response = UserResponse.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success")})
    @PutMapping("/{userId}")
    public ResponseEntity<?> updateUserByUpn(@PathVariable("userId") Long userId, @RequestBody UserDto userDto) {
        return ResponseEntity.ok(userService.updateUser(userId, userDto));
    }

    @ApiOperation(value = "Delete user by id", httpMethod = "DELETE", notes = "Удаление пользователя", response = Resp.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success")})
    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUserByUpn(@PathVariable("userId") Long id) {
        return ResponseEntity.ok(userService.deleteUserById(id));
    }

    @ApiOperation(value = "Add role to user", httpMethod = "PUT", notes = "Добавление роли пользователю", response = UserResponse.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success")})
    @PutMapping("/{userId}/role/{roleId}")
    public ResponseEntity<?> addRoleToUser(@PathVariable("userId") Long userId, @PathVariable("roleId") Long roleId) {
        return ResponseEntity.ok(userService.addRoleToUser(userId, roleId));
    }

    @ApiOperation(value = "Remove role from user", httpMethod = "DELETE", notes = "Удаление роли у пользователя", response = UserResponse.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success")})
    @DeleteMapping("/{userId}/role/{roleId}")
    public ResponseEntity<?> removeRoleFromUser(@PathVariable("userId") Long userId, @PathVariable("roleId") Long roleId) {
        return ResponseEntity.ok(userService.removeRoleFromUser(userId, roleId));
    }

}

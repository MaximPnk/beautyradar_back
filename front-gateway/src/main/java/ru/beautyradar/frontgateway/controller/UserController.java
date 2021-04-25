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
    public ResponseEntity<?> getUsers() {
        return ResponseEntity.ok(userService.getUsers());
    }

    @ApiOperation(value = "Get user by UID", httpMethod = "GET", notes = "Получение пользователя по UID", response = UserResponse.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success")})
    @GetMapping("/{upn}")
    public ResponseEntity<?> getUserByUpn(@PathVariable String upn) {
        return ResponseEntity.ok(userService.getUserByUpn(upn));
    }

    @ApiOperation(value = "Exists user by UID", httpMethod = "GET", notes = "Существует ли пользователь по UID", response = BooleanResponse.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success")})
    @GetMapping("/{upn}/exists")
    public ResponseEntity<?> existsUserByUpn(@PathVariable String upn) {
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
    @PutMapping("/")
    public ResponseEntity<?> updateUserByUpn(@RequestBody UserDto userDto) {
        return ResponseEntity.ok(userService.updateUser(userDto));
    }

    @ApiOperation(value = "Delete user by UID", httpMethod = "DELETE", notes = "Удаление пользователя", response = Resp.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success")})
    @DeleteMapping("/{upn}")
    public ResponseEntity<?> deleteUserByUpn(@PathVariable String upn) {
        return ResponseEntity.ok(userService.deleteUserByUpn(upn));
    }

}

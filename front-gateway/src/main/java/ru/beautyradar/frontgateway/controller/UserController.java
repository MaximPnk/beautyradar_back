package ru.beautyradar.frontgateway.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.beautyradar.frontgateway.dto.UserDto;
import ru.beautyradar.frontgateway.service.inter.UserService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
@Slf4j
public class UserController {

    private final UserService userService;

    @GetMapping("/")
    public ResponseEntity<?> getUsers() {
        return ResponseEntity.ok(userService.getUsers());
    }

    @GetMapping("/{upn}")
    public ResponseEntity<?> getUserByUpn(@PathVariable String upn) {
        return ResponseEntity.ok(userService.getUserByUpn(upn));
    }

    @GetMapping("/{upn}/exists")
    public ResponseEntity<?> existsUserByUpn(@PathVariable String upn) {
        return ResponseEntity.ok(userService.existsUserByUpn(upn));
    }

    @PostMapping("/")
    public ResponseEntity<?> saveUser(@RequestBody UserDto userDto) {
        return ResponseEntity.ok(userService.saveUser(userDto));
    }

    @PutMapping("/")
    public ResponseEntity<?> updateUserByUpn(@RequestBody UserDto userDto) {
        return ResponseEntity.ok(userService.updateUser(userDto));
    }

    @DeleteMapping("/{upn}")
    public ResponseEntity<?> deleteUserByUpn(@PathVariable String upn) {
        return ResponseEntity.ok(userService.deleteUserByUpn(upn));
    }

}

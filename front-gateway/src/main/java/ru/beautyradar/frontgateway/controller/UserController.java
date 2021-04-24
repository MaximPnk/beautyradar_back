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

    @GetMapping("/{upn}")
    public ResponseEntity<?> getUserByUpn(@PathVariable String upn) {
        return ResponseEntity.ok(userService.getUserByUpn(upn));
    }

    @PostMapping("/")
    public ResponseEntity<?> saveUser(@RequestBody UserDto userDto) {
        userService.saveUser(userDto);
        return ResponseEntity.ok(null);
    }

}

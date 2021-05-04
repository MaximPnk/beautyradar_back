package ru.beautyradar.uploadservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.beautyradar.uploadservice.service.inter.AvatarService;

@RestController
@RequestMapping("/avatar")
@RequiredArgsConstructor
public class AvatarController {

    private final AvatarService avatarService;

    @PutMapping("/{userId}")
    public Object upload(@RequestParam("file") MultipartFile multipartFile, @PathVariable("userId") Long userId) {
        return avatarService.updateUserAvatar(userId, multipartFile);
    }
}

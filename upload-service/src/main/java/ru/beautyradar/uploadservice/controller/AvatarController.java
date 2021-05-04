package ru.beautyradar.uploadservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.beautyradar.uploadservice.service.inter.AvatarService;
import ru.beautyradar.uploadservice.wrap.Resp;

@RestController
@RequestMapping("/avatar")
@RequiredArgsConstructor
public class AvatarController {

    private final AvatarService avatarService;

    @PutMapping("/{userId}")
    public Resp<?> upload(@RequestParam("file") MultipartFile multipartFile, @PathVariable("userId") Long userId) {
        return avatarService.updateUserAvatar(userId, multipartFile);
    }

    @DeleteMapping("/{userId}")
    public Resp<?> delete(@PathVariable("userId") Long userId) {
        return avatarService.delete(userId);
    }
}

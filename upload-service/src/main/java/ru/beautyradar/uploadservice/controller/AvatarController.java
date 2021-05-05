package ru.beautyradar.uploadservice.controller;

import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.beautyradar.uploadservice.service.inter.AvatarService;
import ru.beautyradar.uploadservice.wrap.Resp;

@Api(value = "AvatarController", tags = {"Avatar"})
@SwaggerDefinition(tags = {@Tag(name = "Avatar Controller", description = "Контроллер аватаров пользователей")})
@RestController
@RequestMapping("/avatar")
@RequiredArgsConstructor
public class AvatarController {

    private final AvatarService avatarService;

    @ApiOperation(value = "Upload avatar", httpMethod = "PUT", notes = "Загрузка аватара пользователя", response = Resp.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success")})
    @PutMapping("/{userId}")
    public Resp<?> upload(@RequestParam("file") MultipartFile multipartFile, @PathVariable("userId") Long userId) {
        return avatarService.updateUserAvatar(userId, multipartFile);
    }

    @ApiOperation(value = "Delete avatar", httpMethod = "DELETE", notes = "Удаление аватара пользователя", response = Resp.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success")})
    @DeleteMapping("/{userId}")
    public Resp<?> delete(@PathVariable("userId") Long userId) {
        return avatarService.delete(userId);
    }
}

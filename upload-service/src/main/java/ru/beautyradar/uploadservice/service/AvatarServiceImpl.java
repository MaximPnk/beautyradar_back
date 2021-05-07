package ru.beautyradar.uploadservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.beautyradar.uploadservice.entity.UserEntity;
import ru.beautyradar.uploadservice.service.inter.AvatarService;
import ru.beautyradar.uploadservice.service.inter.FileService;
import ru.beautyradar.uploadservice.service.inter.UserService;
import ru.beautyradar.uploadservice.wrap.Resp;
import ru.beautyradar.uploadservice.wrap.RespBuilder;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AvatarServiceImpl implements AvatarService {

    private final FileService fileService;
    private final UserService userService;

    @Override
    @Transactional
    public Resp<?> updateUserAvatar(Long userId, MultipartFile multipartFile) {
        try {
            UserEntity user = userService.findUserById(userId);
            fileService.delete(user.getImg());
            String url = fileService.upload(multipartFile);
            user.setImg(url);
            return new RespBuilder<>().setCode(0).setBody(url).build();
        } catch (Exception e) {
            log.info(e.getMessage());
            return new RespBuilder<>().setCode(1).setMessage(e.getMessage()).build();
        }
    }

    @Override
    @Transactional
    public Resp<?> delete(Long userId) {
        try {
            UserEntity user = userService.findUserById(userId);
            user.setImg(null);
            fileService.delete(user.getImg());
            return new RespBuilder<>().setCode(0).build();
        } catch (Exception e) {
            log.info(e.getMessage());
            return new RespBuilder<>().setCode(1).setMessage(e.getMessage()).build();
        }
    }
}

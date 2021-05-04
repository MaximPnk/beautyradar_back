package ru.beautyradar.uploadservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.beautyradar.uploadservice.entity.UserEntity;
import ru.beautyradar.uploadservice.service.inter.AvatarService;
import ru.beautyradar.uploadservice.service.inter.FileService;
import ru.beautyradar.uploadservice.service.inter.UserService;
import ru.beautyradar.uploadservice.wrap.InitResp;
import ru.beautyradar.uploadservice.wrap.Resp;

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

            Resp<?> userResp = userService.findUserById(userId);
            if (userResp.getCode() != 0) {
                return userResp;
            }
            UserEntity user = (UserEntity) userResp.getBody();
            if (user == null) {
                return new InitResp<>().exc(1, "User not found");
            }

            if (user.getImg() != null) {
                Resp<?> deleteResp = fileService.delete(user.getImg());
                if (deleteResp.getCode() != 0) {
                    return deleteResp;
                }
            }

            Resp<?> uploadResp = fileService.upload(multipartFile);
            String url = uploadResp.getBody().toString();
            if (uploadResp.getCode() != 0) {
                return uploadResp;
            }

            user.setImg(url);
            return new InitResp<>().ok(url);
        } catch (Exception e) {
            log.info(e.getMessage());
            return new InitResp<>().exc(1, e.getMessage());
        }
    }

    @Override
    @Transactional
    public Resp<?> delete(Long userId) {
        try {

            Resp<?> userResp = userService.findUserById(userId);
            if (userResp.getCode() != 0) {
                return userResp;
            }
            UserEntity user = (UserEntity) userResp.getBody();
            if (user == null) {
                return new InitResp<>().exc(1, "User not found");
            }

            user.setImg(null);
            Resp<?> deleteResp = fileService.delete(user.getImg());
            if (deleteResp.getCode() != 0) {
                return deleteResp;
            }

            return new InitResp<>().ok(null);
        } catch (Exception e) {
            log.info(e.getMessage());
            return new InitResp<>().exc(1, e.getMessage());
        }
    }
}

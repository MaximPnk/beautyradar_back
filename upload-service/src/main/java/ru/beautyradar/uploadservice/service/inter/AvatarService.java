package ru.beautyradar.uploadservice.service.inter;

import org.springframework.web.multipart.MultipartFile;
import ru.beautyradar.uploadservice.wrap.Resp;

public interface AvatarService {

    Resp<?> updateUserAvatar(Long userId, MultipartFile multipartFile);

    Resp<?> delete(Long userId);
}

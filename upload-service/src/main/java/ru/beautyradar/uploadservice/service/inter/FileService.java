package ru.beautyradar.uploadservice.service.inter;

import org.springframework.web.multipart.MultipartFile;
import ru.beautyradar.uploadservice.wrap.Resp;

public interface FileService {

    Resp<?> upload(MultipartFile multipartFile);

    Resp<?> delete(String fileUrl);
}

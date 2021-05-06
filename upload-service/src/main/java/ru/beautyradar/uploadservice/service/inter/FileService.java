package ru.beautyradar.uploadservice.service.inter;

import org.springframework.web.multipart.MultipartFile;
import ru.beautyradar.uploadservice.wrap.Resp;

import java.io.IOException;

public interface FileService {

    String upload(MultipartFile multipartFile) throws IOException;

    void delete(String fileUrl);
}

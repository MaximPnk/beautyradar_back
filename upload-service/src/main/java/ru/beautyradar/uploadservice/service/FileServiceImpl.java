package ru.beautyradar.uploadservice.service;

import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.beautyradar.uploadservice.service.inter.FileService;
import ru.beautyradar.uploadservice.wrap.InitResp;
import ru.beautyradar.uploadservice.wrap.Resp;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class FileServiceImpl implements FileService {

    @Value("${secret.bucket.name}")
    private String bucketName;

    private final String DOWNLOAD_URL = "https://firebasestorage.googleapis.com/v0/b/%s/o/%s?alt=media";
    private final Storage storage;

    @Override
    public Resp<?> upload(MultipartFile multipartFile) {
        try {
            String fileName = multipartFile.getOriginalFilename();
            fileName = UUID.randomUUID().toString().concat(getExtension(fileName));

            File file = convertToFile(multipartFile, fileName);
            String tmpUrl = uploadFile(file, fileName);
            file.delete();
            return new InitResp<>().ok(tmpUrl);
        } catch (Exception e) {
            log.info(e.getMessage());
            return new InitResp<>().exc(1, e.getMessage());
        }
    }

    @Override
    public Resp<?> delete(String fileUrl) {
        try {
            String fileName = fileUrl.substring(fileUrl.lastIndexOf("/") + 1, fileUrl.lastIndexOf("?"));
            System.out.println(fileUrl);
            System.out.println(fileName);
            BlobId img = BlobId.of(bucketName, fileName);
            storage.delete(img);
            return new InitResp<>().ok(null);
        } catch (Exception e) {
            log.info(e.getMessage());
            return new InitResp<>().exc(1, e.getMessage());
        }
    }

    private String uploadFile(File file, String fileName) throws IOException {
        BlobId blobId = BlobId.of(bucketName, fileName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType("media").build();
        storage.create(blobInfo, Files.readAllBytes(file.toPath()));
        return String.format(DOWNLOAD_URL, URLEncoder.encode(bucketName, "UTF-8"), URLEncoder.encode(fileName, "UTF-8"));
    }

    private String getExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf("."));
    }

    private File convertToFile(MultipartFile multipartFile, String fileName) throws IOException {
        File tempFile = new File(fileName);
        try (FileOutputStream fos = new FileOutputStream(tempFile)) {
            fos.write(multipartFile.getBytes());
        }
        return tempFile;
    }
}

package ru.beautyradar.uploadservice.config;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.beautyradar.uploadservice.service.inter.FileService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/test")
public class TestController {

    private final FileService fileService;

    @DeleteMapping("/{id}")
    public void ttt(@PathVariable("id") String id) {
        System.out.println(id);
        fileService.delete(id);
    }
}

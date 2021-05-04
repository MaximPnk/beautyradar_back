package ru.beautyradar.frontgateway.service.inter;

import ru.beautyradar.frontgateway.dto.UserDto;
import ru.beautyradar.frontgateway.dto.wrap.Resp;

public interface GalleryService {

    Resp<?> findPhoto(Long id);

}

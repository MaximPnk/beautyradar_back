package ru.beautyradar.frontgateway.service.inter;

import ru.beautyradar.frontgateway.dto.wrap.Resp;

public interface FavoriteService {

    Resp<?> getAllFavoriteMastersByClientId(Long id);

    Resp<?> addFavoriteMaster(Long clientId, Long masterId);

    Resp<?> removeFavoriteMaster(Long clientId, Long masterId);

}

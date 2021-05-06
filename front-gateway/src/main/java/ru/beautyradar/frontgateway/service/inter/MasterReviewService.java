package ru.beautyradar.frontgateway.service.inter;

import ru.beautyradar.frontgateway.dto.MasterReviewDto;
import ru.beautyradar.frontgateway.dto.wrap.Resp;

public interface MasterReviewService {
    
    Resp<?> getAllMasterReviewsDtoByMasterId(Long masterId);

    Resp<?> getAllMasterReviewsDtoByClientId(Long clientId);

    Resp<?> getMasterReviewDtoById(Long id);

    Resp<?> createMasterReview(MasterReviewDto masterReviewDto);

    Resp<?> updateMasterReview(Long id, MasterReviewDto masterReviewDto);

    Resp<?> deleteMasterReviewById(Long id);
}

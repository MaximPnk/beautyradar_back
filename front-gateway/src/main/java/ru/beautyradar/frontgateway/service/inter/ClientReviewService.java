package ru.beautyradar.frontgateway.service.inter;

import ru.beautyradar.frontgateway.dto.ClientReviewDto;
import ru.beautyradar.frontgateway.dto.wrap.Resp;

public interface ClientReviewService {

    Resp<?> getAllClientReviewsDtoByMasterId(Long masterId);

    Resp<?> getAllClientReviewsDtoByClientId(Long clientId);

    Resp<?> getClientReviewDtoById(Long id);

    Resp<?> createClientReview(ClientReviewDto clientReviewDto);

    Resp<?> updateClientReview(Long id, ClientReviewDto clientReviewDto);

    Resp<?> deleteClientReviewById(Long id);
}

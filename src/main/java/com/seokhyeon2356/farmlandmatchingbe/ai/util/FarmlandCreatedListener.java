package com.seokhyeon2356.farmlandmatchingbe.ai.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.seokhyeon2356.farmlandmatchingbe.ai.AiClient;
import com.seokhyeon2356.farmlandmatchingbe.ai.dto.*;
import com.seokhyeon2356.farmlandmatchingbe.ai.entity.AiMatchScore;
import com.seokhyeon2356.farmlandmatchingbe.ai.repo.AiMatchScoreRepo;
import com.seokhyeon2356.farmlandmatchingbe.buyer.entity.Buyer;
import com.seokhyeon2356.farmlandmatchingbe.buyer.repository.BuyerRepository;
import com.seokhyeon2356.farmlandmatchingbe.buyer.trustProfile.entity.TrustProfile;
import com.seokhyeon2356.farmlandmatchingbe.buyer.trustProfile.repository.TrustProfileRepository;
import com.seokhyeon2356.farmlandmatchingbe.farmland.entity.Farmland;
import com.seokhyeon2356.farmlandmatchingbe.farmland.repository.FarmlandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;
import java.util.List;

@Component @RequiredArgsConstructor
public class FarmlandCreatedListener {
    private static final long FIXED_BUYER_ID = 1L;

    private final BuyerRepository buyerRepository;
    private final FarmlandRepository farmlandRepository;
    private final TrustProfileRepository trustProfileRepository;
    private final AiMatchScoreRepo aiMatchScoreRepo;
    private final AiClient aiClient;
    private final ObjectMapper om = new ObjectMapper();

    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void handle(FarmlandCreatedEvent e) {
        Farmland f = farmlandRepository.findById(e.farmlandId()).orElse(null);
        Buyer buyer = buyerRepository.findById(FIXED_BUYER_ID).orElse(null);
        TrustProfile tp = trustProfileRepository.findById(1L).orElse(null);
        if (f == null || buyer == null) return;

        BuyerDto buyerDto = AiMapper.toBuyerDTO(buyer, tp);
        FarmlandFeatureDto fdto = AiMapper.toFeature(f);
        MatchRequestDto req = new MatchRequestDto(buyerDto, List.of(fdto));

        // 1) /train 호출 (응답 저장 X)
        try { aiClient.train(req); } catch (Exception ignore) {}

        // 2) /match 호출 → 점수 획득
        AiScoreResponse res = aiClient.match(req);
        if (res == null || res.getResults() == null || res.getResults().isEmpty()) return;
        AiScoreItem item = res.getResults().get(0);

        // 3) (farmland, buyer=1) 업서트 저장
        AiMatchScore row = aiMatchScoreRepo.findByLandAiMatchScoreAndBuyerAiMatchScore(f, buyer)
                .orElseGet(AiMatchScore::new);
        row.setLandAiMatchScore(f);
        row.setBuyerAiMatchScore(buyer);
        row.setAiMatchScore(item.getAiMatchScore());
        try { row.setAiScoreDetail(om.writeValueAsString(item.getAiScoreDetail())); }
        catch (Exception ex) { row.setAiScoreDetail("{}"); }
        aiMatchScoreRepo.save(row);
    }
}

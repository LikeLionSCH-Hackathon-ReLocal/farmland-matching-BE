package com.seokhyeon2356.farmlandmatchingbe.ai;

import com.seokhyeon2356.farmlandmatchingbe.ai.dto.AiScoreResponse;
import com.seokhyeon2356.farmlandmatchingbe.ai.dto.MatchRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service("aiScoreClient")
@RequiredArgsConstructor
public class AiClient {
    private final WebClient aiClient;

    // /train: 같은 바디로 호출하지만 DB 저장은 안 함 (성공/실패만)
    public void train(MatchRequestDto req) {
        aiClient.post().uri("/train")
                .bodyValue(req)
                .retrieve()
                .toBodilessEntity()
                .block();
    }

    // /match: 점수 응답 받기
    public AiScoreResponse match(MatchRequestDto req) {
        return aiClient.post().uri("/match")
                .bodyValue(req)
                .retrieve()
                .bodyToMono(AiScoreResponse.class)
                .block();
    }
}
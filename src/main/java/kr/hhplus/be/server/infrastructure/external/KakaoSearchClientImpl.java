package kr.hhplus.be.server.infrastructure.external;

import kr.hhplus.be.server.domain.search.dto.SearchResult;
import kr.hhplus.be.server.infrastructure.external.dto.KakaoSearchResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class KakaoSearchClientImpl implements KakaoSearchClient {

    private final RestClient.Builder restClientBuilder;

    @Value("${kakao.rest-api-key}")
    private String kakaoApiKey;

    @Override
    public List<SearchResult> search(String keyword) {
        RestClient client = restClientBuilder.baseUrl("https://dapi.kakao.com").build();

        KakaoSearchResponse response = client.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/v2/local/search/keyword.json")
                        .queryParam("query", keyword)
                        .build())
                .headers(headers -> {
                    headers.set("Authorization", "KakaoAK " + kakaoApiKey);
                })
                .retrieve()
                .body(KakaoSearchResponse.class);

        return convertToSearchResult(response);
    }

    private List<SearchResult> convertToSearchResult(KakaoSearchResponse response) {
        if (response == null || response.getDocuments() == null) return List.of();
        return response.getDocuments().stream()
                .map(doc -> SearchResult.builder()
                        .title(doc.getPlaceName())
                        .address(doc.getAddressName())
                        .roadAddress(doc.getRoadAddressName())
                        .category(doc.getCategoryName())
                        .latitude(Double.parseDouble(doc.getY()))
                        .longitude(Double.parseDouble(doc.getX()))
                        .build())
                .toList();
    }
}

package kr.hhplus.be.server.infrastructure.external;

import kr.hhplus.be.server.domain.search.dto.SearchResult;
import kr.hhplus.be.server.infrastructure.external.dto.NaverPlaceDTO;
import kr.hhplus.be.server.infrastructure.external.dto.NaverSearchResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;

import java.util.Collections;
import java.util.List;

@Component
@RequiredArgsConstructor
public class NaverSearchClientImpl implements NaverSearchClient {

    private final RestTemplate restTemplate = new RestTemplate(); // TODO: 추후 Bean 주입으로 리팩토링

    @Value("${naver.api.client-id}")
    private String clientId;

    @Value("${naver.api.client-secret}")
    private String clientSecret;

    @Value("${naver.api.local-search-url}")
    private String apiUrl;

    @Override
    public List<SearchResult> searchRestaurants(String keyword, String location) {
        String query = keyword + " " + location;

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Naver-Client-Id", clientId);
        headers.set("X-Naver-Client-Secret", clientSecret);
        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

        String requestUrl = apiUrl + "?query=" + query;

        try {
            ResponseEntity<NaverSearchResponse> response = restTemplate.exchange(
                    requestUrl,
                    HttpMethod.GET,
                    requestEntity,
                    NaverSearchResponse.class
            );

            List<NaverPlaceDTO> items = response.getBody().getItems();

            return items.stream()
                    .map(item -> SearchResult.builder()
                            .title(item.getTitle())
                            .category(item.getCategory())
                            .address(item.getAddress())
                            .roadAddress(item.getRoadAddress())
                            .latitude(parseOrZero(item.getMapy()))
                            .longitude(parseOrZero(item.getMapx()))
                            .build())
                    .toList();

        } catch (Exception e) {
            System.err.println("Naver API 호출 실패: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    private double parseOrZero(String value) {
        try {
            return Double.parseDouble(value);
        } catch (Exception e) {
            return 0.0;
        }
    }
}

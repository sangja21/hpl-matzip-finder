package kr.hhplus.be.server.search.service;

import kr.hhplus.be.server.search.dto.NaverPlaceDTO;
import kr.hhplus.be.server.search.dto.NaverSearchResponse;
import kr.hhplus.be.server.search.dto.SearchResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;

@Component
@RequiredArgsConstructor
public class NaverSearchClientImpl implements NaverSearchClient {

    private final RestTemplate restTemplate = new RestTemplate();  // 임시 생성

    @Value("${naver.api.client-id}")
    private String clientId;

    @Value("${naver.api.client-secret}")
    private String clientSecret;

    @Value("${naver.api.local-search-url}")
    private String apiUrl;

    @Override
    public List<SearchResponseDTO> searchRestaurants(String keyword, String location) {
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
                    .map(item -> SearchResponseDTO.builder()
                            .title(item.getTitle())
                            .category(item.getCategory())
                            .address(item.getAddress())
                            .roadAddress(item.getRoadAddress())
                            .build())
                    .toList();

        } catch (Exception e) {
            System.err.println("Naver API 호출 실패: " + e.getMessage());
            return Collections.emptyList();
        }
    }

}

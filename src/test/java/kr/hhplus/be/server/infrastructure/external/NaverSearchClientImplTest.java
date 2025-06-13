package kr.hhplus.be.server.infrastructure.external;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import kr.hhplus.be.server.domain.search.dto.SearchResult;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class NaverSearchClientImplTest {

    private MockWebServer mockWebServer;
    private NaverSearchClientImpl client;

    @BeforeEach
    void setUp() throws IOException {
        mockWebServer = new MockWebServer();
        mockWebServer.start();

        String baseUrl = mockWebServer.url("/").toString();

        // baseUrl을 환경변수 대체로 주입
        client = new NaverSearchClientImpl(
                new RestTemplate(),
                "dummyClientId",
                "dummyClientSecret",
                baseUrl
        );
    }

    @AfterEach
    void tearDown() throws IOException {
        mockWebServer.shutdown();
    }

    @Test
    void 네이버API_응답을_정상적으로_받아오는지_검증한다() {
        // given
        String mockBody = """
            {
              "items": [
                {
                  "title": "곱창고",
                  "category": "음식점",
                  "address": "서울시 강남구",
                  "roadAddress": "서울시 강남구 테헤란로",
                  "mapx": "127.123",
                  "mapy": "37.456"
                }
              ]
            }
        """;

        mockWebServer.enqueue(new MockResponse()
                .setBody(mockBody)
                .addHeader("Content-Type", "application/json"));

        // when
        List<SearchResult> results = client.searchRestaurants("곱창", "강남");

        // then
        assertThat(results).hasSize(1);
        SearchResult result = results.get(0);
        assertThat(result.getTitle()).contains("곱창고");
        assertThat(result.getCategory()).isEqualTo("음식점");
        assertThat(result.getAddress()).contains("강남구");
        assertThat(result.getRoadAddress()).contains("테헤란로");
        assertThat(result.getLatitude()).isEqualTo(37.456);
        assertThat(result.getLongitude()).isEqualTo(127.123);
    }
}

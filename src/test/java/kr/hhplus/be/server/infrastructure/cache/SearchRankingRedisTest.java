package kr.hhplus.be.server.infrastructure.cache;

import kr.hhplus.be.server.application.search.port.SearchKeywordRecorder;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import static org.mockito.Mockito.*;

@SpringBootTest
@Import(SearchRankingRedisTest.MockRedisConfig.class)
public class SearchRankingRedisTest {

    @Configuration
    static class MockRedisConfig {
        @Bean
        public SearchRankingRedis searchRankingRedis() {
            return mock(SearchRankingRedis.class);
        }
    }

    @Autowired
    private SearchRankingRedis searchRankingRedis;  // ✅ 필드로 주입

    @Test
    void 키워드를_레디스에_기록한다() {
        // given
        String keyword = "떡볶이";

        // when
        searchRankingRedis.increaseScore(keyword);

        // then
        verify(searchRankingRedis).increaseScore(keyword);
    }
}

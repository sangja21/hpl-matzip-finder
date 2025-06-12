// 📁 src/test/java/kr/hhplus/be/server/application/SearchServiceTest.java
package kr.hhplus.be.server.application;

import kr.hhplus.be.server.application.search.service.SearchService;
import kr.hhplus.be.server.infrastructure.external.NaverSearchClient;
import kr.hhplus.be.server.domain.search.SearchHistoryRepository;
import kr.hhplus.be.server.infrastructure.cache.SearchRankingRedis;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SearchServiceTest {

    @InjectMocks
    private SearchService searchService;

    @Mock
    private NaverSearchClient naverSearchClient;

    @Mock
    private SearchHistoryRepository searchHistoryRepository;

    @Mock
    private SearchRankingRedis searchRankingRedis;

    @Test
    public void 맛집검색시_네이버API를_호출한다() {
        // given
        String keyword = "파스타";
        String location = "서울역";
        when(naverSearchClient.searchRestaurants(anyString(), anyString()))
                .thenReturn(List.of());

        // when
        searchService.searchRestaurants(keyword, location);

        // then
        verify(naverSearchClient).searchRestaurants(keyword, location);
    }

}

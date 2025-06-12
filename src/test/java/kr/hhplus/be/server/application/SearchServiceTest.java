package kr.hhplus.be.server.application;

import kr.hhplus.be.server.application.search.port.SearchKeywordRecorder;
import kr.hhplus.be.server.application.search.service.SearchClientManager;
import kr.hhplus.be.server.application.search.service.SearchService;
import kr.hhplus.be.server.domain.search.dto.SearchResult;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SearchServiceTest {

    @Mock
    private SearchClientManager searchClientManager;

    @Mock
    private SearchKeywordRecorder searchKeywordRecorder;

    @InjectMocks
    private SearchService searchService;

    @Test
    public void 맛집검색시_전략클라이언트를_호출한다() {
        // given
        String keyword = "파스타";
        String location = "서울역";

        when(searchClientManager.search(keyword, location))
                .thenReturn(List.of(SearchResult.builder()
                        .title("파스타집")
                        .category("양식")
                        .address("서울역 근처")
                        .roadAddress("서울특별시 중구")
                        .latitude(37.556)
                        .longitude(126.972)
                        .build()));

        // when
        searchService.searchRestaurants(keyword, location);

        // then
        verify(searchClientManager).search(keyword, location);
        verify(searchKeywordRecorder).record(keyword, location);
    }
}

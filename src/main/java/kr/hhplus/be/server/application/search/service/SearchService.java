package kr.hhplus.be.server.application.search.service;

import kr.hhplus.be.server.api.common.dto.PageResponseDTO;
import kr.hhplus.be.server.application.search.port.SearchKeywordRecorder;
import kr.hhplus.be.server.domain.search.dto.SearchResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchService {

    private final SearchClientManager searchClientManager;
    private final SearchKeywordRecorder searchKeywordRecorder;

    public PageResponseDTO<SearchResult> searchRestaurants(String keyword, String location) {
        List<SearchResult> items = searchClientManager.search(keyword, location);

//        if (!items.isEmpty()) {
            searchKeywordRecorder.record(keyword, location);
//        }

        return PageResponseDTO.<SearchResult>builder()
                .items(items)
                .totalItems(items.size())
                .totalPages(1)
                .currentPage(1)
                .itemsPerPage(items.size())
                .hasPreviousPage(false)
                .hasNextPage(false)
                .build();
    }
}

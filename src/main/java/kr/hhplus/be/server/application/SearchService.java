package kr.hhplus.be.server.application;

import kr.hhplus.be.server.api.common.dto.PageResponseDTO;
import kr.hhplus.be.server.api.search.dto.SearchResponseDTO;
import kr.hhplus.be.server.domain.search.NaverSearchClient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchService {

    private final NaverSearchClient naverSearchClient;

    public SearchService(NaverSearchClient naverSearchClient) {
        this.naverSearchClient = naverSearchClient;
    }

    public PageResponseDTO<SearchResponseDTO> searchRestaurants(String keyword, String location) {
        List<SearchResponseDTO> items = naverSearchClient.searchRestaurants(keyword, location);

        return PageResponseDTO.<SearchResponseDTO>builder()
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


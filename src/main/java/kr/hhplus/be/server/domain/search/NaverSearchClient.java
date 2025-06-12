package kr.hhplus.be.server.domain.search;

import kr.hhplus.be.server.api.search.dto.SearchResponseDTO;

import java.util.List;

public interface NaverSearchClient {
    List<SearchResponseDTO> searchRestaurants(String keyword, String location);
}

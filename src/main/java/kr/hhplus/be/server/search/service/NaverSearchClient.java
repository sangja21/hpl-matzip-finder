package kr.hhplus.be.server.search.service;

import kr.hhplus.be.server.search.dto.SearchResponseDTO;

import java.util.List;

public interface NaverSearchClient {
    List<SearchResponseDTO> searchRestaurants(String keyword, String location);
}

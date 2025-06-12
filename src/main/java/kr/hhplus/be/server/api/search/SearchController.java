package kr.hhplus.be.server.api.search;

import kr.hhplus.be.server.api.common.dto.ResponseDTO;
import kr.hhplus.be.server.api.common.dto.PageResponseDTO;
import kr.hhplus.be.server.api.search.dto.SearchResponseDTO;
import kr.hhplus.be.server.application.search.service.SearchService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/search")
public class SearchController {

    private final SearchService searchService;

    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping
    public ResponseDTO search(
            @RequestParam String keyword,
            @RequestParam String location
    ) {
        PageResponseDTO<SearchResponseDTO> page = searchService.searchRestaurants(keyword, location);
        return ResponseDTO.builder()
                .status(200)
                .message("success")
                .data(page)
                .build();
    }
}

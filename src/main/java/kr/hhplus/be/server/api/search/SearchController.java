package kr.hhplus.be.server.api.search;

import kr.hhplus.be.server.application.SearchService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/search")
public class SearchController {

    private final SearchService searchService;

    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping
    public List<Object> search(
            @RequestParam String keyword,
            @RequestParam String location
    ) {
        return searchService.searchRestaurants(keyword, location);
    }
}


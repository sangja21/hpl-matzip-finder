package kr.hhplus.be.server.domain.search.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SearchResult {

    private final String title;
    private final String address;
    private final String roadAddress;
    private final String category;
    private final double latitude;
    private final double longitude;

}

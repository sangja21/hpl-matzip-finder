package kr.hhplus.be.server.search.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SearchResponseDTO {
    private String title;
    private String category;
    private String address;
    private String roadAddress;
}

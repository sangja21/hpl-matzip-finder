package kr.hhplus.be.server.infrastructure.external.dto;

import lombok.Getter;
import java.util.List;

@Getter
public class NaverSearchResponse {
    private String lastBuildDate;
    private int total;
    private int start;
    private int display;
    private List<NaverPlaceDTO> items;
}

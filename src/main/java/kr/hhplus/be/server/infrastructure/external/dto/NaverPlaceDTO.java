package kr.hhplus.be.server.infrastructure.external.dto;

import lombok.Getter;

@Getter
public class NaverPlaceDTO {
    private String title;
    private String link;
    private String category;
    private String description;
    private String telephone;
    private String address;
    private String roadAddress;
    private String mapx;
    private String mapy;
}

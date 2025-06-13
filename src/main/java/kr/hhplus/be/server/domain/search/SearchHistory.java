package kr.hhplus.be.server.domain.search;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@Builder
public class SearchHistory {

    private final Long id;
    private final String keyword;
    private final String location;
    private final LocalDateTime searchedAt;
}

package kr.hhplus.be.server.infrastructure.persistence.search;

import kr.hhplus.be.server.domain.search.SearchHistory;

public class SearchHistoryMapper {
    public static SearchHistoryEntity toEntity(SearchHistory domain) {
        return SearchHistoryEntity.builder()
                .keyword(domain.getKeyword())
                .location(domain.getLocation())
                .searchedAt(domain.getSearchedAt())
                .build();
    }

    public static SearchHistory toDomain(SearchHistoryEntity entity) {
        return new SearchHistory(
                entity.getId(),
                entity.getKeyword(),
                entity.getLocation(),
                entity.getSearchedAt()
        );
    }
}


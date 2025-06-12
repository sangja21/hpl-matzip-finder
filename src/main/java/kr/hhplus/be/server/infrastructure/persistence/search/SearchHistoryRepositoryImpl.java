package kr.hhplus.be.server.infrastructure.persistence.search;

import kr.hhplus.be.server.domain.search.SearchHistory;
import kr.hhplus.be.server.domain.search.SearchHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class SearchHistoryRepositoryImpl implements SearchHistoryRepository {

    private final JpaSearchHistoryRepository jpaSearchHistoryRepository;

    @Override
    public void save(SearchHistory searchHistory) {
        SearchHistoryEntity entity = SearchHistoryMapper.toEntity(searchHistory);
        jpaSearchHistoryRepository.save(entity);
    }

}

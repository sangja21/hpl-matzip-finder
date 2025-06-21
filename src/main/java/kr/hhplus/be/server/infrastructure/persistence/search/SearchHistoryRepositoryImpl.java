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

    // ✅ 테스트용 메서드: 모든 데이터 삭제
    @Override
    public void deleteAll() {
        jpaSearchHistoryRepository.deleteAll();
    }

    // ✅ 테스트용 메서드: 특정 키워드의 검색 기록 수
    @Override
    public long countByKeyword(String keyword) {
        return jpaSearchHistoryRepository.countByKeyword(keyword);
    }

}

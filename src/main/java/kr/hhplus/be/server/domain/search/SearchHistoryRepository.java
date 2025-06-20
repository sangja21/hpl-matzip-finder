package kr.hhplus.be.server.domain.search;


public interface SearchHistoryRepository {
    void save(SearchHistory searchHistory);

    // ✅ 테스트를 위한 메서드
    void deleteAll();

    long countByKeyword(String keyword);
}
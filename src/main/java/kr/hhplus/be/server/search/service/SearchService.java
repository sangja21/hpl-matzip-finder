// 임시 선언들: 실제 구현 없이!
package kr.hhplus.be.server.search.service;

import java.util.List;

public class SearchService {
    public void searchRestaurants(String keyword, String location) {}
}

interface NaverSearchClient {
    List<Object> searchRestaurants(String keyword, String location);
}

interface SearchHistoryRepository {
    void save(Object entity);
}

interface SearchRankingRedis {
    void increaseScore(String keyword);
}

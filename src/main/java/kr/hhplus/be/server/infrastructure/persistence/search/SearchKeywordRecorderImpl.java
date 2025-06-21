package kr.hhplus.be.server.infrastructure.persistence.search;

import kr.hhplus.be.server.application.search.port.SearchKeywordRecorder;
import kr.hhplus.be.server.domain.search.SearchHistory;
import kr.hhplus.be.server.domain.search.SearchHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class SearchKeywordRecorderImpl implements SearchKeywordRecorder {

    private final RedisTemplate<String, String> redisTemplate;
    private final SearchHistoryRepository searchHistoryRepository;

    @Override
    public void record(String keyword, String location) {
        // Redis에 저장
        String redisKey = "search:" + keyword;
        redisTemplate.opsForValue().set(redisKey, location);

        // 도메인 객체 생성
        SearchHistory searchHistory = SearchHistory.builder()
                .id(null)  // 생성 시점에 id는 null
                .keyword(keyword)
                .location(location)
                .searchedAt(LocalDateTime.now())
                .build();

        // 저장 (Repository 내부에서 toEntity 변환)
        searchHistoryRepository.save(searchHistory);
    }
}

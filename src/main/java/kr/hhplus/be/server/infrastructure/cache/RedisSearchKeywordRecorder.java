package kr.hhplus.be.server.infrastructure.cache;

import kr.hhplus.be.server.application.search.port.SearchKeywordRecorder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.data.redis.core.StringRedisTemplate;
import lombok.extern.slf4j.Slf4j;


@Component
@RequiredArgsConstructor
@Slf4j
public class RedisSearchKeywordRecorder implements SearchKeywordRecorder {

    private final StringRedisTemplate redisTemplate;

    @Override
    public void record(String keyword, String location) {
        String key = "popular_keywords:" + location;
        log.info("🔍 [RedisSearchKeywordRecorder] Recording keyword: '{}' at location: '{}'", keyword, location); // 로그 찍기

        redisTemplate.opsForZSet().incrementScore(key, keyword, 1.0);
    }
}

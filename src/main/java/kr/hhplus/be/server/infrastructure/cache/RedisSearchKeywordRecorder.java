package kr.hhplus.be.server.infrastructure.cache;

import kr.hhplus.be.server.application.search.port.SearchKeywordRecorder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.data.redis.core.StringRedisTemplate;


@Component
@RequiredArgsConstructor
public class RedisSearchKeywordRecorder implements SearchKeywordRecorder {

    private final StringRedisTemplate redisTemplate;

    @Override
    public void record(String keyword, String location) {
        String key = "popular_keywords:" + location;
        redisTemplate.opsForZSet().incrementScore(key, keyword, 1.0);
    }
}

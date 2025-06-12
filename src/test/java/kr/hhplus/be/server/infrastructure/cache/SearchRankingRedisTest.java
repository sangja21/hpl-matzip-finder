package kr.hhplus.be.server.infrastructure.cache;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SearchRankingRedisTest {

    @Autowired
    private SearchRankingRedis searchRankingRedis;

    @Test
    public void 검색어_점수를_증가시킨다() {
        String keyword = "떡볶이";
        searchRankingRedis.increaseScore(keyword);
        // TODO: redisTemplate으로 직접 스코어 검증
    }
}


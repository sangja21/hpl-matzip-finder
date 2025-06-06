package kr.hhplus.be.server.search.redis;

public interface SearchRankingRedis {
    void increaseScore(String keyword);
}

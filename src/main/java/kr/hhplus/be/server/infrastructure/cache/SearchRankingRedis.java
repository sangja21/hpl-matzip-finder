package kr.hhplus.be.server.infrastructure.cache;

public interface SearchRankingRedis {
    void increaseScore(String keyword);
}

package kr.hhplus.be.server.application.search.port;

import kr.hhplus.be.server.domain.search.dto.SearchResult;

import java.util.List;

// 외부 전략 인터페이스
public interface SearchClient {
    List<SearchResult> search(String keyword, String location);
}

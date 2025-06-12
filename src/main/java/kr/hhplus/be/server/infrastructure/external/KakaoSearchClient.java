package kr.hhplus.be.server.infrastructure.external;

import kr.hhplus.be.server.domain.search.dto.SearchResult;
import java.util.List;

public interface KakaoSearchClient {
    List<SearchResult> search(String keyword);
}

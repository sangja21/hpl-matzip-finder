package kr.hhplus.be.server.infrastructure.persistence.search;

import kr.hhplus.be.server.domain.search.SearchHistory;
import kr.hhplus.be.server.domain.search.SearchHistoryRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.time.LocalDateTime;

@DataJpaTest
@Import(SearchHistoryRepositoryImpl.class)
public class SearchHistoryRepositoryImplTest {

    @Autowired
    private SearchHistoryRepository searchHistoryRepository;

    @Test
    public void 검색기록을_DB에_저장할_수_있다() {
        SearchHistory history = new SearchHistory(null, "떡볶이", "서울역", LocalDateTime.now());
        searchHistoryRepository.save(history);
        // TODO: 실제 저장 여부 확인
    }
}

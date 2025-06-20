package kr.hhplus.be.server.api.search;

import kr.hhplus.be.server.application.search.service.SearchService;
import kr.hhplus.be.server.domain.search.SearchHistoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("test")
class SearchKeywordConcurrencyTest {

    @Autowired
    private SearchService searchService;

    @Autowired
    @Qualifier("searchHistoryRepositoryImpl")
    private SearchHistoryRepository keywordSearchHistoryRepository;

    @BeforeEach
    void clearData() {
        keywordSearchHistoryRepository.deleteAll();
    }

    @Test
    void 동시에_여러_검색요청이_들어오면_정확히_기록된다() throws InterruptedException {
        int threadCount = 10;
        ExecutorService executor = Executors.newFixedThreadPool(threadCount);
        CountDownLatch latch = new CountDownLatch(threadCount);

        for (int i = 0; i < threadCount; i++) {
            executor.execute(() -> {
                try {
                    searchService.searchRestaurants("강남", "서울");
                } finally {
                    latch.countDown();
                }
            });
        }

        latch.await();

        long count = keywordSearchHistoryRepository.countByKeyword("강남");
        assertEquals(threadCount, count);
    }
}

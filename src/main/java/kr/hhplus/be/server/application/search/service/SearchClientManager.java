package kr.hhplus.be.server.application.search.service;

import kr.hhplus.be.server.application.search.port.SearchClient;
import kr.hhplus.be.server.domain.search.dto.SearchResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchClientManager {

    private final List<SearchClient> clients;

    public List<SearchResult> search(String keyword, String location) {
        for (SearchClient client : clients) {
            try {
                List<SearchResult> result = client.search(keyword, location);
                if (!result.isEmpty()) {
                    return result;
                }
            } catch (Exception e) {
                System.err.println("SearchClient 실패: " + e.getMessage());
            }
        }

        return Collections.emptyList();
    }
}

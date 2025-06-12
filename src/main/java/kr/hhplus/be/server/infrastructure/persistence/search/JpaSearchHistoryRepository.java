package kr.hhplus.be.server.infrastructure.persistence.search;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaSearchHistoryRepository extends JpaRepository<SearchHistoryEntity, Long> {
}

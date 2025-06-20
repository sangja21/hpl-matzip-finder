package kr.hhplus.be.server.infrastructure.persistence.search;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaSearchHistoryRepository extends JpaRepository<SearchHistoryEntity, Long> {
}

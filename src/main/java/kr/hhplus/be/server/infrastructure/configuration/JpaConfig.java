package kr.hhplus.be.server.infrastructure.configuration;

import kr.hhplus.be.server.domain.search.SearchHistoryRepository;
import kr.hhplus.be.server.infrastructure.persistence.search.JpaSearchHistoryRepository;
import kr.hhplus.be.server.infrastructure.persistence.search.SearchHistoryRepositoryImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableJpaAuditing
@EnableJpaRepositories(basePackages = "kr.hhplus.be.server.infrastructure.persistence")
public class JpaConfig {

    @Bean
    public PlatformTransactionManager transactionManager() {
        return new JpaTransactionManager();
    }

    @Bean
    public SearchHistoryRepository searchHistoryRepository(
            JpaSearchHistoryRepository jpaSearchHistoryRepository
    ) {
        return new SearchHistoryRepositoryImpl(jpaSearchHistoryRepository);
    }
}

package kr.hhplus.be.server.infrastructure.persistence.search;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "search_history")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class SearchHistoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String keyword;

    private String location;

    @Column(name = "searched_at", nullable = false, updatable = false)
    private LocalDateTime searchedAt;
}

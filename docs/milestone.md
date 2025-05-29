# 📍 HPL 맛집 검색 서비스 – Milestone

## ✅ 1주차: 요구사항 분석 및 시스템 설계 문서화

* 전체 서비스 흐름 파악 및 문서화

* 설계 문서 기반 구현 구조 확정

* [ ] 기능적 / 비기능적 요구사항 분석

* [ ] API 명세서 /search, /popular 작성

* [ ] ERD 설계 (search\_history)

* [ ] 전략 패턴 기반 클래스 다이어그램

* [ ] 시퀀스 다이어그램 작성

* [ ] 인프라 구성도 스케치 (Redis, PostgreSQL, Naver/Kakao API)

* [ ] 시스템 개요서/문서 정리 및 PR 제출

---

## ✅ 2주차: API 구현 및 단위 테스트 작성

* API 구현 및 테스트 기반 검증

* [ ] /search API 구현

    * [ ] 외부 API 전략 패턴 적용 (Naver → Kakao fallback)
    * [ ] Redis / PostgreSQL에 검색 기록 저장

* [ ] /popular API 구현

    * [ ] Redis ZSET 기반 인기 키워드 집계
    * [ ] (선택) 지역 필터 적용

* [ ] 단위 테스트 작성

    * [ ] 전략 클래스 단위 테스트
    * [ ] Redis 통계 처리 테스트
    * [ ] Repository 테스트

* [ ] Swagger 기반 API 문서 자동화

---

## ✅ 3주차: 보완 및 인프라 구성 정비

* 시스템 안정성 확보 및 배포 준비

* [ ] 예외 처리 및 전역 핸들러 구성

* [ ] 캐시 TTL 및 무효화 전략 수립

* [ ] Docker-compose 기반 실행 환경 구성

* [ ] README 최종 정리 및 PR 제출

* [ ] PR 템플릿 및 문서 정비

* [ ] (선택) E2E 테스트 작성

* [ ] (선택) 로그 집계, 서버 모니터링, 알림 설계

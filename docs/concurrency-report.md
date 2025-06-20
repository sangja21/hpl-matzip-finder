# [STEP-3] 검색 키워드 동시성 제어 및 통계 정합성 확보 실험 보고서

## 1. 문제 상황 정의

### 1-1. 키워드 검색 횟수 누락/중복 문제
- 여러 사용자가 동시에 동일한 키워드로 검색할 경우,
- `SELECT` 이후 `INSERT or UPDATE` 사이에 **race condition**이 발생해 **count가 누락되거나 중복될 수 있음**

### 1-2. 인기 키워드 통계 정합성 문제
- 인기 키워드 조회 중 다른 요청이 count를 변경할 경우,
- 통계 결과가 사용자가 본 것과 실제가 다를 수 있음

---

## 2. 해결 전략

### 2-1. 조건부 UPDATE 기반 키워드 저장
- `UPDATE keyword SET count = count + 1 WHERE keyword = ?`
- 영향을 받은 row 수가 0이면 → `INSERT` 시도
- `INSERT` 중 충돌 발생 시 예외 처리 후 다시 `UPDATE`

### 2-2. 트랜잭션 격리 수준 비교 실험
- `READ COMMITTED` vs `REPEATABLE READ`
- 인기 키워드 조회 중 count 변경이 시야에 보이는가 실험

---

## 3. 구현 방식 요약

- 사용 기술: Spring Boot + JPA + H2(in-memory) + JUnit5
- 동시성 제어 전략: 조건부 UPDATE + 예외기반 재시도
- 테스트 방식: 멀티스레드 환경 구성 (`ExecutorService` + `CountDownLatch`)

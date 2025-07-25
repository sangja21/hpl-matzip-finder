## Kafka 개념 정리 문서

### 1. Kafka란 무엇인가?

Apache Kafka는 **대용량의 실시간 데이터 스트리밍을 처리하기 위한 분산 이벤트 스트리밍 플랫폼**이다. 기본적으로 **Pub/Sub 메시징 시스템**이지만, 영속성, 확장성, 처리 속도 면에서 뛰어난 특성을 제공한다.

Kafka는 LinkedIn에서 시작되어 현재는 Apache Software Foundation에서 관리되며, 로그 수집, 실시간 분석, 데이터 파이프라인, 마이크로서비스 통신 등에 널리 사용된다.

---

### 2. Kafka의 장점과 단점

#### ✅ 장점

* **고성능**: 초당 수백만 개의 메시지를 처리 가능
* **확장성**: 브로커를 추가하여 수평 확장 가능
* **내결함성**: 데이터 복제를 통해 장애 대비 가능
* **내구성**: 메시지가 디스크에 저장되어 영속성 보장
* **유연한 소비자 처리**: 하나의 메시지를 여러 Consumer Group이 독립적으로 처리 가능

#### ❌ 단점

* **초기 학습 곡선이 있음**
* **운영 난이도**: 클러스터 운영/모니터링/장애 처리에 대한 학습 필요
* **짧은 메시지 보존이 디폴트**: 장기 저장은 별도 설정 필요

---

### 3. Kafka의 주요 구성요소

* **Zookeeper**: Kafka 클러스터의 메타데이터와 상태 관리. 최근 Kafka 3.x 이후로는 Zookeeper 없이도 운영 가능한 KRaft 모드도 등장
* **Broker**: Kafka 서버. 메시지를 저장하고 전송하는 핵심 단위
* **Topic**: 메시지가 저장되는 논리적인 채널
* **Partition**: Topic을 나눈 단위. 병렬 처리 및 확장성 제공
* **Producer**: 메시지를 Kafka에 발행하는 클라이언트
* **Consumer**: 메시지를 Kafka에서 구독하는 클라이언트
* **Consumer Group**: 여러 Consumer가 하나의 그룹으로 묶여 병렬로 메시지를 소비함

---

### 4. Kafka의 핵심 기능

* **Pub/Sub 메시징**: 메시지를 발행(Publish)하고 구독(Subscribe)하는 모델 지원
* **내구성(Durability)**: 디스크에 메시지를 저장하여 데이터 유실 방지
* **확장성(Scalability)**: 파티션을 통해 수평 확장 가능
* **고가용성(HA)**: 브로커 장애 시 복제된 데이터로 자동 복구
* **순서 보장(Ordering)**: 파티션 내에서 메시지 순서 보장
* **재처리 지원**: Consumer는 오프셋을 통해 특정 시점부터 메시지를 재처리할 수 있음

---

### 5. Kafka를 왜 쓰는가? (이벤트 기반 아키텍처 확장 관점)

Kafka는 단순 메시지 큐를 넘어, **시스템 전체의 이벤트 흐름을 중심으로 구성하는 아키텍처**를 가능하게 한다. 이를 통해 다음과 같은 이점을 얻는다:

* **서비스 간 결합도 낮추기**: 직접적인 REST API 호출 대신 이벤트 기반 처리로 독립성 확보
* **비동기 처리**: 대량 요청에도 유연하게 대응 가능
* **감사 로그 및 이벤트 저장소 역할**: 이벤트 소싱 패턴에 적합
* **마이크로서비스의 커뮤니케이션 허브**로 사용 가능

---

### 6. 로컬 클러스터 구성 요약 & 테스트 흐름

#### ✅ 구성 요약

* **Zookeeper 1대 + Kafka Broker 3대** 구성 (Docker Compose 활용)
* 각 브로커는 고유 포트 및 브로커 ID로 구동됨
* `docker-compose.kafka.yaml`으로 손쉽게 실행 가능

```bash
docker-compose -f docker-compose.kafka.yaml up -d
```

#### ✅ 테스트 흐름

1. Kafka 클러스터 실행 확인 (`docker ps`)
2. Kafka CLI 또는 Kafka client 라이브러리로 Topic 생성
3. Producer를 통해 메시지 발행
4. Consumer를 통해 메시지 수신 확인

#### 🔧 실제 명령어 예시

```bash
# broker1 컨테이너 진입 후
kafka-topics --create \
  --topic test-topic \
  --bootstrap-server broker1:29092 \
  --replication-factor 3 \
  --partitions 3

# Producer 실행
kafka-console-producer --bootstrap-server broker1:29092 --topic test-topic
# 입력 대기 상태에서 메시지 입력: hello kafka!

# broker2 컨테이너 진입 후
kafka-console-consumer \
  --bootstrap-server broker2:29093 \
  --topic test-topic \
  --from-beginning
# 출력 결과: hello kafka!
```

![img.png](img/kafka_test.png)
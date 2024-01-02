# MSA와 Kotlin으로 만든 결제 프로젝트

## 개요

- MSA + DDD + Hexagonal Architecture 구조 개선
- Kotlin을 도입하여 결제 도메인 구성

# Overview

회원(Membership), 뱅킹(Banking), 머니(Money), 송금(Remittance), 결제(Payment), 정산(Settlement) 등 6개의 서비스로 구성되어 있으며, 각각의 독립적인 프로젝트로
구성.

## Membership Service

고객의 회원 가입, 회원 정보 변경, 회원 정보 조회 등의 기능을 제공하는 서비스

- Hexagonal Architecture 를 활용하여 기본적인 Membership Service 를 구현 (회원 가입)
- gradle build tool 을 이용하여 Docker build 연동
- Axon Framework 를 이용하여 Event Driven Architecture 로 리팩토링.
    - 고객 정보가 변경될 경우, 이벤트를 발행하고, 이를 구독하는 Money 서비스 에서 고객의 머니 정보를 변경하는 CQRS 패턴 구현 (with AWS DynamoDB)
- 보안을 위해 JWT 를 이용한 간단한 API 인증 구현

## Banking Service

고객의 계좌 정보 등록, 등록된 계좌 정보 조회, 입/출금, 거래내역 조회 등의 기능을 제공하는 서비스

- Hexagonal Architecture 를 활용하여 기본적인 Banking Service 를 구현 (가상의 법인 계좌 및 고객 계좌 정보 등록, 은행으로 입/출금 요청하기)

## Money Service

고객의 충전 잔액(머니) CRUD, 충전 내역 조회 등의 기능을 제공하는 서비스

- Hexagonal Architecture 를 활용하여 Membership 서비스 및 Banking 서비스를 이용하는 충전 잔액(머니)을 충전하는 기능 구현
- kafka 을 이용한 간단한 로깅 파이프라인 적용 (실제로는 ELK/EFK 등을 이용한 로깅 파이프라인을 구성하는 것이 좋으나, 예제 용도로 간단하게만 적용)
- 잔액(머니) 충전 프로세스를 Async 방식으로 구현하고, Polling 을 통한 결과 조회 방식 구현
- Axon Framework 를 이용하여 Saga Pattern 적용 및 Event Driven Architecture로 리팩토링
- Membership 서비스로부터 고객 정보 변경 이벤트를 수신하고, 이를 기반으로 CQRS 패턴을 구현 (with AWS DynamoDB)

## Remittance Service

고객 간 송금 기능 및 송금 내역 정보 조회 등의 기능을 제공하는 서비스

- Hexagonal Architecture 를 활용하여 Membership 서비스, Banking 서비스, Money 서비스를 이용하는 고객 간 혹은 계좌 송금 기능 구현
- 고객 간 송금하는 기능은 Axon Framework 를 이용하여 Saga Pattern 적용 및 리팩토링
- 특정 송금 건을 기준으로, 머니의 충전 내역을 조회해보는 기능 구현을 위해 API Aggregation Pattern 적용

## Payment Service

가맹점에서 Kotlin Pay 를 이용한 간편 결제 및 결제 내역 조회 등의 기능을 제공하는 서비스

- Hexagonal Architecture 를 활용하여 Membership 서비스, Money 서비스를 이용하는 가맹점에서의 결제 기능 구현
- Membership Service 의 가맹점주 기능 확장

## Settlement Service

완료된 결제 내역을 기준으로 가맹점에 정산된 금액을 입금하고, 수수료 수취를 위한 기능을 제공하는 서비스

- Hexagonal Architecture 를 활용하여 Payment 서비스를 이용하는 기간별 정산 기능 구현
- 수수료 수취 기능 구현, 가맹점주 계좌로 입금 기능 구현
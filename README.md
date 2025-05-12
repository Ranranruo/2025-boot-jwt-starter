# 프로젝트 설정

## 기본 정보
- **JDK 버전**: 21
- **Spring Boot 버전**: 3.4.4
- **빌드 툴**: Gradle

## 사용 플러그인
- Java
- Spring Boot (`org.springframework.boot`): 3.4.4
- Spring Dependency Management (`io.spring.dependency-management`): 1.1.7

## 주요 의존성

### Spring
- spring-boot-starter-web
- spring-boot-starter-data-jpa
- spring-boot-starter-validation
- spring-boot-starter-security
- spring-boot-starter-data-redis
- spring-boot-devtools (개발 전용)

### 데이터베이스
- PostgreSQL: `org.postgresql:postgresql`

### JWT
- jjwt-api: 0.11.5
- jjwt-impl: 0.11.5
- jjwt-jackson: 0.11.5

### Lombok
- lombok: `compileOnly` 및 `annotationProcessor`

### 테스트
- spring-boot-starter-test
- spring-security-test
- junit-platform-launcher

### 환경 변수 관리
- dotenv-java: 2.2.0

### 유효성 검사
- jakarta.validation-api: 3.0.2
- hibernate-validator: 6.2.0.Final

## 테스트 설정
- JUnit Platform 사용

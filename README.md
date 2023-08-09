# TEMPLATE
## Version
* Gradle - Kotlin
* Language : Kotlin
* Spring Boot : 2.7.14
* Java : 17

## Swagger
### [Swagger Url](http://localhost:8080/swagger-ui.html)

## 구현
### application.yml
* `jasypt`를 이용해서 비밀번호 암호화
* `dotenv`를 이용해서 `jasypt`에 사용한 secretKey `.env.properties`에 따로 분리
* `.env.properties` gitignore에 추가하여 git에 안올라가게 세팅

### exception 처리
* 예외사항 발생시 `throw new ServiceException()`으로 에러 발생
* `@ControllerAdvice`를 이용하여 전역 에러처리

### JWT
* `Interceptors` 이용해서 특정 url만 jwt 체크 예외 처리
* `Interceptors` 이용해서 jwt 토큰 체크하여 문제가 있는경우 에러 발생

### Resolver
* `JWT token` 이용해서 데이터를 조회 해야 되는경우 Resolver를 이용하여 데이터를 VO로 가공하여 전달
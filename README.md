# 🚀1단계 - 홈 화면

---

## 🔧구현사항

- [x] @Controller 작성

---

## 🧐고민사항

😈 "/"로 요청을 보냈는데 아무것도 인식하지 못하는 문제
> 💡thymeleaf의존성 추가 후 해결
>
> 템플릿 엔진 없이 렌더링 하는 방법 : https://bottom-to-top.tistory.com/38

# 🚀2단계 - 예약 조회

---

## 🔧구현사항

- [x] /reservation 렌더링
- [x] 예약 목록 조회 API 구현

---

## 🧐고민사항

😈 builder 패턴을 인식하지 못하는 상태
> 💡lombok 의존성 수정
>
> 참고 : https://ururuwave.tistory.com/66

# 🚀3단계 - 예약 추가/취소

---

## 🔧구현사항

- [x] 예약 추가 API 구현
- [x] 예약 삭제 API 구현

---

# 🚀4단계 - 예외 처리

---

## 🔧구현사항

- [x] 예약 추가 시 필요한 인자 값이 비어있는 경우 예외 처리
- [x] 예약 삭제 시 식별자로 저장된 예약을 찾을 수 없는 경우 예외 처리

## 🧐고민사항

😈삭제할 예약이 없는 경우는 NOT_FOUND를 반환하는 게 알맞다고 생각했다. 하지만 요청을 보낼 때 없는 번호로 보낸다면 이 상황에서는 BAD_REQUEST가 맞다고도 생각했다.
> 이에 대한 고민이 더 필요할 것 같다.

😈stream()에서 .orElseThrow()를 할 때는 실행되지 않았는데 .orElse()로 바꾸니 테스트를 통과함
> orElse

# ⭐️리뷰 정리

- Exception이름 구체화 : 현재 미션에서는 구체화된 이름보단 빠르게 해결할 수 있도록 핵심 의미만 포함한 이름 사용(이후 적용기회가 있다면 적용해 볼 예정)
- 테스트 코드 : Mockito 사용해 볼 예정

# 🚀5단계 - 데이터베이스 적용하기

---

## 🔧구현사항

- [X] 데이터베이스 설정
- [X] 데이터베이스 연결

---

## 🧐고민사항

😈 민감한 정보 노출 주의
> application-secrets.properties를 만들어 숨김

😈 .sql파일을 resources/db/~에 두니 인식 X
> classpath: 바로 아래에 두어야 함

# 🚀6단계 - 데이터 조회하기

---

## 🔧구현사항

- [x] 패키지 구조 변경
- [ ] 데이터베이스 연결

---

## 🧐고민사항

😈 reservation, reservationsEntity 등 단수와 복수의 통일(컨벤션)이 필요해 보임
> 복수형을 기본으로 

😈 update vs batchUpdate

😈 keyHolder? PreparedStatement? NotSerializableException?

# 🚀8단계 - 시간 관리 기능

---

## 🧐고민사항

😈 Service에 데이터를 저장하고 불러오는 DB관련이 기능이 있는 것이 어색함
> DAO객체를 만들어 이곳에서 처리하도록 수정
> Reservation도 이처럼 수정할 예정

 # NOTEU

 - RDS 연동된 AWS 계정 프리티어 종료로 인해 h2로 DB 변경(2024.03.13)
 - 배포 종료(2024.10.23)
<br/>

## 프로젝트 소개

- Note + Education
- 학습 관리와 교육이 이루어지는 교육 플랫폼
<br/>

## 개발 환경
- Front : Thymeleaf / BootStrap
- Back : JAVA17 / Spring Boot 3.1.5 / Spring Security / Spring Data JPA
- DB : AWS RDS (MySQL)
- 협업 툴 : Notion
- 배포 환경 : AWS EC2 (Ubuntu 20.04 LTS)
<br/>

## 개발 기간
2023-11-01 ~ 2023-12-01
<br/><br/>

## 팀원 구성 및 역할

### 김수연([@yeonann](https://github.com/yeonann))
- 회원


### 김채원([@chaewoniiii](https://github.com/chaewoniiii))
- 과목
- 공지사항
- 과제


### 배용현([@Baeyounghyeon](https://github.com/Baeyonghyeon))
- 채팅
- WebSocket 과 Socket 의 차이점 학습
    - WebSocket 은 HTTP를 통한 핸드쉐이크가 성공적으로 이루어지면 101(Switching Protocols) 응답을 클라이언트에게 보낸다.
    - WebSocket은 메시지 형식을 텍스트, 이진 데이터를 지원한다.
- Sock.js , Stomp 기술 학습
    - sock.js 는 브라우저에서 웹 소켓을 지원하지 않는 경우에 웹 소켓을 대안하는 기술로 사용한다. 즉. sock.js를 사용하면 애플리케이션의 코드의 변경이 필요 없어진다.
    - Stomp 를 사용하지 않는다면 HTTP 핸드쉐이크 이 후 개발자가 직접 세션을 맵, 리스트 등 관리를 해야한다. stomp는 이러한 부분들을 pub / sub을 통해 관리를 편하게 해주는 라이브러리이다. (ex: 구독 관리 및 메세지를 broadcast 하는데 사용), 스케일 아웃도 고려할때 좋은 선택이였다.
- SSE(Server-Sent-Events) 학습
    - 서버로 부터 실시간으로 알림을 받아야 했다. 가장 먼저 생각한 것은 Polling 이었지만 실시간성이 중요하다 생각해 SSE 방식으로 구현하게 되었다. Spring 의 경우 SSemitter 로 SSE를 지원했고, Tomcat을 사용한다면 Default로 SSE가 30초 유지되고 다시 SSE 커넥션을 만든다.

### 이우진([@iuj09](https://github.com/iuj09))
- 자료실
  - 자료실 게시글 CRUD
  - 다중파일 업로드 및 파일 미리보기를 통한 개별 삭제
- 질문게시판
  - 질문게시글 CRUD
  - SimpleMDE를 통한 마크다운 양식 작성 기능
  - 댓글 작성 및 삭제


<div align="center">
<img src="https://github.com/user-attachments/assets/33b47d73-f081-4ac4-a2da-53f638771163">
</div>




<h1 align="center">
  익명 게시판
</h1>
<p align="center">익명 게시판 사이트</p>
<p align="center">스프링부트를 활용하여 익명 게시판을 만들고 <br>
  기본적인 기능인 게시글 추가, 수정, 삭제, 비밀글 등을 추가한 홈페이지</p>

---

## Contents

<p align="left">목차</p>
<p align="left">
  <a href="#What-is">What is</a> <br>
  <a href="기능-설명">기능 설명</a>  <br>
  <a href="#development-setup--database-design">Development Setup</a> <br>
  <a href="#repository-structure">Repository Structure</a> <br>
  <a href="#authors">Authors</a>
</p>

---

## What is

<h3>1. 개요 및 목적</h3>

  • 개요 
   - 익명 게시판을 제공하는 웹사이트로, 익명으로 글을 쓸 수 있고, 수정 및 삭제가 가능하고 숨긴 글 작성도 가능한 페이지 생성

  • 목적
   - 주요 목표는 익명으로 게시글을 작성하고 볼 수 있으며, 사용자가 입력한 상태에 따라 전체보기 혹은 숨김 처리를 통해 게시판을 생성
   - 수정하기 위해서 입력한 비밀번호가 필요하며, 일치할 경우 수정 혹은 삭제 가능

<h3>2. 프로젝트 배경 및 적용 기술</h3>

  • 핵심 가치와 비즈니스적 영향 
   - 일반적인 사용자들이 이용할 수 있는 익명 게시판을 생성하여, 내 정보를 밝히지 않고 다양한 소통을 할 수 있는 홈페이지를 제공          
  
  • 스프링부트를 통해 작성하였으며, JSP와 컨트롤러를 사용하여 동적인 웹 페이지를 구현하고, Git을 통해 버전 관리 수행

<br>

## 기능 설명

| 핵심 기능                | 설명                                                                                                                                                       |
|----------------------------|------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **[QNA_Controller](src/main/java/kr/co/greenart/web/customer/qna/QNA_Controller.java)**                  | 가게 부분의 기능과 DB연결을 처리<br> - Mapper와 ServiceImpl 클래스를 통해 MySQL과 연결하여 DB와 연동 및 SQL문을 처리하고 가게 정보들을 불러오거나 저장                                |
| **[QNA_ControllerAdvice](src/main/java/kr/co/greenart/web/customer/qna/QNA_ControllerAdvice.java)**                 | 회원가입과 로그인 등의 유저 관리<br> - 기본적으로 DB와 연동하여 처리되며, JSON으로 값을 불러온 뒤 UserValidator를 통해 유효성 검사를 진행하고 모두 통과했을 때 가입을 완료                                     |
| **[QNA_ServiceImpl](src/main/java/kr/co/greenart/web/customer/qna/QNA_ServiceImpl.java)**                 | Filter 클래스를 통해 로그인과 로그아웃 상태를 확인하여 처리<br> - session을 확인하여 로그인 상태를 확인하고 특정 페이지의 경우 로그인이 되어있는지 확인하여 안되어있을 경우 로그인 페이지로 이동하도록 구현                                      |
| **[QNA_Mapper](src/main/java/kr/co/greenart/web/customer/qna/QNA_Mapper.java)**           | 사용자의 예산과 원하는 태그에 맞는 가게 검색<br>  - 사용자와 카페의 정보를 초기화하고, 카테고리와 가격 조건에 맞는 카페 리스트를 검색하여 카페에 대한 사진 및 태그 정보를 병합한 뒤 결과를 제공                                                  |
| **[메인 페이지](src/main/webapp/WEB-INF/views/qna.jsp)**      | 지역별로 찾고 싶은 가게 리스트 출력<br> - 요청된 지역에 맞는 카페 목록을 검색하고, 해당 카페와 관련된 이미지 정보를 병합하여 JSON 형식으로 응답한 뒤 상태 코드를 설정하여 성공 또는 실패를 사용자에게 전달       |
| **[작성글 보기](src/main/webapp/WEB-INF/views/qnaDetail.jsp)**        | 선택한 가게의 세부 정보를 사용자에게 보여줌<br> - 특정 카페의 세부 정보를 조회하여, 가게 정보, 메뉴 목록, 리뷰, 평균 평점, 카테고리, 태그 및 이미지를 보여주며, 예외 발생 시 에러 페이지로 포워딩                     |
| **[게시글 작성](src/main/webapp/WEB-INF/views/qnaForm.jsp)**    | 사업주로 등록된 사용자가 가게 정보를 등록하고 DB에 저장 <br> - 페이지에서 가게 정보를 입력하고 전송. GET 요청으로 카테고리 목록과 소유자 ID를 제공하며, POST 요청에서는 JSON 데이터를 읽고 가게 정보, 태그, 이미지를 데이터베이스에 저장                |
| **[게시글 수정](src/main/webapp/WEB-INF/views/qnaUpdate.jsp)**               | 관리자가 가입된 사용자들의 정보와 사업자등록증 등 확인 및 제재 가능 <br> DB에서 사용자 리스트를 조회하여 가져오며, 사업주의 경우 사업자등록증 확인 가능. 아이디별로 활성화/비활성화 가능                                                        |

<br>

## Development setup / Database Design

* 본 프로젝트는 사용자들이 익명 게시판을 통해 다양한 정보를 공유하고 수정 및 삭제를 할 수 있도록 설계된 웹 사이트
* 이를 통해 사용자는 다양한 정보를 검색 또는 직접 확인할 수 있으며, 숨김 시스템을 통해 특정 사용자들끼리만도 정보 공유 가능

* **프로젝트 구조**
  - 이 웹 어플리케이션은 Maven을 기반으로 구성되었으며, 스프링부트를 통해 컨트롤러와 JSP를 활용하여 동적인 웹사이트를 구현
  - 다양한 라이브러리(JSTL, DBCP2, Lombok, h2DB 등)를 통해 기능을 확장하고 데이터 처리를 최적화. 특히, H2-Console DB를 사용하여 SQL 쿼리와 객체 지향 프로그래밍의 통합을 용이하게 하였고, DBCP2를 통해 데이터베이스 연결 풀링을 적용하여 성능 개선
  - 본 프로젝트는 개인 프로젝트로 진행

* **개발 과정**
  - 초기 단계에서는 홈페이지의 기본 기능을 설계하였고, 익명게시판 사용자 요구사항들을 반영할 수 있도록 준비
  - Controller를 먼저 구성하여 기능들을 구현한 뒤, 해당 기능들을 수행할 수 있는 전체적인 페이지들을 구축하고 디자인 조정 진행
  - 데이터베이스 연결 및 검색 기능 구현하여 사용자가 필요한 정보 검색 가능
  - JUnitTest를 통해 생성한 메서드들과 기능들을 테스트하였으며, 올바른 결과값이 나왔을 경우 직접 적용하는 방식으로 디버깅 수행

본 프로젝트는 익명 게시판 개발을 목표로 하여, 다양한 정보를 효과적으로 공유하고 익명의 사용자들 간의 소통을 손쉽게 수행할 수 있도록 제작하였음. 앞으로도 지속적인 기능 추가를 통해 더 나은 서비스 제공을 계획.

<br>

## Repository Structure

```sh
└──customerqna/
  ├─ README.md
  ├─ .gitignore
  ├─ pom.xml/
  └─ src/
     ├─ main/
     │  ├─ java/kr/co/greenart/web/
     │  │    └─ qna/
     │  │    ├─ util/
     │  │    └─ DemoApplication.java
     │  ├─ resources/
     │  │   └─ schema.sql
     │  └─ webapp/
     │      └─ WEB-INF/views
     └─ test/
          └─ java/kr/co/greenart/web/
```

<p align="center">
  <h2>Built With</h2>
  <img src="https://img.shields.io/badge/MySQL-005C84?style=for-the-badge&logo=mysql&logoColor=white">
  <img src="https://img.shields.io/badge/Eclipse-2C2255?style=for-the-badge&logo=eclipse&logoColor=white">

<br>

## Authors
> 프로필 
>
> 이재민 [@깃허브 프로필 페이지](https://github.com/qwer123toy)
> 


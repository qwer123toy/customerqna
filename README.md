
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
  <a href="#기능-설명">기능 설명</a>  <br>
  <a href="#development-setup">Development Setup</a> <br>
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
| **[QNA_Controller](src/main/java/kr/co/greenart/web/customer/qna/QNA_Controller.java)**                  | ● Q&A 게시판의 조회, 작성, 수정, 삭제 기능을 관리하며, 페이징과 검색, 보안 접근 제어를 구현한 스프링 컨트롤러 <br> -  Q&A 게시물을 조회하고 검색할 수 있도록 페이징 기능을 제공하며, 게시물 작성, 수정, 삭제를 처리 <br> - 보안이 필요한 게시물에 대해서는 비밀번호 인증을 통해 접근을 제어하고, 세션 관리를 통해 사용자 권한을 유지              |
| **[QNA_ControllerAdvice](src/main/java/kr/co/greenart/web/customer/qna/QNA_ControllerAdvice.java)**                 | ● QNA_Controller에서 발생하는 QNA_NotFoundException을 처리하여 404 페이지로 리다이렉트      |
| **[QNA_ServiceImpl](src/main/java/kr/co/greenart/web/customer/qna/QNA_ServiceImpl.java)**                 |  ● Q&A 게시판의 DB 로직을 구현하며, 다양한 게시물 조회, 검색, 수정, 삭제, 카운팅 기능을 제공     |
| **[QNA_Mapper](src/main/java/kr/co/greenart/web/customer/qna/QNA_Mapper.java)**           | ● QNA 관련 SQL 쿼리를 수행하여 CRUD 작업과 데이터 조회 기능을 제공                                             |
| **[메인 페이지](src/main/webapp/WEB-INF/views/qna.jsp)**      | ● Q&A 게시글 목록을 페이징, 검색, 정렬 옵션과 함께 표시하는 JSP 페이지       |
| **[작성글 보기](src/main/webapp/WEB-INF/views/qnaDetail.jsp)**        | ● 게시글 상세 페이지로, 제목, 작성자, 작성일자/수정일자, 조회수, 내용 등을 표시하고 목록, 삭제, 수정 버튼을 제공                    |
| **[게시글 작성](src/main/webapp/WEB-INF/views/qnaForm.jsp)**    | ● 게시글 작성을 위한 폼으로, 제목, 이름, 비밀번호, 내용을 입력받고 추가 버튼과 취소 버튼을 제공              |
| **[게시글 수정](src/main/webapp/WEB-INF/views/qnaUpdate.jsp)**               | ● 게시글 수정 폼으로, 사용자가 제목, 이름, 비밀번호 및 내용을 입력할 수 있도록 구성 <br> 수정하기 버튼과 돌아가기 버튼이 포함되어 있으며, 수정 중 에러가 발생할 경우 에러 메시지를 표시                                                  |

<br>

## Development setup

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

* 본 프로젝트는 익명 게시판 개발을 목표로 하여, 다양한 정보를 효과적으로 공유하고 익명의 사용자들 간의 소통을 손쉽게 수행할 수 있도록 제작하였음
* 앞으로도 지속적인 기능 추가를 통해 더 나은 서비스 제공을 계획 중

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
  <img src="https://img.shields.io/badge/Java-007396?style=flat-square&logo=Java&logoColor=white">
  <img src="https://img.shields.io/badge/Spring-6DB33F?style=flat-square&logo=Spring&logoColor=white">

<br>

## Authors
> 프로필 
>
> 이재민 [@깃허브 프로필 페이지](https://github.com/qwer123toy)
> 


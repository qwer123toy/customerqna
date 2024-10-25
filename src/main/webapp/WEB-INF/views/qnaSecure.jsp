<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>고객 센터</title>
  <style>
    table {
      width: 60%;
      margin: 0 auto;
      border-collapse: collapse;
    }
    th, td {
      border: 1px solid #ddd;
      padding: 8px;
    }
    th {
      background-color: #f2f2f2;
      text-align: left;
    }
    tr:nth-child(even) {
      background-color: #f9f9f9;
    }
    tr:hover {
      background-color: #ddd;
    }
    a {
      color: #333;
      text-decoration: none;
    }
    a:hover {
      text-decoration: underline;
    }
    .title {
      width: 60%;
    }
    .articleId, .username, .views {
      width: auto;
    }
  </style>
  <script>
  function checkPassword(event, expectedPassword) {
      var inputPassword = prompt("비밀번호를 입력하세요:");
      if (inputPassword === null || inputPassword.trim() !== expectedPassword.trim()) {
        alert("비밀번호가 일치하지 않습니다.");
        event.preventDefault(); // 링크 동작을 막음
      }
    }
  </script>
</head>
<body>
  <h1>게시글 목록 구현</h1>
  <form action="/qna" method="post">
    <p><a href="/qnaForm">게시글 추가하기</a></p>
    <p><a href="/qna">게시글 전체 보기</a></p>
    <p><a href="/qnaSecure">숨긴 게시글만 보기</a></p>
    <table>
      <tr>
        <th class="articleId">번호</th>
        <th class="title">제목</th>
        <th class="username">작성자</th>
        <th class="secure">숨김여부</th>
        <th class="views">조회수</th>
      </tr>
      <c:forEach items="${qnaList}" var="qna">
        <tr>
          <td class="articleId">${qna.articleId}</td>
          <td class="title">
            <c:choose>
              <c:when test="${qna.secure == true}">
                <a href="/qna/${qna.articleId}" onclick="checkPassword(event, '${qna.password}')">${qna.title}</a>
              </c:when>
              <c:otherwise>
                <a href="/qna/${qna.articleId}">${qna.title}</a>
              </c:otherwise>
            </c:choose>
          </td>
          <td class="username">${qna.username}</td>
          <td class="secure">
            숨김
          </td>
          <td class="views">${qna.views}</td>
        </tr>
      </c:forEach>
    </table>
  </form>
</body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>고객 센터</title>
  <style>
    /* 기본 스타일 */
    body {
      font-family: Arial, sans-serif;
      margin: 0;
      padding: 20px;
      background-color: #f7f7f7;
      display: flex;
      justify-content: center;
    }
    /* 컨테이너 스타일 */
    .container {
      width: 70%;
      background-color: #fff;
      padding: 20px;
      box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
      border-radius: 8px;
    }
    /* 제목 스타일 */
    h1 {
      text-align: center;
      color: #333;
    }
    /* 테이블 스타일 */
    table {
      width: 100%;
      margin-top: 20px;
      border-collapse: collapse;
    }
    th, td {
      border: 1px solid #ddd;
      padding: 12px;
      text-align: left;
    }
    th {
    }
    tr:nth-child(even) {
      background-color: #f1f9ff;
    }
    /* 글씨 색상 및 링크 스타일 */
    .title1, .title {
      font-weight: bold;
      color: #0056b3;
    }
    .username1, .username, .password {
      font-style: italic;
      color: #555;
    }
    td {
      line-height: 1.6;
    }
    /* 버튼 스타일 */
    .action-buttons {
      text-align: center;
      margin-top: 20px;
    }
    .action-buttons input[type="button"],
    input[type="submit"] {
      background-color: #007bff;
      color: white;
      padding: 10px 20px;
      border: none;
      border-radius: 4px;
      margin: 5px;
      cursor: pointer;
      transition: background-color 0.3s;
    }
    .action-buttons input[type="button"]:hover,
    input[type="submit"]:hover {
      background-color: #0056b3;
    }
  </style>
</head>
<body>
  <div class="container">
    <h1>게시글 삭제하기</h1>
    <form method="post">
      <input type="hidden" name="articleId" value="${qna.articleId }">
      <table>
        <tr>
          <th class="title1">제목</th>
          <th class="title"><input type="text" name="title" value="${qna.title}"></th>
          <th class="username1">이름</th>
          <th class="username"><input type="text" name="username" value="${qna.username}"></th>
          <th class="password"><input type="password" name="inputPassword" placeholder="비밀번호 입력"></th>
        </tr>
        <tr>
          <td colspan="5"><input type="text" name="content" value="${qna.content}"></td>
        </tr>
      </table>
      <c:choose>
			<c:when test="${not empty errorMessage}">
				<div class="error-message">${errorMessage}</div>
			</c:when>
		</c:choose>
      <div class="action-buttons">
        <input type="submit" value="수정하기">
        <input type="button" value="돌아가기" onclick="window.location.href='/qna/${articleId}'">
      </div>
    </form>
  </div>
</body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>고객 센터</title>
  <style>
    body {
      font-family: Arial, sans-serif;
      margin: 0;
      padding: 20px;
      background-color: #f7f7f7;
    }
    h1 {
      text-align: center;
      color: #333;
    }
    table {
      width: 70%;
      margin: 20px auto;
      border-collapse: collapse;
      box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
      background-color: #fff;
    }
    th, td {
      border: 1px solid #ddd;
      padding: 12px;
      text-align: left;
    }
    th {
      background-color: #f4f4f4;
      color: #333;
    }
    tr:nth-child(even) {
      background-color: #f9f9f9;
    }
    tr:hover {
      background-color: #f1f1f1;
    }
    .title1 {
      width: 10%;
      font-weight: bold;
      color: #2a6496;
    }
    .title {
      width: 60%;
      font-weight: bold;
      color: #2a6496;
    }
    .username1 {
      width: 10%;
      font-style: italic;
      color: #555;
    }
    .username {
      width: 20%;
      font-style: italic;
      color: #555;
    }
    td {
      line-height: 1.6;
    }
  </style>
</head>
<body>
  <h1>게시글 삭제하기</h1>
  <form method="post">
  	<input type="hidden" name="articleId" value="${articleId }">
    <table>
      <tr>
        <th class="title1">제목</th>
        <th class="title">${qna.title}</th>
        <th class="username1">이름</th>
        <th class="username">${qna.username}</th>
        <th class="password"><input type="password" name="password"></th>
      </tr>
      <tr>
        <td colspan="5">${qna.content}</td>
      </tr>
    </table>
    <input type="submit" value="삭제하기">
  </form>
<input type="button" value="돌아가기" onclick="history.back()" />
  
</body>
</html>

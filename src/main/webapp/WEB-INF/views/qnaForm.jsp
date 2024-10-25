<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>고객 센터 문의 남기기</title>
  <style>
    body {
      font-family: Arial, sans-serif;
      background-color: #f7f7f7;
      margin: 0;
      padding: 20px;
    }
    h1 {
      text-align: center;
      color: #333;
    }
    table {
      width: 50%;
      margin: 0 auto;
      border-collapse: collapse;
      background-color: #fff;
      box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
      padding: 20px;
    }
    th, td {
      padding: 10px;
      text-align: left;
    }
    .title {
      font-weight: bold;
      color: #2a6496;
    }
    input[type="text"], input[type="password"] {
      width: 100%;
      padding: 10px;
      border: 1px solid #ddd;
      border-radius: 4px;
      box-sizing: border-box;
    }
    input[type="submit"], input[type="button"] {
      background-color: #4CAF50;
      color: white;
      padding: 10px 20px;
      margin: 8px 0;
      border: none;
      border-radius: 4px;
      cursor: pointer;
    }
    input[type="button"] {
      background-color: #f44336;
    }
    input[type="submit"]:hover, input[type="button"]:hover {
      background-color: #45a049;
    }
    input[type="button"]:hover {
      background-color: #e53935;
    }
    .content {
      height: 100px;
    }
  </style>
</head>
<body>
  <h1>게시글</h1>
  <form action="qnaForm" method="post">
    <table>
      <tr>
        <td class="title">제목</td>
        <td><input type="text" name="title"/></td>
      </tr>
      <tr>
        <td>이름</td>
        <td><input type="text" name="username"/></td>
      </tr>
      <tr>
        <td>비밀번호</td>
        <td><input type="password" name="password"/></td>
      </tr>
      <tr>
        <td>내용</td>
        <td><input type="text" name="content" class="content"/></td>
      </tr>
      <tr>
        <td><input type="submit" value="추가" /></td>
        <td><input type="button" value="취소" onclick="window.location.href='./qna'" /></td>
      </tr>
    </table>
  </form>
</body>
</html>


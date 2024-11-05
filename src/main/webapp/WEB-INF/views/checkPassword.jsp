<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>비밀번호 입력</title>
<style>
/* 기본적인 스타일 */
body {
	font-family: Arial, sans-serif;
	background-color: #f8f9fa;
	margin: 0;
	padding: 0;
	display: flex;
	justify-content: center;
	align-items: center;
	height: 100vh;
}
/* 컨테이너 스타일 */
.container {
	width: 400px;
	background-color: #fff;
	padding: 20px;
	box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
	border-radius: 8px;
	text-align: center;
}
/* 오류 메시지 스타일 */
.error-message {
	color: #d9534f;
	font-size: 16px;
	margin-bottom: 20px;
}
/* 폼 스타일 */
form {
	margin-top: 20px;
}

label {
	display: block;
	margin-bottom: 8px;
	color: #343a40;
}

input[type="password"] {
	width: calc(100% - 22px);
	padding: 10px;
	border: 1px solid #ddd;
	border-radius: 4px;
	margin-bottom: 20px;
}

button[type="submit"] {
	background-color: #007bff;
	color: white;
	padding: 10px 20px;
	border: none;
	border-radius: 4px;
	cursor: pointer;
	transition: background-color 0.3s;
}

button[type="submit"]:hover {
	background-color: #0056b3;
}

a {
	display: inline-block;
	margin-top: 20px;
	color: #007bff;
	text-decoration: none;
	transition: color 0.3s;
}

a:hover {
	color: #0056b3;
}
</style>
</head>
<body>
	<div class="container">
		<c:choose>
			<c:when test="${not empty errorMessage}">
				<div class="error-message">${errorMessage}</div>
			</c:when>
		</c:choose>
		<form action="/qna/checkPassword" method="post">
			<input type="hidden" name="articleId" value="${articleId}" /> <label
				for="password">비밀번호:</label> <input type="password" name="password"
				id="password" required />
			<button type="submit">확인</button>
		</form>
		<a href="/qna">목록으로 돌아가기</a>
	</div>
</body>
</html>


<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>고객 센터</title>
<style>
/* 기본적인 스타일 */
body {
	font-family: Arial, sans-serif;
	background-color: #f8f9fa;
	margin: 0;
	padding: 0;
}

/* 컨테이너 스타일 */
.container {
	width: 80%;
	margin: 30px auto;
	background-color: #fff;
	padding: 20px;
	box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
	border-radius: 8px;
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
}

th {
	background-color: #343a40;
	color: #fff;
	text-align: center;
}

td {
	text-align: center;
}

tr:nth-child(even) {
	background-color: #f2f2f2;
}

tr:hover {
	background-color: #e9ecef;
}

/* 링크 스타일 */
a {
	color: #007bff;
	text-decoration: none;
}

a:hover {
	text-decoration: underline;
}

/* 제목 및 버튼 스타일 */
h1 {
	text-align: center;
	color: #343a40;
}

.action-buttons {
	text-align: center;
	margin-bottom: 20px;
}

.action-buttons a {
	display: inline-block;
	margin: 5px;
	padding: 10px 20px;
	background-color: #007bff;
	color: #fff;
	border-radius: 4px;
	text-decoration: none;
	transition: background-color 0.3s;
}

.action-buttons a:hover {
	background-color: #0056b3;
}

/* 페이지 네비게이션 스타일 */
nav {
	text-align: center;
	margin-top: 20px;
}

nav a {
	margin: 0 5px;
	padding: 8px 12px;
	background-color: #007bff;
	color: #fff;
	border-radius: 4px;
	text-decoration: none;
	transition: background-color 0.3s;
}

nav a:hover {
	background-color: #0056b3;
}

.current-page {
	font-weight: bold;
	color: #343a40;
	background-color: #e9ecef;
}
</style>
</head>
<body>
	<div class="container">
		<h1>게시글 목록</h1>
		<div class="action-buttons">
			<a href="/qnaForm">게시글 추가하기</a>
			<a href="/qna">게시글 전체 보기</a>
			<a href="/qnaSecure">숨긴 게시글만 보기</a>
		</div>
		<table>
			<tr>
				<th class="articleId">번호</th>
				<th class="title">제목</th>
				<th class="username">작성자</th>
				<th class="username">숨김여부</th>
				<th class="createdAt">작성일자</th>
				<th class="views">조회수</th>
			</tr>
			<c:forEach items="${list}" var="qna">
				<tr>
					<td class="articleId">${qna.articleId}</td>
					<td class="title">
						<c:choose>
							<c:when test="${qna.secure == true}">${qna.title}</c:when>
							<c:otherwise>
								<a href="/qna/${qna.articleId}">${qna.title}</a>
							</c:otherwise>
						</c:choose>
					</td>
					<td class="username">${qna.username}</td>
					<td class="secure">
						<c:choose>
							<c:when test="${qna.secure == true}">숨김</c:when>
							<c:otherwise>공개</c:otherwise>
						</c:choose>
					</td>
					<td>${qna.createdAt}</td>
					<td class="views">${qna.views}</td>
				</tr>
			</c:forEach>
		</table>
		<nav>
			<c:forEach var="num" begin="0" end="${totalPages-1}">
				<c:url var="pages" value="/qna">
					<c:param name="page">${num}</c:param>
					<c:param name="size">${size}</c:param>
				</c:url>
				<a href="${pages}" class="${page == num ? 'current-page' : ''}">${num + 1}</a>
			</c:forEach>
		</nav>
	</div>
</body>
</html>

<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="zh-TW">
<head>
	<meta charset="UTF-8">
	<title>遊樂場</title>
	<link href="<%= request.getContextPath() %>/style/myStyle.css" rel="stylesheet" />
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet"
		integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU"
		crossorigin="anonymous">
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ"
		crossorigin="anonymous"></script>
</head>
<body>
	<jsp:include page="/WEB-INF/pages/navBar.jsp" /> <!-- 包含導覽列 -->
	<div class="container mt-5"> <!-- Bootstrap 容器 -->
		<h1 class="text-center">請選擇遊戲</h1> <!-- 文字置中 -->
		<div class="text-center">
			<a class="btn btn-success btn-lg mx-2" href="lottery/input">生成大樂透號碼</a>
		</div>
	</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="zh-TW">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0"> <!-- 使頁面響應式 -->
    <title>登入</title>
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
        <h1 class="text-center">登入系統</h1> <!-- 文字置中 -->
        <c:if test="${not empty error}">
            <div class="alert alert-danger" role="alert">
                ${error}
            </div>
        </c:if>
        <form action="${pageContext.request.contextPath}/login" method="post"> <!-- 提交表單 -->
            <div class="mb-3"> <!-- 用戶名欄位 -->
                <label for="username" class="form-label">用戶名</label>
                <input type="text" class="form-control" id="username" name="username" required="required" />
            </div>
            <div class="mb-3"> <!-- 密碼欄位 -->
                <label for="password" class="form-label">密碼</label>
                <input type="password" class="form-control" id="password" name="password"
                    required="required" />
            </div>
            <button type="submit" class="btn btn-primary">登入</button> <!-- 提交按鈕 -->
        </form>
        <div class="mt-3 text-center">
            <a href="register">還沒有帳號？註冊</a> <!-- 註冊連結 -->
        </div>
    </div>
</body>
</html>
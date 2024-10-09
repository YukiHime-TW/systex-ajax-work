<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="zh-TW">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>註冊</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU"
        crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ"
        crossorigin="anonymous"></script>
</head>
<body>
    <jsp:include page="/WEB-INF/pages/navBar.jsp" /> <!-- 包含導覽列 -->
    <div class="container mt-5">
        <h2 class="text-center mb-4">註冊帳戶</h2>
        <div class="row justify-content-center">
            <div class="col-md-6">
                <c:if test="${not empty error}">
                    <div class="alert alert-danger" role="alert">
                        ${error}
                    </div>
                </c:if>
                <form:form action="register" method="post" modelAttribute="member">
                    <div class="mb-3">
                        <label for="username" class="form-label">用戶名</label>
                        <form:input type="text" class="form-control" id="username" name="username"
                            path="username" placeholder="輸入用戶名" required="required" />
                    </div>
                    <div class="mb-3">
                        <label for="password" class="form-label">密碼</label>
                        <form:input type="password" class="form-control" id="password" name="password"
                            path="password" placeholder="輸入密碼" required="required" />
                    </div>
                    <div class="mb-3">
                        <label for="confirmPassword" class="form-label">確認密碼</label>
                        <form:input type="password" class="form-control" id="confirmPassword"
                            name="confirmPassword" path="confirmPassword" placeholder="再次輸入密碼" required="required" />
                    </div>
                    <div class="d-grid">
                        <button type="submit" class="btn btn-primary">註冊</button>
                    </div>
                </form:form>
            </div>
        </div>
    </div>
</body>
</html>
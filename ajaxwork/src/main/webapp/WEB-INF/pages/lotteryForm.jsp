<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Lottery Settings</title>
    <link href="<%= request.getContextPath() %>/style/myStyle.css" rel="stylesheet" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU"
        crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ"
        crossorigin="anonymous"></script>
</head>
<body>
    <jsp:include page="navBar.jsp" /> <!-- 包含導覽列 -->
    <div class="container mt-5">
        <h2 class="text-center">大樂透號碼</h2>
        <c:if test="${not empty error}">
            <div class="alert alert-danger" role="alert">
                ${error}
            </div>
        </c:if>
        <form:form method="POST" action="lotteryGenerate" modelAttribute="lotteryForm"
            class="needs-validation">
            <div class="mb-3">
                <form:label path="groupCount" class="form-label">組數</form:label>
                <form:input path="groupCount" type="number" class="form-control" required="required" />
                <div class="invalid-feedback">
                    請輸入組數
                </div>
            </div>
            <div class="mb-3">
                <form:label path="excludeNumberString" class="form-label">要排除的數字</form:label>
                <form:input path="excludeNumberString" type="text" class="form-control"
                    placeholder="例如:1 16 10" />
                <div class="form-text">請輸入要排除的數字，以空格分隔</div>
            </div>
            <div class="text-center">
                <button type="submit" class="btn btn-primary">開獎</button>
            </div>
        </form:form>
        <div class="mt-3 text-center">
            <a href="<%= request.getContextPath() %>/index.jsp" class="btn btn-secondary">回首頁</a>
        </div>
    </div>
</body>
</html>
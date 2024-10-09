<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="zh-TW">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>錯誤</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ"
        crossorigin="anonymous"></script>
</head>
<body>
    <div class="container mt-5">
        <h1 class="text-center">發生錯誤</h1>
        <div class="alert alert-danger text-center" role="alert">
            <h4 class="alert-heading">抱歉！</h4>
            <p>您所請求的頁面無法顯示。請稍後再試或聯繫管理員。</p>
            <hr>
            <p class="mb-0">錯誤訊息：${ exception.message }</p>
            <p class="mb-0">錯誤原因：${ exception.getClass().getName()}</p>
            <p class="mb-0">錯誤追蹤：</p>
            <pre class="text-start">
                <c:forEach var="trace" items="${ exception.getStackTrace() }">
                    ${ trace }
                </c:forEach>
            </pre>
        </div>
        <div class="text-center">
            <a class="btn btn-primary" href="<%= request.getContextPath() %>/index.jsp">回到首頁</a>
        </div>
    </div>
</body>
</html>
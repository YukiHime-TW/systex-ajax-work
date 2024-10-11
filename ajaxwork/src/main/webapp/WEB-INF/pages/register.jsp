<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="zh-TW">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>註冊</title>
    <!-- 加載 jQuery -->
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <!-- 加載 Bootstrap -->
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
        <h2 id="registerTitle" class="text-center mb-4">註冊帳戶 (Form)</h2>
        <!-- 選擇提交方式的切換按鈕 -->
        <div class="text-center mb-3">
            <button id="toggleSubmitType" class="btn btn-info">使用 AJAX 提交</button>
        </div>
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
    <script>
        let useAjax = false;
        // 點擊切換提交方式
        $('#toggleSubmitType').click(function () {
            useAjax = !useAjax;
            // 隱藏錯誤訊息
            $('#errorMessage').hide();
            $('#errorFormMessage').hide();

            if (useAjax) {
                $('#toggleSubmitType').text('使用表單提交');
                $('#loginForm').attr('onsubmit', 'submitViaAjax(event)'); // 使用 AJAX 提交
                $('#registerTitle').text('註冊帳戶 (Ajax)');
            } else {
                $('#toggleSubmitType').text('使用 AJAX 提交');
                $('#loginForm').removeAttr('onsubmit'); // 恢復表單提交
                $('#registerTitle').text('註冊帳戶 (Form)');
            }
        });
        // 使用 AJAX 提交的函數
        function submitViaAjax(event) {
            event.preventDefault(); // 阻止表單的默認提交行為
            $.ajax({
                type: 'POST',
                url: '${pageContext.request.contextPath}/login',
                data: {
                    username: $('#username').val(),
                    password: $('#password').val()
                },
                success: function (response) {
                    // 成功登入，重定向到首頁
                    window.location.href = '${pageContext.request.contextPath}/index.jsp';
                },
                error: function (xhr) {
                    // 取出錯誤訊息並顯示
                    try {
                        $('#errorMessage').text(xhr.responseJSON.message).show();
                    } catch (e) {
                        $('#errorMessage').text('發生未知錯誤，請稍後再試。').show();
                    }
                }
            });
        }
    </script>
</body>
</html>
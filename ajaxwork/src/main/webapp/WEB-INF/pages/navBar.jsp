<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
        <a class="navbar-brand" href="<%= request.getContextPath() %>/index.jsp">遊樂場</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav"
            aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav">
                <c:if test="${not empty sessionScope.loggedInUser}">
                    <li class="nav-item">
                        您已登入為 ${sessionScope.loggedInUser.username}
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="logout">登出</a>
                    </li>
                </c:if>
                <c:if test="${empty sessionScope.loggedInUser}">
                    <li class="nav-item">
                        <a class="nav-link" href="login">登入</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="register">註冊</a>
                    </li>
                </c:if>
            </ul>
        </div>
    </div>
</nav>
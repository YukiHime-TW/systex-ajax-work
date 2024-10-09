<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.TreeSet" %>
<!DOCTYPE html>
<html lang="zh-Hant">
<head>
    <meta charset="UTF-8">
    <title>Lottery Result</title>
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
        <!-- 確保 result 是有效的 -->
        <% ArrayList<TreeSet<Integer>> result = (ArrayList<TreeSet<Integer>>)
                request.getAttribute("result");
                if (result != null && !result.isEmpty()) {
                %>
                <table class="table table-bordered table-striped mt-4">
                    <thead class="table-light">
                        <tr>
                            <th>組別</th>
                            <th>號碼</th>
                        </tr>
                    </thead>
                    <tbody>
                        <% for (int i=0; i < result.size(); i++) { TreeSet<Integer> group = result.get(i);
                            %>
                            <tr>
                                <td>第 <%= i + 1 %> 組</td>
                                <td>
                                    <% boolean first=true; for (Integer num : group) { if (!first) {
                                        out.print(", "); // 在號碼之間添加逗號
                                    }
                                    out.print(num);
                                    first = false; // 第一次顯示後設為 false
                                } %>
                                </td>
                            </tr>
                        <% } %>
                    </tbody>
                </table>
        <% } else { %>
            <div class=" alert alert-warning mt-4" role="alert">無法顯示結果，請重新嘗試。</div>
        <% } %>
    </div>
    <div class="text-center mt-4">
        <a class="btn btn-primary" href="<%= request.getContextPath() %>/index.jsp">回首頁</a>
    </div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="UTF-8">
    <title>관리자</title>
    <link rel="stylesheet" href="/css/bootstrap.min.css">
</head>
<body>
<div>
    <nav class="navbar navbar-dark sticky-top bg-dark ">
        <div class="container">
            <a class="text-light" href="/admin"><h4>KosmoFurniture 관리자페이지</h4></a>
            <ul class="nav justify-content-end">
<%--                <li class="nav-item"><a class="nav-link text-light" href="/login">로그인</a>--%>
<%--                </li>--%>
<%--                <li class="nav-item"><a class="nav-link text-light" href="/users">회원가입</a>--%>
<%--                </li>--%>
<%--                <li class="nav-item">--%>
<%--                    <form action="/logout" method="post">--%>
<%--                        <!--                        <input type="hidden"-->--%>
<%--                        <!--                               th:name="${_csrf.parameterName}"-->--%>
<%--                        <!--                               th:value="${_csrf.token}"/>-->--%>
<%--                        <input class="nav-link text-light" type="submit" value="로그아웃"--%>
<%--                               style="background: none; border: none;">--%>
<%--                    </form>--%>
<%--                </li>--%>
                <li class="nav-item"><a class="nav-link text-light" href="/admin">HOME</a></li>
            </ul>
        </div>
    </nav>
</div>

<nav class="col-md-2 d-none d-md-block bg-light sidebar">
    <div class="sidebar-sticky">
        <ul class="nav flex-column">
            <a class="nav-item">
                <div style="padding-top:10px;" class="nav flex-column nav-pills" aria-orientation="vertical">
                    <a href="/admin/products?section=&search=&pageNum=1&pageSize=10" style="margin:5px;" class="nav-link active">상품목록</a>
                    <a href="admin/map" style="margin:5px;" class="nav-link active">지점목록</a>
                </div>
            </a>
            </li>
        </ul>
    </div>
</nav>
</body>
</html>
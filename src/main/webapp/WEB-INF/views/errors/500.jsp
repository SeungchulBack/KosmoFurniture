<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="UTF-8">
    <title>에러페이지</title>
    <link rel="stylesheet" href="/css/bootstrap.min.css">
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-md-12">
            <div class="error-template">
                <h3>에러페이지</h3>
                <h4>에러코드 : <c:if test="${empty errorCode}">500</c:if> ${errorCode} <c:if test="${empty errorStatus}">INTERNAL_SERVER_ERROR</c:if> ${errorStatus} </h4>
                <div class="error-details">
                    <div>메세지 : <c:if test="${empty errorMessage}">서버에 오류가 발생하였습니다.</c:if> ${errorMessage}</div>
                </div>

                <div class="text-center">
                    <h1 class="mb-2" >KosmoFurniture</h1>
                    <div class="error-actions">
                        <a href="http://localhost:8484/admin" class="btn btn-primary btn-lg">홈으로</a>
                        <button onclick="history.back()" class="btn btn-default btn-lg">뒤로가기</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<script src="/js/jquery-3.3.1.js"></script>
<script src="/js/bootstrap.min.js"></script>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>관리자</title>
    <link rel="stylesheet" href="/css/bootstrap.min.css">
</head>
<body>
<jsp:include page="layout/header.jsp"/>

<div class="container-fluid">
    <div class="row">
        <jsp:include page="layout/left_nav.jsp" />
        <div class="col">
            <h1> 관리자 메인페이지 </h1>
        </div>
    </div>
</div>
</body>
</html>
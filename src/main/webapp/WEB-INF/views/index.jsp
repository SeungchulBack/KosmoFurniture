<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
</head>
<%! int count = 0; %>
<body>

<h2>JSP페이지</h2>

Page Count is :

<% out.println(++count); %>


</body>
</html>
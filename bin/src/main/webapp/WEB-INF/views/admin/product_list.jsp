<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="UTF-8">
    <title>관리자 상품 목록</title>
    <link rel="stylesheet" href="/css/bootstrap.min.css">
<body>
<div class="container">
    <table class="table">
        <thead class="thead-light">
        <tr class="text-center">
            <th scope="col">ID</th>
            <th scope="col">이름</th>
            <th scope="col">상세설명</th>
            <th scope="col">가격</th>
            <th scope="col">카테고리</th>
            <th scope="col">수량</th>
            <th scope="col">이미지</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="product" items="${products}">
            <tr>
                <td>
                    ${product.productId}
                </td>
                <td>
                    <a href="/admin/products/${product.productId}">${product.name}</a>
                </td>
                <td>
                        ${product.description}
                </td>
                <td>
                        ${product.price}
                </td>
                <td>
                        ${product.category}
                </td>
                <td>
                        ${product.stock}
                </td>
                <td>
                    <c:forEach var="image" items="${images}">
                        <c:if test="${product.productId == image.productId}">
                            <img src="/files/${image.dbFileName}" style="width:200px;height:auto">
                        </c:if>
                    </c:forEach>
                </td>
            </tr>
        </c:forEach>
<%--        <tr class="text-center" th:each="post : ${postList}">--%>
<%--            <th scope="row">--%>
<%--                <span th:text="${post.id}"></span>--%>
<%--            </th>--%>
<%--            <td>--%>
<%--                <a th:href="@{'/post/' + ${post.id}}">--%>
<%--                    <img th:if="${!post.photoDtos.isEmpty()}" th:src="@{${'/files/' + post.photoDtos[0].filename}}" width="120"/>--%>
<%--                </a>--%>
<%--            </td>--%>
<%--            <td>--%>
<%--                <a th:href="@{'/post/' + ${post.id}}">--%>
<%--                    <span th:text="${post.title}"></span>--%>
<%--                </a>--%>
<%--            </td>--%>
<%--            <td>--%>
<%--                <span th:text="${post.author}"></span>--%>
<%--            </td>--%>
<%--            <td>--%>
<%--                <span th:text="${#temporals.format(post.createdDate, 'yyyy-MM-dd HH:mm')}"></span>--%>
<%--            </td>--%>
<%--        </tr>--%>
        </tbody>
    </table>
    <div class="row">
        <div class="col-auto mr-auto"></div>
        <div class="col-auto">
            <a class="btn btn-primary" th:href="@{/post}" role="button">글쓰기</a>
        </div>
    </div>
</div>
</body>
</html>

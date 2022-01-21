<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>장바구니</title>
    <link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css"
          integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p"
          crossorigin="anonymous"/>
    <link rel="stylesheet" href="/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/style.css">
</head>
<body>

<%--모달 로그인 팝업--%>
<div
        class="modal fade"
        id="modalLoginForm"
        tabindex="-1"
        role="dialog"
        aria-labelledby="myModalLabel"
        aria-hidden="true"
>
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <form id="loginForm">
                <div class="login-div">
                    <ul class="top">
                        <li>
                            <label for="txt1">아이디</label
                            ><input type="text" id="account"/>
                        </li>
                        <li>
                            <label for="txt2">비밀번호</label
                            ><input type="password" id="pwd"/>
                        </li>
                        <li>
                            <button data-dismiss="modal" id="login">로그인</button>
                        </li>
                        <li class="save">
                            <input type="checkbox" id="check1"/><label for="check1"
                        >아이디저장</label
                        >
                        </li>
                    </ul>
                    <button class="btm">
                        <a id="mem" href="member.html">회원가입</a>
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

<!--Header Start-->
<section id="header">
    <a href="#"><img src="/img/logo.png" class="logo" alt=""></a>
    <div>
        <ul id="navbar">
            <li><a href="http://localhost:3000">Home</a></li>
            <li><a href="#" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-expanded="false">
                Shop </a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                    <a class="dropdown-item" href="#">Chair</a>
                    <a class="dropdown-item" href="#">Bed</a>
                    <a class="dropdown-item" href="#">Table</a>
                </div>
            </li>
            <li><a href="about.html">About</a></li>
            <li><a href="contact.html">Contact</a></li>
            <li>
                <sec:authorize access="isAuthenticated()">
                    <div id="logonUser">
                        <a
                                href="#"
                                id="navbarDropdownMenuLink"
                                data-toggle="dropdown"
                                aria-expanded="false"
                        >
                            <span><i class="fas fa-user fa-lg"></i></span>
                        </a>
                        <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                            <a class="dropdown-item" href="#"><sec:authentication property="principal.fullName"/>님</a>
                            <a class="dropdown-item" href="#">내 정보</a>
                            <a class="dropdown-item" href="#" onclick="moveToCart(); return false;"
                            >장바구니</a>
                            <a class="dropdown-item" href="#" onclick="moveToOrder(); return false;">주문조회</a>
                            <a class="dropdown-item" href="#"
                            >
                                <form action="/logout" method="post">
                                    <input type="hidden"
                                           name="${_csrf.parameterName}"
                                           value="${_csrf.token}"/>
                                    <input class=" btn btn-secondary" type="submit" value="로그아웃">
                                </form>
                            </a
                            >
                        </div>
                    </div>
                </sec:authorize>
                <sec:authorize access="isAnonymous()">
                    <div>
                        <a
                                id="login_button"
                                href=""
                                data-toggle="modal"
                                data-target="#modalLoginForm"
                        >
                            <i class="far fa-user fa-lg"></i
                            ></a>
                    </div>
                </sec:authorize>
            </li>
        </ul>
    </div>
</section>
<!--Header End-->
<div class="jumbotron m-4">
    <h3 class="text-center mb-4 mt-n4 text-secondary">주문목록</h3>
    <table class="table">
        <thead class="thead-light">
        <tr class="text-center">
            <th class="col-5" scope="col">주문번호</th>
            <th class="col-2" scope="col">주문상태</th>
            <th class="col-2" scope="col">주문시각</th>
            <th class="col-3" scope="col">이미지</th>
        </tr>
        </thead>
        <tbody class="text-center">
        <c:forEach var="order" items="${orders}">
            <tr>
                <td class="align-middle">
                    <a href="/shop/order/${order.orderId}">${order.orderId}</a>
                </td>
<%--                <td class="align-middle">--%>
<%--                    <a href="/admin/products/${order.product.productId}">${order.product.name}</a>--%>
<%--                </td>--%>
                <td class="align-middle">
                        ${order.orderStatus}
                </td>
                <td class="td-description align-middle">
                        ${order.createdAt}
                </td>
<%--                <td>--%>
<%--                    <img src="/files/${order.imageFileName}" style="width:80px;height:auto">--%>
<%--                </td>--%>
            </tr>
        </c:forEach>
        </tbody>
    </table>

</div>
<script src="/js/jquery-3.3.1.js"></script>
<script src="/js/bootstrap.bundle.js"></script>
<script src="/js/login.js"></script>
<script src="/js/navbar.js"></script>
</body>
</html>
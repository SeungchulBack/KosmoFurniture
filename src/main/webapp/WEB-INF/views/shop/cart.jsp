<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>장바구니</title>
    <link rel="stylesheet" href="/css/style.css">
    <link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css"
          integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p"
          crossorigin="anonymous"/>
    <link rel="stylesheet" href="/css/bootstrap.min.css">
</head>
<body>

<%--모달 로그인 팝업--%>
<div class="modal fade" id="modalLoginForm" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header text-center">
                <h4 class="modal-title w-100 font-weight-bold">Sign in</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body mx-3">
                <div class="md-form mb-5">
                    <i class="fas fa-envelope prefix grey-text"></i>
                    <input type="email" id="defaultForm-email" class="form-control validate">
                    <label data-error="wrong" data-success="right" for="defaultForm-email">Your email</label>
                </div>
                <div class="md-form mb-4">
                    <i class="fas fa-lock prefix grey-text"></i>
                    <input type="password" id="defaultForm-pass" class="form-control validate">
                    <label data-error="wrong" data-success="right" for="defaultForm-pass">Your password</label>
                </div>
            </div>
            <div class="modal-footer d-flex justify-content-center">
                <button class="btn btn-default">Login</button>
            </div>
        </div>
    </div>
</div>

<!--Header Start-->
<section id="header">
    <a href="#"><img src="/img/logo.png" class="logo" alt=""></a>
    <div>
        <ul id="navbar">
            <li><a href="index.html">Home</a></li>
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
            <li><a class="active" href="/shop/cart"><i class="fas fa-shopping-bag"></i></a></li>
            <li><a href="" class="btn btn-default btn-rounded"
                   data-toggle="modal" data-target="#modalLoginForm">
                <i class="fas fa-user"></i></a></li>
            <form action="/logout" method="post">
                <input type="hidden"
                       name="${_csrf.parameterName}"
                       value="${_csrf.token}"/>
                <input class="nav-link text-light" type="submit" value="로그아웃">
            </form>
        </ul>
    </div>
</section>
<!--Header End-->
<div class="jumbotron m-4">
    <table class="table">
        <thead class="thead-light">
        <tr class="text-center">
            <th class="col-6" scope="col">상품명</th>
            <th class="col-2" scope="col">가격</th>
            <th class="col-1" scope="col">수량</th>
            <th class="col-3" scope="col">이미지</th>
        </tr>
        </thead>
        <tbody class="text-center">
        <c:forEach var="cart" items="${cartDtoList}">
            <tr>
                <td class="align-middle">
                    <a href="/admin/products/${cart.product.productId}">${cart.product.name}</a>
                </td>
                <td class="align-middle">
                        ${cart.product.price}
                </td>
                <td class="td-description align-middle">
                        ${cart.quantity}
                </td>
                <td>
                    <img src="/files/${cart.imageFileName}" style="width:150px;height:auto">
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <div class="text-center mt-3">
        <form action="/shop/order" method="post">
            <input
                    name="${_csrf.parameterName}"
                    type="hidden"
                    value="${_csrf.token}"
            />
        <button class="btn btn-dark">주문하기</button>
        </form>
    </div>

</div>
<script src="/js/jquery-3.3.1.js"></script>
<script src="/js/bootstrap.bundle.js"></script>
</script>
</body>
</html>
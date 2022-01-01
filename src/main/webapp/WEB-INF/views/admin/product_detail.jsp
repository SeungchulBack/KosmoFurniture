<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="_csrf" content="${_csrf.token}"/>
    <meta name="_csrf_header" content="${_csrf.headerName}"/>
    <title>상품 상세 보기</title>
    <link rel="stylesheet" href="/css/bootstrap.min.css">
    <script src="/js/jquery-3.3.1.js">
    </script>
    <script>
        <%--$(function () {--%>
        <%--    $(".favorite").click(function () {--%>
        <%--        $.ajax({--%>
        <%--            url: './check_favorite_like.zoo',--%>
        <%--            type: 'post',--%>
        <%--            data: {--%>
        <%--                'favorite': $("#favorite").val(),--%>
        <%--                'board_num': '${board_num}'--%>
        <%--            },--%>
        <%--            success: function (data) {--%>
        <%--                console.log(data)--%>
        <%--                if (data == 1) {--%>
        <%--                    alert("즐겨찾기 추가 했습니다.");--%>
        <%--                    $(".favorite").attr("src", "./resources/image/icon/star_on.png");--%>
        <%--                    $("#favorite").val("none");--%>

        <%--                } else if (data == 0) {--%>
        <%--                    alert("즐겨 찾기 해제 했습니다.")--%>
        <%--                    $(".favorite").attr("src", "./resources/image/icon/star_off.png");--%>
        <%--                    $("#favorite").val("favorite");--%>
        <%--                }--%>
        <%--            }--%>
        <%--        })--%>
        <%--    })--%>
        <%--})--%>

        //삭제 확인 후 삭제하기
        var token = $("meta[name='_csrf']").attr("content");
        var header = $("meta[name='_csrf_header']").attr("content");

        function check_delete() {
            var answer = confirm("정말 삭제하시 겠습니까?");
            if (answer) {
                $.ajax({
                    url: '/admin/products/${product.productId}/delete',
                    type: 'delete',
                    beforeSend : function(xhr){
                        <%--xhr.setRequestHeader(${_csrf.headerName}, ${_csrf.token});--%>
                        xhr.setRequestHeader(header, token);
                    },
                    <%--data: {--%>
                    <%--    'productId': '${product.productId}'--%>
                    <%--},--%>
                    success: function (data) {
                        console.log(data)
                        var result = JSON.parse(data);
                        if (result.isDeleted == "true") {
                            alert("상품이 삭제되었습니다.");
                            location.href='/admin/products';
                        } else {
                            alert("오류가 발생했습니다.");
                        }
                    }
                })
            }
        }
    </script>
    <style>
        .carousel-inner > .item > img {
            margin: 0 auto;
            max-width: 100%
        }
        .td-description {
            max-width: 0;
            word-break: break-all;
        }

        /* 	.item{	width:500px} */
    </style>
</head>
<body>

<jsp:include page="layout/header.jsp"/>

<div class="container m-0">
    <div class="row align-items-start">
        <jsp:include page="layout/left_nav.jsp" />
        <div class="col">

            <div class="table-responsive">
                <table class="table table-bordered">
                    <tr>
                        <td class="col-md-1">상품명</td>
                        <td class="col-md-11">${product.name}</td>
                    </tr>
                    <tr>
                        <td>카테고리</td>
                        <td>${product.category}</td>
                    </tr>
                    <tr>
                        <td colspan="2">상세설명 및 이미지</td>
                    </tr>
                    <tr>
                        <td colspan="2" class="td-description">
                            ${product.description}
                            <br><br><br>
                            <c:if test="${!empty images}">
                                <!-- 이미지 들어가는 부분 start-->
                                <div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel">

                                        <%--                        <ol class="carousel-indicators">--%>
                                        <%--                            <c:forEach var="image" items="${images}" varStatus="a">--%>
                                        <%--                                <c:choose>--%>
                                        <%--                                    <c:when test="${a.index==0 }">--%>
                                        <%--                                        <li data-target="#carouselExampleIndicators" data-slide-to="${a.index }"--%>
                                        <%--                                            class="active"></li>--%>
                                        <%--                                    </c:when>--%>
                                        <%--                                    <c:otherwise>--%>
                                        <%--                                        <li data-target="#carouselExampleIndicators" data-slide-to="${a.index }"></li>--%>
                                        <%--                                    </c:otherwise>--%>
                                        <%--                                </c:choose>--%>
                                        <%--                            </c:forEach>--%>
                                        <%--                        </ol>--%>

                                    <div class="carousel-inner">
                                        <c:out value="이미지 갯수 : ${images.size()}"></c:out>
                                        <c:forEach var="image" items="${images}" varStatus="a">

                                            <%--                                <c:choose>--%>
                                            <%--                                    <c:when test="${a.index==0 }">--%>
                                            <%--                                        <div class="item active">--%>
                                            <%--                                            <img class="d-block w-50" src="/files/${image.dbFileName}">--%>
                                            <%--                                        </div>--%>
                                            <%--                                    </c:when>--%>
                                            <%--                                    <c:otherwise>--%>
                                            <div class="item active">
                                                <img class="d-block w-50" src="/files/${image.dbFileName}">
                                            </div>
                                            <%--                                    </c:otherwise>--%>
                                            <%--                                </c:choose>--%>

                                        </c:forEach>
                                    </div>

                                        <%--                        <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button"--%>
                                        <%--                           data-slide="prev">--%>
                                        <%--                            <span class="carousel-control-prev-icon" aria-hidden="true"></span>--%>
                                        <%--                            <span class="sr-only">Previous</span>--%>
                                        <%--                        </a>--%>
                                        <%--                        <a class="carousel-control-next" href="#carouselExampleIndicators" role="button"--%>
                                        <%--                           data-slide="next">--%>
                                        <%--                            <span class="carousel-control-next-icon" aria-hidden="true"></span>--%>
                                        <%--                            <span class="sr-only">Next</span>--%>
                                        <%--                        </a>--%>
                                </div>
                                <!-- 이미지 들어가는 부분 end-->
                            </c:if>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <div class="row">
                                <input type="button" value='수정하기' onclick="location.href='/admin/products/${product.productId}/update'"
                                       class="btn btn-info m-1">
                                <input type="button" onclick="check_delete()" value="삭제하기" class="btn btn-info m-1">
                                <input type="button" value='일반삭제' onclick="location.href='/admin/products/delete-${product.productId}'"
                                       class="btn btn-info m-1">
                                <input type="button" onclick="history.back()" value="뒤로가기" class="btn btn-info m-1">
                            </div>
                        </td>
                    </tr>
                </table>

            </div>
        </div>
    </div>
</div>

<script src="/js/bootstrap.min.js"></script>
</body>
</html>
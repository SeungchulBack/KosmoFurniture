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
    <title>고객질문 보기</title>
    <link rel="stylesheet" href="/css/bootstrap.min.css">
    <script src="/js/jquery-3.3.1.js">
    </script>
    <script>
        //삭제 확인 후 삭제하기
        var token = $("meta[name='_csrf']").attr("content");
        var header = $("meta[name='_csrf_header']").attr("content");

        function check_delete() {
            var answer = confirm("정말 삭제하시겠습니까?");
            if (answer) {
                $.ajax({
                    url: '/admin/serviceboard/${serviceboard.serviceboardId}/delete',
                    type: 'delete',
                    beforeSend : function(xhr){
                        xhr.setRequestHeader(header, token);
                    },
                    success: function (data) {
                        console.log(data)
                        var result = JSON.parse(data);
                        if (result.isDeleted == "true") {
                            alert("고객질문이 삭제되었습니다.");
                            location.href='/admin/serviceboard?pageNum=1&pageSize=5';
                        } else {
                            alert("오류가 발생했습니다.");
                        }
                    }
                })
            }
        }

        $('#serviceboard').addClass('btn-info')
    </script>
</head>
<body>

<jsp:include page="layout/header.jsp"/>

<div class="container-fluid ">
    <div class="row align-items-start">
        <jsp:include page="layout/left_nav.jsp" />
        <div class="col">

            <div class="table-responsive">
                <table class="table table-bordered">
                    <tr>
                        <td class="col-md-1">제목</td>
                        <td class="col-md-11">${serviceboard.title}</td>
                    </tr>
                    <tr>
                        <td class="col-md-1">글쓴이</td>
                        <td class="col-md-11">${serviceboard.writer}</td>
                    </tr>
                    <tr>
                        <td class="col-md-1">멤버 ID</td>
                        <td class="col-md-11">${serviceboard.memberId}</td>
                    </tr>
                    <tr>
                        <td colspan="2">내용</td>
                    </tr>
                    <tr>
                        <td colspan="2" class="td-description">
                            ${serviceboard.content}
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <div class="row">
                                <input type="button" value='수정하기' onclick="location.href='/admin/serviceboard/${serviceboard.serviceboardId}/update'"
                                       class="btn btn-info m-1">
                                <input type="button" onclick="check_delete()" value="삭제하기" class="btn btn-info m-1">
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
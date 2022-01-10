<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="UTF-8">
    <title>계정등록페이지</title>
    <link rel="stylesheet" href="/css/bootstrap.min.css">
</head>
<body>
<div class="container d-flex justify-content-center">
    <div style="width:700px; margin-top: 30px;">
        <form class="form-horizontal" action="/members/register" method="post">
            <input name="${_csrf.parameterName}" type="hidden" value="${_csrf.token}"/>
            <div class="form-group">
                <label for="account" class="col-sm-2 control-label">계정명</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control input-large" name="account" id="account" placeholder="ID"
                           required>
                    <button class="btn-dark mt-1" onclick="checkAccountId()">중복확인</button>
                </div>
            </div>
            <div class="form-group">
                <label for="fullName" class="col-sm-2 control-label">성명</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control input-large" name="fullName" id="fullName" placeholder="name"
                           required>
                </div>
            </div>
            <div class="form-group">
                <label for="pwd" class="col-sm-2 control-label">비밀번호</label>
                <div class="col-sm-10">
                    <input type="password" class="form-control input-large" name="pwd" id="pwd" placeholder="Password"
                           data-minlength="6" required>
                </div>
            </div>
            <div class="form-group">
                <label for="email" class="col-sm-2 control-label">이메일</label>
                <div class="col-sm-10">
                    <input type="email" class="form-control input-large" name="email" id="email" placeholder="이메일"
                           required>
                </div>
            </div>
            <div class="form-group">
                <label for="phone" class="col-sm-2 control-label">휴대폰</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control input-large" name="phone" id="phone"
                           placeholder="01012345678" required>
                </div>
            </div>
            <div class="form-group">
                <label for="address" class="col-sm-8 control-label">주소</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control input-large" name="address" id="address" placeholder="주소"
                           required>
                </div>
            </div>
            <div class="form-group">
                <label for="ssn" class="col-sm-8 control-label">주민등록번호(000000-0000000)</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control input-large" name="ssn" id="ssn" placeholder="주민등록번호"
                           required>
                </div>
            </div>
            <div class="form-group">
                <label for="role" class="col-sm-2 control-label">권한</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control input-large" name="role" id="role" placeholder="권한" required>
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-1 col-sm-10">
                    <button type="Submit" class="btn btn-dark btn-lg">가입하기</button>
                </div>
            </div>
        </form>
    </div>
</div>
</body>
<script>
    var token = '${_csrf.parameterName}';
    var header = '${_csrf.token}';

    function checkAccountId() {
        var accountCheckUrl = '/members/check/' + $('#account').val()
        $.ajax({
            url: accountCheckUrl,
            type: 'get',
            beforeSend: function (xhr) {
                <%--xhr.setRequestHeader(${_csrf.headerName}, ${_csrf.token});--%> //오류난다 왜인지 모르겠지만
                xhr.setRequestHeader(header, token);
            },
            success: function (data) {
                console.log(data)
                var result = JSON.parse(data);
                if (result.accountExists == "true") {
                    alert("아이디가 이미 존재합니다! 다른 아이디를 입력해주세요!");
                    $('#account').val("")
                } else {
                    alert("사용가능한 아이디입니다!");
                }
            }
        })
    }
</script>
<script src="/js/jquery-3.3.1.js">
<script src="/js/bootstrap.min.js"></script>
</script>
</html>
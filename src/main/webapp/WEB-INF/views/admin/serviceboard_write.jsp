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
    <title>고객질문 작성</title>
    <link rel="stylesheet" href="/css/bootstrap.min.css">
</head>
<body>

<jsp:include page="layout/header.jsp"/>

<div class="container-fluid">
    <div class="row align-items-start">
        <jsp:include page="layout/left_nav.jsp" />
        <div class="col-10">
			<div class="table-responsive">
                <form
                        method="post"
                        action="/admin/serviceboard"
                        enctype="multipart/form-data"
                        class="serviceboard"
                >
                    <input
                            name="${_csrf.parameterName}"
                            type="hidden"
                            value="${_csrf.token}"
                    />
                    <table class="table table-hover table-bordered">
                        <tr>
                            <td>제목</td>
                            <td>
                                <input
                                        style="width: 25%"
                                        type="text"
                                        name="title"
                                        id="title"
                                />
                            </td>
                        </tr>
                        <tr>
                            <td>글쓴이</td>
                            <td>
                                <input
                                        style="width: 25%"
                                        type="text"
                                        name="writer"
                                        id="writer"
                                        value="${principal.account}"
                                        readonly
                                />
                            </td>
                        </tr>
                        <tr>
                            <td>내용</td>
                            <td>
			                    <textarea
			                            style="resize: none; width: 100%"
			                            rows="10"
			                            name="content"
			                            id="content"
			                    ></textarea>
                            </td>
                        </tr>
                        <!-- 
                        <tr>
                            <td>memberId</td>
                            <td>
                                <input
                                        style="width: 25%"
                                        type="hidden"
                                        name="writer"
                                        id="writer"
                                        value="${principal.memberId}"
                                        readonly
                                />
                            </td>
                        </tr>
                        -->
                    </table>
                    <div id="serviceboard">
                        <input
                                type="submit"
                                value="등록"
                                class="input_button btn btn-info"
                        />
                        <input
                                type="reset"
                                value="취소"
                                class="input_button btn btn-info"
                                onclick="history.back()"
                        />
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
<script src="/js/jquery-3.3.1.js"></script>
<script src="/js/bootstrap.min.js"></script>
</html>
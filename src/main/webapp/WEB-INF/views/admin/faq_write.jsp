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
    <title>FAQ 작성</title>
    <link rel="stylesheet" href="/css/bootstrap.min.css">
</head>
<body>

<jsp:include page="layout/header.jsp"/>

<div class="container-fluid">
    <div class="row">
        <jsp:include page="layout/left_nav.jsp" />
        <div class="col-10">
			<div class="table-responsive">
                <form
                        method="post"
                        action="/admin/faq"
                        enctype="multipart/form-data"
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
                                        type="text"
                                        name="title"
                                        id="title"
                                        class="w-100"
                                />
                            </td>
                        </tr>
                        <tr>
                            <td>글쓴이</td>
                            <td>
                                <input
                                        type="text"
                                        name="writer"
                                        id="writer"

                                        class="w-100"
                                />
                            </td>
                        </tr>
                        <tr>
                            <td>내용</td>
                            <td>
			                    <textarea
			                            rows="10"
			                            name="content"
			                            id="content"
                                        class="w-100"
			                    ></textarea>
                            </td>
                        </tr>
                    </table>
                    <div class="d-flex justify-content-center">
                        <input
                                type="submit"
                                value="등록"
                                class="btn btn-info m-1"
                        />
                        <input
                                type="reset"
                                value="취소"
                                class="btn btn-info m-1"
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
<script>
    $('#faqList').addClass('btn-info')
</script>
</html>
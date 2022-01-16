<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <meta name="_csrf" content="${_csrf.token}"/>
    <meta name="_csrf_header" content="${_csrf.headerName}"/>
    <title>관리자 공지수정페이지</title>
    <script src="/js/jquery-3.3.1.js"></script>
    <script src="/js/jquery.form.js"></script>
    <script src="/js/jquery.MultiFile.js"></script>
    <script>
        // ajax 관련 로직들
        var token = $("meta[name='_csrf']").attr("content");
        var header = $("meta[name='_csrf_header']").attr("content");

        function updateNotice() {
            var answer = confirm("수정하시겠습니까?");

            let title = $("#title").val();
            let content = $("#content").val();

            let noticeData = {
                'noticeId': '${notice.noticeId}',
                'title': title,
                'content': content
            };

            let formData = new FormData();

            if (answer) {
                $.ajax({
                    url: '/admin/notice/update',
                    type: 'put',
                    data: noticeData,
                    beforeSend : function(xhr){
                        xhr.setRequestHeader(header, token);
                    },
                    success: function (data) {

                        console.log("notice ajax succeed")

                        $("form").submit();

                        console.log(data)
                        var result = JSON.parse(data);
                        if (result.isUpdated == "true") {
                            alert("공지사항이 수정되었습니다.");
                            history.back(3);
                        } else {
                            alert("오류가 발생했습니다.");
                        }
                    }
                })
            }
        }
        $('#notice').addClass('btn-info')
    </script>
    <link rel="stylesheet" href="/css/bootstrap.min.css"/>
</head>
<body>
<jsp:include page="layout/header.jsp"/>

<div class="container-fluid">
    <div class="row align-items-start">
        <jsp:include page="layout/left_nav.jsp"/>
        <div class="col">
            <div class="table-responsive">

                    <table class="table table-hover table-bordered">
                        <tr>
                            <td>제목</td>
                            <td>
                                <input
                                        style="width: 100%"
                                        type="text"
                                        name="title"
                                        id="title"
                                        class="board_input_box"
                                        value="${notice.title}"
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
                            class="board_input_box"
                    >${notice.content}</textarea>
                            </td>
                        </tr>
                    </table>
                    <div id="notice">
                        <input
                                type="button"
                                onclick="updateNotice()"
                                value="저장하기"
                                class="input_button btn btn-info"
                        >
                        <input
                                type="reset"
                                value="취소"
                                class="input_button btn btn-info"
                                onclick="history.back()"
                        />
                    </div>
            </div>
        </div>
    </div>
</div>
</body>
<script src="/js/bootstrap.min.js"></script>
</html>

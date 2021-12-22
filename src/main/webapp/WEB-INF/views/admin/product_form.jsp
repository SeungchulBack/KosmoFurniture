<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>관리자 상품등록페이지</title>
    <script src="/js/jquery-3.3.1.js"></script>
    <script src="/js/jquery.form.js"></script>
    <script src="/js/jquery.MultiFile.js"></script>
    <script>
        $(function () {
            $("#uploadfiles").MultiFile({
                max: 10,
                accept: 'jpg|png|gif|jpeg',
                STRING: {
                    duplicate: "중복된 선택된 파일이 있습니다.($file)",
                    denied: "이미지 파일만 업로드 가능 합니다.",
                    // selected: "$file을 선택했습니다.",
                    toomany: "이미지는 10개 까지 업로드 가능 합니다."
                }
            })

            $('.product').submit(function () {
                if ($("#category").val() == '') {
                    alert('카테고리를 선택해주세요');
                    return false;
                }

                if ($("#name").val() == '') {
                    alert("상품명을 입력해 주세요.");
                    return false;
                }

                if ($("#description").val() == '') {
                    alert("상품설명을 입력해 주세요.");
                    return false;
                }

                if ($("#price").val() == '') {
                    alert("상품가격을 입력해 주세요.");
                    return false;
                }

                if ($("#stock").val() == '') {
                    alert("상품수량을 입력해 주세요.");
                    return false;
                }
            })
        })


    </script>
    <link rel="stylesheet" href="/css/bootstrap.min.css">
</head>
<body>

<div class="table-responsive">
    <form method="post" action="/admin/products" enctype="multipart/form-data" class="product">
        <input name="${_csrf.parameterName}" type="hidden" value="${_csrf.token}"/>
        <table class="table table-hover table-bordered">
            <tr>
                <td colspan="2" class="col-md-12">
                    <select name="category" id="category">
                        <option value="">카테고리 선택</option>
                        <option value="chair">의자</option>
                        <option value="desk">책상</option>
                        <option value="bed">침대</option>
                        <option value="sofa">소파</option>
                        <option value="etc">기타</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td class="col-md-2">글쓴이</td>
<%--                <td class="col-md-10">${sessionScope.id}</td>--%>
            </tr>
            <tr>
                <td>상품명</td>
                <td>
                    <input style="width:100%" type="text" name="name" id="name"
                           class="board_input_box">
                </td>
            </tr>
            <tr>
                <td>상품가격</td>
                <td>
                    <input style="width:100%" type="text" name="price" id="price"
                           class="board_input_box">
                </td>
            </tr>
            <tr>
                <td>상품수량</td>
                <td>
                    <input style="width:100%" type="text" name="stock" id="stock"
                           class="board_input_box">
                </td>
            </tr>
            <tr>
                <td>상품설명</td>
                <td>
                    <textarea style="resize:none;width:100%" rows="10" name="description" id="description"
                              class="board_input_box"></textarea>
                </td>
            </tr>
            <tr>
                <td>파일 첨부</td>
                <td>
                    <input type="file" id="uploadfiles" name="uploadfiles" class="with-preview">
                </td>
            </tr>
        </table>
        <div id="product">
            <input type="submit" value="등록" class="input_button btn btn-info">
            <input type="reset" value="취소" class="input_button btn btn-info" onclick="history.back()">
        </div>
    </form>

</div>
<script src="/js/bootstrap.min.js"></script>
</body>
</html>
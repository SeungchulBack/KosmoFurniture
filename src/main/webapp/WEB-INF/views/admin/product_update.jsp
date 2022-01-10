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
    <title>관리자 상품등록페이지</title>
    <script src="/js/jquery-3.3.1.js"></script>
    <script src="/js/jquery.form.js"></script>
    <script src="/js/jquery.MultiFile.js"></script>
    <script>
        $(function () {
            $('#uploadfiles').MultiFile({
                max: 10,
                accept: 'jpg|png|gif|jpeg',
                STRING: {
                    duplicate: '중복된 선택된 파일이 있습니다.($file)',
                    denied: '이미지 파일만 업로드 가능 합니다.',
                    // selected: "$file을 선택했습니다.",
                    toomany: '이미지는 10개 까지 업로드 가능 합니다.',
                },
            });

            let uploadImages = [];

            $('input[type="file"]').on('change', function(){
                uploadImages.push(this.files[0])
                console.log(uploadImages);
                console.log(document.getElementById('uploadfiles'))
            })

        });

        // ajax 관련 로직들
        var token = $("meta[name='_csrf']").attr("content");
        var header = $("meta[name='_csrf_header']").attr("content");

        var imageForm = document.imageForm;

        function updateProduct() {
            var answer = confirm("수정하시겠습니까?");

            let checkedDeleteImages = [];

            $('input[name="deleteImageCheck"]:checked').each(function (i) {
                checkedDeleteImages.push($(this).val());
            })

            let name = $("#name").val();
            let description = $("#description").val();
            let price = $("#price").val();
            let category = $("#category").val();
            let stock = $("#stock").val();

            let productData = {
                'productId': '${product.productId}',
                'name': name,
                'description': description,
                'price': price,
                'category': category,
                'stock': stock,
                'checkedDeleteImages' : checkedDeleteImages
            };

            let formData = new FormData();

            // formData.append('uploadImages', document.getElementById('uploadfiles').files[0]);
            // formData.append('product', new Blob([JSON.stringify(productData)], {type: "application/json"}));

            if (answer) {
                $.ajax({
                    url: '/admin/products/update',
                    type: 'put',
                    data: productData,
                    beforeSend : function(xhr){
                        <%--xhr.setRequestHeader(${_csrf.headerName}, ${_csrf.token});--%> //오류난다 왜인지 모르겠지만
                        xhr.setRequestHeader(header, token);
                    },
                    success: function (data) {

                        console.log("product ajax succeed")

                        $("form").submit();

                        console.log(data)
                        var result = JSON.parse(data);
                        if (result.isUpdated == "true") {
                            alert("상품이 수정되었습니다.");
                            history.back(3);
                        } else {
                            alert("오류가 발생했습니다.");
                        }
                    }
                })
            }
        }
        $('#productList').addClass('btn-info')
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
                            <td colspan="2" class="col-md-12">
                                <select name="category" id="category">
                                    <option value="">카테고리 선택</option>
                                    <option value="chair" <c:if test="${product.category == 'chair'}">selected</c:if>>의자</option>
                                    <option value="desk" <c:if test="${product.category == 'desk'}">selected</c:if>>책상</option>
                                    <option value="bed" <c:if test="${product.category == 'bed'}">selected</c:if>>침대</option>
                                    <option value="sofa" <c:if test="${product.category == 'sofa'}">selected</c:if>>소파</option>
                                    <option value="etc" <c:if test="${product.category == 'etc'}">selected</c:if>>기타</option>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td>상품명</td>
                            <td>
                                <input
                                        style="width: 100%"
                                        type="text"
                                        name="name"
                                        id="name"
                                        class="board_input_box"
                                        value="${product.name}"
                                />
                            </td>
                        </tr>
                        <tr>
                            <td>상품가격</td>
                            <td>
                                <input
                                        style="width: 100%"
                                        type="text"
                                        name="price"
                                        id="price"
                                        class="board_input_box"
                                        value="${product.price}"
                                />
                            </td>
                        </tr>
                        <tr>
                            <td>상품수량</td>
                            <td>
                                <input
                                        style="width: 100%"
                                        type="text"
                                        name="stock"
                                        id="stock"
                                        class="board_input_box"
                                        value="${product.stock}"
                                />
                            </td>
                        </tr>
                        <tr>
                            <td>상품설명</td>
                            <td>
                    <textarea
                            style="resize: none; width: 100%"
                            rows="10"
                            name="description"
                            id="description"
                            class="board_input_box"
                    >${product.description}</textarea>
                            </td>
                        </tr>
                        <tr>
                            <td>파일 첨부</td>
                            <td>
                                <form
                                        method="post"
                                        action="/admin/products/ajaxImageUpload"
                                        enctype="multipart/form-data"
                                        class="product"
                                        name="imageForm"
                                        id="imageForm"
                                >
                                    <input
                                            name="${_csrf.parameterName}"
                                            type="hidden"
                                            value="${_csrf.token}"
                                    />
                                    <input
                                            type="file"
                                            id="uploadfiles"
                                            name="uploadfiles"
                                            class="with-preview"
                                    />
                                    <input
                                            style="width: 100%"
                                            type="hidden"
                                            name="productId"
                                            id="productId"
                                            class="board_input_box"
                                            value="${product.productId}"
                                    />
                                </form>
                                <c:if test="${!empty images}">
                                    <!-- 이미지 들어가는 부분 start-->
                                    <div>
                                        <div>
                                            <c:forEach var="image" items="${images}" varStatus="a">
                                                <img class="d-block w-50 mt-5" src="/files/${image.dbFileName}">
                                                삭제 : <input type="checkbox" name="deleteImageCheck" value="${image.productImageId}">${image.originalFileName}
                                            </c:forEach>
                                        </div>
                                    </div>
                                </c:if>
                            </td>
                        </tr>
                    </table>
                    <div id="product">
                        <input
                                type="button"
                                onclick="updateProduct()"
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

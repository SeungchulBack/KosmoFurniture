<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<nav class="col-2 bg-light sidebar">
    <div class="nav nav-pills pt-2">
        <a id="memberList" href="/admin/members?section=&search=&pageNum=1&pageSize=10"
           class="nav-link active m-1 w-100 text-center">회원목록</a>
        <a id="productList" href="/admin/products?section=&search=&pageNum=1&pageSize=10"
           class="nav-link active m-1 w-100 text-center">상품목록</a>
        <a id="productForm" href="/admin/products/form" class="nav-link active m-1 w-100 text-center">상품등록</a>
        <a id="locationList" href="/admin/maps" class="nav-link active m-1 w-100 text-center">지점목록</a>
        <a id="locationForm" href="/admin/map" class="nav-link active m-1 w-100 text-center">지점등록</a>
        <a id="noticeList" href="/admin/notice?pageNum=1&pageSize=10"
           class="nav-link active m-1 w-100 text-center">공지사항</a>
        <a id="faqList" href="/admin/faq?pageNum=1&pageSize=10" class="nav-link active m-1 w-100 text-center">F A Q</a>
        <a id="serviceboardList" href="/admin/serviceboard?pageNum=1&pageSize=10" class="nav-link active m-1 w-100 text-center">고객질문</a>
    </div>
</nav>
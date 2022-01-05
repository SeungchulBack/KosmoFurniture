<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<nav class="col-2 bg-light sidebar ">
    <div class="sidebar-sticky">
        <ul class="nav flex-column">
            <a class="nav-item">
                <div style="padding-top:10px;" class="nav flex-column nav-pills" aria-orientation="vertical">
                    <a href="/admin/products?section=&search=&pageNum=1&pageSize=10" style="margin:5px;" class="nav-link active">상품목록</a>
                    <a href="/admin/products/form" style="margin:5px;" class="nav-link active">상품등록</a>
                    <a href="/admin/maps" style="margin:5px;" class="nav-link active">지점목록</a>
                    <a href="/admin/map" style="margin:5px;" class="nav-link active">지점등록</a>
                    <a href="/admin/notice?pageNum=1&pageSize=10" style="margin:5px;" class="nav-link active">공지사항</a>
                </div>
            </a>
            </li>
        </ul>
    </div>
</nav>
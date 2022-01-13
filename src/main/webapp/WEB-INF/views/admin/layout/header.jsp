<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<div>
    <nav class="navbar navbar-dark sticky-top bg-dark ">
        <div class="container">
            <a class="text-light" href="/admin"><h4>KosmoFurniture 관리자페이지</h4></a>
            <ul class="nav justify-content-end">
                <li class="nav-item"><a class="nav-link text-light" href="/admin">HOME</a></li>
                <li class="nav-item">
                    <form action="/logout" method="post">
                        <input type="hidden"
                               name="${_csrf.parameterName}"
                               value="${_csrf.token}"/>
                        <input class="nav-link btn btn-light" type="submit" value="로그아웃">
                    </form>
                </li>
            </ul>
        </div>
    </nav>
</div>
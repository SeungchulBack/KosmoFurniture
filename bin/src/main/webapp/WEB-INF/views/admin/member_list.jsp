<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="UTF-8">
    <title>회원 목록</title>
    <link rel="stylesheet" href="/css/bootstrap.min.css">
</head>
<body>

<jsp:include page="layout/header.jsp"/>

<div class="container-fluid">
    <div class="row align-items-start">
        <jsp:include page="layout/left_nav.jsp"/>
        <div class="col-10">
            <div class="d-flex justify-content-end">
                <form action="/admin/members" id="searchForm" method="get" name="searchDto"
                      class=" d-inline-flex m-1">
                    <select id="section" name="section">
                        <option value="name"
                                <c:if test="${param.section == 'fullname'}">selected</c:if> >이름
                        </option>
                        <option value="description"
                                <c:if test="${param.section == 'account'}">selected</c:if> >아이디
                        </option>
                        <option value="category"
                                <c:if test="${param.section == 'phone'}">selected</c:if> >폰번호
                        </option>
                    </select>
                    <input type="text" name="search" id="search" value="${param.search}">
                    <select name="pageSize" id="pageSize">
                        <option value="10"
                                <c:if test="${param.pageSize == 10}">selected</c:if> >게시글수(10줄)
                        </option>
                        <option value="3" <c:if test="${param.pageSize == 3}">selected</c:if>>3줄보기</option>
                        <option value="5" <c:if test="${param.pageSize == 5}">selected</c:if>>5줄보기</option>
                        <option value="7" <c:if test="${param.pageSize == 7}">selected</c:if>>7줄보기</option>
                        <option value="10" <c:if test="${param.pageSize == 10}">selected</c:if>>10줄보기</option>
                    </select>
                    <input type="hidden" name="pageNum" id="pageNum" value="${pageInfo.pageNum}">
                    <input type="submit" value="검색" class="btn btn-info">
                </form>
            </div>
            <table class="table">
                <thead class="thead-light">
                <tr class="text-center">
                    <th class="col-1" scope="col">ID</th>
                    <th class="col-1" scope="col">계정명</th>
                    <th class="col-1" scope="col">이름</th>
                    <th class="col-1" scope="col">이메일</th>
                    <th class="col-1" scope="col">전화번호</th>
                    <th class="col-2" scope="col">주소</th>
                    <th class="col-2" scope="col">주민등록번호</th>
                    <th class="col-2" scope="col">가입시간</th>
                    <th class="col-1" scope="col">권한</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="member" items="${members}">
                    <tr>
                        <td>
                                ${member.memberId}
                        </td>
                        <td>
                            <a href="/admin/members/${member.memberId}">${member.account}</a>
                        </td>
                        <td>
                                ${member.fullName}
                        </td>
                        <td>
                                ${member.email}
                        </td>
                        <td>
                                ${member.phone}
                        </td>
                        <td>
                                ${member.address}
                        </td>
                        <td>
                                ${member.ssn}
                        </td>
                        <td>
                                ${member.createdAt}
                        </td>
                        <td>
                                ${member.role}
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <div class="d-flex justify-content-center">
                <ul class="pagination">
                    <%--  페이징 로직 시작  --%>
                    <c:set var="pageNumInURL" value="${pageInfo.pageNum}"/>

                    <c:choose>
                        <c:when test="${pageInfo.pages < 7}">
                            <c:forEach var="i" begin="1" end="${pageInfo.pages}" step="1">
                                <c:choose>
                                    <c:when test="${i == pageNumInURL}">
                                        <li class="page-item disabled"><a class="page-link"
                                                                          href="javascript:PageMove(${i})">${i}</a>
                                        </li>
                                    </c:when>
                                    <c:otherwise>
                                        <li class="page-item"><a class="page-link"
                                                                 href="javascript:PageMove(${i})">${i}</a>
                                        </li>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </c:when>

                        <c:otherwise>
                            <c:if test="${pageInfo.pageNum < 5}">
                                <c:set target="${pageInfo}" property="pageNum" value="4"/>
                            </c:if>
                            <c:if test="${pageInfo.pageNum + 3 > pageInfo.pages}">
                                <c:set target="${pageInfo}" property="pageNum"
                                       value="${pageInfo.pages - 3}"/>
                            </c:if>
                            <li class="page-item"><a class="page-link"
                                                     href="javascript:PageMove(1)">맨앞으로</a></li>
                            <li class="page-item"><a class="page-link"
                                                     href="javascript:PageMove(${pageInfo.prePage})">앞으로</a>
                            </li>
                            <c:forEach var="i" begin="${pageInfo.pageNum - 3}" end="${pageInfo.pageNum + 3}"
                                       step="1">
                                <c:choose>
                                    <c:when test="${i == pageNumInURL}">
                                        <li class="page-item disabled"><a class="page-link"
                                                                          href="javascript:PageMove(${i})">${i}</a>
                                        </li>
                                    </c:when>
                                    <c:otherwise>
                                        <li class="page-item"><a class="page-link"
                                                                 href="javascript:PageMove(${i})">${i}</a>
                                        </li>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                            <c:if test="${pageInfo.isLastPage}">
                                <c:set target="${pageInfo}" property="nextPage" value="${pageInfo.pages}"/>
                            </c:if>
                            <li class="page-item"><a class="page-link"
                                                     href="javascript:PageMove(${pageInfo.nextPage})">뒤로</a>
                            </li>
                            <li class="page-item"><a class="page-link"
                                                     href="javascript:PageMove(${pageInfo.pages})">맨뒤로</a>
                            </li>
                        </c:otherwise>
                    </c:choose>
                    <%--  페이징 로직 끝  --%>
                </ul>
            </div>
        </div>
    </div>
</div>
</div>
</body>
<script src="/js/jquery-3.3.1.js"></script>
<script src="/js/bootstrap.min.js"></script>
<script>

    function PageMove(page) {

        let searchParams = new URLSearchParams(window.location.search)
        var section = searchParams.get('section');
        var search = searchParams.get('search');
        var pageSize = searchParams.get('pageSize');

        if (page == 0) page = 1; //0페이지로 가면 안되기때문에 page가 0이면 1로 바꿔준다

        location.href = "/admin/members?section=" + section + "&search=" + search + "&pageNum=" + page + "&pageSize=" + pageSize;
    }

    $('#memberList').addClass('btn-info')

</script>
</html>
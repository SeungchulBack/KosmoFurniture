<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="UTF-8">
    <title>공지사항</title>
    <link rel="stylesheet" href="/css/bootstrap.min.css">
</head>
<body>

<jsp:include page="layout/header.jsp"/>

<div class="container-fluid">
    <div class="row align-items-start">
        <jsp:include page="layout/left_nav.jsp" />
        <div class="col-10">
				<a class="btn btn-primary mr-5 my-1" href="/admin/notice-write" role="button">공지등록</a>
                <table class="table">
                    <thead class="thead-light">
                    <tr class="text-center">
                        <th class="col-3" scope="col">번호</th>
                        <th class="col-3" scope="col">제목</th>
                        <th class="col-3" scope="col">내용</th>
                        <th class="col-3" scope="col">작성일</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="notice" items="${noticeList}">
                        <tr>
                            <td class="text-center">
                                    ${notice.noticeId}
                            </td>
                            <td class="text-center">
                                <a href="/admin/notice/${notice.noticeId}">${notice.title}</a>
                            </td>
                            <td class="td-description text-center">
                                    ${notice.content}
                            </td>
                            <td class="text-center">
                                    ${notice.createdAt}
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <div class="toolbar-bottom">
                    <div class="toolbar mt-lg">
                        <div class="sorter">
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

        location.href = "/admin/notice?pageNum=" + page + "&pageSize=" + pageSize;
    }
    $('#noticeList').addClass('btn-info')
</script>
</html>
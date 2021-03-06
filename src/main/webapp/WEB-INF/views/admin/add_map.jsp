<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>마커 등록</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="/css/bootstrap.min.css" rel="stylesheet">
    <style>
        #map{
            margin: 10px;
            width:50%;
            float : right;
            height: 78vh;
            border-radius: 10px;
        }
        input[type=text]{
            width:35%;
        }
    </style>
</head>
<body>

<jsp:include page="layout/header.jsp" />
<div class="container-fluid">
<div class="row">
    <jsp:include page="layout/left_nav.jsp" />
    <div class="col-10">
        <form action="map" method="post" id="addMarker">
            <%-- Spring Security가 csrf토큰을 디폴트로 필요로 한다. --%>
            <input name="${_csrf.parameterName}" type="hidden" value="${_csrf.token}"/>
            <div id="map"></div>
            <div id="placeName" class="my-1">
                지점명 : <input type="text" id="Name" name="branchName">
            </div>
            <div id="placeAddress" >
                주 &ensp;소 : <input class="my-1" type="text" id="address" name="city" onclick="daumPostcode()" value="여기를 클릭하시오.">
            </div>
            <input type="hidden" id="latitude" name="latitude">
            <input type="hidden" id="longitude" name="longitude">
            <input type="submit" id="addSubmit" class="btn btn-info" value="추가하기">
        </form>
    </div>
</div>
</div>

</body>

<script src="/js/jquery-3.3.1.js"></script>
<script src="/js/bootstrap.min.js"></script>
<script src="/js/Marker.js"></script>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script type="text/javascript"
        src="//dapi.kakao.com/v2/maps/sdk.js?appkey=cdb07f97cd6753f6309ef47ab19d87b2&libraries=services"></script>
<script>
    var mapContainer = document.getElementById('map'), // 지도를 표시할 div
        mapOption = {
            center : new kakao.maps.LatLng(37.537187, 127.005476), // 지도의 중심좌표
            level : 6
            // 지도의 확대 레벨
        };

    //지도를 미리 생성
    var map = new kakao.maps.Map(mapContainer, mapOption);
    //주소-좌표 변환 객체를 생성
    var geocoder = new kakao.maps.services.Geocoder();
    //마커를 미리 생성
    var marker = new kakao.maps.Marker({
        position : new kakao.maps.LatLng(37.537187, 127.005476),
        map : map
    });

    function daumPostcode() {
        new daum.Postcode(
            {
                oncomplete : function(data) {
                    // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                    // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                    var fullAddr = data.address; // 최종 주소 변수
                    var extraAddr = ''; // 조합형 주소 변수

                    // 기본 주소가 도로명 타입일때 조합한다.
                    if (data.addressType === 'R') {
                        //법정동명이 있을 경우 추가한다.
                        if (data.bname !== '') {
                            extraAddr += data.bname;
                        }
                        // 건물명이 있을 경우 추가한다.
                        if (data.buildingName !== '') {
                            extraAddr += (extraAddr !== '' ? ', '
                                + data.buildingName
                                : data.buildingName);
                        }
                        // 조합형주소의 유무에 따라 양쪽에 괄호를 추가하여 최종 주소를 만든다.
                        fullAddr += (extraAddr !== '' ? ' ('
                            + extraAddr + ')' : '');
                    }

                    // 주소 정보를 해당 필드에 넣는다.
                    document.getElementById("address").value = fullAddr;
                    // 주소로 상세 정보를 검색
                    console.log("Before geocoder.addressSearch")
                    geocoder.addressSearch(data.address, function(
                        results, status) {
                        // 정상적으로 검색이 완료됐으면
                        console.log(status === kakao.maps.services.Status.OK);
                        if (status === kakao.maps.services.Status.OK) {

                            var result = results[0]; //첫번째 결과의 값을 활용

                            console.log(result.x);
                            console.log(result.y);

                            // 해당 주소에 대한 좌표를 받아서
                            var coords = new kakao.maps.LatLng(result.y,	result.x);
                            // 지도를 보여준다.

                            mapContainer.style.display = "block";
                            map.relayout();

                            // 지도 중심을 변경한다.
                            map.setCenter(coords);
                            // 마커를 결과값으로 받은 위치로 옮긴다.
                            marker.setPosition(coords)

                            var LatLng = marker.getPosition();
                            console.log(marker.getPosition());
                            console.log(LatLng.getLat());
                            console.log(LatLng.getLng());
                            $('#latitude').val(LatLng.getLat());
                            $('#longitude').val(LatLng.getLng());
                        }
                    });
                }
            }).open();
    }
    $('#locationForm').addClass('btn-info')
</script>
</html>
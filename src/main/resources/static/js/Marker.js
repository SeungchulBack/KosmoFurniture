$(document).ready(function(){
	$("#addMarker").submit(function(){
		if($.trim($("#Name").val()) == ""){
			alert("지점명을 입력하세요");
			$("#placeName").focus();
			return false;
		}
		if($.trim($("#sample5_address").val()) == ""){
			alert("주소를 입력하세요");
			$("#placeAddress").focus();
			return false;
		}
	})
	$("#editMarker").submit(function(){
		if($.trim($("#Name").val()) == ""){
			alert("지점명을 입력하세요");
			$("#placeName").focus();
			return false;
		}
		if($.trim($("#sample5_address").val()) == ""){
			alert("주소를 입력하세요");
			$("#placeAddress").focus();
			return false;
		}
	})
});
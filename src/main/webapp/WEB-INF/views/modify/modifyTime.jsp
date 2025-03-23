<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="HY-FOCUS CAMERA FORM" />
<meta name="author" content="45기 권보성" />
<title>하이포커스</title>
<link href="resources/images/hyfocus_logo.png" rel="Shortcut Icon" />
<link
	href="https://cdn.jsdelivr.net/npm/bootswatch@5.1.3/dist/morph/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet" href="resources/css/ModifyList.css" />
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>	
</head>
<script>
	$(document).ready(function() {
		$("#homeBtn").on("click", function() {
			window.location.href = "rentList";
		});
		
		$("#subBtn").on("click", function(event) {
		    event.preventDefault(); 
			
		    let openTime = $("#openTime").val();
		    let closeTime = $("#closeTime").val();
		    
		    if(!openTime || !closeTime) {
		    	alert("날짜를 입력해 주세요");
		    } else if (openTime >= closeTime) {
		    	alert("시간이 같거나 종료 시간이 더 빠를 순 없습니다.");
		    } else {
		    	if(confirm("오픈 시간 : " + openTime + "\n종료 시간 : " + closeTime + "\n(으)로 설정한 게 맞나요?")) {
		    		let data = {
		    			    openTime: openTime,
		    			    closeTime: closeTime
		    		};
		    		
				    $.ajax({
				        type: "POST",
				        url: "modifyTimeData",
				        data: data,
				        success: function(e) {
				        	if(e === "SUCCESS") {
				        		alert("사용자 입력 폼 시간 수정 완료");
					            location.reload();
				        	}
				        },
				        error: function(xhr, status, error) {
				        	console.log("데이터 오류");
				        }
				    });
			    }
		    }
		});
	});
</script>
<body>
	<form id="ModifyForm">
		<div id="container">
			<nav class="navbar navbar-expand-lg bg-body-tertiary">
				<div class="container-fluid">
					<div class="collapse navbar-collapse" id="navbarColor04">
						<ul class="navbar-nav me-auto">
							<li class="nav-item"><a class="nav-link" id="homeBtn">대여
									목록 </a></li>
						</ul>
					</div>
				</div>
			</nav>
			<br>
			<div id="cameraSelectDiv">
				<h3>날짜 및 시간 선택</h3>
				<br>
				<h6>현재 오픈 시간 : ${startDate}</h6>
				<br>
				<h6>현재 마감 시간 : ${endDate}</h6>
				<br>
				<hr>
				<form id="timeForm">
					<label>오픈 시간 선택</label><br> <input type="datetime-local"
						id="openTime" value="${startDate}">
					<br>
					<br>
					<label>마감 시간 선택</label><br> <input type="datetime-local"
						id="closeTime" value="${endDate}">
				</form>
			</div>
			<br>
			<hr>
			<br>
			<button class="btn btn-outline-primary" id="subBtn">변경</button>
		</div>
	</form>
</body>
</html>
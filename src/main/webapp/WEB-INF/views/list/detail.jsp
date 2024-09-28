<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>📷 하이포커스 📷</title>
<link href="resources/images/hyfocus_logo.png" rel="Shortcut Icon" />
<link rel="stylesheet" href="resources/css/detail.css" />
<link
	href="https://cdn.jsdelivr.net/npm/bootswatch@5.1.3/dist/morph/bootstrap.min.css"
	rel="stylesheet">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript" src="resources/js/detail.js"></script>
</head>
<body>
	<input type="hidden" id="rentNo" value="${rentVO.rentNo}">
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
		<h2>신청 내역</h2>
		<br>
		<h3>${rentVO.stuInfo}</h3>
		<br>
		<ul class="list-group">
			<li class="list-group-item list-group-item-secondary"><strong>카메라
					: </strong> ${rentVO.camName}</li>
			<li class="list-group-item list-group-item-secondary"><strong>렌즈
					: </strong> ${rentVO.lensName}</li>
			<li class="list-group-item list-group-item-secondary"><strong>추가장비
					: </strong> <c:choose>
					<c:when test="${not empty rentVO.tripod}">
						<c:choose>
							<c:when test="${not empty rentVO.bag}">
								${rentVO.bag}, ${rentVO.tripod}
							</c:when>
							<c:otherwise>
								${rentVO.tripod}
							</c:otherwise>
						</c:choose>
					</c:when>
					<c:otherwise>
						${rentVO.bag}
					</c:otherwise>
				</c:choose></li>
			<li class="list-group-item list-group-item-secondary" id="rentChk"><strong>대여
					확인 : </strong> ${rentVO.rentChk}</li>
			<li class="list-group-item list-group-item-secondary"><strong>반납
					확인 : </strong> ${rentVO.returnChk}</li>
		</ul>
		<br>
		<div class="button-container">
			<div class="btn-group" id="leftBtn">
				<button type="button" class="btn btn-secondary" id="modifyBtn" disabled>수정</button>
				<!-- 필요하면 추가 -->
				<button type="button" class="btn btn-secondary" id="deleteBtn">삭제</button>
			</div>
			<div class="btn-group" id="rightBtn">
				<button type="button" class="btn btn-secondary" id="rentChkBtn">대여</button>
				<button type="button" class="btn btn-secondary" id="returnBtn">반납</button>
			</div>
		</div>
	</div>
</body>
</html>

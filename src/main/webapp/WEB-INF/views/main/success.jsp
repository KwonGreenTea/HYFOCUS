<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="HY-FOCUS CAMERA FORM" />
<meta name="author" content="46기 권보성" />
<title>하이포커스</title>
<link href="resources/images/hyfocus_logo.png" rel="Shortcut Icon" />
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet" href="resources/css/MainForm.css" />
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript" src="resources/js/success.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
	<input type="hidden" id="rentNo" value="${rentVO.rentNo}">
	<div class="container">
		<div class="form-container">
			<div class="text-center form-header">
				<h1 class="text-primary">대여 리스트</h1>
				<br>
				<h2 class="text-secondary" id="stuInfo">${rentVO.stuInfo}</h2>
				<p>카메라 : ${rentVO.camName}</p>
				<p>렌즈 : ${rentVO.lensName}</p>
				<hr>
				<p>
					카메라 가방은 수량이 적어 선착순으로 받을 수도 있습니다.<br>대여시 임원진에게 확인해주세요.
				</p>
				<form id="extraForm">
					<input class="form-check-input" type="checkbox" id="bagCheck"
						name="bag" value="카메라 가방" checked> <label class="form-check-label">
						🎒 카메라 가방 </label>
				</form>
				<br>
			</div>

			<div class="text-center mt-4">
				<button id="subBtn" class="btn btn-primary btn-lg">신청</button>
			</div>
		</div>
	</div>

</body>
</html>

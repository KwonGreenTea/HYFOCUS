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
<script type="text/javascript" src="resources/js/ModifyList.js"></script>
</head>
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
				<div class="select-group">
					<label class="form-label mt-4">카메라</label> <select
						class="form-select" id="dslrSelect" name="dslr">
						<option value="">선택</option>
						<c:forEach var="cameraVO" items="${camList}">
							<option id="${cameraVO.camName}">${cameraVO.camName}</option>
						</c:forEach>
					</select> <br>
					<div id="camInput" style="display: none;">
						<input type="text" id="camName" name="camSelect" readOnly>
						<input type="text" name="camCnt" id="camCnt">
					</div>
				</div>
			</div>

			<div id="lensSelectDiv">
				<br>
				<hr>
				<div class="select-group">
					<label class="form-label mt-4">렌즈</label> <select
						class="form-select" id="canonLensSelect" name="canonLens">
						<option value="">선택</option>
						<c:forEach var="lensVO" items="${lensList}">
							<option id="${lensVO.lensName}">${lensVO.lensName}</option>
						</c:forEach>
					</select> <br>
					<div id="lensInput" style="display: none;">
						<input type="text" id="lensName" name="lensSelect" readOnly>
						<input type="text" name="lensCnt" id="lensCnt">
					</div>
				</div>
			</div>

			<div id="extraSelectDiv">
				<br>
				<hr>
				<div class="select-group">
					<label class="form-label mt-4">가방 / 삼각대</label> <select
						class="form-select" id="canonLensSelect" name="canonLens">
						<option value="">선택</option>
						<c:forEach var="extraVO" items="${extraList}">
							<option id="${extraVO.extraName}">${extraVO.extraName}</option>
						</c:forEach>
					</select> <br>
					<div id="extraInput" style="display: none;">
						<input type="text" id="extraName" name="extraSelect" readOnly>
						<input type="text" name="extraCnt" id="extraCnt">
					</div>
				</div>
			</div>
			<br>
			<hr>
			<br>
			<button class="btn btn-outline-primary" id="subBtn">수정</button>
		</div>
	</form>
</body>
</html>
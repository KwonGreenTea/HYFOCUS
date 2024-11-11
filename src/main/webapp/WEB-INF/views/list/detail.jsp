<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>하이포커스</title>
<link href="resources/images/hyfocus_logo.png" rel="Shortcut Icon" />
<link rel="stylesheet" href="resources/css/detail.css" />
<link
	href="https://cdn.jsdelivr.net/npm/bootswatch@5.1.3/dist/morph/bootstrap.min.css"
	rel="stylesheet">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript" src="resources/js/detail.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
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
			<li class="list-group-item list-group-item-secondary"><strong>신청시간
					: </strong> <fmt:formatDate value="${rentVO.createdDate}"
					pattern="MM월 dd일 HH시 mm분 ss초" /></li>
			<li class="list-group-item list-group-item-secondary" id="rentChk"><strong>대여
					확인 : </strong> ${rentVO.rentChk}</li>
			<li class="list-group-item list-group-item-secondary"><strong>반납
					확인 : </strong> ${rentVO.returnChk}</li>
		</ul>
		<br>
		<div class="button-container">
			<div class="btn-group" id="leftBtn">
				<button type="button" class="btn btn-secondary" id="modifyBtn">수정</button>
				<button type="button" class="btn btn-secondary" id="deleteBtn">삭제</button>
			</div>
			<div class="btn-group" id="rightBtn">
				<button type="button" class="btn btn-secondary" id="rentChkBtn">대여</button>
				<button type="button" class="btn btn-secondary" id="returnChkBtn">반납</button>
			</div>
		</div>
	</div>

	<div class="modal fade" id="editModal" tabindex="-1"
		aria-labelledby="editModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="editModalLabel">대여 정보 수정</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body">
					<form id="editForm">
						<div class="mb-4">
							<label for="stuInfo" class="form-label">학번/이름</label> <input
								type="text" class="form-control" id="stuInfo"
								value="${rentVO.stuInfo}">
						</div>
						<div class="mb-4">
							<label for="camName" class="form-label">카메라</label> <select
								class="form-select" id="camSelect">
								<option value="">선택</option>
								<c:forEach var="cameraVO" items="${camList}">
									<option id="${cameraVO.camName}">${cameraVO.camName}</option>
								</c:forEach>
							</select>

						</div>
						<div class="mb-4">
							<label for="lensName" class="form-label">렌즈</label> <select
								class="form-select" id="lensSelect">
								<option value="">선택</option>
								<c:forEach var="lensVO" items="${lensList}">
									<option id="${lensVO.lensName}">${lensVO.lensName}</option>
								</c:forEach>
							</select>
						</div>
						<div class="mb-4">
							<label class="form-label">가방</label> <select class="form-select"
								id="extraSelect">
								<option value="">선택</option>
								<c:forEach var="extraVO" items="${extraList}">
									<option id="${extraVO.extraName}">${extraVO.extraName}</option>
								</c:forEach>
							</select>
							<!-- 가방만 수정할 수 있게 변경 / 팔요 시 추가. -->
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-bs-dismiss="modal">닫기</button>
					<button type="button" class="btn btn-primary" id="saveChangesBtn">수정</button>
				</div>
			</div>
		</div>
	</div>
</body>
</html>

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
<meta name="author" content="46기 권보성" />
<title>하이포커스</title>
<link href="resources/images/hyfocus_logo.png" rel="Shortcut Icon" />
<link
	href="https://cdn.jsdelivr.net/npm/bootswatch@5.1.3/dist/morph/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet" href="resources/css/MainForm.css" />
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript" src="resources/js/MainForm.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
	<form id="MainForm">
		<div id="container">
			<div class="collapse navbar-collapse show" id="navbarColor04">
				<ul class="navbar-nav">
					<li class="nav-item"><a class="nav-link" id="homeBtn">홈으로</a></li>
					<li class="nav-item"><a class="nav-link" id="rentList">신청내역</a></li>
					<li class="nav-item"><a class="nav-link" id="NCamera">렌즈신청</a></li>
				</ul>
			</div>
			<br>
			<h3>카메라 신청 폼</h3>
			<!-- 출사 or 정기 대여 선택 창 -->
			<!-- 
			<div class="form-check">
				<input class="form-check-input" type="radio" name="rentType"
					value="정기출사1" checked> <label class="form-check-label">정기출사
					1 </label> 
			</div>
			<div class="form-check">
				<input class="form-check-input" type="radio" name="rentType"
					value="정기출사2"> <label class="form-check-label">정기출사
					2 </label>
			</div>
			 -->
			<div id="cameraSelectDiv">
				<div class="select-group">
					<label class="form-label mt-4">📷DSLR📷</label> <select
						class="form-select" id="dslrSelect" name="dslr">
						<option value="">선택</option>
						<c:forEach var="cameraVO" items="${DSLRCamList}">
							<c:choose>
								<c:when test="${cameraVO.camCount < 1}">
									<option id="${cameraVO.camName}" disabled>${cameraVO.camName}
										(${cameraVO.camCount}대 남음)</option>
								</c:when>
								<c:otherwise>
									<option id="${cameraVO.camName}">${cameraVO.camName}
										(${cameraVO.camCount}대 남음)</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</select>
				</div>

				<div class="select-group">
					<label class="form-label mt-4">📷미러리스 Mirror-less📷</label> <select
						class="form-select" id="mirrorlessSelect" name="mirrorless">
						<option value="">선택</option>
						<c:forEach var="cameraVO" items="${MirrorLessCamList}">
							<c:choose>
								<c:when test="${cameraVO.camCount < 1}">
									<option id="${cameraVO.camName}" value="${cameraVO.camName}"
										disabled>${cameraVO.camName} (${cameraVO.camCount}대
										남음)</option>
								</c:when>
								<c:otherwise>
									<option id="${cameraVO.camName}" value="${cameraVO.camName}">${cameraVO.camName}
										(${cameraVO.camCount}대 남음)</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</select>
				</div>

				<div class="select-group">
					<label class="form-label mt-4">📷필름 카메라 Film Auto📷 - 자동</label> <select
						class="form-select" id="filmSelectAuto" name="filmAuto">
						<option value="">선택</option>
						<c:forEach var="cameraVO" items="${FilmACamList}">
							<c:choose>
								<c:when test="${cameraVO.camCount < 1}">
									<option id="${cameraVO.camName}" disabled>${cameraVO.camName}
										(${cameraVO.camCount}대 남음)</option>
								</c:when>
								<c:otherwise>
									<option id="${cameraVO.camName}">${cameraVO.camName}
										(${cameraVO.camCount}대 남음)</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</select>
				</div>

				<div class="select-group">
					<label class="form-label mt-4">📷필름 카메라 Film Manual📷 - 수동</label>
					<select class="form-select" id="filmSelectManual" name="filmManual">
						<option value="">선택</option>
						<c:forEach var="cameraVO" items="${FilmMCamList}">
							<c:choose>
								<c:when test="${cameraVO.camCount < 1}">
									<option id="${cameraVO.camName}" disabled>${cameraVO.camName}
										(${cameraVO.camCount}대 남음)</option>
								</c:when>
								<c:otherwise>
									<option id="${cameraVO.camName}">${cameraVO.camName}
										(${cameraVO.camCount}대 남음)</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</select>
				</div>
			</div>

			<div id="lensSelectDiv" style="display: none;">
				<br>
				<hr>
				<br>
				<h4>렌즈 선택</h4>
				<div class="select-group">
					<label class="form-label mt-4">⚗캐논 Canon</label> <select
						class="form-select" id="canonLensSelect" name="canonLens">
						<option value="">선택</option>
						<c:forEach var="lensVO" items="${canonLensList}">
							<c:choose>
								<c:when test="${lensVO.lensCount < 1}">
									<option id="${lensVO.lensName}" disabled>${lensVO.lensName}
										(${lensVO.lensCount}개 남음)</option>
								</c:when>
								<c:otherwise>
									<option id="${lensVO.lensName}">${lensVO.lensName}
										(${lensVO.lensCount}개 남음)</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</select>
				</div>

				<div class="select-group">
					<label class="form-label mt-4">🔮탐론 Tamron</label> <select
						class="form-select" id="tamronLensSelect" name="tamronLens">
						<option value="">선택</option>
						<c:forEach var="lensVO" items="${tamronLensList}">
							<c:choose>
								<c:when test="${lensVO.lensCount < 1}">
									<option id="${lensVO.lensName}" disabled>${lensVO.lensName}
										(${lensVO.lensCount}개 남음)</option>
								</c:when>
								<c:otherwise>
									<option id="${lensVO.lensName}">${lensVO.lensName}
										(${lensVO.lensCount}개 남음)</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</select>
				</div>

				<div class="select-group">
					<label class="form-label mt-4">⚗시그마 Sigma</label> <select
						class="form-select" id="sigmaLensSelect" name="sigmaLens">
						<option value="">선택</option>
						<c:forEach var="lensVO" items="${sigmaLensList}">
							<c:choose>
								<c:when test="${lensVO.lensCount < 1}">
									<option id="${lensVO.lensName}" disabled>${lensVO.lensName}
										(${lensVO.lensCount}개 남음)</option>
								</c:when>
								<c:otherwise>
									<option id="${lensVO.lensName}">${lensVO.lensName}
										(${lensVO.lensCount}개 남음)</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</select>
				</div>
			</div>

			<div id="extraSelectDiv" style="display: none;">
				<br>
				<hr>
				<br>
				<h4>학번 / 이름 입력</h4>
				<br>
				<div class="info-input">
					<input type="text" class="form-control"
						placeholder="2023000000 권보성" id="stuInfo" name="stuInfo" required>
				</div>
				<br>
				<button type="submit" class="btn btn-outline-primary" id="subBtn">신청</button>
			</div>
		</div>
	</form>

	<!-- 신청내역 모달 -->
	<div class="modal fade" id="searchModal" tabindex="-1"
		aria-labelledby="searchModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="searchModalLabel">학번을 입력해주세요</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body">
					<input type="text" id="searchInput" class="form-control"
						placeholder="2023000000" />
					<ul id="resultList" class="list-group mt-3">
					</ul>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" id="searchModalBtn">검색</button>
				</div>
			</div>
		</div>
	</div>

	<!-- 렌즈, 가방, 삼각대만 선택할 수 있는 모달 -->
	<div class="modal fade" id="editModal" tabindex="-1"
		aria-labelledby="editModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="editModalLabel">렌즈, 가방, 삼각대만 신청 시</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body">
					<form id="editForm">
						<div class="mb-3">
							<label for="lensName" class="form-label">렌즈</label> <select
								class="form-select" id="onlyLensSelect">
								<option value="">선택</option>
								<c:forEach var="lensVO" items="${canonLensList}">
									<c:choose>
										<c:when test="${lensVO.lensCount < 1}">
											<option id="${lensVO.lensName}" disabled>${lensVO.lensName}
												(${lensVO.lensCount}개 남음)</option>
										</c:when>
										<c:otherwise>
											<option id="${lensVO.lensName}">${lensVO.lensName}
												(${lensVO.lensCount}개 남음)</option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
								<c:forEach var="lensVO" items="${tamronLensList}">
									<c:choose>
										<c:when test="${lensVO.lensCount < 1}">
											<option id="${lensVO.lensName}" disabled>${lensVO.lensName}
												(${lensVO.lensCount}개 남음)</option>
										</c:when>
										<c:otherwise>
											<option id="${lensVO.lensName}">${lensVO.lensName}
												(${lensVO.lensCount}개 남음)</option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
								<c:forEach var="lensVO" items="${sigmaLensList}">
									<c:choose>
										<c:when test="${lensVO.lensCount < 1}">
											<option id="${lensVO.lensName}" disabled>${lensVO.lensName}
												(${lensVO.lensCount}개 남음)</option>
										</c:when>
										<c:otherwise>
											<option id="${lensVO.lensName}">${lensVO.lensName}
												(${lensVO.lensCount}개 남음)</option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</select>
						</div>
						<br>
						<div class="mb-3">
							<label for="stuInfo" class="form-label">학번 / 이름 입력</label> <input
								type="text" class="form-control" placeholder="2023000000 권보성"
								id="stuInfoModal" required>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="submit" class="btn btn-outline-primary"
						id="subBtnModal">다음</button>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
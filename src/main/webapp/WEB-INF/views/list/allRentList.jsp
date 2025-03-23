<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>하이포커스</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="HY-FOCUS CAMERA FORM" />
<meta name="author" content="45기 권보성" />
<link href="resources/images/hyfocus_logo.png" rel="Shortcut Icon" />
<link rel="stylesheet" href="resources/css/rentList.css" />
<link
	href="https://cdn.jsdelivr.net/npm/bootswatch@5.1.3/dist/morph/bootstrap.min.css"
	rel="stylesheet">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>
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
		<h2>카메라 대여 전체 목록</h2>
		<br>

		<!-- 게시글 페이지 사이즈 선택 
		<select class="form-select" id="selectSize">
			<option value="" disabled selected>목록 표시 수</option>
			<option value="5">5</option>
			<option value="10">10</option>
		</select> -->

		<br>
		<div class="table-responsive">
			<table class="table table-hover">
				<thead>
					<tr>
						<th scope="col">이름</th>
						<th scope="col">대여</th>
						<th scope="col">반납</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="rentVO" items="${rentList}">
						<tr class="table-secondary" id="${rentVO.rentNo}">
							<td>${rentVO.stuInfo}</td>
							<td>${rentVO.rentChk}</td>
							<td>${rentVO.returnChk}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>


		<!-- 게시글 번호, 페이지 번호, 페이지 사이즈를 전송하는 form  -->
		<form id="detailForm" action="detail" method="get">
			<input type="hidden" name="rentNo"> <input type="hidden"
				name="pageNum"> <input type="hidden" name="pageSize">
			<input type="hidden" name="keyword">
		</form>

		<!-- 페이지 번호와 페이지 사이즈를 전송하는 form -->
		<form id="listForm" action="allRentList" method="get">
			<input type="hidden" name="pageNum"> <input type="hidden"
				name="pageSize"> <input type="hidden" name="type"> <input
				type="hidden" name="keyword"> <input type="hidden"
				name="rentChk"> <input type="hidden" name="returnChk">
			<input type="hidden" name="rentData">
		</form>

	</div>

	<div id="footer">
		<div id="paginatiob">
			<ul class="pagination">
				<!-- 이전 버튼 생성을 위한 조건문 -->
				<c:choose>
					<c:when test="${pageMaker.isPrev() }">
						<li class="page-item"><a class="page-link"
							href="${pageMaker.startNum - 1}">&laquo;</a></li>
					</c:when>
					<c:otherwise>
						<li class="page-item disabled"><a class="page-link"
							href="${pageMaker.startNum - 1}">&laquo;</a>
					</c:otherwise>
				</c:choose>
				<!-- 반복문으로 시작 번호부터 끝 번호까지 생성 -->
				<c:forEach begin="${pageMaker.startNum }" end="${pageMaker.endNum }"
					var="num">
					<li class="page-item"><a class="page-link" href="${num }">${num }</a></li>
				</c:forEach>
				<!-- 다음 버튼 생성을 위한 조건문 -->
				<c:choose>
					<c:when test="${pageMaker.isNext() }">
						<li class="page-item"><a class="page-link"
							href="${pageMaker.endNum + 1}">&raquo;</a></li>
					</c:when>
					<c:otherwise>
						<li class="page-item disabled"><a class="page-link"
							href="${pageMaker.endNum + 1}">&raquo;</a></li>
					</c:otherwise>
				</c:choose>
			</ul>
		</div>

		<div class="footer-content">
			<form id="searchForm" action="allRentList" method="get">
				<label class="form-check-label">신청자: </label> <input type="text"
					name="keyword">
				<button class="btn btn-primary btn-sm">검색</button>
				<input type="hidden" name="pageNum"> <input type="hidden"
					name="pageSize"> <input type="hidden" name="rentData">
			</form>
		</div>
	</div>


	<script type="text/javascript">
		$(document).ready(function() {
			$('.table tbody tr').click(function() {
				var rentNo = $(this).attr('id');

				window.location.href = "detail?rentNo=" + rentNo;
			});
		});

		// select 옵션을 선택하면 이벤트 발생
		$("#selectSize").on("change", function(e) {
			var listForm = $("#listForm");
			// c:out을 이용한 현재 페이지 번호값 저장
			var pageNum = "<c:out value='${pageMaker.pagination.pageNum }' />";
			var pageSize = $(this).val(); // 선택된 값을 저장

			var keyword = "<c:out value='${pageMaker.pagination.keyword }' />";

			// 페이지 번호를 input name='pageNum' 값으로 적용
			listForm.find("input[name='pageNum']").val(pageNum);
			// 선택된 옵션 값을 input name='pageSize' 값으로 적용
			listForm.find("input[name='pageSize']").val(pageSize);
			// keyword 값을 적용
			listForm.find("input[name='keyword']").val(keyword);

			listForm.submit();
		});

		// page-item을 클릭하면 페이지 이동
		$(".page-item a")
				.on(
						"click",
						function(e) {
							var listForm = $("#listForm");
							e.preventDefault();

							var pageNum = $(this).attr("href"); // a태그의 href 값 저장
							// 현재 페이지 사이즈값 저장
							var pageSize = "<c:out value='${pageMaker.pagination.pageSize }' />";
							var keyword = "<c:out value='${pageMaker.pagination.keyword }' />";
							
							var data = 'false';

							// 페이지 번호를 input name='pageNum' 값으로 적용
							listForm.find("input[name='pageNum']").val(pageNum);
							// 선택된 옵션 값을 input name='pageSize' 값으로 적용
							listForm.find("input[name='pageSize']").val(
									pageSize);
							// keyword 값을 적용
							listForm.find("input[name='keyword']").val(keyword);
							// 전체 리스트로 데이터 가져옴
							listForm.find("input[name='rentData']").val(data);
							listForm.submit();
						});

		$("#searchForm button")
				.on(
						"click",
						function(e) {
							var searchForm = $("#searchForm");
							e.preventDefault();

							var keywordVal = searchForm.find(
									"input[name='keyword']").val();
							if (keywordVal == '') {
								alert('검색 내용을 입력하세요.');
								return;
							}

							var pageNum = 1; // 검색 후 1페이지로 고정
							// 현재 페이지 사이즈값 저장
							var pageSize = "<c:out value='${pageMaker.pagination.pageSize }' />";

							var data = 'false';
							
							// 페이지 번호를 input name='pageNum' 값으로 적용
							searchForm.find("input[name='pageNum']").val(
									pageNum);
							// 선택된 옵션 값을 input name='pageSize' 값으로 적용
							searchForm.find("input[name='pageSize']").val(
									pageSize);
							// 전체 리스트로 데이터 가져옴
							searchForm.find("input[name='rentData']").val(data);
							searchForm.submit(); // form 전송
						});


		$("#homeBtn").on("click", function() {
			window.location.href = "rentList";
		});
	</script>

</body>
</html>

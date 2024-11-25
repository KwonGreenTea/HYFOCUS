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

<script type="text/javascript">
	function reset() {
		window.location.href = "/";
	}

	window.onload = function() {
		if (performance.navigation.type === 1) {
			window.location.href = "/";
		}
	};

	function bagCheck() {
		const checkbox = document.getElementById('bagCheck');
		checkbox.checked = !checkbox.checked;
	}

	function tripodCheck() {
		const checkbox = document.getElementById('tripodCheck');
		checkbox.checked = !checkbox.checked;
	}
	
	//----------------------------------------------------------------------------
	var stuInfo = "${rentVO.stuInfo}";
	var socket = new WebSocket("ws://hyfocus.xyz/rentSuccess?stuInfo=" + stuInfo);

	socket.onmessage = function(event) {
		const data = JSON.parse(event.data);
		const label = document.querySelector(`#label-extra-${data.extraNo}`);
		label.innerHTML = `${data.extraName} (${data.extraCount}개 남음)`;

		const checkbox = document.querySelector(`#check-extra-${data.extraNo}`);
		if (data.extraCount < 1) {
			checkbox.disabled = true;
		}
	};
	
	// ----------------------------------------------------------------------------
	
	$("#bagCheck").on("change", function() {
		if ($(this).is(":checked")) {
			setTimeout(function() {
				const extraSelect = $(this).val();
				updateCount(extraSelect);
		    }, 1000);
	    }
	});
	
	$("#tripodCheck").on("change", function() {
		if ($(this).is(":checked")) {
			setTimeout(function() {
				const extraSelect = $(this).val();
				updateCount(extraSelect);
		    }, 1000);
	    }
	});

	function updateCount(extraSelect) {
		const rentNo = "${rentVO.rentNo}";
		socket.send(rentNo, extraSelect);
	}
</script>
</head>
<body>
	<input type="hidden" id="rentNo" value="${rentVO.rentNo}">
	<div class="container">
		<div class="form-container">
			<div class="text-center form-header">
				<h1 class="text-primary">신청 완료 되었습니다</h1>
				<h2 class="text-secondary">${rentVO.stuInfo}</h2>
				<p>${rentVO.camName}</p>
				<p>${rentVO.lensName}</p>
				<hr>
			</div>

			<div>
				<c:forEach var="extraVO" items="${extraList}">
					<c:if test="${extraVO.extraNo == 1}">
						<div class="form-check">
							<input class="form-check-input" type="checkbox"
								value="${extraVO.extraName}" id="bagCheck" name="bag"
								${extraVO.extraCount < 1 ? 'disabled' : ''}> <label
								class="form-check-label ${extraVO.extraCount < 1 ? 'text-muted' : ''}"
								onclick="${extraVO.extraCount > 0 ? 'bagCheck()' : ''}">
								🎒 ${extraVO.extraName} (${extraVO.extraCount}개 남음) </label>
						</div>
					</c:if>
				</c:forEach>
			</div>
			
			<!-- 삼각대 필요 시 수정
			<div>
				<c:forEach var="extraVO" items="${extraList}">
					<c:if test="${extraVO.extraNo == 2}">
						<div class="form-check">
							<input class="form-check-input" type="checkbox"
								value="${extraVO.extraName}" id="tripodCheck" name="tripod"
								${extraVO.extraCount < 1 ? 'disabled' : ''}> <label
								class="form-check-label ${extraVO.extraCount < 1 ? 'text-muted' : ''}"
								onclick="${extraVO.extraCount > 0 ? 'tripodCheck()' : ''}">
								📐 ${extraVO.extraName} (${extraVO.extraCount}개 남음) </label>
						</div>
					</c:if>
				</c:forEach>
			</div>
			 -->
			 
			<div class="text-center mt-4">
				<button onclick="reset()" class="btn btn-primary btn-lg">새로고침</button>
			</div>
		</div>
	</div>

</body>
</html>

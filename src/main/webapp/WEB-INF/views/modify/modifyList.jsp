<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>📷 하이포커스 📷</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootswatch@5.1.3/dist/morph/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet" href="resources/css/ModifyList.css" />
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>
	<form id="ModifyForm">
		<div id="container">
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
						<input type="text" id="camName" readOnly> <input
							type="text" id="camCnt">
						<button type="submit" class="btn btn-outline-primary" id="subBtn">수정</button>
					</div>
				</div>
			</div>

			<div id="lensSelectDiv">
				<br>
				<hr>
				<br>
				<div class="select-group">
					<label class="form-label mt-4">렌즈</label> <select
						class="form-select" id="canonLensSelect" name="canonLens">
						<option value="">선택</option>
						<c:forEach var="lensVO" items="${lensList}">
							<option id="${lensVO.lensName}">${lensVO.lensName}</option>
						</c:forEach>
					</select>
					<div id="lensInput" style="display: none;">
						<input type="text" id="lensName" readOnly> <input
							type="text" id="lensCnt">
						<button type="submit" class="btn btn-outline-primary" id="subBtn">수정</button>
					</div>
				</div>
			</div>

			<div id="extraSelectDiv">
				<br>
				<hr>
				<br>
				<div class="select-group">
					<label class="form-label mt-4">가방 / 삼각대</label> <select
						class="form-select" id="canonLensSelect" name="canonLens">
						<option value="">선택</option>
						<c:forEach var="extraVO" items="${extraList}">
							<option id="${extraVO.extraName}">${extraVO.extraName}</option>
						</c:forEach>
					</select>
					<div id="extraInput" style="display: none;">
						<input type="text" id="extraName" readOnly> <input
							type="text" id="extraCnt">
						<button type="submit" class="btn btn-outline-primary" id="subBtn">수정</button>
					</div>
				</div>
				<br>
			</div>
		</div>
	</form>

	<script type="text/javaScript">
		$(document).ready(function() {
			$('#cameraSelectDiv .form-select').change(function() {
				const selectedValue = $(this).val();
				
				$.ajax({
                    type: "POST",
                    url: "camData", 
                    contentType: "application/x-www-form-urlencoded",
                    data: { data: selectedValue }, 
                    success: function(response) {
            				$('#camName').val(selectedValue);
            				$('#camCnt').val(response.camCount);
            				$('#camInput').css('display', 'block');
                    },
                    error: function(xhr, status, error) {
                    	console.log("option데이터오류");
                    }
                });
			});

			$('#lensSelectDiv .form-select').change(function() {
				const selectedValue = $(this).val();

				$.ajax({
                    type: "POST",
                    url: "lensData", 
                    contentType: "application/x-www-form-urlencoded",
                    data: { data: selectedValue }, 
                    success: function(response) {
                        	$('#lensName').val(selectedValue);
            				$('#lensCnt').val(response.lensCount);
            				$('#lensInput').css('display', 'block');
                    },
                    error: function(xhr, status, error) {
                    	console.log("option데이터오류");
                    }
                });
			});

			$('#extraSelectDiv .form-select').change(function() {
				const selectedValue = $(this).val();

				$.ajax({
                    type: "POST",
                    url: "extraData", 
                    contentType: "application/x-www-form-urlencoded",
                    data: { data: selectedValue }, 
                    success: function(response) {
           					$('#extraName').val(selectedValue);
            				$('#extraCnt').val(response.extraCount);
            				$('#extraInput').css('display', 'block');
                    },
                    error: function(xhr, status, error) {
                    	console.log("option데이터오류");
                    }
                });
			});

			/* 
			function formSub() {
				const formData = $('#ModifyForm').serializeArray();

				$.ajax({
                    type: "POST",
                    url: "modifyListData", 
                    contentType: "application/x-www-form-urlencoded",
                    data: JSON.stringify(formData), 
                    success: function() {
                        	const extraCnt = "<c:out value='${extraVO.extraCount}'/>";
                        	$('#extraName').val(selectedValue);
            				$('#extraCnt').val(extraCnt);
            				$('#extraInput').css('display', 'block');
                    },
                    error: function(xhr, status, error) {
                    	console.log("option데이터오류");
                    }
				});
			} */

		});
	</script>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>하이포커스</title>
<link href="resources/images/hyfocus_logo.png" rel="Shortcut Icon" />
</head>
<script type="text/javascript">
	function reset() {
		window.location.href = "/";
	}

	window.onload = function() {
		// 새로 고침 감지 (performance.navigation.type 값이 1일 때는 F5 또는 브라우저의 새로 고침)
		if (performance.navigation.type === 1) {
			window.location.href = "/";
		}
	};
</script>
<body>
	<br>
	<br>
	<h1 style="text-align: center;">신청 완료 되었습니다</h1>
	<br>
	<h2 style="text-align: center;">${rentVO.stuInfo}</h2>
	<p style="text-align: center;">${rentVO.camName}</p>
	<p style="text-align: center;">${rentVO.lensName}</p>
	<p style="text-align: center;">${rentVO.bag}</p>
	<p style="text-align: center;">${rentVO.tripod}</p>
	<fmt:formatDate value="${rentVO.createdDate}"
		pattern="MM월 dd일 HH시 mm분 ss초" />
	<div style="text-align: center; margin-top: 20px;">
		<button onclick="reset()" style="font-size: 20px; padding: 10px 20px;">새로고침</button>
	</div>

</body>
</html>
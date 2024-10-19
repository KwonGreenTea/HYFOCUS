<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>하이포커스</title>
<link href="resources/images/hyfocus_logo.png" rel="Shortcut Icon" />
</head>
<script type="text/javascript">
	function reset() {
		window.location.href = "/HYFOCUS"; 
	}
	
	window.onload = function() {
		// 새로 고침 감지 (performance.navigation.type 값이 1일 때는 F5 또는 브라우저의 새로 고침)
		if (performance.navigation.type === 1) {
			window.location.href = "/HYFOCUS"; 
		}
	};
</script>
<body>
	<h2>
		카메라 신청 폼 오픈 시간이 아닙니다<br>Page not Open
	</h2>
	<button onclick="reset()">새로고침</button>
</body>
</html>
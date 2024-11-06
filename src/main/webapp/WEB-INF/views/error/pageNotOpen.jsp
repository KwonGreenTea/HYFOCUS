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
	<h2 style="text-align: center;">
		카메라 신청 폼 오픈 시간이 아닙니다<br>Page not Open
	</h2>
	<div style="margin: 0 auto;">
		<button onclick="reset()" style="size: 40px;">새로고침</button>
	</div>
</body>
</html>
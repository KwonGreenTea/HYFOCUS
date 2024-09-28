<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>📷 하이포커스 📷</title>
<link href="resources/images/hyfocus_logo.png" rel="Shortcut Icon" />
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>
	<script type="text/javascript">
		$(document).ready(function() {
			let password = prompt("비밀번호를 입력하세요:");

			$.ajax({
                type: "POST",
                url: "adminChk", 
                data: { password: password },
                success: function(result) {
                    if (result > 0) {
                        window.location.href = "rentList"; 
                    } else {
                        alert("비밀번호가 일치하지 않습니다."); 
                    }
                },
                error: function() {
                    alert("서버 오류가 발생했습니다.");
                }
            });
		});
	</script>
</body>
</html>
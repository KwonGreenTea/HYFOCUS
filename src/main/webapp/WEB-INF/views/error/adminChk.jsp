<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>📷 하이포커스 📷</title>
<link href="resources/images/hyfocus_logo.png" rel="Shortcut Icon" />
</head>
<body>
	<script type="text/javascript">
        $(document).ready(function() {
            var password = prompt("비밀번호를 입력하세요:");
            var correctPassword = "${pwd}";

            if (password === correctPassword) {
                alert("비밀번호가 일치합니다.");
                window.location.href = "rentList";
            } else {
                alert("비밀번호가 일치하지 않습니다.");
            }
        });
    </script>
</body>
</html>
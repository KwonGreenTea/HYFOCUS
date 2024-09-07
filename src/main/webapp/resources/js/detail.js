		$(document).ready(function() {
			$("#homeBtn").on("click", function() {
				window.location.href = "rentList";
			});
		
			$('#modifyBtn').click(function() {
				if(confirm("신청 내역을 수정하시나요?")) {
					const rentNo = $('#rentNo').val();
					$.ajax({
                        type: "POST",
                        url: "modify", 
                        contentType: "application/x-www-form-urlencoded",
                        data: { rentNo : rentNo }, 
                        success: function(result) {
                            if (result > 0) {
                                alert("수정되었습니다.");
                                location.reload();
                            } else {
                                alert("rentNo 데이터 오류");
                            }
                        },
                        error: function(xhr, status, error) {
                        	alert("rentNo 데이터 오류");
                        }
                    });
				}
			});
			
			$('#deleteBtn').click(function() {
				if(confirm("신청 내역을 삭제하시나요?")) {
					const rentNo = $('#rentNo').val();
					$.ajax({
                        type: "POST",
                        url: "delete", 
                        contentType: "application/x-www-form-urlencoded",
                        data: { rentNo : rentNo }, 
                        success: function(result) {
                            if (result > 0) {
                                alert("삭제되었습니다.");
                                window.location.href="rentList";
                            } else {
                                alert("rentNo 데이터 오류");
                            }
                        },
                        error: function(xhr, status, error) {
                        	alert("rentNo 데이터 오류");
                        }
                    });
				}
			});
			
			$('#rentChkBtn').click(function() {
				if(confirm("대여하시나요?")) {
					const rentNo = $('#rentNo').val();
					$.ajax({
                        type: "POST",
                        url: "rentChk", 
                        contentType: "application/x-www-form-urlencoded",
                        data: { rentNo : rentNo }, 
                        success: function(result) {
                            if (result > 0) {
                                alert("대여되었습니다.");
                                window.location.href="rentList";
                            } else {
                                alert("rentNo 데이터 오류");
                            }
                        },
                        error: function(xhr, status, error) {
                        	alert("rentNo 데이터 오류");
                        }
                    });
				}
			});
			
			$('#returnBtn').click(function() {
				const rentChk = $('#rentChk').text().trim(); 
				
				if(rentChk.includes("X")) {  
				    alert("대여가 아직 안됐습니다");
				} else {
					if(confirm("반납하시나요?")) {
						const rentNo = $('#rentNo').val();
						$.ajax({
	                        type: "POST",
	                        url: "returnRent", 
	                        contentType: "application/x-www-form-urlencoded",
	                        data: { rentNo : rentNo }, 
	                        success: function(result) {
	                            if (result > 0) {
	                                alert("반납되었습니다.");
	                                window.location.href="rentList";
	                            } else {
	                                alert("rentNo 데이터 오류");
	                            }
	                        },
	                        error: function(xhr, status, error) {
	                        	alert("rentNo 데이터 오류");
	                        }
	                    });
					}
				}
			});
		});
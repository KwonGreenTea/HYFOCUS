		$(document).ready(function() {
			$("#homeBtn").on("click", function() {
				window.location.href = "rentList";
			});
		
			$('#modifyBtn').click(function() {
				$.ajax({
	               type: "POST",
	               url: "modifyList", 
	               contentType: "application/x-www-form-urlencoded",
		               success: function() {
		               		$('#editModal').modal('show');
		               },
	                   error: function(xhr, status, error) {
	                        alert("데이터 오류");
	                   }
	               });
			});
			
			$('#saveChangesBtn').on('click', function() {
				if(confirm("내역을 수정하시나요?")) {
					let rentNo = $('#rentNo').val();
					let stuInfo = $('#stuInfo').val();
					let camName = $('#camSelect').val();
			    	let lensName = $('#lensSelect').val();
			    	let extraName = $('#extraSelect').val();
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
				
				
			    // 폼의 입력 값 가져오기
			    let camName = $('#camName').val();
			    let lensName = $('#lensName').val();
			    // 기타 필요한 값도 함께 가져오기
			
			    // AJAX 요청으로 서버에 수정 데이터 전송
			    $.ajax({
			        type: 'POST',
			        url: 'updateRentData', // 수정 요청을 처리할 컨트롤러 URL
			        data: {
			            rentNo: $('#rentNo').val(), // 식별자 값
			            camName: camName,
			            lensName: lensName,
			            // 기타 필요한 데이터 추가
			        },
			        success: function(response) {
			            alert('수정되었습니다.');
			            location.reload(); // 페이지 새로고침하여 변경 내용 반영
			        },
			        error: function(xhr, status, error) {
			            alert('수정에 실패했습니다.');
			        }
			    });
			});
			
			$('#deleteBtn').click(function() {
				const returnChk = $('#returnChk').text().trim(); 
				
				if(returnChk.includes("X")) {  
				    alert("삭제가 불가능합니다.");
				} else {
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
				}
			});
			
			$('#rentChk').click(function() {
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
			
			$('#returnChk').click(function() {
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
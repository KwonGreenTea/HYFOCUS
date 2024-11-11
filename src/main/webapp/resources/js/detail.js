		// 모달에 데이터 채우는 함수
		function dataForModal(camList, lensList, extraList) {
		    $('#camSelect').empty();
		    $('#camSelect').append('<option value="">선택</option>');
		    camList.forEach(function(camera) {
		        $('#camSelect').append('<option value="' + camera.camName + '">' + camera.camName + '</option>');
		    });
		
		    $('#lensSelect').empty();
		    $('#lensSelect').append('<option value="">선택</option>');
		    lensList.forEach(function(lens) {
		        $('#lensSelect').append('<option value="' + lens.lensName + '">' + lens.lensName + '</option>');
		    });
		
		    $('#extraSelect').empty();
		    $('#extraSelect').append('<option value="">선택</option>');
		    extraList.forEach(function(extra) {
		        $('#extraSelect').append('<option value="' + extra.extraName + '">' + extra.extraName + '</option>');
		    });
		}
		
		$(document).ready(function() {
			$("#homeBtn").on("click", function() {
				window.location.href = "rentList";
			});
		
			$('#modifyBtn').click(function() {
				$.ajax({
	               type: "POST",
	               url: "modifyListToDetail", 
	               contentType: "application/x-www-form-urlencoded",
		               success: function(result) {
		               		dataForModal(result.camList, result.lensList, result.extraList);
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
                        url: "modifyUserData", 
                        contentType: "application/json",
						data: JSON.stringify({ rentNo: rentNo, stuInfo: stuInfo, camName: camName, lensName: lensName, extraName: extraName }), 
                        success: function() {
                            alert("수정되었습니다.");
                            location.reload();
                        },
                        error: function(xhr, status, error) {
                        	alert("rentNo 데이터 오류");
                        }
                    });
				}
			});
			
			$('#deleteBtn').click(function() {
				const returnChk = $('#returnChk').text().trim();
				
				if(returnChk.includes("O")) {  
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
			
			$('#returnChkBtn').click(function() {
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
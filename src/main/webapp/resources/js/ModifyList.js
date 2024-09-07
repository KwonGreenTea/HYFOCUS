		$(document).ready(function() {
		
			$("#homeBtn").on("click", function() {
				window.location.href = "rentList";
			});
		
			$('#cameraSelectDiv .form-select').change(function() {
				const selectedValue = $(this).val();

				$.ajax({
					type : "POST",
					url : "camData",
					contentType : "application/x-www-form-urlencoded",
					data : {
						data : selectedValue
					},
					success : function(response) {
						$('#camName').val(selectedValue);
						$('#camCnt').val(response.camCount);
						$('#camInput').css('display', 'block');
					},
					error : function(xhr, status, error) {
						console.log("option데이터오류");
					}
				});
			});

			$('#lensSelectDiv .form-select').change(function() {
				const selectedValue = $(this).val();

				$.ajax({
					type : "POST",
					url : "lensData",
					contentType : "application/x-www-form-urlencoded",
					data : {
						data : selectedValue
					},
					success : function(response) {
						$('#lensName').val(selectedValue);
						$('#lensCnt').val(response.lensCount);
						$('#lensInput').css('display', 'block');
					},
					error : function(xhr, status, error) {
						console.log("option데이터오류");
					}
				});
			});

			$('#extraSelectDiv .form-select').change(function() {
				const selectedValue = $(this).val();

				$.ajax({
					type : "POST",
					url : "extraData",
					contentType : "application/x-www-form-urlencoded",
					data : {
						data : selectedValue
					},
					success : function(response) {
						$('#extraName').val(selectedValue);
						$('#extraCnt').val(response.extraCount);
						$('#extraInput').css('display', 'block');
					},
					error : function(xhr, status, error) {
						console.log("option데이터오류");
					}
				});
			});
			
			$("#subBtn").on("click", function(event) {
			    event.preventDefault(); 
			
			    const formData = $('#ModifyForm').serialize(); 
			
			    $.ajax({
			        type: "POST",
			        url: "modifyListData",
			        data: formData,
			        success: function() {
			            alert("데이터 수정 완료");
			            location.reload();
			        },
			        error: function(xhr, status, error) {
			            console.log("데이터 오류: " + error);
			        }
			    });
			});

		});
	
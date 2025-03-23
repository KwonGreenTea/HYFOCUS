function bagCheck() {
		const checkbox = document.getElementById('bagCheck');
		checkbox.checked = !checkbox.checked;
	}

	function tripodCheck() {
		const checkbox = document.getElementById('tripodCheck');
		checkbox.checked = !checkbox.checked;
	}

	$(document).ready(function() {
		$("#subBtn").on("click", function() {
			let stuInfo = $("#stuInfo").text();
			let bag = $("#bagCheck").val();
			
			let data = {
		    	stuInfo: stuInfo,
		    	bag: bag
		    };
			
			$.ajax({
				type : "POST",
				url : "rentBag",
				data : data,
				success : function(result) {
					if (result === "success") {
						alert("신청 완료 되었습니다.");
						window.location.href = "/";
					}
				},
				error : function(xhr, status, error) {
					console.error("Error details:", xhr, status, error);
				}
			});
		});
	});
		$(document).ready(function() {
		 
		 $('#cameraSelectDiv .form-select').change(function() {
		 	var selectedValue = $(this).val();
		 	const camCnt = "<c:out value='${cameraVO.camCount}'/>";
		 	
		 	$('#camName').val(selectedValue);
		 	$('#camCnt').val(camCnt);
		 	$('#camInput').css('display', 'block');
		 });
		 
		 $('#lensSelectDiv .form-select').change(function() {
		 	var selectedValue = $(this).val();
		 	
		 	$('#lensName').val(selectedValue);
		 	$('#lensCnt').val("<c:out value='${lensVO.lensCount}'/>");
		 	$('#lensInput').css('display', 'block');
		 });
		 
		 $('#extraSelectDiv .form-select').change(function() {
		 	var selectedValue = $(this).val();
		 	
		 	$('#extraName').val(selectedValue);
		 	$('#extraCnt').val("<c:out value='${extraVO.extraCount}'/>");
		 	$('#extraInput').css('display', 'block');
		 });
		 
		    function formSub() {
		    	var formData = $('#ModifyForm').serializeArray();
		    	
		    	
		     	alert("수정 되었습니다.");
		    }
	
		});
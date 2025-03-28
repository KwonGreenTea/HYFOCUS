		window.localStorage.clear();
		window.sessionStorage.clear();
		
		function bagCheckModal() {
			const checkbox = document.getElementById('bagCheckModal');
    		checkbox.checked = !checkbox.checked;
		}
		
		function tripodCheckModal() {
			const checkbox = document.getElementById('tripodCheckModal');
    		checkbox.checked = !checkbox.checked;
		}
			
		$(document).ready(function() {
			$("#homeBtn").on("click", function() {
				window.location.href = "/";
			});
			
			$('#rentList').on('click', function() {
				$('#searchModal').modal('show');
			});
			
			$("#NCamera").on("click", function() {
				$('#editModal').modal('show');
			});
			
			$('#subBtnModal').click(function(event) {
			    event.preventDefault();
			    
			    const stuInfo = $('#stuInfoModal').val();
			    if (stuInfo.length < 10) {
			        alert('학번, 이름을 입력해 주세요.');
			        return;
			    }
				  
			    const lens = $('#onlyLensSelect option:selected').attr('id');
			    
				 if (confirm(`렌즈 : ${lens}(으)로 신청하시나요?`)) {
				    formSubmit(null, lens, stuInfo);
				}
				
			});
		
		    // 카메라 선택창 변경 시, 다른 카메라 선택창 숨김 및 값 초기화
		    $('#cameraSelectDiv .form-select').change(function() {
		        var selectedValue = $(this).val();
		        var $currentGroup = $(this).closest('.select-group');
		
		        // 하나의 카메라 창을 선택 시, 다른 카메라 창들을 숨기고 값 초기화
		        if (selectedValue) {
		        	
		            $('#cameraSelectDiv .select-group').each(function() {
		                if ($(this).is($currentGroup)) {
		                    $(this).show();
		                } else {
		                    $(this).hide();
		                }
		            });
		
		            // DSLR 선택 시 렌즈 선택 창과 정보 창을 표시
		            // 소니 A7M2 선택 시 렌즈 선택 창과 정보 창을 표시 " || $('#mirrorlessSelect').val() === '소니 A7M2' "
		            if ($('#dslrSelect').val()) {
		                $('#lensSelectDiv').show();
		                $('#extraSelectDiv').show();
		            } else if ($('#mirrorlessSelect').val() === '소니 A7M2'){
				        // 'canonLensSelect'만 보이게 하고 다른 선택창 숨기기
					    $('#lensSelectDiv .select-group').hide(); // 모든 select-group 숨기기
					    $('#canonLensSelect').closest('.select-group').show(); // 'canonLensSelect'만 표시
					
					    // 렌즈 선택창과 기타 선택창 표시
					    $('#lensSelectDiv').show();
					    $('#extraSelectDiv').show();
						
					    $('#canonLensSelect option').each(function () {
					        const optionId = $(this).attr('id');
					      	if (['24mm', '10-18mm', '55-250mm'].includes(optionId)) {
					            $(this).prop('disabled', true); // 비활성화
					        }
					    });		            	
		            } else if ($('#filmSelectManual').val() || $('#filmSelectAuto').val() || $('#mirrorlessSelect').val()) {
		            	$('#lensSelectDiv').hide();
		                $('#extraSelectDiv').show();
		            } else {
		                $('#lensSelectDiv').hide();
		                $('#extraSelectDiv').hide();
		                // 렌즈 선택창 초기화
		                $('#canonLensSelect').prop('selectedIndex', 0);
		                $('#tamronLensSelect').prop('selectedIndex', 0);
		                $('#sigmaLensSelect').prop('selectedIndex', 0);
		                $('#lensSelectDiv .select-group').show();
		            }
		
		        } else {
		            $('#cameraSelectDiv .select-group').show();
		            $('#lensSelectDiv').hide();
		            $('#extraSelectDiv').hide();
		            // 렌즈 선택창 초기화
		            $('#canonLensSelect').prop('selectedIndex', 0);
		            $('#tamronLensSelect').prop('selectedIndex', 0);
		            $('#sigmaLensSelect').prop('selectedIndex', 0);
		            $('#lensSelectDiv .select-group').show();
		            
		            $('#canonLensSelect option').each(function () {
					    const optionId = $(this).attr('id');
					    if (['24mm', '10-18mm', '55-250mm'].includes(optionId)) {
					        $(this).prop('disabled', false); // 활성화
					    }
					});
		        }
		    });
	
		    // 렌즈 선택창 변경 시, 다른 렌즈 선택창 숨김 및 값 초기화
		    $('#lensSelectDiv .form-select').change(function() {
		        var selectedValue = $(this).val();
		        var $currentLensGroup = $(this).closest('.select-group');
	
		        // 하나의 렌즈 창을 선택 시, 다른 렌즈 창들을 숨김
		        if (selectedValue) {
		            $('#lensSelectDiv .select-group').each(function() {
		                if ($(this).is($currentLensGroup)) {
		                    $(this).show();
		                } else {
		                    $(this).hide();
		                }
		            });
	
		        } else {	
		        	if ($('#mirrorlessSelect').val() === '소니 A7M2'){
				        // 'canonLensSelect'만 보이게 하고 다른 선택창 숨기기
					    $('#lensSelectDiv .select-group').hide(); // 모든 select-group 숨기기
					    $('#canonLensSelect').closest('.select-group').show(); // 'canonLensSelect'만 표시
					
					    // 렌즈 선택창과 기타 선택창 표시
					    $('#lensSelectDiv').show();
					    $('#extraSelectDiv').show();
						
					    $('#canonLensSelect option').each(function () {
					        const optionId = $(this).attr('id');
					      	if (['24mm', '10-18mm', '55-250mm'].includes(optionId)) {
					            $(this).prop('disabled', true); // 비활성화
					        }
					    });
					} else {
		            	$('#lensSelectDiv .select-group').show();
		            
			            $('#canonLensSelect option').each(function () {
						    const optionId = $(this).attr('id');
						    if (['24mm', '10-18mm', '55-250mm'].includes(optionId)) {
						        $(this).prop('disabled', false); // 활성화
						    }
						});
		        	}
		        }
		    });
		    
		    // 신청 버튼을 누름
		   $('#subBtn').click(function(event) {
			    event.preventDefault();
			    
			    const stuInfo = $('#stuInfo').val();
			    if (stuInfo.length < 10) {
			        alert('학번, 이름을 입력해 주세요.');
			        return;
			    }
			    
			    const dslr = $('#dslrSelect option:selected').attr('id');
			    const mirrorless = $('#mirrorlessSelect option:selected').attr('id');
			    const filmAuto = $('#filmSelectAuto option:selected').attr('id');
			    const filmManual = $('#filmSelectManual option:selected').attr('id');
			    const cam_List = [dslr, mirrorless, filmAuto, filmManual];
			    
			    let camera = cam_List.find(cam => cam);
			    const canonLens = $('#canonLensSelect option:selected').attr('id');
			    const tamronLens = $('#tamronLensSelect option:selected').attr('id');
			    const sigmaLens = $('#sigmaLensSelect option:selected').attr('id');
			    const lens_List = [canonLens, tamronLens, sigmaLens];
			    
			    let lens = lens_List.find(lensItem => lensItem);
			    
			    if (lens != null) {
				    if (confirm(`카메라 : ${camera}\n렌즈 : ${lens}\n(으)로 신청하시나요?`)) {
				        formSubmit(camera, lens, stuInfo);
				    }
				} else {
					if (confirm(`카메라 : ${camera}\n(으)로 신청하시나요?`)) {
				        formSubmit(camera, lens, stuInfo);
				    }
				}
			});

		    function formSubmit(camera, lens, stuInfo) {
			    $.ajax({
			        type: "POST",
			        url: "rent",
			        contentType: "application/x-www-form-urlencoded",
			        data: { camera: camera, lens: lens, stuInfo: stuInfo },
			        success: function(result) {
			            if (result.success) { 
			                window.location.href = `rentSuccess?stuInfo=${encodeURIComponent(stuInfo)}`;
			            } else {
			                alert(result.message); // 수량 부족 메시지
			                location.reload();
			            }
			        },
			        error: function(xhr, status, error) {
					    console.error("Error details:", xhr, status, error);
					    alert("신청 처리 중 오류가 발생했습니다.");
					    location.reload();
					}
			    });
			}
			
			$('#searchModalBtn').on('click', function() {
	            let data = $('#searchInput').val();
	
				if(data.length < 10) {
					alert("학번 10자리를 입력해주세요");
				} else {
					
		           $.ajax({
					    type: "POST",
					    url: "rentListForStuInfo",
					    contentType: "application/x-www-form-urlencoded",
					    data: { data: data },
					    success: function(result) {
					        $('#resultList').empty();
					        
					        if (!result || result.length === 0) {
					            $('#resultList').append('<li class="list-group-item">검색 결과가 없습니다.</li>');
					        } else {
					            // result 배열이 있을 때만 반복문을 돌며 항목 추가
					            result.forEach(function(item) {
					                // RentVO 객체의 필드를 사용하여 항목을 추가
					                var rentInfo = `
					                    <div>
					                    	<strong>신청 날짜 : </strong> ${item.formattedCreatedDate} <br>
					                        <strong>카메라 :</strong> ${item.camName} <br>
					                        <strong>렌즈 :</strong> ${item.lensName} <br>
					                    </div>
					                `;
					                $('#resultList').append('<li class="list-group-item">' + rentInfo + '</li>');
					            });
					        }
					    },
					    error: function(xhr, status, error) {
					        alert("해당 학번에 맞는 데이터가 없습니다.");
					    }
					});
				}
	        });
			
		});
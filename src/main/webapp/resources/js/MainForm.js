		// 가방 라벨 클릭시 체크박스 선택
		function bagCheck() {
			const checkbox = document.getElementById('bagCheck');
    		checkbox.checked = !checkbox.checked;
		}
		
		// 삼각대 라벨 클릭시 체크박스 선택
		function tripodCheck() {
			const checkbox = document.getElementById('tripodCheck');
    		checkbox.checked = !checkbox.checked;
		}
			
		$(document).ready(function() {
		
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
		            // 소니 A7M2 선택 시 렌즈 선택 창과 정보 창을 표시
		            if ($('#dslrSelect').val() || $('#mirrorlessSelect').val('소니 A7M2')) {
		                $('#lensSelectDiv').show();
		                $('#extraSelectDiv').show();
		            } else if ($('#filmSelectManual').val() || $('#filmSelectAuto').val() || $('#mirrorlessSelect').val()) {
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
		            $('#lensSelectDiv .select-group').show();
		        }
		    });
		    
		    // 신청 버튼을 누름
		    $('#subBtn').click(function(event) {
		    	event.preventDefault();
		    	
		    	const stuInfo = $('#stuInfo').val();
		        if (!stuInfo) {
		            alert('학번, 이름을 입력해 주세요.');
		            return;
		        }
		    	
		    	// 선택된 카메라
		        const dslr = $('#dslrSelect option:selected').attr('id');
		        const mirrorless = $('#mirrorlessSelect option:selected').attr('id');
		        const filmAuto = $('#filmSelectAuto option:selected').attr('id');
		        const filmManual = $('#filmSelectManual option:selected').attr('id');
		        const cam_List = [dslr, mirrorless, filmAuto, filmManual];
		        
		        let camera = null;
		        cam_List.forEach(function(cam) {
		            if (cam) {
		                camera = cam;
		                return false;
		            }
		        });
		        

		        // 선택된 렌즈
		        const canonLens = $('#canonLensSelect option:selected').attr('id');
		        const tamronLens = $('#tamronLensSelect option:selected').attr('id');
		        const sigmaLens = $('#sigmaLensSelect option:selected').attr('id');
		        const lens_List = [canonLens, tamronLens, sigmaLens];
		        
		        let lens = null;
		        lens_List.forEach(function(lensItem) {
		            if (lensItem) {
		                lens = lensItem;
		                return false;
		            }
		        });

		        // 가방, 삼각대
		        const bag = $('#bagCheck').is(':checked') ? $('#bagCheck').val() : '';
    			const tripod = $('#tripodCheck').is(':checked') ? $('#tripodCheck').val() : '';
		        const extras = [];
			    if (bag) {
			        extras.push(bag);
			    }
			    if (tripod) {
			        extras.push(tripod);
			    }
		        
		     	if(lens) {
		     		if(extras) {
		     			if(confirm("카메라 : " + camera + "\n렌즈 : " + lens + "\n추가 장비 : " + extras + "\n(으)로 신청하시나요?")) {
		     				chkCnt(camera, lens, extras, bag, tripod);
		     			}
		     		} else {
		     			if(confirm("카메라 : " + camera + "\n렌즈 : " + lens + "\n(으)로 신청하시나요?")) {
		     				chkCnt(camera, lens, extras, bag, tripod);
		     			}
		     		}
		     	} else {
		     		if(extras) {
		     			if(confirm("카메라 : " + camera + "\n추가 장비 : " + extras + "\n(으)로 신청하시나요?")) {
		     				chkCnt(camera, lens, extras, bag, tripod);
		     			}
		     		} else {
		     			if(confirm("카메라 : " + camera + "\n(으)로 신청하시나요?")) {
		     				chkCnt(camera, lens, extras, bag, tripod);
		     			}
		     		}
		       	}
		    });
		    
		    function formSub() {
		    	$('#MainForm').attr('action', 'rent');
		     	$('#MainForm').attr('method', 'POST');
		     	$('#MainForm').submit();
		     	alert("신청 되었습니다.");
		    }
		    
		    // DB와 갯수 비교
		    function chkCnt(camera, lens, extras, bag, tripod) {
			    $.ajax({
			        type: "POST",
			        url: "chkCnt", 
			        contentType: "application/x-www-form-urlencoded",
			        data: { data: camera }, // 데이터는 URL 인코딩 형식으로 전송됨
			        success: function(result) {
			            if (result > 0) {
			                if (lens) {
			                    $.ajax({
			                        type: "POST",
			                        url: "chkCnt", 
			                        contentType: "application/x-www-form-urlencoded",
			                        data: { data: lens }, // 데이터는 URL 인코딩 형식으로 전송됨
			                        success: function(result) {
			                            if (result > 0) {
			                                if (extras.length > 0) {
			                                    chkExtra(bag, tripod);
			                                } else {
			                                    formSub();
			                                }
			                            } else {
			                                alert(lens + " 렌즈의 수량이 없습니다.");
			                                location.reload();
			                            }
			                        },
			                        error: function(xhr, status, error) {
			                            alert("렌즈 오류");
			                            location.reload();
			                        }
			                    });
			                } else if (extras.length > 0) {
			                    chkExtra(bag, tripod);
			                } else {
			                    formSub();
			                }
			            } else {
			                alert(camera + " 카메라의 수량이 없습니다.");
			                location.reload();
			            }
			        },
			        error: function(xhr, status, error) {
			            alert("카메라 오류");
			            location.reload();
			        }
			    });
			}
			
			function chkExtra(bag, tripod) {
			    if (bag) {
			        $.ajax({
			            type: "POST",
			            url: "chkCnt", 
			            contentType: "application/x-www-form-urlencoded",
			            data: { data: bag }, // 데이터는 URL 인코딩 형식으로 전송됨
			            success: function(result) {
			                if (result > 0) {
			                	if (tripod) {
				                    $.ajax({
				                        type: "POST",
				                        url: "chkCnt", 
				                        contentType: "application/x-www-form-urlencoded",
				                        data: { data: tripod }, // 데이터는 URL 인코딩 형식으로 전송됨
				                        success: function(result) {
				                            if (result > 0) {
				                                formSub();
				                            } else {
				                                alert(tripod + " 삼각대의 수량이 없습니다.");
				                                location.reload();
				                            }
				                        },
				                        error: function(xhr, status, error) {
				                            alert("삼각대 오류");
				                            location.reload();
				                        }
				                    });
				           		} else {
				           			formSub();
				           		}
			                } else {
			                    alert(bag + " 가방의 수량이 없습니다.");
			                    location.reload();
			                }
			            },
			            error: function(xhr, status, error) {
			                alert("가방 오류");
			                location.reload();
			            }
			        });
			    } else if (tripod) {
			        $.ajax({
			            type: "POST",
			            url: "chkCnt", 
			            contentType: "application/x-www-form-urlencoded",
			            data: { data: tripod }, // 데이터는 URL 인코딩 형식으로 전송됨
			            success: function(result) {
			                if (result > 0) {
			                    formSub();
			                } else {
			                    alert(tripod + " 삼각대의 수량이 없습니다.");
			                    location.reload();
			                }
			            },
			            error: function(xhr, status, error) {
			                alert("삼각대 오류");
			                location.reload();
			            }
			        });
			    }
			}
		});
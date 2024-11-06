package com.hyfocus.web.service;

import java.util.ArrayList;

import com.hyfocus.web.domain.ExtraVO;

public interface ExtraService {

	ArrayList<ExtraVO> selectAllData();
	ExtraVO selectAllDataByName(String data);
	
	int chkCntByBag();
	int chkCntByTripod();
	int modifyExtraCnt(String extraSelect, Integer extraCnt);

	
}

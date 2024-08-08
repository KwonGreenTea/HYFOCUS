package com.hyfocus.web.service;

import java.util.ArrayList;

import com.hyfocus.web.domain.LensVO;

public interface LensService {

	ArrayList<LensVO> selectAllData();
	ArrayList<LensVO> selectAllDataCanon();
	ArrayList<LensVO> selectAllDataTamron();
	ArrayList<LensVO> selectAllDataSigma();
	LensVO selectAllDataByName(String data);
	
	int chkCntByName(String data);
	
	

}

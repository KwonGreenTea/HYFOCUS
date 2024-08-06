package com.hyfocus.web.service;

import java.util.ArrayList;

import com.hyfocus.web.domain.LensVO;

public interface LensService {

	ArrayList<LensVO> selectAllDataCanon();
	ArrayList<LensVO> selectAllDataTamron();
	ArrayList<LensVO> selectAllDataSigma();
	
	int chkCntByName(String data);

}

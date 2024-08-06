package com.hyfocus.web.service;

import java.util.ArrayList;

import com.hyfocus.web.domain.CameraVO;

public interface CameraService {
	
	ArrayList<CameraVO> selectAllDataDSLR();
	ArrayList<CameraVO> selectAllDataFilmM();
	ArrayList<CameraVO> selectAllDataFilmA();
	ArrayList<CameraVO> selectAllDataMirrorLess();
	
	int updateCount(String camName);
	
	int chkCntByName(String data);

}

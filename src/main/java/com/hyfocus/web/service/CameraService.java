package com.hyfocus.web.service;

import java.util.ArrayList;

import com.hyfocus.web.domain.CameraVO;

public interface CameraService {
	
	ArrayList<CameraVO> selectAllData();
	ArrayList<CameraVO> selectAllDataDSLR();
	ArrayList<CameraVO> selectAllDataFilmM();
	ArrayList<CameraVO> selectAllDataFilmA();
	ArrayList<CameraVO> selectAllDataMirrorLess();
	CameraVO selectAllDataByName(String data);
	
	int updateCount(String camName);
	
	int chkCntByName(String data);
	
	int modifyCameraCnt(String camSelect, Integer camCnt);
	

}

package com.hyfocus.web.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hyfocus.web.domain.CameraVO;
import com.hyfocus.web.persistence.CameraMapper;

@Service
public class CameraServiceImple implements CameraService {
	
	@Autowired
	private CameraMapper cameraMapper;

	@Override
	public ArrayList<CameraVO> selectAllData() { 
		return cameraMapper.selectAllData();
	}
	
	@Override
	public ArrayList<CameraVO> selectAllDataDSLR() { 
		return cameraMapper.selectAllDataDSLR();
	}
	
	@Override
	public ArrayList<CameraVO> selectAllDataFilmM() { 
		return cameraMapper.selectAllDataFilmM();
	}
	
	@Override
	public ArrayList<CameraVO> selectAllDataFilmA() { 
		return cameraMapper.selectAllDataFilmA();
	}
	
	@Override
	public ArrayList<CameraVO> selectAllDataMirrorLess() { 
		return cameraMapper.selectAllDataMirrorLess();
	}
	
	@Override
	public CameraVO selectAllDataByName(String data) { 
		return cameraMapper.selectAllDataByName(data);
	}

	@Override
	public int updateCount(String camName) {
		int count = cameraMapper.selectCamCount(camName);
		if (count < 1) {
			return 0;
		} else {
			return cameraMapper.updateCount(camName, --count);
		}
	}

	@Override
	public int chkCntByName(String data) {
		return cameraMapper.getCount(data);
	}

}

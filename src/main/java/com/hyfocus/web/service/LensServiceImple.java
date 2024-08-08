package com.hyfocus.web.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hyfocus.web.domain.LensVO;
import com.hyfocus.web.persistence.LensMapper;

@Service
public class LensServiceImple implements LensService {
	
	@Autowired
	private LensMapper lensMapper;

	@Override
	public ArrayList<LensVO> selectAllData() {
		return lensMapper.selectAllData();
	}
	
	@Override
	public ArrayList<LensVO> selectAllDataCanon() {
		return lensMapper.selectAllDataCanon();
	}

	@Override
	public ArrayList<LensVO> selectAllDataTamron() {
		return lensMapper.selectAllDataTamron();
	}

	@Override
	public ArrayList<LensVO> selectAllDataSigma() {
		return lensMapper.selectAllDataSigma();
	}
	
	@Override
	public LensVO selectAllDataByName(String data) {
		return lensMapper.selectAllDataByName(data);
	}

	@Override
	public int chkCntByName(String data) {
		return lensMapper.getCount(data);
	}

	

}

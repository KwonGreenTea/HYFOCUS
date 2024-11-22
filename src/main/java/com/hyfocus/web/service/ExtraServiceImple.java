package com.hyfocus.web.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hyfocus.web.domain.ExtraVO;
import com.hyfocus.web.persistence.ExtraMapper;

@Service
public class ExtraServiceImple implements ExtraService {
	
	@Autowired
	private ExtraMapper extraMapper;

	@Override
	public ArrayList<ExtraVO> selectAllData() {
		return extraMapper.selectAllData();
	}
	
	@Override
	public ExtraVO selectAllDataByName(String data) {
		return extraMapper.selectAllDataByName(data);
	}

	@Override
	public int chkCntByBag() {
		return extraMapper.getBagCount();
	}

	@Override
	public int chkCntByTripod() {
		return extraMapper.getTripodCount();
	}

	@Override
	public int modifyExtraCnt(String extraSelect, Integer extraCnt) {
		return extraMapper.modifyExtraCnt(extraSelect, extraCnt);
	}

	@Override
	public int chkCntByName(String extraName) {
		return extraMapper.chkCntByName(extraName);
	}

	

}

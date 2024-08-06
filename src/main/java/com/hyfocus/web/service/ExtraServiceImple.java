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
	public int chtCntByBag(String data) {
		return extraMapper.getBagCount(data);
	}

	@Override
	public int chkCntByTripod(String data) {
		return extraMapper.getTripodCount(data);
	}

}

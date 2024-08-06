package com.hyfocus.web.service;

import java.util.ArrayList;

import com.hyfocus.web.domain.ExtraVO;

public interface ExtraService {

	ArrayList<ExtraVO> selectAllData();

	int chtCntByBag(String data);
	int chkCntByTripod(String data);
}

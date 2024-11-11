package com.hyfocus.web.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.hyfocus.web.domain.RentVO;
import com.hyfocus.web.util.Pagination;

public interface RentService {

	int insert(String camera, String lens, String bag, String tripod, String stuInfo, Date createdDate);

	ArrayList<RentVO> getAllData();
	
	List<RentVO> getPagingBoards(Pagination pagination);
	int getTotalCount(Pagination pagination);

	RentVO getAllDataByRentNo(int rentNo);

	int delete(int rentNo);

	int rent(int rentNo);
	int returnRent(RentVO rentVO);

	List<RentVO> getRentData();

	ArrayList<RentVO> getAllDataByStuInfo(String stuInfo);

	String modifyUserCamera(String rentNo, String camName);

	String modifyUserData(String rentNo, String stuInfo);

	String modifyUserLens(String rentNo, String lensName);

	String modifyUserExtra(String rentNo, String extraName);

	
}

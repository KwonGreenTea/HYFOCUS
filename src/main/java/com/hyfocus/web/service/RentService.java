package com.hyfocus.web.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.hyfocus.web.domain.RentVO;
import com.hyfocus.web.util.Pagination;

public interface RentService {

	int insert(String camera, String lens, String stuInfo, Date createdDate);

	ArrayList<RentVO> getAllData();
	
	List<RentVO> getPagingBoards(Pagination pagination);
	int getTotalCount(Pagination pagination);

	RentVO getAllDataByRentNo(int rentNo);

	int delete(int rentNo);

	int rent(int rentNo);
	int returnRent(RentVO rentVO);

	List<RentVO> getRentData();

	ArrayList<RentVO> getAllDataByStuInfo(String stuInfo);

	int modifyUserCamera(Integer rentNo, String camName);

	int modifyUserData(Integer rentNo, String stuInfo);

	int modifyUserLens(Integer rentNo, String lensName);

	int modifyUserExtra(Integer rentNo, String extraName);

	int updateUserExtra(Integer rentNo, String extraName, int extraCount);

	int updateBad(String bag, String stuInfo);

	int deleteAllRentForList();

	int updateAllRentForList();

	int updateAllReturnForList();

	
}

package com.hyfocus.web.persistence;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.hyfocus.web.domain.RentVO;
import com.hyfocus.web.util.Pagination;

@Mapper
public interface RentMapper {

	int insert(@Param("camera") String camera, @Param("lens") String lens, @Param("bag") String bag,
			@Param("tripod") String tripod, @Param("stuInfo") String stuInfo, @Param("createdDate") Date createdDate);

	ArrayList<RentVO> getAllData();

	List<RentVO> selectListByPagination(Pagination pagination);

	int selectTotalCount(Pagination pagination);

	RentVO getAllDataByRentNo(int rentNo);

	int delete(int rentNo);

	int rent(int rentNo);

	int returnRent(int rentNo);

	List<RentVO> getRentData();

	ArrayList<RentVO> getAllDataByStuInfo(String stuInfo);

	String modifyUserCamera(@Param("rentNo") Integer rentNo, @Param("camera") String camName);

	String modifyUserData(@Param("rentNo") Integer rentNo, @Param("stuInfo") String stuInfo);

	String modifyUserLens(@Param("rentNo") Integer rentNo, @Param("lens") String lensName);

	String modifyUserExtra(@Param("rentNo") Integer rentNo, @Param("bag") String extraName);

}

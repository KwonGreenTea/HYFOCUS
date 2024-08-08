package com.hyfocus.web.persistence;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.hyfocus.web.domain.ExtraVO;

@Mapper
public interface ExtraMapper {

	ArrayList<ExtraVO> selectAllData();

	int getBagCount(@Param("bag") String bag);
	int updateBagCount(@Param("bag") String bag, @Param("count") int count);
	ExtraVO selectAllDataByName(@Param("data") String data);
	
	int getTripodCount(@Param("tripod") String tripod);
	int updateTripodCount(@Param("tripod") String tripod, @Param("count") int count);

	

}

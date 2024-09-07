package com.hyfocus.web.persistence;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.hyfocus.web.domain.LensVO;

@Mapper
public interface LensMapper {

	ArrayList<LensVO> selectAllData();
	ArrayList<LensVO> selectAllDataCanon();
	ArrayList<LensVO> selectAllDataTamron();
	ArrayList<LensVO> selectAllDataSigma();
	LensVO selectAllDataByName(@Param("data") String data);
	
	int getCount(@Param("lens") String lens);
	int updateCount(@Param("lens") String lens, @Param("count") int count);
	int modifyLensCnt(@Param("lens") String lens, @Param("count") int count);
	
	

}

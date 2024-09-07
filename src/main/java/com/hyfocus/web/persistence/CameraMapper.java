package com.hyfocus.web.persistence;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.hyfocus.web.domain.CameraVO;

@Mapper
public interface CameraMapper {

	ArrayList<CameraVO> selectAllData();
	ArrayList<CameraVO> selectAllDataDSLR();
	ArrayList<CameraVO> selectAllDataFilmM();
	ArrayList<CameraVO> selectAllDataFilmA();
	ArrayList<CameraVO> selectAllDataMirrorLess();
	CameraVO selectAllDataByName(@Param("data") String data);
	
	int selectCamCount(String camName);
	int getCount(@Param("camera") String camera);
	int updateCount(@Param("camera") String camera, @Param("count") int count);
	int modifyCameraCnt(@Param("camera") String camera, @Param("count") int count);
	
	
	

}

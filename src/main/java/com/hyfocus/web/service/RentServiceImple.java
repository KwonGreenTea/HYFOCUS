package com.hyfocus.web.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hyfocus.web.domain.RentVO;
import com.hyfocus.web.persistence.CameraMapper;
import com.hyfocus.web.persistence.ExtraMapper;
import com.hyfocus.web.persistence.LensMapper;
import com.hyfocus.web.persistence.RentMapper;
import com.hyfocus.web.util.Pagination;

import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class RentServiceImple implements RentService {
	
	@Autowired
	private RentMapper rentMapper;
	
	@Autowired
	private CameraMapper cameraMapper;
	
	@Autowired
	private LensMapper lensMapper;
	
	@Autowired
	private ExtraMapper extraMapper;

	@Transactional(value = "transactionManager") 
	@Override
	public int insert(String camera, String lens, String bag, String tripod, String stuInfo, Date createdDate) {
		int count = 0;
		count = cameraMapper.getCount(camera);
		log.info(cameraMapper.updateCount(camera, --count) + "행 카메라 갯수 업데이트");
		
		if(lens != null && !lens.isEmpty()) {
			count = lensMapper.getCount(lens);
			log.info(lensMapper.updateCount(lens, --count) + "행 렌즈 갯수 업데이트");
		}
		
		if(bag != null && !bag.isEmpty()) {
			count = extraMapper.getBagCount();
			log.info(extraMapper.updateBagCount(bag, --count) + "행 가방 갯수 업데이트");
		}
		
		if(tripod != null && !tripod.isEmpty()) {
			count = extraMapper.getTripodCount();
			log.info(extraMapper.updateTripodCount(tripod, --count) + "행 삼각대 갯수 업데이트");
		}
		
		return rentMapper.insert(camera, lens, bag, tripod, stuInfo, createdDate);
	}

	@Override
	public ArrayList<RentVO> getAllData() {
		return rentMapper.getAllData();
	}
	
	@Override
	public List<RentVO> getPagingBoards(Pagination pagination) {
		return rentMapper.selectListByPagination(pagination);
	} 
	
	@Override
	public int getTotalCount(Pagination pagination) {
		return rentMapper.selectTotalCount(pagination);
	} 

	@Override
	public RentVO getAllDataByRentNo(int rentNo) {
		return rentMapper.getAllDataByRentNo(rentNo);
	}

	@Transactional(value = "transactionManager")
	@Override
	public int delete(int rentNo) {
		int count = 0;
		
		RentVO rentVO = rentMapper.getAllDataByRentNo(rentNo);
		
		count = cameraMapper.getCount(rentVO.getCamName());
		log.info(cameraMapper.updateCount(rentVO.getCamName(), ++count) + "행 카메라 갯수 업데이트");
		
		if(rentVO.getLensName() != null && !rentVO.getLensName().isEmpty()) {
			count = lensMapper.getCount(rentVO.getLensName());
			log.info(lensMapper.updateCount(rentVO.getLensName(), ++count) + "행 렌즈 갯수 업데이트");
		}
		
		if(rentVO.getBag() != null && !rentVO.getBag().isEmpty()) {
			count = extraMapper.getBagCount();
			log.info(extraMapper.updateBagCount(rentVO.getBag(), ++count) + "행 가방 갯수 업데이트");
		}
		
		if(rentVO.getTripod() != null && !rentVO.getTripod().isEmpty()) {
			count = extraMapper.getTripodCount();
			log.info(extraMapper.updateTripodCount(rentVO.getTripod(), ++count) + "행 삼각대 갯수 업데이트");
		}
		
		
		return rentMapper.delete(rentNo);
	}

	@Override
	public int rent(int rentNo) {
		return rentMapper.rent(rentNo);
	}
	
	@Transactional(value = "transactionManager")
	@Override
	public int returnRent(RentVO rentVO) {
		int count = 0;
		count = cameraMapper.getCount(rentVO.getCamName());
		log.info(cameraMapper.updateCount(rentVO.getCamName(), ++count) + "행 카메라 갯수 업데이트");
		
		if(rentVO.getLensName() != null && !rentVO.getLensName().isEmpty()) {
			count = lensMapper.getCount(rentVO.getLensName());
			log.info(lensMapper.updateCount(rentVO.getLensName(), ++count) + "행 렌즈 갯수 업데이트");
		}
		
		if(rentVO.getBag() != null && !rentVO.getBag().isEmpty()) {
			count = extraMapper.getBagCount();
			log.info(extraMapper.updateBagCount(rentVO.getBag(), ++count) + "행 가방 갯수 업데이트");
		}
		
		if(rentVO.getTripod() != null && !rentVO.getTripod().isEmpty()) {
			count = extraMapper.getTripodCount();
			log.info(extraMapper.updateTripodCount(rentVO.getTripod(), ++count) + "행 삼각대 갯수 업데이트");
		}
		
		return rentMapper.returnRent(rentVO.getRentNo());
	}

	@Override
	public List<RentVO> getRentData() {
		return rentMapper.getRentData();
	}

	@Override
	public ArrayList<RentVO> getAllDataByStuInfo(String stuInfo) {
		return rentMapper.getAllDataByStuInfo(stuInfo);
	}

	@Override
	public int modifyUserCamera(Integer rentNo, String camName) {
		return rentMapper.modifyUserCamera(rentNo, camName);
	}

	@Override
	public int modifyUserData(Integer rentNo, String stuInfo) {
		return rentMapper.modifyUserData(rentNo, stuInfo);
	}

	@Override
	public int modifyUserLens(Integer rentNo, String lensName) {
		return rentMapper.modifyUserLens(rentNo, lensName);
	}

	@Override
	public int modifyUserExtra(Integer rentNo, String extraName) {
		return rentMapper.modifyUserExtra(rentNo, extraName);
	}

}

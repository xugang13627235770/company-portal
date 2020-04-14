package com.bootdo.indexpic.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.indexpic.dao.PicTypeDao;
import com.bootdo.indexpic.domain.PicTypeDO;
import com.bootdo.indexpic.service.PicTypeService;



@Service
public class PicTypeServiceImpl implements PicTypeService {
	@Autowired
	private PicTypeDao picTypeDao;
	
	@Override
	public PicTypeDO get(Integer id){
		return picTypeDao.get(id);
	}
	
	@Override
	public List<PicTypeDO> list(Map<String, Object> map){
		return picTypeDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return picTypeDao.count(map);
	}
	
	@Override
	public int save(PicTypeDO picType){
		return picTypeDao.save(picType);
	}
	
	@Override
	public int update(PicTypeDO picType){
		return picTypeDao.update(picType);
	}
	
	@Override
	public int remove(Integer id){
		return picTypeDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return picTypeDao.batchRemove(ids);
	}
	
}

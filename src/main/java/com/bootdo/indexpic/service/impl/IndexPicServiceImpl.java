package com.bootdo.indexpic.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.indexpic.dao.IndexPicDao;
import com.bootdo.indexpic.domain.IndexPicDO;
import com.bootdo.indexpic.service.IndexPicService;



@Service
public class IndexPicServiceImpl implements IndexPicService {
	@Autowired
	private IndexPicDao indexPicDao;
	
	@Override
	public IndexPicDO get(Long cid){
		return indexPicDao.get(cid);
	}
	
	@Override
	public List<IndexPicDO> list(Map<String, Object> map){
		return indexPicDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return indexPicDao.count(map);
	}
	
	@Override
	public int save(IndexPicDO indexPic){
		return indexPicDao.save(indexPic);
	}
	
	@Override
	public int update(IndexPicDO indexPic){
		return indexPicDao.update(indexPic);
	}
	
	@Override
	public int remove(Long cid){
		return indexPicDao.remove(cid);
	}
	
	@Override
	public int batchRemove(Long[] cids){
		return indexPicDao.batchRemove(cids);
	}
	
}

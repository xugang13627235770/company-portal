package com.bootdo.indexpic.service;

import com.bootdo.indexpic.domain.IndexPicDO;

import java.util.List;
import java.util.Map;

/**
 * 首页轮播图片
 * 
 * @author ZhangLG
 * @email 979858199@qq.com
 * @date 2019-10-15 15:43:42
 */
public interface IndexPicService {
	
	IndexPicDO get(Long cid);
	
	List<IndexPicDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(IndexPicDO indexPic);
	
	int update(IndexPicDO indexPic);
	
	int remove(Long cid);
	
	int batchRemove(Long[] cids);
}

package com.bootdo.indexpic.service;

import com.bootdo.indexpic.domain.PicTypeDO;

import java.util.List;
import java.util.Map;

/**
 * 首页轮播图片类型
 * 
 * @author ZhangLG
 * @email 979858199@qq.com
 * @date 2019-10-15 15:43:42
 */
public interface PicTypeService {
	
	PicTypeDO get(Integer id);
	
	List<PicTypeDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(PicTypeDO picType);
	
	int update(PicTypeDO picType);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}

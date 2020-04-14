package com.bootdo.indexpic.dao;

import com.bootdo.indexpic.domain.IndexPicDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 首页轮播图片
 * @author ZhangLG
 * @email 979858199@qq.com
 * @date 2019-10-15 15:43:42
 */
@Mapper
public interface IndexPicDao {

	IndexPicDO get(Long cid);
	
	List<IndexPicDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(IndexPicDO indexPic);
	
	int update(IndexPicDO indexPic);
	
	int remove(Long cid);
	
	int batchRemove(Long[] cids);
}

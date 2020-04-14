package com.bootdo.product.dao;

import com.bootdo.product.domain.ProductPicDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 产品图片
 * @author ZhangLG
 * @email 979858199@qq.com
 * @date 2019-10-15 15:41:13
 */
@Mapper
public interface ProductPicDao {

	ProductPicDO get(Long cid);
	
	List<ProductPicDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(ProductPicDO productPic);
	
	int update(ProductPicDO productPic);
	
	int remove(Long cid);
	
	int batchRemove(Long[] cids);
}

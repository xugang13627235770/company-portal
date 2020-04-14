package com.bootdo.product.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.product.dao.ProductPicDao;
import com.bootdo.product.domain.ProductPicDO;
import com.bootdo.product.service.ProductPicService;



@Service
public class ProductPicServiceImpl implements ProductPicService {
	@Autowired
	private ProductPicDao productPicDao;
	
	@Override
	public ProductPicDO get(Long cid){
		return productPicDao.get(cid);
	}
	
	@Override
	public List<ProductPicDO> list(Map<String, Object> map){
		return productPicDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return productPicDao.count(map);
	}
	
	@Override
	public int save(ProductPicDO productPic){
		return productPicDao.save(productPic);
	}
	
	@Override
	public int update(ProductPicDO productPic){
		return productPicDao.update(productPic);
	}
	
	@Override
	public int remove(Long cid){
		return productPicDao.remove(cid);
	}
	
	@Override
	public int batchRemove(Long[] cids){
		return productPicDao.batchRemove(cids);
	}
	
}

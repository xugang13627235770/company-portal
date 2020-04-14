package com.bootdo.article.service;

import com.bootdo.article.domain.NewsDO;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

/**
 * 文章内容
 * 
 * @author ZhangLG
 * @email 979858199@qq.com
 * @date 2019-10-12 10:36:09
 */
public interface NewsService {
	
	NewsDO get(Long cid);
	
	List<NewsDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(NewsDO news);
	
	int update(NewsDO news);
	
	int remove(Long cid);
	
	int batchRemove(Long[] cids);
	
}

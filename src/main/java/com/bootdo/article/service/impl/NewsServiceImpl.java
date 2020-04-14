package com.bootdo.article.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import com.bootdo.article.dao.NewsDao;
import com.bootdo.article.domain.NewsDO;
import com.bootdo.article.service.NewsService;
import com.bootdo.common.domain.FileDO;
import com.bootdo.common.utils.FileType;
import com.bootdo.common.utils.FileUtil;
import com.bootdo.common.utils.ImageUtils;
import com.bootdo.common.utils.R;
import com.bootdo.system.domain.UserDO;



@Service
public class NewsServiceImpl implements NewsService {
	@Autowired
	private NewsDao newsDao;
	
	@Override
	public NewsDO get(Long cid){
		return newsDao.get(cid);
	}
	
	@Override
	public List<NewsDO> list(Map<String, Object> map){
		return newsDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return newsDao.count(map);
	}
	
	@Override
	public int save(NewsDO news){
		return newsDao.save(news);
	}
	
	@Override
	public int update(NewsDO news){
		return newsDao.update(news);
	}
	
	@Override
	public int remove(Long cid){
		return newsDao.remove(cid);
	}
	
	@Override
	public int batchRemove(Long[] cids){
		return newsDao.batchRemove(cids);
	}
	
	
}

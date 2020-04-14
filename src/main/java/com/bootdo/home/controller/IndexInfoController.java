package com.bootdo.home.controller;

import com.alibaba.fastjson.JSONObject;
import com.bootdo.article.domain.NewsDO;
import com.bootdo.article.service.NewsService;

import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author huamu.com
 */
@Controller
@RequestMapping("/home/indexinfo")
public class IndexInfoController {
	@Autowired
	private NewsService newsService;

	@ResponseBody
	@PostMapping("/list")
	public JSONObject list(int page,int rows){
		//查询列表数据
		Map<String, Object> params=new HashMap<String, Object>();
		params.put("offset", (page-1)*rows);
		params.put("limit", rows);
		params.put("page", page);
		List<NewsDO> newsList = newsService.list(params);
		int total = newsService.count(params);
//		PageUtils pageUtils = new PageUtils(newsList, total);
//		return pageUtils;
		
		JSONObject json = new JSONObject();
		json.put("total", total);
		json.put("rows", newsList);
		return json;
	}
	
}

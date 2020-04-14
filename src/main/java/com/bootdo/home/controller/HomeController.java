package com.bootdo.home.controller;

import com.alibaba.fastjson.JSONObject;
import com.bootdo.article.domain.NewsDO;
import com.bootdo.article.service.NewsService;
import com.bootdo.common.utils.DateUtils;
import com.bootdo.common.utils.GenUtils;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.indexpic.domain.IndexPicDO;
import com.bootdo.indexpic.service.IndexPicService;
import com.bootdo.product.domain.ProductPicDO;
import com.bootdo.product.service.ProductPicService;
import com.bootdo.system.service.UserService;

import org.apache.commons.configuration.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author huamu.com
 */
@Controller
public class HomeController {

	@Autowired
	private NewsService newsService;
	@Autowired
	private ProductPicService productPicService;
	@Autowired
	private UserService userService;
	@Autowired
	private IndexPicService indexPicService;

	@GetMapping("/home")
	String home(Model model) {
		// 查询新闻列表数据
		Map<String, Object> params = SetParams(1, 3);
		params.put("type", "news");
		List<NewsDO> newsList = newsService.list(params);
		int total1 = newsService.count(params);
		// 查询公告列表数据
		Map<String, Object> params1 = SetParams(1, 2);
		params1.put("type", "announcement");
		List<NewsDO> ggList = newsService.list(params1);
		int total2 = newsService.count(params1);
		// 查询资讯列表数据
		Map<String, Object> params2 = SetParams(1, 3);
		params2.put("type", "message");
		List<NewsDO> zxList = newsService.list(params2);
		int total3 = newsService.count(params2);
		// 查询一条案例数据
		Map<String, Object> params4 = SetParams(1, 1);
		params4.put("type", "case");
		NewsDO alinfo = newsService.list(params4).get(0);
//		int total4 = newsService.count(params4);
		//获取轮播图列表
		Map<String, Object> params5 = SetParams(1, 3);
		List<IndexPicDO> list3=indexPicService.list(params5);
		IndexPicDO lbinfo=new IndexPicDO();
		if (list3.size()<1) {
			list3.add(lbinfo);
		}
		model.addAttribute("indexPicList", list3);
		//
		model.addAttribute("newsList", newsList);
		model.addAttribute("newfirstpic", newsList.get(0).getPic());
		model.addAttribute("ggList", ggList);
		model.addAttribute("zxList", zxList);
		model.addAttribute("alinfo", alinfo);
		//查询产品图片列表信息
		Map<String, Object> params3 = SetParams(1, 6);
		List<ProductPicDO> productList=productPicService.list(params3);
		model.addAttribute("productList", productList);
		return "index/index";
	}


	@GetMapping("/index.html")
	public String index(Model model) {
		// 查询新闻列表数据
		Map<String, Object> params = SetParams(1, 3);
		params.put("type", "news");
		List<NewsDO> newsList = newsService.list(params);
		int total1 = newsService.count(params);
		// 查询公告列表数据
		Map<String, Object> params1 = SetParams(1, 2);
		params1.put("type", "announcement");
		List<NewsDO> ggList = newsService.list(params1);
		int total2 = newsService.count(params1);
		// 查询资讯列表数据
		Map<String, Object> params2 = SetParams(1, 3);
		params2.put("type", "message");
		List<NewsDO> zxList = newsService.list(params2);
		int total3 = newsService.count(params2);
		// 查询一条案例数据
		Map<String, Object> params4 = SetParams(1, 1);
		params4.put("type", "case");
		NewsDO alinfo = newsService.list(params4).get(0);
//		int total4 = newsService.count(params4);
		//获取轮播图列表
		Map<String, Object> params5 = SetParams(1, 3);
		List<IndexPicDO> list3=indexPicService.list(params5);
		IndexPicDO lbinfo=new IndexPicDO();
		if (list3.size()<1) {
			list3.add(lbinfo);
		}
		model.addAttribute("indexPicList", list3);
		//
		model.addAttribute("newsList", newsList);
		model.addAttribute("newfirstpic", newsList.get(0).getPic());
		model.addAttribute("ggList", ggList);
		model.addAttribute("zxList", zxList);
		model.addAttribute("alinfo", alinfo);
		//查询产品图片列表信息
		Map<String, Object> params3 = SetParams(1, 6);
		List<ProductPicDO> productList=productPicService.list(params3);
		model.addAttribute("productList", productList);
		return "index/index";
	}

	@GetMapping("/about.html")
	public String about(@RequestParam(value = "type", required = false, defaultValue = "1") Integer type,Model model) {
		//获取配置的关于的基本信息
		Configuration conf = GenUtils.getAboutConfig();		
		Map<String, Object> pageinfo = new HashMap<>(16);
		if (type==1) {
			//公司简介
			pageinfo.put("title", conf.getProperty("company_profile_title"));
			pageinfo.put("author", conf.getProperty("company_profile_author"));
			pageinfo.put("origin", conf.getProperty("company_profile_origin"));
			pageinfo.put("content", conf.getProperty("company_profile_content"));
		}else if (type==2) {
			//企业文化
			pageinfo.put("title", conf.getProperty("company_culture_title"));
			pageinfo.put("author", conf.getProperty("company_culture_author"));
			pageinfo.put("origin", conf.getProperty("company_culture_origin"));
			pageinfo.put("content", conf.getProperty("company_culture_content"));
		}else if (type==3) {
			//经营理念
			pageinfo.put("title", conf.getProperty("business_philosophy_title"));
			pageinfo.put("author", conf.getProperty("business_philosophy_author"));
			pageinfo.put("origin", conf.getProperty("business_philosophy_origin"));
			pageinfo.put("content", conf.getProperty("business_philosophy_content"));
		}else{//默认简介
			pageinfo.put("title", conf.getProperty("company_profile_title"));
			pageinfo.put("author", conf.getProperty("company_profile_author"));
			pageinfo.put("origin", conf.getProperty("company_profile_origin"));
			pageinfo.put("content", conf.getProperty("company_profile_content"));
		}
		model.addAttribute("pageinfo", pageinfo);
		setPageInfo(model);
		return "index/about";
	}

	@GetMapping("/list_article.html")
	public String list_article(@RequestParam(value = "type", required = false, defaultValue = "0") Integer type,Model model) {
		//查询产品图片列表信息
		Map<String, Object> params = new HashMap<String, Object>();
//		params.put("type", "news");
		//请求参数动态加载类型列表(筛选过滤参数设置)
//		 <option value="news">公司新闻</option>
//		 <option value="message">行业资讯</option>
//		 <option value="case">客户案例</option>	
//		 <option value="announcement">公告</option>	
		if (type==1) {
			params.put("type", "news");
		}else if (type==2) {
			params.put("type", "message");
		}else if (type==3) {
			params.put("type", "case");
		}else if (type==4) {
			params.put("type", "announcement");
		}else {
		}
		int total = newsService.count(params);
		Map<String, Object> params1 = SetParams(1, total);
		//请求参数动态加载类型列表(筛选过滤参数设置)
		if (type==1) {
			params1.put("type", "news");
		}else if (type==2) {
			params1.put("type", "message");
		}else if (type==3) {
			params1.put("type", "case");
		}else if (type==4) {
			params1.put("type", "announcement");
		}else {
		}
		List<NewsDO> newsList = newsService.list(params1);
		model.addAttribute("newsList", newsList);
		setPageInfo(model);
		return "index/list_article";
	}

	@GetMapping("/product.html")
	public String product(Model model) {
		//查询产品图片列表信息
		int total = productPicService.count(null);
			Map<String, Object> params3 = SetParams(1, total);
			List<ProductPicDO> productList=productPicService.list(params3);
			model.addAttribute("productList", productList);
			//补充页面的信息查询
			setPageInfo(model);
		return "index/product";
	}

	@GetMapping("/showproduct.html")
	public String showproduct(@RequestParam(value = "id", required = false, defaultValue = "0") int id, Model model) {
		ProductPicDO productInfo=productPicService.get(Long.valueOf(id));
		model.addAttribute("showproductInfo", productInfo);
		model.addAttribute("author", userService.get(productInfo.getCreated()).getUsername());
		//补充页面的信息查询
		setPageInfo(model);
		return "index/showproduct";
	}

	@GetMapping("/shownews.html")
	public String shownews(int id, Model model) {
		model.addAttribute("shownewsInfo", newsService.get(Long.valueOf(id)));
		//补充页面的信息查询
		setPageInfo(model);
		return "index/shownews";
	}

	@GetMapping("/shownews1.html")
	public String shownews1(Model model) {
		// model.addAttribute("shownewsInfo",
		// newsService.get(Long.valueOf(id)));
		//补充页面的信息查询
		setPageInfo(model);
		return "index/shownews1";
	}

	@GetMapping("/shownews.htm")
	public String shownews(Model model) {
		Map<String, Object> params = SetParams(1, 2);
		List<NewsDO> ns = newsService.list(params);
		if (ns.size() > 0) {
			model.addAttribute("shownewsInfo", ns.get(0));
		} else {
			// 补空
			model.addAttribute("shownewsInfo", new NewsDO());
		}
		//补充页面的信息查询
		setPageInfo(model);
		return "index/shownews";
	}
	
	@GetMapping("/contactus.html")
	public String contactus(Model model) {
		//补充页面的信息查询
		setPageInfo(model);
		return "index/contactus";
	}
	
	public Map<String, Object> SetParams(int page, int rows) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("offset", (page - 1) * rows);
		params.put("limit", rows);
		params.put("page", page);
		return params;
	}
	
	public Model setPageInfo(Model model) {
		// 查询一条公告列表数据
		Map<String, Object> params1 = SetParams(1, 1);
		params1.put("type", "announcement");
		List<NewsDO> list1=newsService.list(params1);
		NewsDO gginfo=new NewsDO();
		if (list1.size()>0) {
			gginfo = list1.get(0);
		}
		// 查询一条案例数据
		Map<String, Object> params2 = SetParams(1, 1);
		params2.put("type", "case");
		List<NewsDO> list2=newsService.list(params2);
		NewsDO alinfo=new NewsDO();
		if (list1.size()>0) {
			alinfo = list2.get(0);
		}
		//获取轮播图列表
		Map<String, Object> params3 = SetParams(1, 3);
		List<IndexPicDO> list3=indexPicService.list(params3);
		IndexPicDO lbinfo=new IndexPicDO();
		if (list3.size()<1) {
			list3.add(lbinfo);
		}
		model.addAttribute("alinfo", alinfo);
		model.addAttribute("indexPicList", list3);
		model.addAttribute("gginfo", gginfo);
		
		return model;
	}
}

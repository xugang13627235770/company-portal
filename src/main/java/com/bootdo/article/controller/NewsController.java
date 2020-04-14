package com.bootdo.article.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.bootdo.article.domain.NewsDO;
import com.bootdo.article.service.NewsService;
import com.bootdo.common.config.BootdoConfig;
import com.bootdo.common.controller.BaseController;
import com.bootdo.common.domain.FileDO;
import com.bootdo.common.service.FileService;
import com.bootdo.common.utils.FileType;
import com.bootdo.common.utils.FileUtil;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 文章内容
 * 
 * @author ZhangLG
 * @email 979858199@qq.com
 * @date 2019-10-12 10:36:09
 */
 
@Controller
@RequestMapping("/article/news")
public class NewsController extends BaseController{
	@Autowired
	private NewsService newsService;
	@Autowired
	private BootdoConfig bootdoConfig;
	@Autowired
	private FileService sysFileService;
	
	@GetMapping()
	@RequiresPermissions("article:news:news")
	String News(){
	    return "article/news/news";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("article:news:news")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<NewsDO> newsList = newsService.list(query);
		int total = newsService.count(query);
		PageUtils pageUtils = new PageUtils(newsList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("article:news:add")
	String add(){
	    return "article/news/add";
	}

	@GetMapping("/edit/{cid}")
	@RequiresPermissions("article:news:edit")
	String edit(@PathVariable("cid") Long cid,Model model){
		NewsDO news = newsService.get(cid);
		model.addAttribute("news", news);
	    return "article/news/edit";
	}
	//查看内容详细
	@GetMapping("/showarticle")
	String showarticle(int id,Model model){
		NewsDO news = newsService.get(Long.valueOf(id));
		model.addAttribute("news", news);
	    return "article/news/showarticle";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("article:news:add")
	public R save( NewsDO news){
		//设置初始的创建时间
		news.setGtmCreate(new Date());
		news.setGtmModified(new Date());
		//重设作者的名称及创建用户id信息
		news.setAuthor(getUsername());
		news.setCreated(getUserId());
		if(newsService.save(news)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("article:news:edit")
	public R update( NewsDO news){
		//设置修改的修改时间
		news.setGtmModified(new Date());
		//
		news.setModified(getUserId());
		newsService.update(news);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("article:news:remove")
	public R remove( Long cid){
		if(newsService.remove(cid)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("article:news:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] cids){
		newsService.batchRemove(cids);
		return R.ok();
	}
	
	//上传图片内容
	@ResponseBody
	@PostMapping("/upload")
	R upload(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
		if ("test".equals(getUsername())) {
			return R.error(1, "演示系统不允许修改,完整体验请部署程序");
		}
		String fileName = file.getOriginalFilename();
		fileName = FileUtil.renameToUUID(fileName);
		//获取图片后缀
        String prefix = fileName.substring((fileName.lastIndexOf(".") + 1));
        //判断是否为图片
        if (!prefix.contains("jpg")&&!prefix.contains("png")&&!prefix.contains("bmp")&&!prefix.contains("gif")) {
        	return R.error("您上传的不是图片类型文件上传失败！");
		}
		FileDO sysFile = new FileDO(FileType.fileType(fileName), "/files/" + fileName, new Date());
		try {
			FileUtil.uploadFile(file.getBytes(), bootdoConfig.getUploadPath(), fileName);
		} catch (Exception e) {
			return R.error();
		}
		if (sysFileService.save(sysFile) > 0) {
			return R.ok().put("fileName",sysFile.getUrl()).put("idvalue", sysFile.getId());
		}
		return R.error();
	}
	
	/**
	 * 获取服务部署根路径 http:// + ip + port
	 *
	 * @param request
	 * @return
	 */
	public static String getServerIPPort(HttpServletRequest request) {
		//+ ":" + request.getServerPort()
		return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
	}

}

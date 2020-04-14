package com.bootdo.indexpic.controller;

import com.bootdo.common.config.BootdoConfig;
import com.bootdo.common.controller.BaseController;
import com.bootdo.common.domain.FileDO;
import com.bootdo.common.service.FileService;
import com.bootdo.common.utils.*;
import com.bootdo.indexpic.domain.IndexPicDO;
import com.bootdo.indexpic.domain.PicTypeDO;
import com.bootdo.indexpic.service.IndexPicService;
import com.bootdo.indexpic.service.PicTypeService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 首页轮播图片
 * 
 * @author ZhangLG
 * @email 979858199@qq.com
 * @date 2019-10-15 15:43:42
 */
 
@Controller
@RequestMapping("/indexpic/indexPic")
public class IndexPicController extends BaseController{
	@Autowired
	private BootdoConfig bootdoConfig;
	@Autowired
	private FileService sysFileService;
	@Autowired
	private IndexPicService indexPicService;
	@Autowired
	private PicTypeService picTypeService;
	
	@GetMapping()
	@RequiresPermissions("indexpic:indexPic:indexPic")
	String IndexPic(){
	    return "indexpic/indexPic/indexPic";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("indexpic:indexPic:indexPic")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<IndexPicDO> indexPicList = indexPicService.list(query);
		int total = indexPicService.count(query);
		PageUtils pageUtils = new PageUtils(indexPicList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("indexpic:indexPic:add")
	String add(Model model){
		Map<String, Object> params=null;
		List<PicTypeDO> PicTypeList=picTypeService.list(params);
		model.addAttribute("PicTypeList", PicTypeList);
	    return "indexpic/indexPic/add";
	}

	@GetMapping("/edit/{cid}")
	@RequiresPermissions("indexpic:indexPic:edit")
	String edit(@PathVariable("cid") Long cid,Model model){
		IndexPicDO indexPic = indexPicService.get(cid);
		Map<String, Object> params=null;
		List<PicTypeDO> PicTypeList=picTypeService.list(params);
		model.addAttribute("PicTypeList", PicTypeList);
		model.addAttribute("indexPic", indexPic);
	    return "indexpic/indexPic/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("indexpic:indexPic:add")
	public R save( IndexPicDO indexPic){
		//设置初始的创建时间
		indexPic.setGtmCreate(new Date());
		//重设作者的名称及创建用户id信息
		indexPic.setCreated(getUserId());
		if(indexPicService.save(indexPic)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("indexpic:indexPic:edit")
	public R update( IndexPicDO indexPic){
		indexPicService.update(indexPic);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("indexpic:indexPic:remove")
	public R remove( Long cid){
		if(indexPicService.remove(cid)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("indexpic:indexPic:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] cids){
		indexPicService.batchRemove(cids);
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
}

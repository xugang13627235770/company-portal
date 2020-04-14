package com.bootdo.product.controller;

import java.util.Date;
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

import com.bootdo.product.domain.ProductPicDO;
import com.bootdo.product.service.ProductPicService;
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
 * 产品图片
 * 
 * @author ZhangLG
 * @email 979858199@qq.com
 * @date 2019-10-15 15:41:13
 */
 
@Controller
@RequestMapping("/product/productPic")
public class ProductPicController extends BaseController{
	@Autowired
	private ProductPicService productPicService;
	@Autowired
	private BootdoConfig bootdoConfig;
	@Autowired
	private FileService sysFileService;
	
	@GetMapping()
	@RequiresPermissions("product:productPic:productPic")
	String ProductPic(){
	    return "product/productPic/productPic";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("product:productPic:productPic")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<ProductPicDO> productPicList = productPicService.list(query);
		int total = productPicService.count(query);
		PageUtils pageUtils = new PageUtils(productPicList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("product:productPic:add")
	String add(){
	    return "product/productPic/add";
	}

	@GetMapping("/edit/{cid}")
	@RequiresPermissions("product:productPic:edit")
	String edit(@PathVariable("cid") Long cid,Model model){
		ProductPicDO productPic = productPicService.get(cid);
		model.addAttribute("productPic", productPic);
	    return "product/productPic/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("product:productPic:add")
	public R save( ProductPicDO productPic){
		//设置初始的创建时间
		productPic.setGtmCreate(new Date());
		//重设作者的名称及创建用户id信息
		productPic.setCreated(getUserId());
		if(productPicService.save(productPic)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("product:productPic:edit")
	public R update( ProductPicDO productPic){
		productPicService.update(productPic);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("product:productPic:remove")
	public R remove( Long cid){
		if(productPicService.remove(cid)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("product:productPic:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] cids){
		productPicService.batchRemove(cids);
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

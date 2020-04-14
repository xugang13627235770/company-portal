package com.bootdo.indexpic.controller;

import java.util.List;
import java.util.Map;

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

import com.bootdo.indexpic.domain.PicTypeDO;
import com.bootdo.indexpic.service.PicTypeService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 首页轮播图片类型
 * 
 * @author ZhangLG
 * @email 979858199@qq.com
 * @date 2019-10-15 15:43:42
 */
 
@Controller
@RequestMapping("/indexpic/picType")
public class PicTypeController {
	@Autowired
	private PicTypeService picTypeService;
	
	@GetMapping()
	@RequiresPermissions("indexpic:picType:picType")
	String PicType(){
	    return "indexpic/picType/picType";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("indexpic:picType:picType")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<PicTypeDO> picTypeList = picTypeService.list(query);
		int total = picTypeService.count(query);
		PageUtils pageUtils = new PageUtils(picTypeList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("indexpic:picType:add")
	String add(){
	    return "indexpic/picType/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("indexpic:picType:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		PicTypeDO picType = picTypeService.get(id);
		model.addAttribute("picType", picType);
	    return "indexpic/picType/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("indexpic:picType:add")
	public R save( PicTypeDO picType){
		if(picTypeService.save(picType)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("indexpic:picType:edit")
	public R update( PicTypeDO picType){
		picTypeService.update(picType);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("indexpic:picType:remove")
	public R remove( Integer id){
		if(picTypeService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("indexpic:picType:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		picTypeService.batchRemove(ids);
		return R.ok();
	}
	
}

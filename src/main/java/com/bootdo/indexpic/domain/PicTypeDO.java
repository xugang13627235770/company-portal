package com.bootdo.indexpic.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 首页轮播图片类型
 * 
 * @author ZhangLG
 * @email 979858199@qq.com
 * @date 2019-10-15 15:43:42
 */
public class PicTypeDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//类型名
	private String name;

	/**
	 * 设置：
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置：类型名
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：类型名
	 */
	public String getName() {
		return name;
	}
}

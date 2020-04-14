package com.bootdo.product.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 产品图片
 * 
 * @author ZhangLG
 * @email 979858199@qq.com
 * @date 2019-10-15 15:41:13
 */
public class ProductPicDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Long cid;
	//标题
	private String title;
	//简介
	private String content;
	//封面或介绍图片
	private String pic;
	//状态
	private Integer status;
	//型号（类型）
	private String type;
	//创建人id
	private Long created;
	//创建时间
	private Date gtmCreate;

	/**
	 * 设置：
	 */
	public void setCid(Long cid) {
		this.cid = cid;
	}
	/**
	 * 获取：
	 */
	public Long getCid() {
		return cid;
	}
	/**
	 * 设置：标题
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * 获取：标题
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * 设置：简介
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * 获取：简介
	 */
	public String getContent() {
		return content;
	}
	/**
	 * 设置：封面或介绍图片
	 */
	public void setPic(String pic) {
		this.pic = pic;
	}
	/**
	 * 获取：封面或介绍图片
	 */
	public String getPic() {
		return pic;
	}
	/**
	 * 设置：状态
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：状态
	 */
	public Integer getStatus() {
		return status;
	}
	/**
	 * 设置：型号（类型）
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * 获取：型号（类型）
	 */
	public String getType() {
		return type;
	}
	/**
	 * 设置：创建人id
	 */
	public void setCreated(Long created) {
		this.created = created;
	}
	/**
	 * 获取：创建人id
	 */
	public Long getCreated() {
		return created;
	}
	/**
	 * 设置：创建时间
	 */
	public void setGtmCreate(Date gtmCreate) {
		this.gtmCreate = gtmCreate;
	}
	/**
	 * 获取：创建时间
	 */
	public Date getGtmCreate() {
		return gtmCreate;
	}
}

package com.bootdo.article.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 文章内容
 * 
 * @author ZhangLG
 * @email 979858199@qq.com
 * @date 2019-10-16 11:23:54
 */
public class NewsDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Long cid;
	//标题
	private String title;
	//摘要
	private String summary;
	//封面或介绍图片
	private String pic;
	//内容
	private String content;
	//类型
	private String type;
	//状态
	private Integer status;
	//最近修改人id
	private Long modified;
	//创建人id
	private Long created;
	//作者
	private String author;
	//创建时间
	private Date gtmCreate;
	//修改时间
	private Date gtmModified;

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
	 * 设置：摘要
	 */
	public void setSummary(String summary) {
		this.summary = summary;
	}
	/**
	 * 获取：摘要
	 */
	public String getSummary() {
		return summary;
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
	 * 设置：内容
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * 获取：内容
	 */
	public String getContent() {
		return content;
	}
	/**
	 * 设置：类型
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * 获取：类型
	 */
	public String getType() {
		return type;
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
	 * 设置：最近修改人id
	 */
	public void setModified(Long modified) {
		this.modified = modified;
	}
	/**
	 * 获取：最近修改人id
	 */
	public Long getModified() {
		return modified;
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
	 * 设置：作者
	 */
	public void setAuthor(String author) {
		this.author = author;
	}
	/**
	 * 获取：作者
	 */
	public String getAuthor() {
		return author;
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
	/**
	 * 设置：修改时间
	 */
	public void setGtmModified(Date gtmModified) {
		this.gtmModified = gtmModified;
	}
	/**
	 * 获取：修改时间
	 */
	public Date getGtmModified() {
		return gtmModified;
	}
}

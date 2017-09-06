package com.centrin.ciyun.entity.hid;

import java.io.Serializable;
import java.util.Date;

import org.springframework.context.annotation.Lazy;

/**
 * 创建证件管理的实体类，对应数据库表是：HID_CERTIFICATES
 * 序列名是：SEQ_HID_CERTIFICATES
 * @author 立
 *
 */

@SuppressWarnings("serial")
@Lazy
public class HidCertificates implements Serializable {

	private long id;//证件类型表主键，唯一标示
	private Integer cerId ;//自定义证件编号
	private String cerName ;//证件名称
	private Integer cerType ;//证件类型 1 公共，2 与健管机构相关联

	private String hmoId;//健管机构id
	private String hmoName ;//健管机构名称
	private Integer state;//自定义证件编号
	
	private Date createTime;//创建时间
	private String createUser;//创建人
	
	
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Integer getCerId() {
		return cerId;
	}
	public void setCerId(Integer cerId) {
		this.cerId = cerId;
	}
	public String getCerName() {
		return cerName;
	}
	public void setCerName(String cerName) {
		this.cerName = cerName;
	}
	
	public Integer getCerType() {
		return cerType;
	}
	public void setCerType(Integer cerType) {
		this.cerType = cerType;
	}

	public String getHmoId() {
		return hmoId;
	}
	public void setHmoId(String hmoId) {
		this.hmoId = hmoId;
	}
	public String getHmoName() {
		return hmoName;
	}
	public void setHmoName(String hmoName) {
		this.hmoName = hmoName;
	}
	
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	
	
	
	
	
}

package com.centrin.ciyun.entity.vo;

import java.util.Date;

public class PerPersonVO  implements java.io.Serializable {
	private String person_id;
	private String user_name;
	private String id_no;
	private int gender;
	private Date birthday;
	private String mobile;
	private String cur_ent_id;
	private Date modify_time;
	public String getPerson_id() {
		return person_id;
	}
	public void setPerson_id(String person_id) {
		this.person_id = person_id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getId_no() {
		return id_no;
	}
	public void setId_no(String id_no) {
		this.id_no = id_no;
	}
	public int getGender() {
		return gender;
	}
	public void setGender(int gender) {
		this.gender = gender;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getCur_ent_id() {
		return cur_ent_id;
	}
	public void setCur_ent_id(String cur_ent_id) {
		this.cur_ent_id = cur_ent_id;
	}
	public Date getModify_time() {
		return modify_time;
	}
	public void setModify_time(Date modify_time) {
		this.modify_time = modify_time;
	}
}

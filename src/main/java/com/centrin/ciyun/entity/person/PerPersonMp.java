package com.centrin.ciyun.entity.person;

import java.sql.Timestamp;


/**
 * PerPersonMp entity. @author MyEclipse Persistence Tools
 */

public class PerPersonMp implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4780755367018830472L;
	// Fields

	private Long id;
	private String authMpNum;
	private String authOpenId;
	private String userId;
	private String personId;
	private String remarks;
	private String fromOpenId;
	private String fromMpNum;
	private String apiKey;
	private String apiSecret;
	private String nickName;
	private String userPic;
	private String pic;
	private int height = 170;
	private int gender = 3;
	private float weight;
	private int age = 30;
	private int subState;
	
	private String mobile;
	private Timestamp createTime;	

	// Constructors

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/** default constructor */
	public PerPersonMp() {
	}

	/** minimal constructor */
	public PerPersonMp(String fromOpenId, String fromMpNum, String apiKey,
			String apiSecret, int height, int gender, float weight,
			int age) {
		this.fromOpenId = fromOpenId;
		this.fromMpNum = fromMpNum;
		this.apiKey = apiKey;
		this.apiSecret = apiSecret;
		this.height = height;
		this.gender = gender;
		this.weight = weight;
		this.age = age;
	}

	/** full constructor */
	public PerPersonMp(String authMpNum, String authOpenId, String userId,
			String personId, String remarks, String fromOpenId,
			String fromMpNum, String apiKey, String apiSecret, String nickName,
			String userPic, int height, int gender, float weight,
			int age) {
		this.authMpNum = authMpNum;
		this.authOpenId = authOpenId;
		this.userId = userId;
		this.personId = personId;
		this.remarks = remarks;
		this.fromOpenId = fromOpenId;
		this.fromMpNum = fromMpNum;
		this.apiKey = apiKey;
		this.apiSecret = apiSecret;
		this.nickName = nickName;
		this.userPic = userPic;
		this.height = height;
		this.gender = gender;
		this.weight = weight;
		this.age = age;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAuthMpNum() {
		return this.authMpNum;
	}

	public void setAuthMpNum(String authMpNum) {
		this.authMpNum = authMpNum;
	}

	public String getAuthOpenId() {
		return this.authOpenId;
	}

	public void setAuthOpenId(String authOpenId) {
		this.authOpenId = authOpenId;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPersonId() {
		return this.personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getFromOpenId() {
		return this.fromOpenId;
	}

	public void setFromOpenId(String fromOpenId) {
		this.fromOpenId = fromOpenId;
	}

	public String getFromMpNum() {
		return this.fromMpNum;
	}

	public void setFromMpNum(String fromMpNum) {
		this.fromMpNum = fromMpNum;
	}

	public String getApiKey() {
		return this.apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	public String getApiSecret() {
		return this.apiSecret;
	}

	public void setApiSecret(String apiSecret) {
		this.apiSecret = apiSecret;
	}

	public String getNickName() {
		return this.nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getUserPic() {
		return this.userPic;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public void setUserPic(String userPic) {
		this.userPic = userPic;
	}

	public int getHeight() {
		return this.height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getGender() {
		return this.gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public float getWeight() {
		return this.weight;
	}

	public void setWeight(float weight) {
		this.weight = weight;
	}

	public int getAge() {
		return this.age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getSubState() {
		return subState;
	}

	public void setSubState(int subState) {
		this.subState = subState;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

}
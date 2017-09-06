package com.centrin.ciyun.entity.hid;

import java.sql.Timestamp;


/**
 * 微信t_wx_key→hid_wx_key
 * @author yocici
 *
 */
public class HidWxKey implements java.io.Serializable{
	/** 
     * <用一句话描述这个变量表示什么>
     */  
    private static final long serialVersionUID = 1L;
	private String medCorpId;	//体检机构id
	private String privateKey;	//私钥
	private String publicKey;  //公钥
	private Timestamp createTime = new Timestamp(System.currentTimeMillis());
	
	public HidWxKey(){}
	
	public String getMedCorpId() {
		return medCorpId;
	}
	public void setMedCorpId(String medCorpId) {
		this.medCorpId = medCorpId;
	}
	public String getPrivateKey() {
		return privateKey;
	}
	public void setPrivateKey(String privateKey) {
		this.privateKey = privateKey;
	}
	public String getPublicKey() {
		return publicKey;
	}
	public void setPublicKey(String publicKey) {
		this.publicKey = publicKey;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	
	
		
}

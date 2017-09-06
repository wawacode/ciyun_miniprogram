package com.centrin.ciyun.entity.vo;

public class VoucherMemoryVo implements java.io.Serializable {
	private Long cashId;  //代金券ID
	private Long volumeAmount; //代金券金额
	private Long detailId;   //代金券号详情ID
	private String code;     //代金券号码
	public Long getCashId() {
		return cashId;
	}
	public void setCashId(Long cashId) {
		this.cashId = cashId;
	}
	public Long getVolumeAmount() {
		return volumeAmount;
	}
	public void setVolumeAmount(Long volumeAmount) {
		this.volumeAmount = volumeAmount;
	}
	public Long getDetailId() {
		return detailId;
	}
	public void setDetailId(Long detailId) {
		this.detailId = detailId;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
}

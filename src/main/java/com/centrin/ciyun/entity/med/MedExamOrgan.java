package com.centrin.ciyun.entity.med;

import java.util.Date;

import org.apache.commons.lang.StringUtils;

public class MedExamOrgan implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private long id;
	private Long importId;
	private String organId;
	private int organType;
	private String organName;
	private Integer organOrder;
	private String parent;
	private String hidOrganId;
	private String hidOrganName;
	private Date createTime;
	private int type = 3; //1:表示从项目组中得到  2：从科室中得到 ，完整状态   3：从科室或项目组中得到，不针对类型比较
	private String medCorpId; //体检中心ID

	public MedExamOrgan(){}
	
	public MedExamOrgan(String orgName,int organType, int type){
		this.organName = orgName;
		this.organType = organType;
		this.type = type;
	}
	
	/**
	 * 
	 * @author yanxf
	 * @since 1.0
	 * @param organType
	 * @param organValue
	 *
	 */
	public MedExamOrgan(int organType, String organName){
		this.organName = organName;
		this.organType = organType;
	}
	
	public MedExamOrgan(int organType, long id){
		this.id = id;
		this.organType = organType;
	}
	
	public MedExamOrgan(Long importId, Object organId, int organType, String name, Object order, String parent, String medCorpId){
		this.importId = importId;
		this.organId = organId == null ? null : organId.toString();
		this.organType = organType;
		this.organName = name;
		this.organOrder = (order == null || "".equals(order.toString().trim()))? null : Integer.parseInt(order.toString());
		this.parent = parent;
		this.createTime = new Date();
		this.medCorpId = medCorpId;
	}
	
	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getOrganId() {
		return this.organId;
	}

	public void setOrganId(String organId) {
		this.organId = organId;
	}

	public int getOrganType() {
		return this.organType;
	}

	public void setOrganType(int organType) {
		this.organType = organType;
	}

	public String getOrganName() {
		return this.organName;
	}

	public void setOrganName(String organName) {
		this.organName = organName;
	}

	public Integer getOrganOrder() {
		return this.organOrder;
	}

	public void setOrganOrder(Integer organOrder) {
		this.organOrder = organOrder;
	}

	public String getParent() {
		return this.parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}

	public String getHidOrganId() {
		return this.hidOrganId;
	}

	public void setHidOrganId(String hidOrganId) {
		this.hidOrganId = hidOrganId;
	}

	public String getHidOrganName() {
		return this.hidOrganName;
	}

	public void setHidOrganName(String hidOrganName) {
		this.hidOrganName = hidOrganName;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Long getImportId() {
		return importId;
	}

	public void setImportId(Long importId) {
		this.importId = importId;
	}

	
	
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	// yangsisi date : 2016年3月23日 下午3:21:13
	//体检报告organs  使用hashCode和equals
	@Override
	public int hashCode() {
		return (StringUtils.isNotBlank(organName) ? organName.hashCode() : 1024) + (organType << 3) + (type << 3);
	}

	@Override
	public boolean equals(Object obj) {
		if(obj == null) return false;
		if(this == obj) return true;
		if(obj instanceof MedExamOrgan){
			MedExamOrgan o = (MedExamOrgan)obj;
			if(o.getOrganName().equals(organName) && (o.getOrganType() == organType)){
				return true;
			}
		}
		return false;
//		return super.equals(obj);
	}

	public String getMedCorpId() {
		return medCorpId;
	}

	public void setMedCorpId(String medCorpId) {
		this.medCorpId = medCorpId;
	}
	

	
	
	
}
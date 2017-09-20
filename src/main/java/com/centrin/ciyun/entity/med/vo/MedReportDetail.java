package com.centrin.ciyun.entity.med.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.centrin.ciyun.entity.med.MedExamAdvice;
import com.centrin.ciyun.entity.med.MedExamItem;
import com.centrin.ciyun.entity.med.MedExamRpt;
import com.centrin.ciyun.entity.med.MedExamSummary;
import com.centrin.ciyun.medrpt.domain.vo.SummaryVo;

/**
 * 一份体检报告详情
 * 
 * @author hql
 * 
 */
public class MedReportDetail implements Serializable {

	private static final long serialVersionUID = 1L;
	private long lastId;// 存在上一条记录
	private long nextId;// 存在下一条记录
	private List<MedExamRpt> userExamRptList;//用户的所有体检报告记录
	private MedExamRpt medExamRpt;// 体检报告基本内容
	private List<MedExamSummary> summaryList;//小结信息 ,和summaryMap不共存。由MedExamRpt.recordFlag的值决定  1使用这个，2使用summaryMap
	private List<MedExamAdvice> adviceList;// 汇总信息(建议 )
	private List<MedExamItem> exceptionItems;//异常体检项目
	private int exceptionItemNum;// 异常记录数
	
	private int extrasFlag = 1;
	private String extrasConts;
	private HidExtraContsVo extrasContsVo; //目前仅用于小程序
	private List<SummaryVo> summaryVoList; //目前仅用于小程序,用于体检报告中"检查所见"的小结页面数据输出，把所有summary处理后放在这里面
	                                       
	
	// 2016年5月12日 上午8:53:52 开始
	private Map<String, MedDetail> medDetails;// 每个体检部门信息以及对应部门的体检项目
	private int showSummary = 0; //是否存在可输出的小结，（存在小结文本不为'ciyun'的小结）。默认0，为1时表示可输出小结。
								// 这里的showSummary用于汇总分析块输出小结

	public String getExtrasConts() {
		return extrasConts;
	}

	public void setExtrasConts(String extrasConts) {
		this.extrasConts = extrasConts;
	}

	public int getExtrasFlag() {
		return extrasFlag;
	}

	public void setExtrasFlag(int extrasFlag) {
		this.extrasFlag = extrasFlag;
	}

	public Map<String, MedDetail> getMedDetails() {
		return medDetails;
	}

	public void setMedDetails(Map<String, MedDetail> medDetails) {
		this.medDetails = medDetails;
	}
	
	public List<SummaryVo> getSummaryVoList() {
		return summaryVoList;
	}

	public void setSummaryVoList(List<SummaryVo> summaryVoList) {
		this.summaryVoList = summaryVoList;
	}

	public static class MedDetail implements java.io.Serializable {
		private int rptMode = 0; //1:科室-大项-小项  2：科室-小项  3：大项-小项,,,一份报告中可能多种层次的结构都有
		private String organName = ""; //科室或大项目名称
		private List<String> mediaList = new ArrayList<String>();   //当rptLevel为1时，这里没有值，值在itemClassList中
		private List<MedExamItem> itemList = new ArrayList<MedExamItem>();   //rptLevel为1时，这里没有值
		private Map<String, MedDetail> itemClassList = new LinkedHashMap<String, MedDetail>(); // 大项列表，k为大项，v为图片和小项
		//和summaryList不共存。由MedExamRpt.recordFlag的值决定  2使用这个，1使用summaryList
		private List<MedExamSummary> summaryList = new ArrayList<MedExamSummary>();  //rptLevel为1时，这里没有值

		private int showSummary = 0; //是否存在可输出的小结，（存在小结文本不为'ciyun'的小结）。默认0，为1时表示可输出小结。
		// 这里的showSummary用于汇总分析块下两层结构输出小结和三层结构下输出小结
		// 科室或两层结构下的大项或两层结构的科室
		public int getRptMode() {
			return rptMode;
		}

		public void setRptMode(int rptMode) {
			this.rptMode = rptMode;
		}

		public List<String> getMediaList() {
			return mediaList;
		}

		public void setMediaList(List<String> mediaList) {
			this.mediaList = mediaList;
		}

		public List<MedExamItem> getItemList() {
			return itemList;
		}

		public void setItemList(List<MedExamItem> itemList) {
			this.itemList = itemList;
		}

		public Map<String, MedDetail> getItemClassList() {
			return itemClassList;
		}

		public List<MedExamSummary> getSummaryList() {
			return summaryList;
		}

		public void setSummaryList(List<MedExamSummary> summaryList) {
			this.summaryList = summaryList;
		}

		public void setItemClassList(Map<String, MedDetail> itemClassList) {
			this.itemClassList = itemClassList;
		}

		public String getOrganName() {
			return organName;
		}

		public void setOrganName(String organName) {
			this.organName = organName;
		}

		public String getMediaArray(){
			String url="";
			if(mediaList!=null && mediaList.size()>0){
				for(String media:mediaList){
					if(StringUtils.isEmpty(url)){
						url = media;
					}else{
						url += ","+media;
					}
				}
			}
			return url;
		}

		public int getShowSummary() {
			return showSummary;
		}

		public void setShowSummary(int showSummary) {
			this.showSummary = showSummary;
		}
	}

	public int getExceptionItemNum() {
		return exceptionItemNum;
	}

	public void setExceptionItemNum(int exceptionItemNum) {
		this.exceptionItemNum = exceptionItemNum;
	}

	public MedExamRpt getMedExamRpt() {
		return medExamRpt;
	}

	public void setMedExamRpt(MedExamRpt medExamRpt) {
		this.medExamRpt = medExamRpt;
	}

	public long getLastId() {
		return lastId;
	}

	public void setLastId(long lastId) {
		this.lastId = lastId;
	}

	public long getNextId() {
		return nextId;
	}

	public void setNextId(long nextId) {
		this.nextId = nextId;
	}

	public List<MedExamAdvice> getAdviceList() {
		return adviceList;
	}

	public void setAdviceList(List<MedExamAdvice> adviceList) {
		this.adviceList = adviceList;
	}

	public List<MedExamSummary> getSummaryList() {
		return summaryList;
	}

	public void setSummaryList(List<MedExamSummary> summaryList) {
		this.summaryList = summaryList;
	}

	public List<MedExamRpt> getUserExamRptList() {
		return userExamRptList;
	}

	public void setUserExamRptList(List<MedExamRpt> userExamRptList) {
		this.userExamRptList = userExamRptList;
	}

	public List<MedExamItem> getExceptionItems() {
		return exceptionItems;
	}

	public void setExceptionItems(List<MedExamItem> exceptionItems) {
		this.exceptionItems = exceptionItems;
	}

	public int getShowSummary() {
		return showSummary;
	}

	public void setShowSummary(int showSummary) {
		this.showSummary = showSummary;
	}

	public HidExtraContsVo getExtrasContsVo() {
		return extrasContsVo;
	}

	public void setExtrasContsVo(HidExtraContsVo extrasContsVo) {
		this.extrasContsVo = extrasContsVo;
	}
	
}

package com.centrin.ciyun.entity.med.vo;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import com.centrin.ciyun.entity.med.MedExamAdvice;
import com.centrin.ciyun.entity.med.MedExamItem;
import com.centrin.ciyun.entity.med.MedExamRpt;

/**
 * 多份体检报告对比详情
 * 
 * @author hql 当比较 汇总信息的时候只有adviceList记录
 *         当比较异常项和全部体检项的时候有medReportList和departmentList
 */
public class MedReportCompare implements Serializable {

	private int totalMedRptCount;// 这个人总共有几分体检报告
	private static final long serialVersionUID = 1L;
	private List<MedExamRpt> medReportList;// 体检报告基本内容 (size=体检报告份数)
	private List<MedCompareDepartment> departmentList;// 每个体检部门部门信息以及对应部门的体检项目和小结（size=科室数）
	private List<MedCompareAdvice> adviceList;// 多个体检报告中的汇总信息
	private Map<Long, Integer> rptExceptionItemSize = new HashMap<Long, Integer>();// 记录对应体检报告错误项数
	private Map<Long, Integer> rptItemSize = new HashMap<Long, Integer>();// 记录对应体检报告体检项数

	public static class MedCompareDepartment implements Serializable{
		private long stdDepartId;
		private String stdDepartName;
		private LinkedHashMap<String, LinkedHashMap<Long, MedExamItem[]>> itemClassMap;//　内部Ｍａｐ: key为itemId，value为体检项记录（多年） 外部Ｍap:key为stdItemClassName
		private LinkedHashMap<String, LinkedHashMap<Long,Long>> exceptionItemClassMap;//外层key为stdItemClassName,内层key和value都为 

		public String getStdDepartName() {
			return stdDepartName;
		}
		public void setStdDepartName(String stdDepartName) {
			this.stdDepartName = stdDepartName;
		}
		public long getStdDepartId() {
			return stdDepartId;
		}
		public void setStdDepartId(long stdDepartId) {
			this.stdDepartId = stdDepartId;
		}
		public LinkedHashMap<String, LinkedHashMap<Long, MedExamItem[]>> getItemClassMap() {
			return itemClassMap;
		}
		public void setItemClassMap(LinkedHashMap<String, LinkedHashMap<Long, MedExamItem[]>> itemClassMap) {
			this.itemClassMap = itemClassMap;
		}
		public LinkedHashMap<String, LinkedHashMap<Long,Long>> getExceptionItemClassMap() {
			return exceptionItemClassMap;
		}
		public void setExceptionItemClassMap(LinkedHashMap<String, LinkedHashMap<Long,Long>> exceptionItemClassMap) {
			this.exceptionItemClassMap = exceptionItemClassMap;
		}

	}

	public static class MedCompareAdvice implements Serializable{
		private MedExamRpt medExamRpt;
		private List<MedExamAdvice> advices;

		public MedExamRpt getMedExamRpt() {
			return medExamRpt;
		}

		public void setMedExamRpt(MedExamRpt medExamRpt) {
			this.medExamRpt = medExamRpt;
		}

		public List<MedExamAdvice> getAdvices() {
			return advices;
		}

		public void setAdvices(List<MedExamAdvice> advices) {
			this.advices = advices;
		}
	}

	public List<MedCompareDepartment> getDepartmentList() {
		return departmentList;
	}

	public void setDepartmentList(List<MedCompareDepartment> departmentList) {
		this.departmentList = departmentList;
	}

	public List<MedCompareAdvice> getAdviceList() {
		return adviceList;
	}

	public void setAdviceList(List<MedCompareAdvice> adviceList) {
		this.adviceList = adviceList;
	}

	public Map<Long, Integer> getRptExceptionItemSize() {
		return rptExceptionItemSize;
	}

	public void setRptExceptionItemSize(Map<Long, Integer> rptExceptionItemSize) {
		this.rptExceptionItemSize = rptExceptionItemSize;
	}

	public Map<Long, Integer> getRptItemSize() {
		return rptItemSize;
	}

	public void setRptItemSize(Map<Long, Integer> rptItemSize) {
		this.rptItemSize = rptItemSize;
	}

	public List<MedExamRpt> getMedReportList() {
		return medReportList;
	}

	public void setMedReportList(List<MedExamRpt> medReportList) {
		this.medReportList = medReportList;
	}

	public int getTotalMedRptCount() {
		return totalMedRptCount;
	}

	public void setTotalMedRptCount(int totalMedRptCount) {
		this.totalMedRptCount = totalMedRptCount;
	}
}

package com.centrin.ciyun.service.interfaces.hid;

public interface HidItemAbnormalCheckInterface {
	
	public void checkItemAbnormal(String personId, String type,//记录类型
			int hight, int age, int gender,
			float diastolic, //舒张压
			float systolic,//收缩压
			int pulse,//脉搏
			String bloodsugartype,//血糖类型
			float bloodsugar,//血糖值
			float weight,//体重
			float bmi,
			float uricacid,//尿酸值
			int sporttype, //运动类型
			int sportstrength, //运动强度
			int longtime //运动时长
		);
}

package com.centrin.ciyun.entity.med.vo;

import java.util.LinkedList;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class HidExtraContsVo  implements java.io.Serializable {
	private List<String> listStringValue = new LinkedList<String>();
	private List<JSONObject> listJsonObject = new LinkedList<JSONObject>();  
	private List<JSONArray> listJsonArray = new LinkedList<JSONArray>();  
	public List<String> getListStringValue() {
		return listStringValue;
	}
	public void setListStringValue(List<String> listStringValue) {
		this.listStringValue = listStringValue;
	}
	public List<JSONObject> getListJsonObject() {
		return listJsonObject;
	}
	public void setListJsonObject(List<JSONObject> listJsonObject) {
		this.listJsonObject = listJsonObject;
	}
	public List<JSONArray> getListJsonArray() {
		return listJsonArray;
	}
	public void setListJsonArray(List<JSONArray> listJsonArray) {
		this.listJsonArray = listJsonArray;
	}
	
}

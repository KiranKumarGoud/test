package com.excelra.mvc.model.analyzer;

import java.io.Serializable;
import java.util.List;

public class HeatMapResultsData implements Serializable{
	
	private Long gvkId;
	private Long strId;
	private String commonName;
	private Long activityValue;
	private String colorCode;
	private Long actId;
	private String smiles;
	
	public Long getGvkId() {
		return gvkId;
	}
	public void setGvkId(Long gvkId) {
		this.gvkId = gvkId;
	}
	public Long getStrId() {
		return strId;
	}
	public void setStrId(Long strId) {
		this.strId = strId;
	}
	public String getCommonName() {
		return commonName;
	}
	public void setCommonName(String commonName) {
		this.commonName = commonName;
	}
	public Long getActivityValue() {
		return activityValue;
	}
	public void setActivityValue(Long activityValue) {
		this.activityValue = activityValue;
	}
	public String getColorCode() {
		return colorCode;
	}
	public void setColorCode(String colorCode) {
		this.colorCode = colorCode;
	}
	public Long getActId() {
		return actId;
	}
	public void setActId(Long actId) {
		this.actId = actId;
	}
	public String getSmiles() {
		return smiles;
	}
	public void setSmiles(String smiles) {
		this.smiles = smiles;
	}
	
}

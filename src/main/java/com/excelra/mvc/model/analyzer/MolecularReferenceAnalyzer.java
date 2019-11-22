package com.excelra.mvc.model.analyzer;

public class MolecularReferenceAnalyzer {
	
	private String smile;
	private String activityUOM;
	private Long activityValue;
	private String proteinName;
	private String activityType;
	private Long activitySource;
	private String activityPrefix;
	private String targetType;
	
	public String getSmile() {
		return smile;
	}
	public void setSmile(String smile) {
		this.smile = smile;
	}
	public String getActivityUOM() {
		return activityUOM;
	}
	public void setActivityUOM(String activityUOM) {
		this.activityUOM = activityUOM;
	}
	public Long getActivityValue() {
		return activityValue;
	}
	public void setActivityValue(Long activityValue) {
		this.activityValue = activityValue;
	}
	public String getProteinName() {
		return proteinName;
	}
	public void setProteinName(String proteinName) {
		this.proteinName = proteinName;
	}
	public String getActivityType() {
		return activityType;
	}
	public void setActivityType(String activityType) {
		this.activityType = activityType;
	}
	
	public Long getActivitySource() {
		return activitySource;
	}
	public void setActivitySource(Long activitySource) {
		this.activitySource = activitySource;
	}
	public String getActivityPrefix() {
		return activityPrefix;
	}
	public void setActivityPrefix(String activityPrefix) {
		this.activityPrefix = activityPrefix;
	}
	public String getTargetType() {
		return targetType;
	}
	public void setTargetType(String targetType) {
		this.targetType = targetType;
	}	
	
}

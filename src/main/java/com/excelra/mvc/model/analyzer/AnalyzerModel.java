package com.excelra.mvc.model.analyzer;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties
public class AnalyzerModel implements Serializable {
	
	private Long proteinName;
	private String activityType;
	private Long activitySource;
	private String activityPrefix;
	private String targetType;	
	
	public Long getProteinName() {
		return proteinName;
	}
	public void setProteinName(Long proteinName) {
		this.proteinName = proteinName;
	}
	public String getActivityType() {
		return activityType;
	}
	public void setActivityType(String activityType) {
		this.activityType = activityType;
	}
	
	public String getActivityPrefix() {
		return activityPrefix;
	}
	public Long getActivitySource() {
		return activitySource;
	}
	public void setActivitySource(Long activitySource) {
		this.activitySource = activitySource;
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

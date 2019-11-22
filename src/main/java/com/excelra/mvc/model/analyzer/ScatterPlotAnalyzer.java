package com.excelra.mvc.model.analyzer;

public class ScatterPlotAnalyzer {
	
	private String target;
	private String activityType;
	private String source;
	private String xAxis;
	private String yAxis;
	private Long uniqueMolecules;
	
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	public String getActivityType() {
		return activityType;
	}
	public void setActivityType(String activityType) {
		this.activityType = activityType;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getxAxis() {
		return xAxis;
	}
	public void setxAxis(String xAxis) {
		this.xAxis = xAxis;
	}
	public String getyAxis() {
		return yAxis;
	}
	public void setyAxis(String yAxis) {
		this.yAxis = yAxis;
	}
	public Long getUniqueMolecules() {
		return uniqueMolecules;
	}
	public void setUniqueMolecules(Long uniqueMolecules) {
		this.uniqueMolecules = uniqueMolecules;
	}
	
}

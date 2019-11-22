package com.excelra.mvc.model.analyzer;

import java.io.Serializable;
import java.util.List;

public class HeatMapAnalyzerData implements Serializable{
	
	private List<HeatMapResultsData> heatMapResultsData;
		
	public List<HeatMapResultsData> getHeatMapResultsData() {
		return heatMapResultsData;
	}
	public void setHeatMapResultsData(List<HeatMapResultsData> heatMapResultsData) {
		this.heatMapResultsData = heatMapResultsData;
	}
	
}

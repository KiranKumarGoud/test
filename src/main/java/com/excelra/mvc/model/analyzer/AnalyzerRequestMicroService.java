package com.excelra.mvc.model.analyzer;

import java.io.Serializable;
import java.util.List;

public class AnalyzerRequestMicroService implements Serializable{
	
	private List<Long> strIdList;
	
	private AnalyzerModel heatMapAnalyzer;

	public List<Long> getStrIdList() {
		return strIdList;
	}

	public void setStrIdList(List<Long> strIdList) {
		this.strIdList = strIdList;
	}

	public AnalyzerModel getHeatMapAnalyzer() {
		return heatMapAnalyzer;
	}

	public void setHeatMapAnalyzer(AnalyzerModel heatMapAnalyzer) {
		this.heatMapAnalyzer = heatMapAnalyzer;
	}
	
}

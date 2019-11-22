package com.excelra.mvc.service;

import java.util.List;

import com.excelra.mvc.model.analyzer.AnalyzerModel;
import com.excelra.mvc.model.analyzer.HeatMapAnalyzerData;
import com.excelra.mvc.model.analyzer.MolecularDetails;
import com.excelra.mvc.model.userjdbc.UserJdbc;

public interface DataAnalyzers {
	
	HeatMapAnalyzerData heatMapAnalyzerData(AnalyzerModel heatMapData,UserJdbc userJdbc, String currentSessionUserToken);
	
	List<List<Float>> molecularAnalyzerData(AnalyzerModel molecularQueryAnalyzer,UserJdbc userJdbc, String currentSessionUserToken);
	
	List<MolecularDetails> getMolecularDetails(Float x,Float y, UserJdbc userJdbc, String currentSessionUserToken);

}

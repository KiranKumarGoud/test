package com.excelra.mvc.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.excelra.mvc.model.analyzer.AnalyzerModel;
import com.excelra.mvc.model.analyzer.HeatMapAnalyzerData;
import com.excelra.mvc.model.analyzer.MolecularDetails;
import com.excelra.mvc.model.userjdbc.UserJdbc;
import com.excelra.mvc.repository.DataAnalyzerComponent;
import com.excelra.mvc.repository.DataAnalyzersDAO;

@Service
public class DataAnalyzerService implements DataAnalyzers{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DataAnalyzerService.class);
	
	@Autowired
	DataAnalyzersDAO dataAnalyzersDAO;
	
	@Autowired
    DataAnalyzerComponent dataAnalyzerComponent;
	
	@Override
	public HeatMapAnalyzerData heatMapAnalyzerData(AnalyzerModel heatMapData, UserJdbc userJdbc, String currentSessionUserToken) {
		
		HeatMapAnalyzerData heatMapAnalyzerData = new HeatMapAnalyzerData();
		
		LOGGER.info("Data Analyzer DAO for fetch HeatMap ");
		
		List<Long> strIdList = dataAnalyzersDAO.getListOfstrIds(userJdbc);
		
		if(!currentSessionUserToken.isEmpty() && !strIdList.isEmpty())
			heatMapAnalyzerData.setHeatMapResultsData(dataAnalyzerComponent.generateDefaultHeatMapResults(heatMapData, strIdList, currentSessionUserToken));
		else
			heatMapAnalyzerData.setHeatMapResultsData(new ArrayList<>());
				
		return heatMapAnalyzerData;
	}

	@Override
	public List<List<Float>> molecularAnalyzerData(AnalyzerModel molecularQueryAnalyzer,UserJdbc userJdbc, String currentSessionUserToken) {
		
		LOGGER.info("Data Analyzer DAO for fetch MolecularPairQuery ");
		
		List<Long> strIdList = dataAnalyzersDAO.getMolecularStrIds(molecularQueryAnalyzer, userJdbc);
		
		if(!currentSessionUserToken.isEmpty() && !strIdList.isEmpty())
			return dataAnalyzerComponent.generateMolecularQueryData(molecularQueryAnalyzer,strIdList, currentSessionUserToken);
		else
			return new ArrayList<>();
		
	}

	@Override
	public List<MolecularDetails> getMolecularDetails(Float xAxisVal, Float yAxisVal,UserJdbc userJdbc, String currentSessionUserToken) {
		
		LOGGER.info("Data Analyzer DAO for fetch getMolecularDetails ");
		
		List<Long> strIdList = dataAnalyzersDAO.getMolecularStrIds(null, userJdbc);
		
		if (!currentSessionUserToken.isEmpty() && !strIdList.isEmpty()) 		
			return dataAnalyzerComponent.getMolecularDetails(strIdList,xAxisVal, yAxisVal,currentSessionUserToken);
		else
			return new ArrayList<>();
		
	}

}

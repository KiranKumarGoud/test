package com.excelra.mvc.utility;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import com.excelra.mvc.model.analyzer.AnalyzerModel;
import com.excelra.mvc.model.analyzer.AnalyzerRequestMicroService;

@Component
public class AnalyzersServicesUtility {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AnalyzersServicesUtility.class);
	
	/**
    *
    * @param currentSessionUserToken
    * @return
    */
   public HttpHeaders prepareHttpHeaders(String currentSessionUserToken) {

       HttpHeaders headers = new HttpHeaders();

       LOGGER.info(" RestTemplate - Microservice user Token is {} ", currentSessionUserToken);

       headers.set("Authorization", "Bearer "+currentSessionUserToken);
       headers.set("Content-Type", "application/json");

       return headers;
   }
   
   public AnalyzerRequestMicroService prepareHeatMapRequestForMicroServiceObject(AnalyzerModel heatMapAnalyzer, List<Long> strIdList) {
	   
	   AnalyzerRequestMicroService heatMapAnalyzerRequestMicroService = new AnalyzerRequestMicroService();
	   
	   heatMapAnalyzerRequestMicroService.setStrIdList(strIdList);
	   heatMapAnalyzerRequestMicroService.setHeatMapAnalyzer(heatMapAnalyzer);
	   
	   return heatMapAnalyzerRequestMicroService;
	   
   }

}

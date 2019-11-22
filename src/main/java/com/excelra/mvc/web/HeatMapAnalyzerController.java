package com.excelra.mvc.web;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.excelra.mvc.config.CustomJdbcConnection;
import com.excelra.mvc.model.analyzer.AnalyzerModel;
import com.excelra.mvc.model.analyzer.HeatMapAnalyzerData;
import com.excelra.mvc.service.DataAnalyzers;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/security/analyzers")
public class HeatMapAnalyzerController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(HeatMapAnalyzerController.class);
	
	@Autowired
    private CustomJdbcConnection customJdbcConnection;
	
	@Autowired
	DataAnalyzers dataAnalyzers;
	
	@GetMapping("/heapmapanalyzerdata")
	public ResponseEntity<?> getHeapMapAnalyzerData(@RequestHeader("userSessionId") String userSessionId, HttpSession httpSession) {
		
		LOGGER.info("Data Analyzer for Get Heat Map fetch service");
		
		String currentSessionUserToken = (String) httpSession.getAttribute(userSessionId+"_token");
		
		HeatMapAnalyzerData heatMapAnalyzerData = dataAnalyzers.heatMapAnalyzerData(null, customJdbcConnection.getUserJdbcObject(httpSession, userSessionId), currentSessionUserToken);
		
		LOGGER.info("Data Analyzer for Get Heat Map fetch service Response :"+ heatMapAnalyzerData);
		
		return new ResponseEntity<>(heatMapAnalyzerData, HttpStatus.OK);
	}
		
	@PostMapping("/heapmapanalyzerdata")
	public ResponseEntity<?> heapMapAnalyzerData(@RequestBody AnalyzerModel heatMapAnalyzer,@RequestHeader("userSessionId") String userSessionId, HttpSession httpSession) {
		
		LOGGER.info("Data Analyzer for Post Heat Map fetch service");
		
		String currentSessionUserToken = (String) httpSession.getAttribute(userSessionId+"_token");
		
		HeatMapAnalyzerData heatMapAnalyzerData = dataAnalyzers.heatMapAnalyzerData(heatMapAnalyzer, customJdbcConnection.getUserJdbcObject(httpSession, userSessionId), currentSessionUserToken);
		
		LOGGER.info("Data Analyzer for Post Heat Map fetch service Response :"+ heatMapAnalyzerData);
		
		return new ResponseEntity<>(heatMapAnalyzerData, HttpStatus.OK);
	}
	
	
}

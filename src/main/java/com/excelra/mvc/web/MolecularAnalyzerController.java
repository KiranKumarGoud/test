package com.excelra.mvc.web;

import java.util.LinkedHashMap;
import java.util.List;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.excelra.mvc.config.CustomJdbcConnection;
import com.excelra.mvc.model.analyzer.AnalyzerModel;
import com.excelra.mvc.model.analyzer.MolecularDetails;
import com.excelra.mvc.service.DataAnalyzers;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/security/analyzers")
public class MolecularAnalyzerController {
	
private static final Logger LOGGER = LoggerFactory.getLogger(MolecularAnalyzerController.class);
	
	@Autowired
    private CustomJdbcConnection customJdbcConnection;
	
	@Autowired
	DataAnalyzers dataAnalyzers;
	
	@GetMapping("/moleularanalyzerdata")
	public ResponseEntity<?> getMoleularAnalyzerData(@RequestHeader("userSessionId") String userSessionId, HttpSession httpSession) {
		
		LOGGER.info("Data Analyzer for Get Molecular Pair Query fetch service");
		
		String currentSessionUserToken = (String) httpSession.getAttribute(userSessionId+"_token");
		
		List<List<Float>> result = dataAnalyzers.molecularAnalyzerData(null,customJdbcConnection.getUserJdbcObject(httpSession, userSessionId), currentSessionUserToken);
		
		return new ResponseEntity<>(result, HttpStatus.OK);
		
	}
	
	@PostMapping("/moleularanalyzerdata")
	public ResponseEntity<?> moleularAnalyzerData(@RequestBody AnalyzerModel molecularQueryAnalyzer,@RequestHeader("userSessionId") String userSessionId, HttpSession httpSession) {
		
		LOGGER.info("Data Analyzer for Post Molecular Pair Query fetch service");
		
		String currentSessionUserToken = (String) httpSession.getAttribute(userSessionId+"_token");
		
		List<List<Float>> result = dataAnalyzers.molecularAnalyzerData(molecularQueryAnalyzer,customJdbcConnection.getUserJdbcObject(httpSession, userSessionId), currentSessionUserToken);
		
		return new ResponseEntity<>(result, HttpStatus.OK);
		
	}
	
	@PostMapping("/getmoleculardetails")
	public ResponseEntity<?> getMolecularDetails(@RequestParam("x") Float x,@RequestParam("y") Float y,@RequestHeader("userSessionId") String userSessionId, HttpSession httpSession) {
		
		LOGGER.info("Data Analyzer for Post Molecular Pair Query fetch service");
		
		String currentSessionUserToken = (String) httpSession.getAttribute(userSessionId+"_token");
		
		List<MolecularDetails> molecularDetails = dataAnalyzers.getMolecularDetails(x, y, customJdbcConnection.getUserJdbcObject(httpSession, userSessionId), currentSessionUserToken);
		
		return new ResponseEntity<>(molecularDetails, HttpStatus.OK);
		
	}

}

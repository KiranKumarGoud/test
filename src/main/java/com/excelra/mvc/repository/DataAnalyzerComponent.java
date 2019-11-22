package com.excelra.mvc.repository;

import java.io.File;
import java.net.URI;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.servlet.ServletContext;

import org.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.excelra.mvc.exception.GoStarRestTemplateException;
import com.excelra.mvc.exception.GoStarSqlException;
import com.excelra.mvc.model.analyzer.AnalyzerModel;
import com.excelra.mvc.model.analyzer.AnalyzerRequestMicroService;
import com.excelra.mvc.model.analyzer.HeatMapResultsData;
import com.excelra.mvc.model.analyzer.MolecularDetails;
import com.excelra.mvc.model.userjdbc.UserJdbc;
import com.excelra.mvc.utility.AnalyzersServicesUtility;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import chemaxon.struc.Molecule;
import chemaxon.util.MolHandler;

@Component
public class DataAnalyzerComponent {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DataAnalyzerComponent.class);
	
	@Value("${microservices.url}")
    private String microServiceUrl;
	
	@Value("${microservice.dataanlyzers.heatmap.user.selection.service}")
    private String userSelectionHeatMapUrl;
	
	@Value("${microservice.dataanlyzers.molecular.pair.query.user.selection.service}")
	private String userSelectionMolecularQueryUrl;
	
	@Value("${microservice.dataanlyzers.molecular.pair.query.details}")
	private String molecularDetailsUrl;
	
	@Autowired
	ServletContext context;
	
	@Autowired
    private RestTemplate restTemplate;
	
	@Autowired
	private AnalyzersServicesUtility analyzerServicesUtility;		
		
	
	public List<HeatMapResultsData> generateDefaultHeatMapResults(AnalyzerModel heatMapAnalyzer,List<Long> strIdList,String currentSessionUserToken) throws GoStarRestTemplateException {
		
		try {
			final String baseUrl = microServiceUrl + userSelectionHeatMapUrl;
            URI uri = new URI(baseUrl);
            HttpHeaders headers = analyzerServicesUtility.prepareHttpHeaders(currentSessionUserToken);
            AnalyzerRequestMicroService heatMapAnalyzerRequestMicroService = analyzerServicesUtility.prepareHeatMapRequestForMicroServiceObject(heatMapAnalyzer,strIdList);
            HttpEntity<AnalyzerRequestMicroService> request = new HttpEntity<>(heatMapAnalyzerRequestMicroService, headers);
            ResponseEntity<String> responseEntity = restTemplate.postForEntity(uri, request, String.class);
            JSONArray activityJson = new JSONArray(responseEntity.getBody());
            ObjectMapper mapper = new ObjectMapper();
            TypeReference<List<HeatMapResultsData>> mapType = new TypeReference<List<HeatMapResultsData>>() {};
            List<HeatMapResultsData> resultData = new ArrayList<>();
            if(Objects.nonNull(activityJson)) {
            	resultData = mapper.readValue(activityJson.toString(),mapType);
            }
            
            if(!resultData.isEmpty()) {
				for(HeatMapResultsData data:resultData) {
					data = mapper.convertValue(data, HeatMapResultsData.class);
					java.io.File file = new java.io.File(context.getRealPath("assets/images/strsmileimages/"+data.getStrId()+".png"));
					if(!file.exists()) {
						generateMolecularImagesforStrIds(file,data.getSmiles());
					}
				}
			}
            return resultData;
            
		} catch (Exception ex) {
            LOGGER.error(" AllTabServicesComponent :: generateDefaultHeatMapResults - Exception {} ", ex.getMessage());
            throw new GoStarRestTemplateException(" Error while processing generateDefaultHeatMapResults service method, error is {} ", ex);

        }
		
	}
	
	public List<List<Float>> generateMolecularQueryData(AnalyzerModel molecularQueryAnalyzer,List<Long> strIdList, String currentSessionUserToken) throws GoStarRestTemplateException {
		
		try {
			final String baseUrl = microServiceUrl + userSelectionMolecularQueryUrl;
            URI uri = new URI(baseUrl);
            HttpHeaders headers = analyzerServicesUtility.prepareHttpHeaders(currentSessionUserToken);
            AnalyzerRequestMicroService molecularQueryAnalyzerRequestMicroService = analyzerServicesUtility.prepareHeatMapRequestForMicroServiceObject(molecularQueryAnalyzer,strIdList);
            HttpEntity<AnalyzerRequestMicroService> request = new HttpEntity<>(molecularQueryAnalyzerRequestMicroService,headers);
            ResponseEntity<String> responseEntity = restTemplate.postForEntity(uri, request, String.class);
            JSONArray activityJson = new JSONArray(responseEntity.getBody());
            ObjectMapper mapper = new ObjectMapper();
            TypeReference<List<List<Float>>> mapType = new TypeReference<List<List<Float>>>() {};
            List<List<Float>> resultData = new ArrayList<List<Float>>();
            if(Objects.nonNull(activityJson)) {
            	resultData = mapper.readValue(activityJson.toString(),mapType);
            }
			return resultData;
		} catch (Exception ex) {
            LOGGER.error(" generateMolecularQueryData - Exception {} ", ex.getMessage());
            throw new GoStarRestTemplateException(" Error while processing generateMolecularQueryData service method, error is {} ", ex);
        }
		
	}
	
	public List<MolecularDetails> getMolecularDetails(List<Long> strIdList, Float xAxisVal, Float yAxisVal, String currentSessionUserToken) {
		
		try {
			final String baseUrl = microServiceUrl + molecularDetailsUrl+"?x="+xAxisVal+"&y="+yAxisVal;
            URI uri = new URI(baseUrl);
            HttpHeaders headers = analyzerServicesUtility.prepareHttpHeaders(currentSessionUserToken);
            AnalyzerRequestMicroService molecularQueryAnalyzerRequestMicroService = analyzerServicesUtility.prepareHeatMapRequestForMicroServiceObject(null,strIdList);
            HttpEntity<AnalyzerRequestMicroService> request = new HttpEntity<>(molecularQueryAnalyzerRequestMicroService,headers);
            ResponseEntity<String> responseEntity = restTemplate.postForEntity(uri, request, String.class);
            JSONArray activityJson = new JSONArray(responseEntity.getBody());
            ObjectMapper mapper = new ObjectMapper();
            TypeReference<List<MolecularDetails>> mapType = new TypeReference<List<MolecularDetails>>() {};
            List<MolecularDetails> resultData = new ArrayList<MolecularDetails>();
            if(Objects.nonNull(activityJson)) {
            	resultData = mapper.readValue(activityJson.toString(),mapType);
            }
            for(MolecularDetails molecular:resultData) {
				java.io.File file1 = new java.io.File(context.getRealPath("assets/images/strsmileimages/"+molecular.getStrId1()+".png"));
				if(!file1.exists()) {
					generateMolecularImagesforStrIds(file1,molecular.getSmiles1());
				}
				java.io.File file2 = new java.io.File(context.getRealPath("assets/images/strsmileimages/"+molecular.getStrId2()+".png"));
				if(!file2.exists()) {
					generateMolecularImagesforStrIds(file2,molecular.getSmiles2());
				}
			}
            return resultData;
		} catch (Exception e) {
			LOGGER.error(" Error while processing getMolecularDetails component, error is {} ", e.getMessage());
            throw new GoStarSqlException(" Error while processing getMolecularDetails component, error is {} ", e);
		}
		
	}

	public List<HeatMapResultsData> getListOfHeatMaps(AnalyzerModel heatMapData,JdbcTemplate specificJdbc, String heatMapQuery) {
		
		LOGGER.info("Data Analyzer for fetch HeatMapsList Component"); 
		
		try {
			return getListOfHeatMapsData(specificJdbc, heatMapQuery);
		} catch (Exception e) {
			LOGGER.error(" Error while processing getListOfHeatMaps component, error is {} ", e.getMessage());
            throw new GoStarSqlException(" Error while processing getListOfHeatMaps component, error is {} ", e);
		}
	}

	private List<HeatMapResultsData> getListOfHeatMapsData(JdbcTemplate jdbcTemplate, String heatMapQuery) {
		List<HeatMapResultsData> resultData = null;
		try {
			LOGGER.info(" getListOfHeatMapsData component -  {} ", "");
			resultData = jdbcTemplate.query(heatMapQuery,new HeatMapDataMapper());
			if(!resultData.isEmpty()) {
				for(HeatMapResultsData data:resultData) {
					java.io.File file = new java.io.File(context.getRealPath("assets/images/strsmileimages/"+data.getStrId()+".png"));
					if(!file.exists()) {
						generateMolecularImagesforStrIds(file,data.getSmiles());
					}
				}
			}
		} catch (Exception e) {
			// e.printStackTrace();
            LOGGER.error("Error while executing getListOfHeatMapsData component {} ", e.getMessage());
            throw new GoStarSqlException(" Error while executing getListOfHeatMapsData component {} ", e);
		}
		return resultData;
	}
	
	public void generateMolecularImagesforStrIds(File filename,String smile) throws java.lang.SecurityException {
		try {
			java.io.DataOutputStream out = new java.io.DataOutputStream(new java.io.FileOutputStream(filename));
			MolHandler mh = new MolHandler(smile);
			mh.aromatize();
			Molecule mol = mh.getMolecule();
			mol.clean(2,null);
			mol.dearomatize();	
			byte[] s1 = mol.toBinFormat("png:w250,h250,b32");
			out.write(s1);
			out.close();
			out = null;
		} catch (Exception e) {			
			LOGGER.error("Error while generateMolecularImagesforStrIds component{} ", e.getMessage());
            throw new GoStarSqlException(" Error while generateMolecularImagesforStrIds component{} ", e);
		}
	}
	
	/*private static final class MolecularDataMapper implements RowMapper<MolecularDetails> {
        public MolecularDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
        	MolecularDetails molarDetails = new MolecularDetails();
        	molecularDetails.setStrId1(rs.getLong("str_id_s1"));
        	molecularDetails.setStrId2(rs.getLong("str_id_s2"));
        	molecularDetails.setMm1(rs.getLong("s1.micro_molarvalue"));
        	molecularDetails.setMm2(rs.getLong("s2.micro_molarvalue"));
        	molecularDetails.setSmiles1(rs.getString("smiles_s1"));
        	molecularDetails.setSmiles2(rs.getString("smiles_s2"));
			return molarDetails;
        }
	}*/
	
	private static final class HeatMapDataMapper implements RowMapper<HeatMapResultsData> {
        public HeatMapResultsData mapRow(ResultSet rs, int rowNum) throws SQLException {
        	HeatMapResultsData activityTabDTO = new HeatMapResultsData();
        	activityTabDTO.setStrId(rs.getLong("str_id"));     
       		activityTabDTO.setCommonName(rs.getString("common_name"));
        	activityTabDTO.setSmiles(rs.getString("smiles"));
        	activityTabDTO.setActivityValue(rs.getLong("activity_value"));
			return activityTabDTO;
        }
	}

}

package com.excelra.mvc.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.excelra.mvc.exception.GoStarSqlException;
import com.excelra.mvc.model.analyzer.AnalyzerModel;
import com.excelra.mvc.model.userjdbc.UserJdbc;

@Repository
public class DataAnalyzersDAO {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DataAnalyzersDAO.class);

    @Value("${stridlist.from.visual.temptable.query}")
    private String strIdListVisualTempTableQuery;
    
    @Value("${heatmap.results.query}")
    private String heatMapResultsQuery;
    
    @Value("${userdata.heatmap.results.query}")
    private String userHeatMapResultsQuery;
    
    @Value("${molecular.query.details}")
    private String molecularDetailsQuery;
    
    @Autowired
    private AllMappingIdsDAO allMappingIdsDAO;    
    
    public List<Long> getListOfstrIds(UserJdbc userJdbc) {
		
		LOGGER.info("Data Analyzer for fetch List of HeatMaps Dao");
		List<Long> strIdList = null;
		try {
			strIdList = new ArrayList<>();
			JdbcTemplate jdbcTemplate = allMappingIdsDAO.getActivitySpecificJdbcConnection(userJdbc, "ProteinClassification");
			List<Long> resultData = jdbcTemplate.queryForList(strIdListVisualTempTableQuery,Long.class);
			
			for(int i=0;i<resultData.size();i++) {
            	strIdList.add(((Objects.nonNull(resultData.get(i))) ? resultData.get(i).longValue() : null));
            }
			
			return 	strIdList;	
			
		} catch (Exception e) {
			LOGGER.error(" Error while processing getListOfHeatMaps service method, error is {} ", e.getMessage());
            throw new GoStarSqlException(" Error while processing getListOfHeatMaps service method, error is {} ", e);
		}
	}
    	
	public List<Long> getMolecularStrIds(AnalyzerModel molecularQueryAnalyzer, UserJdbc userJdbc) {
		
		LOGGER.info("Data Analyzer for getMolecularStrIds Dao");
		
		try {
			JdbcTemplate jdbcTemplate = allMappingIdsDAO.getActivitySpecificJdbcConnection(userJdbc, "ProteinClassification");
			String strIdListVisualTempTableMolecularQuery = prepareUserMolecularData(molecularQueryAnalyzer);
			List<Long> strIdList = new ArrayList<>();
			List<Long> resultData = jdbcTemplate.queryForList(strIdListVisualTempTableMolecularQuery,Long.class);

            for(int i=0;i<resultData.size();i++) {
            	strIdList.add(((Objects.nonNull(resultData.get(i))) ? resultData.get(i).longValue() : null));
            }			
			
			return strIdList;
		} catch (Exception e) {
			LOGGER.error(" Error while processing getMolecularStrIds service method, error is {} ", e.getMessage());
            throw new GoStarSqlException(" Error while processing getMolecularStrIds service method, error is {} ", e);
		}
	}
	
	public String prepareHeatMapQuery(AnalyzerModel heatMapData) {
		String userHeatMapPrepareQuery = "";
		if(heatMapData.getProteinName() > 0) 
			userHeatMapPrepareQuery = userHeatMapPrepareQuery+" and ami.stdname_id = '"+heatMapData.getProteinName()+"'";
		
		if(!heatMapData.getActivityType().equals("")) 
			userHeatMapPrepareQuery = userHeatMapPrepareQuery+" and ami.activity_type = '"+heatMapData.getActivityType()+"'";
		
		/*if(!heatMapData.getActivitySource().equals("")) 
			userHeatMapResultsQuery = userHeatMapResultsQuery+" and ami.activity_source = '"+heatMapData.getActivitySource()+"'";*/
		
		if(!heatMapData.getActivityPrefix().equals("")) {
			String actPrefix = activityPrefixValue(heatMapData.getActivityPrefix());			
			userHeatMapPrepareQuery = userHeatMapPrepareQuery +actPrefix;
		}
		
		if(!heatMapData.getTargetType().equals("")) {
			String tarType = targetTypeVal(heatMapData.getTargetType());
			userHeatMapPrepareQuery = userHeatMapPrepareQuery+" and ami.p_s_m "+tarType+"limit 500";
		}
		
		return userHeatMapPrepareQuery;
	}
	
	public String prepareUserMolecularData(AnalyzerModel molecularQueryAnalyzer) {
		
		String analyzerStrIdQuery = "select sr.str_id from visual_results sr inner join target_search.all_mapping_ids ami on (ami.act_id = sr.act_id) where ami.micro_molarvalue >0";
		
		if(null != molecularQueryAnalyzer) {
			if(molecularQueryAnalyzer.getProteinName() > 0) 
				analyzerStrIdQuery = analyzerStrIdQuery+" and ami.stdname_id = '"+molecularQueryAnalyzer.getProteinName()+"'";
			
			if(!molecularQueryAnalyzer.getActivityType().equals("")) 
				analyzerStrIdQuery = analyzerStrIdQuery+" and ami.activity_type = '"+molecularQueryAnalyzer.getActivityType()+"'";
			
			if(molecularQueryAnalyzer.getActivitySource() > 0) 
				analyzerStrIdQuery = analyzerStrIdQuery+" and ami.source_id = '"+molecularQueryAnalyzer.getActivitySource()+"'";
			
			if(!molecularQueryAnalyzer.getActivityPrefix().equals("")) {
				String actPrefix = activityPrefixValue(molecularQueryAnalyzer.getActivityPrefix());			
				analyzerStrIdQuery = analyzerStrIdQuery +actPrefix;
			}
			
			if(!molecularQueryAnalyzer.getTargetType().equals("")) {
				String tarType = targetTypeVal(molecularQueryAnalyzer.getTargetType());
				analyzerStrIdQuery = analyzerStrIdQuery+" and ami.p_s_m "+tarType;
			}
		}
		
		return analyzerStrIdQuery;
	}
	
	public String activityPrefixValue(String prefixVal) {
		String actPrefix = " ";
		if(prefixVal.equals("1"))
			actPrefix = " and ami.activity_prefix in ('<','<=','<~','~<')";
		else if(prefixVal.equals("2"))
			actPrefix = " and ami.activity_prefix in ('>','>=','>~','~>')";
		else if(prefixVal.equals("3"))
			actPrefix = " and ami.activity_prefix in ('=','~=','~')";
		return actPrefix;
	}
	
	public String targetTypeVal(String targetVal) {
		String targetType = "";
		if ("Any".equalsIgnoreCase(targetVal)) {
			targetType = " in ('P','S','L') ";
		} else if ("Secondary And Profile".equalsIgnoreCase(targetVal)) {
			targetType = " in ('S','L') ";
		} else
			targetType = " ='P' ";
		return targetType;
	}

}

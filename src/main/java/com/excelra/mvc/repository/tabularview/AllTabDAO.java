package com.excelra.mvc.repository.tabularview;

import com.excelra.mvc.exception.GoStarSqlException;
import com.excelra.mvc.model.search.AllMappingFinalResultCountDTO;
import com.excelra.mvc.model.tabularview.TabularviewRequest;
import com.excelra.mvc.model.tabularview.TempTableData;
import com.excelra.mvc.model.userjdbc.UserJdbc;
import com.excelra.mvc.utility.StringUtility;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Repository
public class AllTabDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(AllTabDAO.class);

    @Value("${allids.from.visual.temptable.query}")
    private String allIdListVisualTempTableQuery;

    @Value("${actidlist.from.visual.temptable.query}")
    private String actIdListVisualTempTableQuery;

    @Value("${assayidlist.from.visual.temptable.query}")
    private String assayIdListVisualTempTableQuery;

    @Value("${refidlist.from.visual.temptable.query}")
    private String refIdListVisualTempTableQuery;

    @Value("${gvkidlist.from.visual.temptable.query}")
    private String gvkIdListVisualTempTableQuery;

    @Value("${stridlist.from.visual.temptable.oount.query}")
    private String strIdListVisualTempTableQuery;

    @Autowired
    private AllTabServicesComponent allTabServicesComponent;

    @Autowired
    private StringUtility stringUtility;

    /**
     *
     * @param userJdbc
     * @return
     * @throws GoStarSqlException
     */
    public List<TempTableData> getTempTableData(UserJdbc userJdbc) throws GoStarSqlException {

        try {
            JdbcTemplate specificJdbc = allTabServicesComponent.getActivitySpecificJdbcConnection(userJdbc);

            List<TempTableData> tempTableData = allTabServicesComponent.getTempTableData(specificJdbc, allIdListVisualTempTableQuery);

            return tempTableData;
        } catch(Exception e) {
            LOGGER.error(" Error while processing getTempTableData service method, error is {} ", e.getMessage());
            throw new GoStarSqlException(" Error while processing getTempTableData service method, error is {} ", e);
        }
    }

    /**
     *
     * @param tabularviewRequest
     * @param userJdbc
     * @param currentSessionUserToken
     * @return
     * @throws GoStarSqlException
     */
    public AllMappingFinalResultCountDTO searchCountsForTabularResults(TabularviewRequest tabularviewRequest, UserJdbc userJdbc, String currentSessionUserToken) throws GoStarSqlException {

        try {
            if (Objects.isNull(currentSessionUserToken)) return new AllMappingFinalResultCountDTO();

            JdbcTemplate specificJdbc = allTabServicesComponent.getActivitySpecificJdbcConnection(userJdbc);

            LOGGER.info(" findActivityTabData - actIdListVisualTempTableQuery {} ", actIdListVisualTempTableQuery);

            List<Long> actIdList = allTabServicesComponent.fetchIdsFromTempTable(tabularviewRequest.getSelectedTabIds(), tabularviewRequest.getUnSelectedTabIds(), specificJdbc, actIdListVisualTempTableQuery);

            LOGGER.info(" AllTabDAO - searchCountsForTabularResults actIdList Count {} ", actIdList.size());

            List<Long> assayIdList = allTabServicesComponent.fetchIdsFromTempTable(tabularviewRequest.getSelectedTabIds(), tabularviewRequest.getUnSelectedTabIds(), specificJdbc, assayIdListVisualTempTableQuery);

            LOGGER.info(" AllTabDAO - searchCountsForTabularResults assayIdList Count {} ", assayIdList.size());

            List<Long> refIdList = allTabServicesComponent.fetchIdsFromTempTable(tabularviewRequest.getSelectedTabIds(), tabularviewRequest.getUnSelectedTabIds(), specificJdbc, refIdListVisualTempTableQuery);

            LOGGER.info(" AllTabDAO - searchCountsForTabularResults refIdList Count {} ", refIdList.size());

            List<Long> gvkIdList = allTabServicesComponent.fetchIdsFromTempTable(tabularviewRequest.getSelectedTabIds(), tabularviewRequest.getUnSelectedTabIds(), specificJdbc, gvkIdListVisualTempTableQuery);

            LOGGER.info(" AllTabDAO - searchCountsForTabularResults gvkIdList Count {} ", gvkIdList.size());

            AllMappingFinalResultCountDTO allMappingFinalResultCountDTO = new AllMappingFinalResultCountDTO();

            if (!actIdList.isEmpty()) {
                BigDecimal activityCount = new BigDecimal(allTabServicesComponent.getActivityDataCountsAfterFilters(tabularviewRequest, actIdList, currentSessionUserToken));
                allMappingFinalResultCountDTO.setActIdsCount(activityCount);
            } else {
                allMappingFinalResultCountDTO.setActIdsCount(new BigDecimal(0));
            }

            if (!assayIdList.isEmpty()) {
                BigDecimal assayCount = new BigDecimal(allTabServicesComponent.getAssayDataCountsAfterFilters(tabularviewRequest, assayIdList, currentSessionUserToken));
                allMappingFinalResultCountDTO.setAssayIdsCount(assayCount);
            } else {
                allMappingFinalResultCountDTO.setAssayIdsCount(new BigDecimal(0));
            }

            if (!refIdList.isEmpty()) {
                BigDecimal refCount = new BigDecimal(allTabServicesComponent.getReferenceDataCountsAfterFilters(tabularviewRequest, refIdList, currentSessionUserToken));
                allMappingFinalResultCountDTO.setRefIdsCount(refCount);
            } else {
                allMappingFinalResultCountDTO.setRefIdsCount(new BigDecimal(0));
            }

            if (!gvkIdList.isEmpty()) {
                BigDecimal gvkCount = new BigDecimal(allTabServicesComponent.getStructureDataCountsAfterFilters(tabularviewRequest, gvkIdList, currentSessionUserToken));
                allMappingFinalResultCountDTO.setGvkIdsCount(gvkCount);

                String structureCountQuery = StringUtils.EMPTY;
                if(!tabularviewRequest.getSelectedTabIds().isEmpty())
                    structureCountQuery = strIdListVisualTempTableQuery + " where gvk_id in ("+stringUtility.prepareInNumbericList(gvkIdList)+")";
                else
                    structureCountQuery = strIdListVisualTempTableQuery + " where gvk_id in (select distinct gvk_id from visual_results)";

                int structureCount = specificJdbc.queryForObject(structureCountQuery, Integer.class);
                allMappingFinalResultCountDTO.setStrIdsCount(new BigDecimal(structureCount));
            } else {
                allMappingFinalResultCountDTO.setStrIdsCount(new BigDecimal(0));
                allMappingFinalResultCountDTO.setGvkIdsCount(new BigDecimal(0));
            }

            return allMappingFinalResultCountDTO;
        } catch(Exception e) {
            LOGGER.error(" Error while processing searchCountsForTabularResults service method, error is {} ", e.getMessage());
            throw new GoStarSqlException(" Error while processing searchCountsForTabularResults service method, error is {} ", e);
        }
    }
}

package com.excelra.mvc.repository.tabularview;

import com.excelra.mvc.exception.GoStarSqlException;
import com.excelra.mvc.model.tabularview.*;
import com.excelra.mvc.model.userjdbc.UserJdbc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class TargetDetailsTabDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(TargetDetailsTabDAO.class);

    @Autowired
    private AllTabServicesComponent allTabServicesComponent;

    @Value("${targetidlist.from.visual.temptable.query}")
    private String targetIdListVisualTempTableQuery;

    /**
     *
     * @param tabularviewFilterRequest
     * @param userJdbc
     * @param currentSessionUserToken
     * @return
     * @throws GoStarSqlException
     */
    public List<TargetDetailsTabDTO> findTargetDetailsTabFilterFieldByTargetIds(TabularviewFilterRequest tabularviewFilterRequest, UserJdbc userJdbc, String currentSessionUserToken) throws GoStarSqlException {

        try {
            JdbcTemplate specificJdbc = allTabServicesComponent.getActivitySpecificJdbcConnection(userJdbc);

            LOGGER.info(" findTargetDetailsTabFilterFieldByRefIds - targetIdListVisualTempTableQuery {} ", targetIdListVisualTempTableQuery);

            List<Long> targetIdList = allTabServicesComponent.fetchIdsFromTempTable(tabularviewFilterRequest.getSelectedTabIds(), tabularviewFilterRequest.getUnSelectedTabIds(), specificJdbc, targetIdListVisualTempTableQuery);

            LOGGER.info(" findTargetDetailsTabFilterFieldByTargetIds - currentSessionUserToken {} targetIdList size {} ", currentSessionUserToken, targetIdList.size());

            if (Objects.isNull(currentSessionUserToken) || Objects.isNull(targetIdList)) return new ArrayList<>();

            if (!currentSessionUserToken.isEmpty() && !targetIdList.isEmpty())
                return allTabServicesComponent.getTargetDetailsFieldFilteredData(tabularviewFilterRequest, targetIdList, currentSessionUserToken);
            else
                return new ArrayList<>();
        } catch(Exception e) {

            LOGGER.error(" Error while processing findTargetDetailsTabFilterFieldByRefIds service method, error is {} ", e.getMessage());
            throw new GoStarSqlException(" Error while processing findTargetDetailsTabFilterFieldByRefIds service method, error is {} ", e);

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
    public TargetDetailsTabPageData findTargetDetailsTabData(TabularviewRequest tabularviewRequest, UserJdbc userJdbc, String currentSessionUserToken) throws GoStarSqlException {

        try {
            JdbcTemplate specificJdbc = allTabServicesComponent.getActivitySpecificJdbcConnection(userJdbc);

            LOGGER.info(" findTargetDetailsTabData - targetIdListVisualTempTableQuery {} ", targetIdListVisualTempTableQuery);

            List<Long> targetIdList = allTabServicesComponent.fetchIdsFromTempTable(tabularviewRequest.getSelectedTabIds(), tabularviewRequest.getUnSelectedTabIds(), specificJdbc, targetIdListVisualTempTableQuery);

            LOGGER.info(" findTargetDetailsTabData - currentSessionUserToken {} targetIdList size {} ", currentSessionUserToken, targetIdList.size());

            if (Objects.isNull(currentSessionUserToken) || Objects.isNull(targetIdList)) return new TargetDetailsTabPageData();

            if (!currentSessionUserToken.isEmpty())
                return allTabServicesComponent.getTargetDetailsData(tabularviewRequest, targetIdList, currentSessionUserToken);
            else
                return new TargetDetailsTabPageData();
        } catch(Exception e) {

            LOGGER.error(" Error while processing findTargetDetailsTabData service method, error is {} ", e.getMessage());
            throw new GoStarSqlException(" Error while processing findTargetDetailsTabData service method, error is {} ", e);

        }
    }

    /**
     *
     * @param tabularviewExportRequest
     * @param userJdbc
     * @param currentSessionUserToken
     * @return
     * @throws GoStarSqlException
     */
    public List<TargetDetailsTabDTO> findTargetDetailsTabDataForExport(TabularviewExportRequest tabularviewExportRequest, UserJdbc userJdbc, String currentSessionUserToken) throws GoStarSqlException {

        try {
            JdbcTemplate specificJdbc = allTabServicesComponent.getActivitySpecificJdbcConnection(userJdbc);

            LOGGER.info(" findTargetDetailsTabDataForExport - targetIdListVisualTempTableQuery {} ", targetIdListVisualTempTableQuery);

            List<Long> targetIdList = allTabServicesComponent.fetchIdsFromTempTable(tabularviewExportRequest.getSelectedTabIds(), tabularviewExportRequest.getUnSelectedTabIds(), specificJdbc, targetIdListVisualTempTableQuery);

            if (!currentSessionUserToken.isEmpty())
                return allTabServicesComponent.getTargetDetailsDataForExport(targetIdList, currentSessionUserToken);
            else
                return new ArrayList<>();
        } catch(Exception e) {

            LOGGER.error(" Error while processing findTargetDetailsTabDataForExport service method, error is {} ", e.getMessage());
            throw new GoStarSqlException(" Error while processing findTargetDetailsTabDataForExport service method, error is {} ", e);

        }
    }
}

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
public class ClinicalStatusTabDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClinicalStatusTabDAO.class);

    @Autowired
    private AllTabServicesComponent allTabServicesComponent;

    @Value("${gvkidlist.from.visual.temptable.query}")
    private String gvkIdListVisualTempTableQuery;

    @Value("${refidlist.from.visual.temptable.query}")
    private String refIdListVisualTempTableQuery;

    /**
     *
     * @param tabularviewFilterRequest
     * @param userJdbc
     * @param currentSessionUserToken
     * @return
     * @throws GoStarSqlException
     */
    public List<ClinicalStatusTabDTO> findClinicalStatusTabFilterFieldByGvkRefIds(TabularviewFilterRequest tabularviewFilterRequest, UserJdbc userJdbc, String currentSessionUserToken) throws GoStarSqlException {

        try {
            JdbcTemplate specificJdbc = allTabServicesComponent.getActivitySpecificJdbcConnection(userJdbc);

            LOGGER.info(" findClinicalStatusTabFilterFieldByRefIds - gvkIdListVisualTempTableQuery {} ", gvkIdListVisualTempTableQuery);

            List<Long> gvkIdList = allTabServicesComponent.fetchIdsFromTempTable(tabularviewFilterRequest.getSelectedTabIds(), tabularviewFilterRequest.getUnSelectedTabIds(), specificJdbc, gvkIdListVisualTempTableQuery);

            LOGGER.info(" findClinicalStatusTabFilterFieldByRefIds - refIdListVisualTempTableQuery {} ", refIdListVisualTempTableQuery);

            List<Long> refIdList = allTabServicesComponent.fetchIdsFromTempTable(tabularviewFilterRequest.getSelectedTabIds(), tabularviewFilterRequest.getUnSelectedTabIds(), specificJdbc, refIdListVisualTempTableQuery);

            LOGGER.info(" findTargetDetailsTabFilterFieldByTargetIds - currentSessionUserToken {} gvkIdList size {} ", currentSessionUserToken, gvkIdList.size());
            LOGGER.info(" findTargetDetailsTabFilterFieldByTargetIds - currentSessionUserToken {} refIdList size {} ", currentSessionUserToken, refIdList.size());

            if (Objects.isNull(currentSessionUserToken) || (Objects.isNull(gvkIdList) && Objects.isNull(refIdList)) ) return new ArrayList<>();

            if (!currentSessionUserToken.isEmpty() && !gvkIdList.isEmpty() && !refIdList.isEmpty())
                return allTabServicesComponent.getClinicalStatusFieldFilteredData(tabularviewFilterRequest, gvkIdList, refIdList, currentSessionUserToken);
            else
                return new ArrayList<>();
        } catch(Exception e) {

            LOGGER.error(" Error while processing findClinicalStatusTabFilterFieldByRefIds service method, error is {} ", e.getMessage());
            throw new GoStarSqlException(" Error while processing findClinicalStatusTabFilterFieldByRefIds service method, error is {} ", e);

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
    public ClinicalStatusTabPageData findClinicalStatusTabData(TabularviewRequest tabularviewRequest, UserJdbc userJdbc, String currentSessionUserToken) throws GoStarSqlException {

        try {
            JdbcTemplate specificJdbc = allTabServicesComponent.getActivitySpecificJdbcConnection(userJdbc);

            LOGGER.info(" findClinicalStatusTabData - targetIdListVisualTempTableQuery {} ", gvkIdListVisualTempTableQuery);

            List<Long> gvkIdList = allTabServicesComponent.fetchIdsFromTempTable(tabularviewRequest.getSelectedTabIds(), tabularviewRequest.getUnSelectedTabIds(), specificJdbc, gvkIdListVisualTempTableQuery);

            LOGGER.info(" findClinicalStatusTabData - targetIdListVisualTempTableQuery {} ", gvkIdListVisualTempTableQuery);

            List<Long> refIdList = allTabServicesComponent.fetchIdsFromTempTable(tabularviewRequest.getSelectedTabIds(), tabularviewRequest.getUnSelectedTabIds(), specificJdbc, refIdListVisualTempTableQuery);

            LOGGER.info(" findTargetDetailsTabData - currentSessionUserToken {} gvkIdList size {} ", currentSessionUserToken, gvkIdList.size());
            LOGGER.info(" findTargetDetailsTabData - currentSessionUserToken {} refIdList size {} ", currentSessionUserToken, refIdList.size());

            if (Objects.isNull(currentSessionUserToken) || (Objects.isNull(gvkIdList) && Objects.isNull(refIdList))) return new ClinicalStatusTabPageData();

            if (!currentSessionUserToken.isEmpty() && !gvkIdList.isEmpty() && !refIdList.isEmpty())
                return allTabServicesComponent.getClinicalStatusData(tabularviewRequest, gvkIdList, refIdList, currentSessionUserToken);
            else
                return new ClinicalStatusTabPageData();
        } catch(Exception e) {

            LOGGER.error(" Error while processing findClinicalStatusTabData service method, error is {} ", e.getMessage());
            throw new GoStarSqlException(" Error while processing findClinicalStatusTabData service method, error is {} ", e);

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
    public List<ClinicalStatusTabDTO> findClinicalStatusTabDataForExport(TabularviewExportRequest tabularviewExportRequest, UserJdbc userJdbc, String currentSessionUserToken) throws GoStarSqlException {

        try {
            JdbcTemplate specificJdbc = allTabServicesComponent.getActivitySpecificJdbcConnection(userJdbc);

            LOGGER.info(" findClinicalStatusTabDataForExport - gvkIdListVisualTempTableQuery {} ", gvkIdListVisualTempTableQuery);

            List<Long> gvkIdList = allTabServicesComponent.fetchIdsFromTempTable(tabularviewExportRequest.getSelectedTabIds(), tabularviewExportRequest.getUnSelectedTabIds(), specificJdbc, gvkIdListVisualTempTableQuery);

            LOGGER.info(" findClinicalStatusTabDataForExport - refIdListVisualTempTableQuery {} ", refIdListVisualTempTableQuery);

            List<Long> refIdList = allTabServicesComponent.fetchIdsFromTempTable(tabularviewExportRequest.getSelectedTabIds(), tabularviewExportRequest.getUnSelectedTabIds(), specificJdbc, refIdListVisualTempTableQuery);

            if (!currentSessionUserToken.isEmpty())
                return allTabServicesComponent.getClinicalStatusDataForExport(gvkIdList, refIdList, currentSessionUserToken);
            else
                return new ArrayList<>();
        } catch(Exception e) {

            LOGGER.error(" Error while processing findClinicalStatusTabDataForExport service method, error is {} ", e.getMessage());
            throw new GoStarSqlException(" Error while processing findClinicalStatusTabDataForExport service method, error is {} ", e);

        }
    }
}

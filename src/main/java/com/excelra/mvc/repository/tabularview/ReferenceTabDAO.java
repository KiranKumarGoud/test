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
public class ReferenceTabDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReferenceTabDAO.class);

    @Autowired
    private AllTabServicesComponent allTabServicesComponent;

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
    public List<ReferenceTabDTO> findReferenceTabFilterFieldByRefIds(TabularviewFilterRequest tabularviewFilterRequest, UserJdbc userJdbc, String currentSessionUserToken) throws GoStarSqlException {

        try {
            JdbcTemplate specificJdbc = allTabServicesComponent.getActivitySpecificJdbcConnection(userJdbc);

            LOGGER.info(" findReferenceTabFilterFieldByRefIds - refIdListVisualTempTableQuery {} ", refIdListVisualTempTableQuery);

            List<Long> refIdList = allTabServicesComponent.fetchIdsFromTempTable(tabularviewFilterRequest.getSelectedTabIds(), tabularviewFilterRequest.getUnSelectedTabIds(), specificJdbc, refIdListVisualTempTableQuery);

            LOGGER.info(" findReferenceTabFilterFieldByRefIds - currentSessionUserToken {} refIdList size {} ", currentSessionUserToken, refIdList.size());

            if (Objects.isNull(currentSessionUserToken) || Objects.isNull(refIdList)) return new ArrayList<>();

            if (!currentSessionUserToken.isEmpty() && !refIdList.isEmpty())
                return allTabServicesComponent.getReferenceFieldFilteredData(tabularviewFilterRequest, refIdList, currentSessionUserToken);
            else
                return new ArrayList<>();
        } catch(Exception e) {

            LOGGER.error(" Error while processing findReferenceTabFilterFieldByRefIds service method, error is {} ", e.getMessage());
            throw new GoStarSqlException(" Error while processing findReferenceTabFilterFieldByRefIds service method, error is {} ", e);

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
    public ReferenceTabPageData findReferenceTabData(TabularviewRequest tabularviewRequest, UserJdbc userJdbc, String currentSessionUserToken) throws GoStarSqlException {

        try {
            JdbcTemplate specificJdbc = allTabServicesComponent.getActivitySpecificJdbcConnection(userJdbc);

            LOGGER.info(" findReferenceTabData - refIdListVisualTempTableQuery {} ", refIdListVisualTempTableQuery);

            List<Long> refIdList = allTabServicesComponent.fetchIdsFromTempTable(tabularviewRequest.getSelectedTabIds(), tabularviewRequest.getUnSelectedTabIds(), specificJdbc, refIdListVisualTempTableQuery);

            LOGGER.info(" findReferenceTabData - currentSessionUserToken {} refIdList size {} ", currentSessionUserToken, refIdList.size());

            if (Objects.isNull(currentSessionUserToken) || Objects.isNull(refIdList)) return new ReferenceTabPageData();

            if (!currentSessionUserToken.isEmpty())
                return allTabServicesComponent.getReferenceData(tabularviewRequest, refIdList, currentSessionUserToken);
            else
                return new ReferenceTabPageData();
        } catch(Exception e) {

            LOGGER.error(" Error while processing findReferenceTabData service method, error is {} ", e.getMessage());
            throw new GoStarSqlException(" Error while processing findReferenceTabData service method, error is {} ", e);

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
    public List<ReferenceTabDTO> findReferenceTabDataForExport(TabularviewExportRequest tabularviewExportRequest, UserJdbc userJdbc, String currentSessionUserToken) throws GoStarSqlException {

        try {
            JdbcTemplate specificJdbc = allTabServicesComponent.getActivitySpecificJdbcConnection(userJdbc);

            LOGGER.info(" findReferenceTabDataForExport - refIdListVisualTempTableQuery {} ", refIdListVisualTempTableQuery);

            List<Long> refIdList = allTabServicesComponent.fetchIdsFromTempTable(tabularviewExportRequest.getSelectedTabIds(), tabularviewExportRequest.getUnSelectedTabIds(), specificJdbc, refIdListVisualTempTableQuery);

            if (!currentSessionUserToken.isEmpty())
                return allTabServicesComponent.getReferenceDataForExport(refIdList, currentSessionUserToken);
            else
                return new ArrayList<>();
        } catch(Exception e) {

            LOGGER.error(" Error while processing findReferenceTabDataForExport service method, error is {} ", e.getMessage());
            throw new GoStarSqlException(" Error while processing findReferenceTabDataForExport service method, error is {} ", e);

        }

    }
}

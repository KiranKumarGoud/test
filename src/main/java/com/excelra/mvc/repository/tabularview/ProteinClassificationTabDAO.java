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
public class ProteinClassificationTabDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProteinClassificationTabDAO.class);

    @Autowired
    private AllTabServicesComponent allTabServicesComponent;

    @Value("${stdnameidlist.from.visual.temptable.query}")
    private String stdnameIdListVisualTempTableQuery;

    /**
     *
     * @param tabularviewFilterRequest
     * @param userJdbc
     * @param currentSessionUserToken
     * @return
     * @throws GoStarSqlException
     */
    public List<ProteinClassificationTabDTO> findProteinClassificationTabFilterFieldByStdnameIds(TabularviewFilterRequest tabularviewFilterRequest, UserJdbc userJdbc, String currentSessionUserToken) throws GoStarSqlException {

        try {
            JdbcTemplate specificJdbc = allTabServicesComponent.getActivitySpecificJdbcConnection(userJdbc);

            LOGGER.info(" findProteinClassificationTabFilterFieldByRefIds - stdnameIdListVisualTempTableQuery {} ", stdnameIdListVisualTempTableQuery);

            List<Long> stdnameIdList = allTabServicesComponent.fetchIdsFromTempTable(tabularviewFilterRequest.getSelectedTabIds(), tabularviewFilterRequest.getUnSelectedTabIds(), specificJdbc, stdnameIdListVisualTempTableQuery);

            LOGGER.info(" findProteinClassificationTabFilterFieldByStdnameIds - currentSessionUserToken {} stdnameIdList size {} ", currentSessionUserToken, stdnameIdList.size());

            if (Objects.isNull(currentSessionUserToken) || Objects.isNull(stdnameIdList)) return new ArrayList<>();

            if (!currentSessionUserToken.isEmpty() && !stdnameIdList.isEmpty())
                return allTabServicesComponent.getProteinClassificationFieldFilteredData(tabularviewFilterRequest, stdnameIdList, currentSessionUserToken);
            else
                return new ArrayList<>();
        } catch(Exception e) {

            LOGGER.error(" Error while processing findProteinClassificationTabFilterFieldByRefIds service method, error is {} ", e.getMessage());
            throw new GoStarSqlException(" Error while processing findProteinClassificationTabFilterFieldByRefIds service method, error is {} ", e);

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
    public ProteinClassificationTabPageData findProteinClassificationTabData(TabularviewRequest tabularviewRequest, UserJdbc userJdbc, String currentSessionUserToken) throws GoStarSqlException {

        try {
            JdbcTemplate specificJdbc = allTabServicesComponent.getActivitySpecificJdbcConnection(userJdbc);

            LOGGER.info(" findProteinClassificationTabData - stdnameIdListVisualTempTableQuery {} ", stdnameIdListVisualTempTableQuery);

            List<Long> stdnameIdList = allTabServicesComponent.fetchIdsFromTempTable(tabularviewRequest.getSelectedTabIds(), tabularviewRequest.getUnSelectedTabIds(), specificJdbc, stdnameIdListVisualTempTableQuery);

            LOGGER.info(" findProteinClassificationTabData - currentSessionUserToken {} stdnameIdList size {} ", currentSessionUserToken, stdnameIdList.size());

            if (Objects.isNull(currentSessionUserToken) || Objects.isNull(stdnameIdList)) return new ProteinClassificationTabPageData();

            if (!currentSessionUserToken.isEmpty())
                return allTabServicesComponent.getProteinClassificationData(tabularviewRequest, stdnameIdList, currentSessionUserToken);
            else
                return new ProteinClassificationTabPageData();
        } catch(Exception e) {

            LOGGER.error(" Error while processing findProteinClassificationTabData service method, error is {} ", e.getMessage());
            throw new GoStarSqlException(" Error while processing findProteinClassificationTabData service method, error is {} ", e);

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
    public List<ProteinClassificationTabDTO> findProteinClassificationTabDataForExport(TabularviewExportRequest tabularviewExportRequest, UserJdbc userJdbc, String currentSessionUserToken) throws GoStarSqlException {

        try {
            JdbcTemplate specificJdbc = allTabServicesComponent.getActivitySpecificJdbcConnection(userJdbc);

            LOGGER.info(" findProteinClassificationTabDataForExport - stdnameIdListVisualTempTableQuery {} ", stdnameIdListVisualTempTableQuery);

            List<Long> stdnameIdList = allTabServicesComponent.fetchIdsFromTempTable(tabularviewExportRequest.getSelectedTabIds(), tabularviewExportRequest.getUnSelectedTabIds(), specificJdbc, stdnameIdListVisualTempTableQuery);

            if (!currentSessionUserToken.isEmpty())
                return allTabServicesComponent.getProteinClassificationDataForExport(stdnameIdList, currentSessionUserToken);
            else
                return new ArrayList<>();
        } catch(Exception e) {

            LOGGER.error(" Error while processing findProteinClassificationTabDataForExport service method, error is {} ", e.getMessage());
            throw new GoStarSqlException(" Error while processing findProteinClassificationTabDataForExport service method, error is {} ", e);

        }
    }

}

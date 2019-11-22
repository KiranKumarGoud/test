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
public class IndicationTabDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(IndicationTabDAO.class);

    @Autowired
    private AllTabServicesComponent allTabServicesComponent;

    @Value("${gvkidlist.from.visual.temptable.query}")
    private String gvkIdListVisualTempTableQuery;

    /**
     *
     * @param tabularviewFilterRequest
     * @param userJdbc
     * @param currentSessionUserToken
     * @return
     * @throws GoStarSqlException
     */
    public List<IndicationTabDTO> findIndicationTabFilterFieldByGvkIds(TabularviewFilterRequest tabularviewFilterRequest, UserJdbc userJdbc, String currentSessionUserToken) throws GoStarSqlException {

        try {
            JdbcTemplate specificJdbc = allTabServicesComponent.getActivitySpecificJdbcConnection(userJdbc);

            LOGGER.info(" findIndicationTabFilterFieldByRefIds - gvkIdListVisualTempTableQuery {} ", gvkIdListVisualTempTableQuery);

            List<Long> gvkIdList = allTabServicesComponent.fetchIdsFromTempTable(tabularviewFilterRequest.getSelectedTabIds(), tabularviewFilterRequest.getUnSelectedTabIds(), specificJdbc, gvkIdListVisualTempTableQuery);

            LOGGER.info(" findIndicationTabFilterFieldByGvkIds - currentSessionUserToken {} gvkIdList size {} ", currentSessionUserToken, gvkIdList.size());

            if (Objects.isNull(currentSessionUserToken) || Objects.isNull(gvkIdList)) return new ArrayList<>();

            if (!currentSessionUserToken.isEmpty() && !gvkIdList.isEmpty())
                return allTabServicesComponent.getIndicationFieldFilteredData(tabularviewFilterRequest, gvkIdList, currentSessionUserToken);
            else
                return new ArrayList<>();
        } catch(Exception e) {

            LOGGER.error(" Error while processing findIndicationTabFilterFieldByGvkIds service method, error is {} ", e.getMessage());
            throw new GoStarSqlException(" Error while processing findIndicationTabFilterFieldByGvkIds service method, error is {} ", e);

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
    public IndicationTabPageData findIndicationTabData(TabularviewRequest tabularviewRequest, UserJdbc userJdbc, String currentSessionUserToken) throws GoStarSqlException {

        try {
            JdbcTemplate specificJdbc = allTabServicesComponent.getActivitySpecificJdbcConnection(userJdbc);

            LOGGER.info(" findIndicationTabData - gvkIdListVisualTempTableQuery {} ", gvkIdListVisualTempTableQuery);

            List<Long> gvkIdList = allTabServicesComponent.fetchIdsFromTempTable(tabularviewRequest.getSelectedTabIds(), tabularviewRequest.getUnSelectedTabIds(), specificJdbc, gvkIdListVisualTempTableQuery);

            LOGGER.info(" findIndicationTabData - currentSessionUserToken {} gvkIdList size {} ", currentSessionUserToken, gvkIdList.size());

            if (Objects.isNull(currentSessionUserToken) || Objects.isNull(gvkIdList)) return new IndicationTabPageData();

            if (!currentSessionUserToken.isEmpty())
                return allTabServicesComponent.getIndicationData(tabularviewRequest, gvkIdList, currentSessionUserToken);
            else
                return new IndicationTabPageData();
        } catch(Exception e) {

            LOGGER.error(" Error while processing findIndicationTabData service method, error is {} ", e.getMessage());
            throw new GoStarSqlException(" Error while processing findIndicationTabData service method, error is {} ", e);

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
    public List<IndicationTabDTO> findIndicationTabDataForExport(TabularviewExportRequest tabularviewExportRequest, UserJdbc userJdbc, String currentSessionUserToken) throws GoStarSqlException {

        try {
            JdbcTemplate specificJdbc = allTabServicesComponent.getActivitySpecificJdbcConnection(userJdbc);

            LOGGER.info(" findIndicationTabDataForExport - gvkIdListVisualTempTableQuery {} ", gvkIdListVisualTempTableQuery);

            List<Long> gvkIdList = allTabServicesComponent.fetchIdsFromTempTable(tabularviewExportRequest.getSelectedTabIds(), tabularviewExportRequest.getUnSelectedTabIds(), specificJdbc, gvkIdListVisualTempTableQuery);

            if (!currentSessionUserToken.isEmpty())
                return allTabServicesComponent.getIndicationDataForExport(gvkIdList, currentSessionUserToken);
            else
                return new ArrayList<>();
        } catch(Exception e) {

            LOGGER.error(" Error while processing findIndicationTabDataForExport service method, error is {} ", e.getMessage());
            throw new GoStarSqlException(" Error while processing findIndicationTabDataForExport service method, error is {} ", e);

        }
    }
}

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
public class ActivityTabDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(ActivityTabDAO.class);

    @Value("${actidlist.from.visual.temptable.query}")
    private String actIdListVisualTempTableQuery;

    @Autowired
    private AllTabServicesComponent allTabServicesComponent;

    /**
     *
     * @param tabularviewFilterRequest
     * @param userJdbc
     * @param currentSessionUserToken
     * @return
     * @throws GoStarSqlException
     */
    public List<ActivityTabDTO> findActivityTabFilterFieldByActIds(TabularviewFilterRequest tabularviewFilterRequest, UserJdbc userJdbc, String currentSessionUserToken) throws GoStarSqlException {

        try {
            JdbcTemplate specificJdbc = allTabServicesComponent.getActivitySpecificJdbcConnection(userJdbc);

            LOGGER.info(" findActivityTabFilterFieldByActIds - actIdListVisualTempTableQuery {} ", actIdListVisualTempTableQuery);

            List<Long> actIdList = allTabServicesComponent.fetchIdsFromTempTable(tabularviewFilterRequest.getSelectedTabIds(), tabularviewFilterRequest.getUnSelectedTabIds(), specificJdbc, actIdListVisualTempTableQuery);

            LOGGER.info(" findActivityTabFilterFieldByActIds - currentSessionUserToken {} actIdList size {} ", currentSessionUserToken, actIdList.size());

            if (Objects.isNull(currentSessionUserToken) || Objects.isNull(actIdList)) return new ArrayList<>();

            if (!currentSessionUserToken.isEmpty() && !actIdList.isEmpty())
                return allTabServicesComponent.getActivityFieldFilteredData(tabularviewFilterRequest, actIdList, currentSessionUserToken);
            else
                return new ArrayList<>();
        } catch(Exception e) {
            LOGGER.error(" Error while processing findActivityTabFilterFieldByActIds service method, error is {} ", e.getMessage());
            throw new GoStarSqlException(" Error while processing findActivityTabFilterFieldByActIds service method, error is {} ", e);
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
    public ActivityTabPageData findActivityTabData(TabularviewRequest tabularviewRequest, UserJdbc userJdbc, String currentSessionUserToken) throws GoStarSqlException {

        try {
            JdbcTemplate specificJdbc = allTabServicesComponent.getActivitySpecificJdbcConnection(userJdbc);

            LOGGER.info(" findActivityTabData - actIdListVisualTempTableQuery {} ", actIdListVisualTempTableQuery);

            List<Long> actIdList = allTabServicesComponent.fetchIdsFromTempTable(tabularviewRequest.getSelectedTabIds(), tabularviewRequest.getUnSelectedTabIds(), specificJdbc, actIdListVisualTempTableQuery);

            LOGGER.info(" findActivityTabData - currentSessionUserToken {} actIdList size {} ", currentSessionUserToken, actIdList.size());

            if (Objects.isNull(currentSessionUserToken) || Objects.isNull(actIdList)) return new ActivityTabPageData();

            if (!currentSessionUserToken.isEmpty())
                return allTabServicesComponent.getActivityData(tabularviewRequest, actIdList, currentSessionUserToken);
            else
                return new ActivityTabPageData();
        } catch(Exception e) {
            LOGGER.error(" Error while processing findActivityTabData service method, error is {} ", e.getMessage());
            throw new GoStarSqlException(" Error while processing findActivityTabData service method, error is {} ", e);
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
    public List<ActivityTabDTO> findActivityTabDataForExport(TabularviewExportRequest tabularviewExportRequest, UserJdbc userJdbc, String currentSessionUserToken) throws GoStarSqlException {

        try {
            JdbcTemplate specificJdbc = allTabServicesComponent.getActivitySpecificJdbcConnection(userJdbc);

            LOGGER.info(" findActivityTabData - actIdListVisualTempTableQuery {} ", actIdListVisualTempTableQuery);

            List<Long> actIdList = allTabServicesComponent.fetchIdsFromTempTable(tabularviewExportRequest.getSelectedTabIds(), tabularviewExportRequest.getUnSelectedTabIds(), specificJdbc, actIdListVisualTempTableQuery);

            if (!currentSessionUserToken.isEmpty())
                return allTabServicesComponent.getActivityDataForExport(actIdList, currentSessionUserToken);
            else
                return new ArrayList<>();
        } catch(Exception e) {
            LOGGER.error(" Error while processing findActivityTabDataForExport service method, error is {} ", e.getMessage());
            throw new GoStarSqlException(" Error while processing findActivityTabDataForExport service method, error is {} ", e);
        }

    }
}

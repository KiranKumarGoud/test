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
public class AssayTabDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(AssayTabDAO.class);

    @Autowired
    private AllTabServicesComponent allTabServicesComponent;

    @Value("${assayidlist.from.visual.temptable.query}")
    private String assayIdListVisualTempTableQuery;


    /**
     *
     * @param tabularviewFilterRequest
     * @param userJdbc
     * @param currentSessionUserToken
     * @return
     * @throws GoStarSqlException
     */
    public List<AssayTabDTO> findAssayTabFilterFieldByAssayIds(TabularviewFilterRequest tabularviewFilterRequest, UserJdbc userJdbc, String currentSessionUserToken) throws GoStarSqlException {

        try {
            JdbcTemplate specificJdbc = allTabServicesComponent.getActivitySpecificJdbcConnection(userJdbc);

            LOGGER.info(" findAssayTabFilterFieldByAssayIds - assayIdListVisualTempTableQuery {} ", assayIdListVisualTempTableQuery);

            List<Long> assayIdList = allTabServicesComponent.fetchIdsFromTempTable(tabularviewFilterRequest.getSelectedTabIds(), tabularviewFilterRequest.getUnSelectedTabIds(), specificJdbc, assayIdListVisualTempTableQuery);

            LOGGER.info(" findAssayTabFilterFieldByAssayIds - currentSessionUserToken {} assayIdList size {} ", currentSessionUserToken, assayIdList.size());

            if (Objects.isNull(currentSessionUserToken) || Objects.isNull(assayIdList)) return new ArrayList<>();

            if (!currentSessionUserToken.isEmpty() && !assayIdList.isEmpty())
                return allTabServicesComponent.getAssayFieldFilteredData(tabularviewFilterRequest, assayIdList, currentSessionUserToken);
            else
                return new ArrayList<>();
        } catch(Exception e) {
            LOGGER.error(" Error while processing findAssayTabFilterFieldByAssayIds service method, error is {} ", e.getMessage());
            throw new GoStarSqlException(" Error while processing findAssayTabFilterFieldByAssayIds service method, error is {} ", e);
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
    public AssayTabPageData findAssayTabData(TabularviewRequest tabularviewRequest, UserJdbc userJdbc, String currentSessionUserToken) throws GoStarSqlException {

        try {
            JdbcTemplate specificJdbc = allTabServicesComponent.getActivitySpecificJdbcConnection(userJdbc);

            LOGGER.info(" findAssayTabData - assayIdListVisualTempTableQuery {} ", assayIdListVisualTempTableQuery);

            List<Long> assayIdList = allTabServicesComponent.fetchIdsFromTempTable(tabularviewRequest.getSelectedTabIds(), tabularviewRequest.getUnSelectedTabIds(), specificJdbc, assayIdListVisualTempTableQuery);

            LOGGER.info(" findAssayTabData - currentSessionUserToken {} assayIdList size {} ", currentSessionUserToken, assayIdList.size());

            if (Objects.isNull(currentSessionUserToken) || Objects.isNull(assayIdList)) return new AssayTabPageData();

            if (!currentSessionUserToken.isEmpty())
                return allTabServicesComponent.getAssayData(tabularviewRequest, assayIdList, currentSessionUserToken);
            else
                return new AssayTabPageData();
        } catch(Exception e) {
            LOGGER.error(" Error while processing findAssayTabData service method, error is {} ", e.getMessage());
            throw new GoStarSqlException(" Error while processing findAssayTabData service method, error is {} ", e);
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
    public List<AssayTabDTO> findAssayTabDataForExport(TabularviewExportRequest tabularviewExportRequest, UserJdbc userJdbc, String currentSessionUserToken) throws GoStarSqlException {

        try {
            JdbcTemplate specificJdbc = allTabServicesComponent.getActivitySpecificJdbcConnection(userJdbc);

            LOGGER.info(" findAssayTabDataForExport - assayIdListVisualTempTableQuery {} ", assayIdListVisualTempTableQuery);

            List<Long> assayIdList = allTabServicesComponent.fetchIdsFromTempTable(tabularviewExportRequest.getSelectedTabIds(), tabularviewExportRequest.getUnSelectedTabIds(), specificJdbc, assayIdListVisualTempTableQuery);

            if (!currentSessionUserToken.isEmpty())
                return allTabServicesComponent.getAssayDataForExport(assayIdList, currentSessionUserToken);
            else
                return new ArrayList<>();
        } catch(Exception e) {
            LOGGER.error(" Error while processing findAssayTabDataForExport service method, error is {} ", e.getMessage());
            throw new GoStarSqlException(" Error while processing findAssayTabDataForExport service method, error is {} ", e);
        }

    }
}

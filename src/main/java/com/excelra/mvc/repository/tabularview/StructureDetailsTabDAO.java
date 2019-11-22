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
public class StructureDetailsTabDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(StructureDetailsTabDAO.class);

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
    public List<StructureDetailsTabDTO> findStructureTabFilterFieldByGvkIds(TabularviewFilterRequest tabularviewFilterRequest, UserJdbc userJdbc, String currentSessionUserToken) throws GoStarSqlException {

        try {
            JdbcTemplate specificJdbc = allTabServicesComponent.getActivitySpecificJdbcConnection(userJdbc);

            LOGGER.info(" findStructureTabFilterFieldByRefIds - gvkIdListVisualTempTableQuery {} ", gvkIdListVisualTempTableQuery);

            List<Long> gvkIdList = allTabServicesComponent.fetchIdsFromTempTable(tabularviewFilterRequest.getSelectedTabIds(), tabularviewFilterRequest.getUnSelectedTabIds(), specificJdbc, gvkIdListVisualTempTableQuery);

            LOGGER.info(" findStructureTabFilterFieldByRefIds - currentSessionUserToken {} gvkIdList size {} ", currentSessionUserToken, gvkIdList.size());

            if (Objects.isNull(currentSessionUserToken) || Objects.isNull(gvkIdList)) return new ArrayList<>();

            if (!currentSessionUserToken.isEmpty() && !gvkIdList.isEmpty())
                return allTabServicesComponent.getStructureFieldFilteredData(tabularviewFilterRequest, gvkIdList, currentSessionUserToken);
            else
                return new ArrayList<>();
        } catch(Exception e) {

            LOGGER.error(" Error while processing findStructureTabFilterFieldByRefIds service method, error is {} ", e.getMessage());
            throw new GoStarSqlException(" Error while processing findStructureTabFilterFieldByRefIds service method, error is {} ", e);

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
    public StructureDetailsTabPageData findStructureTabData(TabularviewRequest tabularviewRequest, UserJdbc userJdbc, String currentSessionUserToken) throws GoStarSqlException {

        try {
            JdbcTemplate specificJdbc = allTabServicesComponent.getActivitySpecificJdbcConnection(userJdbc);

            LOGGER.info(" findStructureTabData - gvkIdListVisualTempTableQuery {} ", gvkIdListVisualTempTableQuery);

            List<Long> gvkIdList = allTabServicesComponent.fetchIdsFromTempTable(tabularviewRequest.getSelectedTabIds(), tabularviewRequest.getUnSelectedTabIds(), specificJdbc, gvkIdListVisualTempTableQuery);

            LOGGER.info(" findStructureTabData - currentSessionUserToken {} gvkIdList size {} ", currentSessionUserToken, gvkIdList.size());

            if (Objects.isNull(currentSessionUserToken) || Objects.isNull(gvkIdList))
                return new StructureDetailsTabPageData();

            if (!currentSessionUserToken.isEmpty())
                return allTabServicesComponent.getStructureData(tabularviewRequest, gvkIdList, currentSessionUserToken);
            else
                return new StructureDetailsTabPageData();
        } catch(Exception e) {

            LOGGER.error(" Error while processing findStructureTabData service method, error is {} ", e.getMessage());
            throw new GoStarSqlException(" Error while processing findStructureTabData service method, error is {} ", e);

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
    public List<StructureDetailsTabDTO> findStructureTabDataForExport(TabularviewExportRequest tabularviewExportRequest, UserJdbc userJdbc, String currentSessionUserToken) throws GoStarSqlException {

        try {
            JdbcTemplate specificJdbc = allTabServicesComponent.getActivitySpecificJdbcConnection(userJdbc);

            LOGGER.info(" findStructureTabDataForExport - gvkIdListVisualTempTableQuery {} ", gvkIdListVisualTempTableQuery);

            List<Long> gvkIdList = allTabServicesComponent.fetchIdsFromTempTable(tabularviewExportRequest.getSelectedTabIds(), tabularviewExportRequest.getUnSelectedTabIds(), specificJdbc, gvkIdListVisualTempTableQuery);

            if (!currentSessionUserToken.isEmpty())
                return allTabServicesComponent.getStructureDataForExport(gvkIdList, currentSessionUserToken);
            else
                return new ArrayList<>();
        } catch(Exception e) {

            LOGGER.error(" Error while processing findStructureTabDataForExport service method, error is {} ", e.getMessage());
            throw new GoStarSqlException(" Error while processing findStructureTabDataForExport service method, error is {} ", e);

        }
    }
}

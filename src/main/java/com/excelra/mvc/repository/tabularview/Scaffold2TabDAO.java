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
public class Scaffold2TabDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(Scaffold2TabDAO.class);

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
    public List<Scaffold2TabDTO> findScaffold2TabFilterFieldByGvkIds(TabularviewFilterRequest tabularviewFilterRequest, UserJdbc userJdbc, String currentSessionUserToken) throws GoStarSqlException {

        try {
            JdbcTemplate specificJdbc = allTabServicesComponent.getActivitySpecificJdbcConnection(userJdbc);

            LOGGER.info(" findScaffold2TabFilterFieldByGvkIds - gvkIdListVisualTempTableQuery {} ", gvkIdListVisualTempTableQuery);

            List<Long> gvkIdList = allTabServicesComponent.fetchIdsFromTempTable(tabularviewFilterRequest.getSelectedTabIds(), tabularviewFilterRequest.getUnSelectedTabIds(), specificJdbc, gvkIdListVisualTempTableQuery);

            LOGGER.info(" findScaffold2TabFilterFieldByGvkIds - currentSessionUserToken {} gvkIdList size {} ", currentSessionUserToken, gvkIdList.size());

            if (Objects.isNull(currentSessionUserToken) || Objects.isNull(gvkIdList)) return new ArrayList<>();

            if (!currentSessionUserToken.isEmpty() && !gvkIdList.isEmpty())
                return allTabServicesComponent.getScaffold2FieldFilteredData(tabularviewFilterRequest, gvkIdList, currentSessionUserToken);
            else
                return new ArrayList<>();
        } catch(Exception e) {

            LOGGER.error(" Error while processing findScaffold2TabFilterFieldByGvkIds service method, error is {} ", e.getMessage());
            throw new GoStarSqlException(" Error while processing findScaffold2TabFilterFieldByGvkIds service method, error is {} ", e);

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
    public Scaffold2TabPageData findScaffold2TabData(TabularviewRequest tabularviewRequest, UserJdbc userJdbc, String currentSessionUserToken) throws GoStarSqlException {

        try {
            JdbcTemplate specificJdbc = allTabServicesComponent.getActivitySpecificJdbcConnection(userJdbc);

            LOGGER.info(" findScaffold2TabData - gvkIdListVisualTempTableQuery {} ", gvkIdListVisualTempTableQuery);

            List<Long> gvkIdList = allTabServicesComponent.fetchIdsFromTempTable(tabularviewRequest.getSelectedTabIds(), tabularviewRequest.getUnSelectedTabIds(), specificJdbc, gvkIdListVisualTempTableQuery);

            LOGGER.info(" findScaffold2TabData - currentSessionUserToken {} gvkIdList size {} ", currentSessionUserToken, gvkIdList.size());

            if (Objects.isNull(currentSessionUserToken) || Objects.isNull(gvkIdList))
                return new Scaffold2TabPageData();

            if (!currentSessionUserToken.isEmpty())
                return allTabServicesComponent.getScaffold2Data(tabularviewRequest, gvkIdList, currentSessionUserToken);
            else
                return new Scaffold2TabPageData();
        } catch(Exception e) {

            LOGGER.error(" Error while processing findScaffold2TabData service method, error is {} ", e.getMessage());
            throw new GoStarSqlException(" Error while processing findScaffold2TabData service method, error is {} ", e);

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
    public List<Scaffold2TabDTO> findScaffold2TabDataForExport(TabularviewExportRequest tabularviewExportRequest, UserJdbc userJdbc, String currentSessionUserToken) throws GoStarSqlException {

        try {
            JdbcTemplate specificJdbc = allTabServicesComponent.getActivitySpecificJdbcConnection(userJdbc);

            LOGGER.info(" findScaffold2TabDataForExport - gvkIdListVisualTempTableQuery {} ", gvkIdListVisualTempTableQuery);

            List<Long> gvkIdList = allTabServicesComponent.fetchIdsFromTempTable(tabularviewExportRequest.getSelectedTabIds(), tabularviewExportRequest.getUnSelectedTabIds(), specificJdbc, gvkIdListVisualTempTableQuery);

            if (!currentSessionUserToken.isEmpty())
                return allTabServicesComponent.getScaffold2DataForExport(gvkIdList, currentSessionUserToken);
            else
                return new ArrayList<>();
        } catch(Exception e) {

            LOGGER.error(" Error while processing findScaffold2TabDataForExport service method, error is {} ", e.getMessage());
            throw new GoStarSqlException(" Error while processing findScaffold2TabDataForExport service method, error is {} ", e);

        }
    }
}

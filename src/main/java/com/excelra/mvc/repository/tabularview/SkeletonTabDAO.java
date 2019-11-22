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
public class SkeletonTabDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(SkeletonTabDAO.class);

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
    public List<SkeletonTabDTO> findSkeletonTabFilterFieldByGvkIds(TabularviewFilterRequest tabularviewFilterRequest, UserJdbc userJdbc, String currentSessionUserToken) throws GoStarSqlException {

        try {
            JdbcTemplate specificJdbc = allTabServicesComponent.getActivitySpecificJdbcConnection(userJdbc);

            LOGGER.info(" findSkeletonTabFilterFieldByGvkIds - gvkIdListVisualTempTableQuery {} ", gvkIdListVisualTempTableQuery);

            List<Long> gvkIdList = allTabServicesComponent.fetchIdsFromTempTable(tabularviewFilterRequest.getSelectedTabIds(), tabularviewFilterRequest.getUnSelectedTabIds(), specificJdbc, gvkIdListVisualTempTableQuery);

            LOGGER.info(" findSkeletonTabFilterFieldByGvkIds - currentSessionUserToken {} gvkIdList size {} ", currentSessionUserToken, gvkIdList.size());

            if (Objects.isNull(currentSessionUserToken) || Objects.isNull(gvkIdList)) return new ArrayList<>();

            if (!currentSessionUserToken.isEmpty() && !gvkIdList.isEmpty())
                return allTabServicesComponent.getSkeletonFieldFilteredData(tabularviewFilterRequest, gvkIdList, currentSessionUserToken);
            else
                return new ArrayList<>();
        } catch(Exception e) {

            LOGGER.error(" Error while processing findSkeletonTabFilterFieldByGvkIds service method, error is {} ", e.getMessage());
            throw new GoStarSqlException(" Error while processing findSkeletonTabFilterFieldByGvkIds service method, error is {} ", e);

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
    public SkeletonTabPageData findSkeletonTabData(TabularviewRequest tabularviewRequest, UserJdbc userJdbc, String currentSessionUserToken) throws GoStarSqlException {

        try {
            JdbcTemplate specificJdbc = allTabServicesComponent.getActivitySpecificJdbcConnection(userJdbc);

            LOGGER.info(" findSkeletonTabData - gvkIdListVisualTempTableQuery {} ", gvkIdListVisualTempTableQuery);

            List<Long> gvkIdList = allTabServicesComponent.fetchIdsFromTempTable(tabularviewRequest.getSelectedTabIds(), tabularviewRequest.getUnSelectedTabIds(), specificJdbc, gvkIdListVisualTempTableQuery);

            LOGGER.info(" findSkeletonTabData - currentSessionUserToken {} gvkIdList size {} ", currentSessionUserToken, gvkIdList.size());

            if (Objects.isNull(currentSessionUserToken) || Objects.isNull(gvkIdList))
                return new SkeletonTabPageData();

            if (!currentSessionUserToken.isEmpty())
                return allTabServicesComponent.getSkeletonData(tabularviewRequest, gvkIdList, currentSessionUserToken);
            else
                return new SkeletonTabPageData();
        } catch(Exception e) {

            LOGGER.error(" Error while processing findSkeletonTabData service method, error is {} ", e.getMessage());
            throw new GoStarSqlException(" Error while processing findSkeletonTabData service method, error is {} ", e);

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
    public List<SkeletonTabDTO> findSkeletonTabDataForExport(TabularviewExportRequest tabularviewExportRequest, UserJdbc userJdbc, String currentSessionUserToken) throws GoStarSqlException {

        try {
            JdbcTemplate specificJdbc = allTabServicesComponent.getActivitySpecificJdbcConnection(userJdbc);

            LOGGER.info(" findSkeletonTabDataForExport - gvkIdListVisualTempTableQuery {} ", gvkIdListVisualTempTableQuery);

            List<Long> gvkIdList = allTabServicesComponent.fetchIdsFromTempTable(tabularviewExportRequest.getSelectedTabIds(), tabularviewExportRequest.getUnSelectedTabIds(), specificJdbc, gvkIdListVisualTempTableQuery);

            if (!currentSessionUserToken.isEmpty())
                return allTabServicesComponent.getSkeletonDataForExport(gvkIdList, currentSessionUserToken);
            else
                return new ArrayList<>();
        } catch(Exception e) {

            LOGGER.error(" Error while processing findSkeletonTabDataForExport service method, error is {} ", e.getMessage());
            throw new GoStarSqlException(" Error while processing findSkeletonTabDataForExport service method, error is {} ", e);

        }
    }
}

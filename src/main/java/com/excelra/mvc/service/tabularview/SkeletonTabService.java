package com.excelra.mvc.service.tabularview;

import com.excelra.mvc.model.tabularview.*;
import com.excelra.mvc.model.userjdbc.UserJdbc;
import com.excelra.mvc.repository.tabularview.SkeletonTabDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Default Skeleton Tabularview Service Implementation
 *
 * @author Venkat Salagrama
 */
@Service
public class SkeletonTabService implements ISkeletonTab {

    @Autowired
    private SkeletonTabDAO skeletonTabDAO;

    /**
     *
     * @param tabularviewFilterRequest
     * @param userJdbcObject
     * @param currentSessionUserToken
     * @return
     */
    @Override
    public List<SkeletonTabDTO> findSkeletonTabFilterFieldByGvkIds(TabularviewFilterRequest tabularviewFilterRequest, UserJdbc userJdbcObject, String currentSessionUserToken) {
        return skeletonTabDAO.findSkeletonTabFilterFieldByGvkIds(tabularviewFilterRequest, userJdbcObject, currentSessionUserToken);
    }

    /**
     *
     * @param tabularviewRequest
     * @param userJdbc
     * @param currentSessionUserToken
     * @return
     */
    @Override
    public SkeletonTabPageData findSkeletonTabData(TabularviewRequest tabularviewRequest, UserJdbc userJdbc, String currentSessionUserToken) {
        return skeletonTabDAO.findSkeletonTabData(tabularviewRequest, userJdbc, currentSessionUserToken);
    }

    /**
     *
     * @param tabularviewExportRequest
     * @param userJdbc
     * @param currentSessionUserToken
     * @return
     */
    @Override
    public List<SkeletonTabDTO> findSkeletonTabDataForExport(TabularviewExportRequest tabularviewExportRequest, UserJdbc userJdbc, String currentSessionUserToken) {
        return skeletonTabDAO.findSkeletonTabDataForExport(tabularviewExportRequest, userJdbc, currentSessionUserToken);
    }
}

package com.excelra.mvc.service.tabularview;

import com.excelra.mvc.model.tabularview.*;
import com.excelra.mvc.model.userjdbc.UserJdbc;
import com.excelra.mvc.repository.tabularview.Framework2TabDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Default Framework2 Tabularview Service Implementation
 *
 * @author Venkat Salagrama
 */
@Service
public class Framework2TabService implements IFramework2Tab {

    @Autowired
    private Framework2TabDAO framework2TabDAO;

    /**
     *
     * @param tabularviewFilterRequest
     * @param userJdbcObject
     * @param currentSessionUserToken
     * @return
     */
    @Override
    public List<Framework2TabDTO> findFramework2TabFilterFieldByGvkIds(TabularviewFilterRequest tabularviewFilterRequest, UserJdbc userJdbcObject, String currentSessionUserToken) {
        return framework2TabDAO.findFramework2TabFilterFieldByGvkIds(tabularviewFilterRequest, userJdbcObject, currentSessionUserToken);
    }

    /**
     *
     * @param tabularviewRequest
     * @param userJdbc
     * @param currentSessionUserToken
     * @return
     */
    @Override
    public Framework2TabPageData findFramework2TabData(TabularviewRequest tabularviewRequest, UserJdbc userJdbc, String currentSessionUserToken) {
        return framework2TabDAO.findFramework2TabData(tabularviewRequest, userJdbc, currentSessionUserToken);
    }

    /**
     *
     * @param tabularviewExportRequest
     * @param userJdbc
     * @param currentSessionUserToken
     * @return
     */
    @Override
    public List<Framework2TabDTO> findFramework2TabDataForExport(TabularviewExportRequest tabularviewExportRequest, UserJdbc userJdbc, String currentSessionUserToken) {
        return framework2TabDAO.findFramework2TabDataForExport(tabularviewExportRequest, userJdbc, currentSessionUserToken);
    }

}

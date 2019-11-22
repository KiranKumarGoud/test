package com.excelra.mvc.service.tabularview;

import com.excelra.mvc.model.tabularview.*;
import com.excelra.mvc.model.userjdbc.UserJdbc;
import com.excelra.mvc.repository.tabularview.Scaffold2TabDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Default Scaffold2 Tabularview Service Implementation
 *
 * @author Venkat Salagrama
 */
@Service
public class Scaffold2TabService implements IScaffold2Tab {

    @Autowired
    private Scaffold2TabDAO scaffold2TabDAO;

    /**
     *
     * @param tabularviewFilterRequest
     * @param userJdbcObject
     * @param currentSessionUserToken
     * @return
     */
    @Override
    public List<Scaffold2TabDTO> findScaffold2TabFilterFieldByGvkIds(TabularviewFilterRequest tabularviewFilterRequest, UserJdbc userJdbcObject, String currentSessionUserToken) {
        return scaffold2TabDAO.findScaffold2TabFilterFieldByGvkIds(tabularviewFilterRequest, userJdbcObject, currentSessionUserToken);
    }

    /**
     *
     * @param tabularviewRequest
     * @param userJdbc
     * @param currentSessionUserToken
     * @return
     */
    @Override
    public Scaffold2TabPageData findScaffold2TabData(TabularviewRequest tabularviewRequest, UserJdbc userJdbc, String currentSessionUserToken) {
        return scaffold2TabDAO.findScaffold2TabData(tabularviewRequest, userJdbc, currentSessionUserToken);
    }

    /**
     *
     * @param tabularviewExportRequest
     * @param userJdbc
     * @param currentSessionUserToken
     * @return
     */
    @Override
    public List<Scaffold2TabDTO> findScaffold2TabDataForExport(TabularviewExportRequest tabularviewExportRequest, UserJdbc userJdbc, String currentSessionUserToken) {
        return scaffold2TabDAO.findScaffold2TabDataForExport(tabularviewExportRequest, userJdbc, currentSessionUserToken);
    }
}

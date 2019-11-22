package com.excelra.mvc.service.tabularview;

import com.excelra.mvc.model.tabularview.*;
import com.excelra.mvc.model.userjdbc.UserJdbc;

import java.util.List;

/**
 * Scaffold2 Structure Tabularview Service Interface
 *
 * @author Venkat Salagrama
 */
public interface IScaffold2Tab {

    List<Scaffold2TabDTO> findScaffold2TabFilterFieldByGvkIds(TabularviewFilterRequest tabularviewFilterRequest, UserJdbc userJdbcObject, String currentSessionUserToken);

    Scaffold2TabPageData findScaffold2TabData(TabularviewRequest tabularviewRequest, UserJdbc userJdbc, String currentSessionUserToken);

    List<Scaffold2TabDTO> findScaffold2TabDataForExport(TabularviewExportRequest tabularviewExportRequest, UserJdbc userJdbc, String currentSessionUserToken);
}

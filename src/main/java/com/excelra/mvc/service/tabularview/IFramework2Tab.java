package com.excelra.mvc.service.tabularview;

import com.excelra.mvc.model.tabularview.*;
import com.excelra.mvc.model.userjdbc.UserJdbc;

import java.util.List;

/**
 * Framework2 Structure Tabularview Service Interface
 *
 * @author Venkat Salagrama
 */
public interface IFramework2Tab {

    List<Framework2TabDTO> findFramework2TabFilterFieldByGvkIds(TabularviewFilterRequest tabularviewFilterRequest, UserJdbc userJdbcObject, String currentSessionUserToken);

    Framework2TabPageData findFramework2TabData(TabularviewRequest tabularviewRequest, UserJdbc userJdbc, String currentSessionUserToken);

    List<Framework2TabDTO> findFramework2TabDataForExport(TabularviewExportRequest tabularviewExportRequest, UserJdbc userJdbc, String currentSessionUserToken);
}

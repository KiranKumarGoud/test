package com.excelra.mvc.service.tabularview;

import com.excelra.mvc.model.tabularview.*;
import com.excelra.mvc.model.userjdbc.UserJdbc;
import java.util.List;

/**
 * Activity Tabularview Service Interface
 *
 * @author Venkat Salagrama
 */
public interface IActivityTab {

    List<ActivityTabDTO> findActivityTabFilterFieldByActIds(TabularviewFilterRequest tabularviewFilterRequest, UserJdbc userJdbc, String currentSessionUserToken);

    ActivityTabPageData findActivityTabData(TabularviewRequest tabularviewRequest, UserJdbc userJdbc, String currentSessionUserToken);

    List<ActivityTabDTO> findActivityTabDataForExport(TabularviewExportRequest tabularviewExportRequest, UserJdbc userJdbc, String currentSessionUserToken);
}

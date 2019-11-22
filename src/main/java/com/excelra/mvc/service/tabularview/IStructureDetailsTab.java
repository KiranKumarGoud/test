package com.excelra.mvc.service.tabularview;


import com.excelra.mvc.model.tabularview.*;
import com.excelra.mvc.model.userjdbc.UserJdbc;

import java.util.List;

/**
 * Structure Details Tabularview Service Interface
 *
 * @author Venkat Salagrama
 */
public interface IStructureDetailsTab {

    List<StructureDetailsTabDTO> findStructureTabFilterFieldByGvkIds(TabularviewFilterRequest tabularviewFilterRequest, UserJdbc userJdbcObject, String currentSessionUserToken);

    StructureDetailsTabPageData findStructureTabData(TabularviewRequest tabularviewRequest, UserJdbc userJdbc, String currentSessionUserToken);

    List<StructureDetailsTabDTO> findStructureTabDataForExport(TabularviewExportRequest tabularviewExportRequest, UserJdbc userJdbc, String currentSessionUserToken);
}

package com.excelra.mvc.service.tabularview;

import com.excelra.mvc.model.tabularview.*;
import com.excelra.mvc.model.userjdbc.UserJdbc;

import java.util.List;

/**
 * Reference Tabularview Service Interface
 *
 * @author Venkat Salagrama
 */
public interface IReferenceTab {

    List<ReferenceTabDTO> findReferenceTabFilterFieldByRefIds(TabularviewFilterRequest tabularviewFilterRequest, UserJdbc userJdbc, String currentSessionUserToken);

    ReferenceTabPageData findReferenceTabData(TabularviewRequest tabularviewRequest, UserJdbc userJdbc, String currentSessionUserToken);

    List<ReferenceTabDTO> findReferenceTabDataForExport(TabularviewExportRequest tabularviewExportRequest, UserJdbc userJdbc, String currentSessionUserToken);
}

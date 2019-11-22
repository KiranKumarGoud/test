package com.excelra.mvc.service.tabularview;

import com.excelra.mvc.model.tabularview.*;
import com.excelra.mvc.model.userjdbc.UserJdbc;

import java.util.List;

/**
 * Protein Classification Tabularview Service Interface
 *
 * @author Venkat Salagrama
 */
public interface IProteinClassificationTab {

    List<ProteinClassificationTabDTO> findProteinClassificationTabFilterFieldByStdnameIds(TabularviewFilterRequest tabularviewFilterRequest, UserJdbc userJdbc, String currentSessionUserToken);

    ProteinClassificationTabPageData findProteinClassificationTabData(TabularviewRequest tabularviewRequest, UserJdbc userJdbc, String currentSessionUserToken);

    List<ProteinClassificationTabDTO> findProteinClassificationTabDataForExport(TabularviewExportRequest tabularviewExportRequest, UserJdbc userJdbc, String currentSessionUserToken);
}

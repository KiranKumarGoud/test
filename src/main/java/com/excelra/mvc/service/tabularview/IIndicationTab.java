package com.excelra.mvc.service.tabularview;

import com.excelra.mvc.model.tabularview.*;
import com.excelra.mvc.model.userjdbc.UserJdbc;

import java.util.List;

public interface IIndicationTab {

    List<IndicationTabDTO> findIndicationTabFilterFieldByGvkIds(TabularviewFilterRequest tabularviewFilterRequest, UserJdbc userJdbc, String currentSessionUserToken);

    IndicationTabPageData findIndicationTabData(TabularviewRequest tabularviewRequest, UserJdbc userJdbc, String currentSessionUserToken);

    List<IndicationTabDTO> findIndicationTabDataForExport(TabularviewExportRequest tabularviewExportRequest, UserJdbc userJdbc, String currentSessionUserToken);

}

package com.excelra.mvc.service.tabularview;

import com.excelra.mvc.model.tabularview.*;
import com.excelra.mvc.model.userjdbc.UserJdbc;

import java.util.List;

public interface IClinicalStatusTab {

    List<ClinicalStatusTabDTO> findClinicalStatusTabFilterFieldByGvkRefIds(TabularviewFilterRequest tabularviewFilterRequest, UserJdbc userJdbc, String currentSessionUserToken);

    ClinicalStatusTabPageData findClinicalStatusTabData(TabularviewRequest tabularviewRequest, UserJdbc userJdbc, String currentSessionUserToken);

    List<ClinicalStatusTabDTO> findClinicalStatusTabDataForExport(TabularviewExportRequest tabularviewExportRequest, UserJdbc userJdbc, String currentSessionUserToken);
}

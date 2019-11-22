package com.excelra.mvc.service.tabularview;

import com.excelra.mvc.model.search.AllMappingFinalResultCountDTO;
import com.excelra.mvc.model.tabularview.TabularviewRequest;
import com.excelra.mvc.model.tabularview.TempTableData;
import com.excelra.mvc.model.userjdbc.UserJdbc;

import java.util.List;

public interface IAllTab {

    List<TempTableData> getTempTableData(UserJdbc userJdbc);

    AllMappingFinalResultCountDTO searchCountsForTabularResults(TabularviewRequest tabularviewRequest, UserJdbc userJdbc, String currentSessionUserToken);

}

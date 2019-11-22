package com.excelra.mvc.service.tabularview;

import com.excelra.mvc.model.search.AllMappingFinalResultCountDTO;
import com.excelra.mvc.model.tabularview.TabularviewRequest;
import com.excelra.mvc.model.tabularview.TempTableData;
import com.excelra.mvc.model.userjdbc.UserJdbc;
import com.excelra.mvc.repository.tabularview.AllTabDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AllTabService implements IAllTab {

    @Autowired
    private AllTabDAO allTabDAO;

    @Override
    public List<TempTableData> getTempTableData(UserJdbc userJdbc) {
        return allTabDAO.getTempTableData(userJdbc);
    }

    @Override
    public AllMappingFinalResultCountDTO searchCountsForTabularResults(TabularviewRequest tabularviewRequest, UserJdbc userJdbc, String currentSessionUserToken) {
        return allTabDAO.searchCountsForTabularResults(tabularviewRequest, userJdbc, currentSessionUserToken);
    }

}


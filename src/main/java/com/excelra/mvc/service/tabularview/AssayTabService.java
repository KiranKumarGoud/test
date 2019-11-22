package com.excelra.mvc.service.tabularview;

import com.excelra.mvc.model.tabularview.*;
import com.excelra.mvc.model.userjdbc.UserJdbc;
import com.excelra.mvc.repository.tabularview.AssayTabDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Default Assay Tabularview Service Implementation
 *
 * @author Venkat Salagrama
 */
@Service
public class AssayTabService implements IAssayTab {

    @Autowired
    private AssayTabDAO assayTabDAO;

    @Override
    public List<AssayTabDTO> findAssayTabFilterFieldByAssayIds(TabularviewFilterRequest tabularviewFilterRequest, UserJdbc userJdbc, String currentSessionUserToken) {
        return assayTabDAO.findAssayTabFilterFieldByAssayIds(tabularviewFilterRequest, userJdbc, currentSessionUserToken);
    }

    @Override
    public AssayTabPageData findAssayTabData(TabularviewRequest tabularviewRequest, UserJdbc userJdbc, String currentSessionUserToken) {
        return assayTabDAO.findAssayTabData(tabularviewRequest, userJdbc, currentSessionUserToken);
    }

    @Override
    public List<AssayTabDTO> findAssayTabDataForExport(TabularviewExportRequest tabularviewExportRequest, UserJdbc userJdbc, String currentSessionUserToken) {
        return assayTabDAO.findAssayTabDataForExport(tabularviewExportRequest, userJdbc, currentSessionUserToken);
    }
}

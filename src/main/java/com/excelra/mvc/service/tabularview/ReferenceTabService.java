package com.excelra.mvc.service.tabularview;

import com.excelra.mvc.model.tabularview.*;
import com.excelra.mvc.model.userjdbc.UserJdbc;
import com.excelra.mvc.repository.tabularview.ReferenceTabDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Reference tabularview service methods implementation.
 *
 * @author Venkat Salagrama
 */
@Service
public class ReferenceTabService implements IReferenceTab {

    @Autowired
    private ReferenceTabDAO referenceTabDAO;

    @Override
    public List<ReferenceTabDTO> findReferenceTabFilterFieldByRefIds(TabularviewFilterRequest tabularviewFilterRequest, UserJdbc userJdbc, String currentSessionUserToken) {
        return referenceTabDAO.findReferenceTabFilterFieldByRefIds(tabularviewFilterRequest, userJdbc, currentSessionUserToken);
    }

    @Override
    public ReferenceTabPageData findReferenceTabData(TabularviewRequest tabularviewRequest, UserJdbc userJdbc, String currentSessionUserToken) {
        return referenceTabDAO.findReferenceTabData(tabularviewRequest, userJdbc, currentSessionUserToken);
    }

    @Override
    public List<ReferenceTabDTO> findReferenceTabDataForExport(TabularviewExportRequest tabularviewExportRequest, UserJdbc userJdbc, String currentSessionUserToken) {
        return referenceTabDAO.findReferenceTabDataForExport(tabularviewExportRequest, userJdbc, currentSessionUserToken);
    }
}

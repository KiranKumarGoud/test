package com.excelra.mvc.service.tabularview;

import com.excelra.mvc.model.tabularview.*;
import com.excelra.mvc.model.userjdbc.UserJdbc;
import com.excelra.mvc.repository.tabularview.ProteinClassificationTabDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Protein Classification tabularview service methods implementation.
 *
 * @author Venkat Salagrama
 */
@Service
public class ProteinClassificationTabService implements IProteinClassificationTab {

    @Autowired
    private ProteinClassificationTabDAO proteinClassificationTabDAO;

    /**
     *
     * @param tabularviewFilterRequest
     * @param userJdbc
     * @param currentSessionUserToken
     * @return
     */
    @Override
    public List<ProteinClassificationTabDTO> findProteinClassificationTabFilterFieldByStdnameIds(TabularviewFilterRequest tabularviewFilterRequest, UserJdbc userJdbc, String currentSessionUserToken) {
        return proteinClassificationTabDAO.findProteinClassificationTabFilterFieldByStdnameIds(tabularviewFilterRequest, userJdbc, currentSessionUserToken);
    }

    /**
     *
     * @param tabularviewRequest
     * @param userJdbc
     * @param currentSessionUserToken
     * @return
     */
    @Override
    public ProteinClassificationTabPageData findProteinClassificationTabData(TabularviewRequest tabularviewRequest, UserJdbc userJdbc, String currentSessionUserToken) {
        return proteinClassificationTabDAO.findProteinClassificationTabData(tabularviewRequest, userJdbc, currentSessionUserToken);
    }

    /**
     *
     * @param tabularviewExportRequest
     * @param userJdbc
     * @param currentSessionUserToken
     * @return
     */
    @Override
    public List<ProteinClassificationTabDTO> findProteinClassificationTabDataForExport(TabularviewExportRequest tabularviewExportRequest, UserJdbc userJdbc, String currentSessionUserToken) {
        return proteinClassificationTabDAO.findProteinClassificationTabDataForExport(tabularviewExportRequest, userJdbc, currentSessionUserToken);
    }

}

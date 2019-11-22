package com.excelra.mvc.service.tabularview;

import com.excelra.mvc.model.tabularview.*;
import com.excelra.mvc.model.userjdbc.UserJdbc;
import com.excelra.mvc.repository.tabularview.IndicationTabDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Indication tabularview service methods implementation.
 *
 * @author Venkat Salagrama
 */
@Service
public class IndicationTabService implements IIndicationTab {

    @Autowired
    private IndicationTabDAO indicationTabDAO;

    /**
     *
     * @param tabularviewFilterRequest
     * @param userJdbc
     * @param currentSessionUserToken
     * @return
     */
    @Override
    public List<IndicationTabDTO> findIndicationTabFilterFieldByGvkIds(TabularviewFilterRequest tabularviewFilterRequest, UserJdbc userJdbc, String currentSessionUserToken) {
        return indicationTabDAO.findIndicationTabFilterFieldByGvkIds(tabularviewFilterRequest, userJdbc, currentSessionUserToken);
    }

    /**
     *
     * @param tabularviewRequest
     * @param userJdbc
     * @param currentSessionUserToken
     * @return
     */
    @Override
    public IndicationTabPageData findIndicationTabData(TabularviewRequest tabularviewRequest, UserJdbc userJdbc, String currentSessionUserToken) {
        return indicationTabDAO.findIndicationTabData(tabularviewRequest, userJdbc, currentSessionUserToken);
    }

    /**
     *
     * @param tabularviewExportRequest
     * @param userJdbc
     * @param currentSessionUserToken
     * @return
     */
    @Override
    public List<IndicationTabDTO> findIndicationTabDataForExport(TabularviewExportRequest tabularviewExportRequest, UserJdbc userJdbc, String currentSessionUserToken) {
        return indicationTabDAO.findIndicationTabDataForExport(tabularviewExportRequest, userJdbc, currentSessionUserToken);
    }
}

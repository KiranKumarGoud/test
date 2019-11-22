package com.excelra.mvc.service.tabularview;

import com.excelra.mvc.model.tabularview.*;
import com.excelra.mvc.model.userjdbc.UserJdbc;
import com.excelra.mvc.repository.tabularview.TargetDetailsTabDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Target Details tabularview service methods implementation.
 *
 * @author Venkat Salagrama
 */
@Service
public class TargetDetailsTabService implements ITargetDetailsTab {

    @Autowired
    private TargetDetailsTabDAO targetDetailsTabDAO;

    /**
     *
     * @param tabularviewFilterRequest
     * @param userJdbc
     * @param currentSessionUserToken
     * @return
     */
    @Override
    public List<TargetDetailsTabDTO> findTargetDetailsTabFilterFieldByTargetIds(TabularviewFilterRequest tabularviewFilterRequest, UserJdbc userJdbc, String currentSessionUserToken) {
        return targetDetailsTabDAO.findTargetDetailsTabFilterFieldByTargetIds(tabularviewFilterRequest, userJdbc, currentSessionUserToken);
    }

    /**
     *
     * @param tabularviewRequest
     * @param userJdbc
     * @param currentSessionUserToken
     * @return
     */
    @Override
    public TargetDetailsTabPageData findTargetDetailsTabData(TabularviewRequest tabularviewRequest, UserJdbc userJdbc, String currentSessionUserToken) {
        return targetDetailsTabDAO.findTargetDetailsTabData(tabularviewRequest, userJdbc, currentSessionUserToken);
    }

    /**
     *
     * @param tabularviewExportRequest
     * @param userJdbc
     * @param currentSessionUserToken
     * @return
     */
    @Override
    public List<TargetDetailsTabDTO> findTargetDetailsTabDataForExport(TabularviewExportRequest tabularviewExportRequest, UserJdbc userJdbc, String currentSessionUserToken) {
        return targetDetailsTabDAO.findTargetDetailsTabDataForExport(tabularviewExportRequest, userJdbc, currentSessionUserToken);
    }
}

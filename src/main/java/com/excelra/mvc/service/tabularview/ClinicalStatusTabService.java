package com.excelra.mvc.service.tabularview;

import com.excelra.mvc.model.tabularview.*;
import com.excelra.mvc.model.userjdbc.UserJdbc;
import com.excelra.mvc.repository.tabularview.ClinicalStatusTabDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Clinical Status tabularview service methods implementation.
 *
 * @author Venkat Salagrama
 */
@Service
public class ClinicalStatusTabService implements IClinicalStatusTab {

    @Autowired
    private ClinicalStatusTabDAO clinicalStatusTabDAO;

    /**
     *
     * @param tabularviewFilterRequest
     * @param userJdbc
     * @param currentSessionUserToken
     * @return
     */
    @Override
    public List<ClinicalStatusTabDTO> findClinicalStatusTabFilterFieldByGvkRefIds(TabularviewFilterRequest tabularviewFilterRequest, UserJdbc userJdbc, String currentSessionUserToken) {
        return clinicalStatusTabDAO.findClinicalStatusTabFilterFieldByGvkRefIds(tabularviewFilterRequest, userJdbc, currentSessionUserToken);
    }

    /**
     *
     * @param tabularviewRequest
     * @param userJdbc
     * @param currentSessionUserToken
     * @return
     */
    @Override
    public ClinicalStatusTabPageData findClinicalStatusTabData(TabularviewRequest tabularviewRequest, UserJdbc userJdbc, String currentSessionUserToken) {
        return clinicalStatusTabDAO.findClinicalStatusTabData(tabularviewRequest, userJdbc, currentSessionUserToken);
    }

    /**
     *
     * @param tabularviewExportRequest
     * @param userJdbc
     * @param currentSessionUserToken
     * @return
     */
    @Override
    public List<ClinicalStatusTabDTO> findClinicalStatusTabDataForExport(TabularviewExportRequest tabularviewExportRequest, UserJdbc userJdbc, String currentSessionUserToken) {
        return clinicalStatusTabDAO.findClinicalStatusTabDataForExport(tabularviewExportRequest, userJdbc, currentSessionUserToken);
    }

}

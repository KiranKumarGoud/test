package com.excelra.mvc.service.tabularview;

import com.excelra.mvc.model.tabularview.*;
import com.excelra.mvc.model.userjdbc.UserJdbc;
import com.excelra.mvc.repository.tabularview.StructureDetailsTabDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Default Structure Details Tabularview Service Implementation
 *
 * @author Venkat Salagrama
 */
@Service
public class StructureDetailsTabService implements IStructureDetailsTab {

    @Autowired
    private StructureDetailsTabDAO structureDetailsTabDAO;

    /**
     *
     * @param tabularviewFilterRequest
     * @param userJdbc
     * @param currentSessionUserToken
     * @return
     */
    @Override
    public List<StructureDetailsTabDTO> findStructureTabFilterFieldByGvkIds(TabularviewFilterRequest tabularviewFilterRequest, UserJdbc userJdbc, String currentSessionUserToken) {
        return structureDetailsTabDAO.findStructureTabFilterFieldByGvkIds(tabularviewFilterRequest, userJdbc, currentSessionUserToken);
    }

    /**
     *
     * @param tabularviewRequest
     * @param userJdbc
     * @param currentSessionUserToken
     * @return
     */
    @Override
    public StructureDetailsTabPageData findStructureTabData(TabularviewRequest tabularviewRequest, UserJdbc userJdbc, String currentSessionUserToken) {
        return structureDetailsTabDAO.findStructureTabData(tabularviewRequest, userJdbc, currentSessionUserToken);
    }

    /**
     *
     * @param tabularviewExportRequest
     * @param userJdbc
     * @param currentSessionUserToken
     * @return
     */
    @Override
    public List<StructureDetailsTabDTO> findStructureTabDataForExport(TabularviewExportRequest tabularviewExportRequest, UserJdbc userJdbc, String currentSessionUserToken) {
        return structureDetailsTabDAO.findStructureTabDataForExport(tabularviewExportRequest, userJdbc, currentSessionUserToken);
    }
}

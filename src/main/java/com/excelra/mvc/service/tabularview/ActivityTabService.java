package com.excelra.mvc.service.tabularview;

import com.excelra.mvc.model.tabularview.*;
import com.excelra.mvc.model.userjdbc.UserJdbc;
import com.excelra.mvc.repository.tabularview.ActivityTabDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Default Activity Tabularview Service Implementation
 *
 * @author Venkat Salagrama
 */
@Service
public class ActivityTabService implements IActivityTab {

    @Autowired
    private ActivityTabDAO activityTabDAO;

    @Override
    public List<ActivityTabDTO> findActivityTabFilterFieldByActIds(TabularviewFilterRequest tabularviewFilterRequest, UserJdbc userJdbc, String currentSessionUserToken) {
        return activityTabDAO.findActivityTabFilterFieldByActIds(tabularviewFilterRequest, userJdbc, currentSessionUserToken);
    }

    @Override
    public ActivityTabPageData findActivityTabData(TabularviewRequest tabularviewRequest, UserJdbc userJdbc, String currentSessionUserToken) {
        return activityTabDAO.findActivityTabData(tabularviewRequest, userJdbc, currentSessionUserToken);
    }

    @Override
    public List<ActivityTabDTO> findActivityTabDataForExport(TabularviewExportRequest tabularviewExportRequest, UserJdbc userJdbc, String currentSessionUserToken) {
        return activityTabDAO.findActivityTabDataForExport(tabularviewExportRequest, userJdbc, currentSessionUserToken);
    }
}

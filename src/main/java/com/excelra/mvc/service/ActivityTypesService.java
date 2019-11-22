package com.excelra.mvc.service;

import com.excelra.mvc.model.ActivityLabelValueDTO;
import com.excelra.mvc.model.ListActivityTypesDTO;
import com.excelra.mvc.model.userjdbc.UserJdbc;
import com.excelra.mvc.repository.ActivityTypesDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * @author venkateswarlu.s
 */
@Service
public class ActivityTypesService implements IActivityTypes {

    @Autowired
    private ActivityTypesDAO activityTypesDAO;

    /**
     *
     * @param activityType
     * @param userJdbc
     * @return
     */
    @Override
    public List<ListActivityTypesDTO> fetchActivityTypeContaining(String activityType, UserJdbc userJdbc) {

        return activityTypesDAO.fetchActivityTypeContaining(activityType, userJdbc);

    }

    /**
     *
     * @param activityType
     * @param userJdbc
     * @return
     */
    @Override
    public List<ActivityLabelValueDTO> fetchActivityTypeLabelValue(String activityType, UserJdbc userJdbc) {

        return activityTypesDAO.fetchActivityTypeLabelValue(activityType, userJdbc);

    }

    /**
     *
     * @param activityType
     * @param userJdbc
     * @return
     */
    @Override
    public List<ActivityLabelValueDTO> fetchActivityUomLabelValue(String activityType, UserJdbc userJdbc) {

        return activityTypesDAO.fetchActivityUomLabelValue(activityType, userJdbc);

    }

    /**
     *
     * @param activityType
     * @param userJdbc
     * @return
     */
    @Override
    public List<ActivityLabelValueDTO> fetchActivityPrefixLabelValue(String activityType, UserJdbc userJdbc) {

        return activityTypesDAO.fetchActivityPrefixLabelValue(activityType, userJdbc);

    }

    /**
     *
     * @return
     */
    @Override
    public List<ActivityLabelValueDTO> fetchActivityPrefixValues() {

        return activityTypesDAO.fetchActivityPrefixValues();

    }
}

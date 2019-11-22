package com.excelra.mvc.service;

import com.excelra.mvc.model.ActivityLabelValueDTO;
import com.excelra.mvc.model.ListActivityTypesDTO;
import com.excelra.mvc.model.userjdbc.UserJdbc;

import java.util.List;

/**
 *
 * @author venkateswarlu.s
 */
public interface IActivityTypes {

    List<ListActivityTypesDTO> fetchActivityTypeContaining(String activityType, UserJdbc userJdbc);

    List<ActivityLabelValueDTO> fetchActivityTypeLabelValue(String activityType, UserJdbc userJdbc);

    List<ActivityLabelValueDTO> fetchActivityUomLabelValue(String activityType, UserJdbc userJdbc);

    List<ActivityLabelValueDTO> fetchActivityPrefixLabelValue(String activityType, UserJdbc userJdbc);

    List<ActivityLabelValueDTO> fetchActivityPrefixValues();
}

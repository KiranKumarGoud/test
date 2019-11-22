package com.excelra.mvc.model.search.ActivityType;

import java.io.Serializable;
import java.util.List;

public class ActivityTypeAdvSearchDTO implements Serializable {

    private String option;

    private List<ActivityTypeInputDTO> activityTypeInputDTOList;

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    public List<ActivityTypeInputDTO> getActivityTypeInputDTOList() {
        return activityTypeInputDTOList;
    }

    public void setActivityTypeInputDTOList(List<ActivityTypeInputDTO> activityTypeInputDTOList) {
        this.activityTypeInputDTOList = activityTypeInputDTOList;
    }
}

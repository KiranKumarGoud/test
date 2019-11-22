package com.excelra.mvc.model.tabularview;

import java.io.Serializable;
import java.util.List;

/**
 * Activity tab complete data with records count
 *
 * @author Venkat Salagrama
 */
public class ActivityTabPageData implements Serializable {

    private List<ActivityTabDTO> activityTabDTOList;

    private Long count;

    public List<ActivityTabDTO> getActivityTabDTOList() {
        return activityTabDTOList;
    }

    public void setActivityTabDTOList(List<ActivityTabDTO> activityTabDTOList) {
        this.activityTabDTOList = activityTabDTOList;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}

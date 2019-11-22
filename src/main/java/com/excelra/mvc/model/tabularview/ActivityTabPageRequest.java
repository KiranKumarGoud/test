package com.excelra.mvc.model.tabularview;

import java.io.Serializable;
import java.util.List;

/**
 * Activity tab page request input object
 *
 * @author Venkat Salagrama
 */
public class ActivityTabPageRequest implements Serializable {

    private List<Long> actIdList;

    private PageRequestInformation pageRequestInformation;

    public List<Long> getActIdList() {
        return actIdList;
    }

    public void setActIdList(List<Long> actIdList) {
        this.actIdList = actIdList;
    }

    public PageRequestInformation getPageRequestInformation() {
        return pageRequestInformation;
    }

    public void setPageRequestInformation(PageRequestInformation pageRequestInformation) {
        this.pageRequestInformation = pageRequestInformation;
    }
}

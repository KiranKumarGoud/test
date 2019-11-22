package com.excelra.mvc.model.tabularview;

import java.io.Serializable;
import java.util.List;

/**
 * Target Details tab page request input object
 *
 * @author Venkat Salagrama
 */
public class TargetDetailsPageRequest implements Serializable {

    private List<Long> targetIdList;

    private PageRequestInformation pageRequestInformation;

    public List<Long> getTargetIdList() {
        return targetIdList;
    }

    public void setTargetIdList(List<Long> targetIdList) {
        this.targetIdList = targetIdList;
    }

    public PageRequestInformation getPageRequestInformation() {
        return pageRequestInformation;
    }

    public void setPageRequestInformation(PageRequestInformation pageRequestInformation) {
        this.pageRequestInformation = pageRequestInformation;
    }
}

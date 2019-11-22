package com.excelra.mvc.model.tabularview;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author venkateswarlu.s
 */
public class ClinicalStatusPageRequest implements Serializable {

    private List<Long> gvkIdList;

    private List<Long> refIdList;

    private PageRequestInformation pageRequestInformation;

    public List<Long> getGvkIdList() {
        return gvkIdList;
    }

    public void setGvkIdList(List<Long> gvkIdList) {
        this.gvkIdList = gvkIdList;
    }

    public List<Long> getRefIdList() {
        return refIdList;
    }

    public void setRefIdList(List<Long> refIdList) {
        this.refIdList = refIdList;
    }

    public PageRequestInformation getPageRequestInformation() {
        return pageRequestInformation;
    }

    public void setPageRequestInformation(PageRequestInformation pageRequestInformation) {
        this.pageRequestInformation = pageRequestInformation;
    }
}

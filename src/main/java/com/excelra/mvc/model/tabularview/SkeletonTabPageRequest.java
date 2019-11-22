package com.excelra.mvc.model.tabularview;

import java.io.Serializable;
import java.util.List;

public class SkeletonTabPageRequest implements Serializable {

    private List<Long> gvkIdList;

    private PageRequestInformation pageRequestInformation;

    public List<Long> getGvkIdList() {
        return gvkIdList;
    }

    public void setGvkIdList(List<Long> gvkIdList) {
        this.gvkIdList = gvkIdList;
    }

    public PageRequestInformation getPageRequestInformation() {
        return pageRequestInformation;
    }

    public void setPageRequestInformation(PageRequestInformation pageRequestInformation) {
        this.pageRequestInformation = pageRequestInformation;
    }

}

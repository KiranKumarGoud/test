package com.excelra.mvc.model.tabularview;

import java.io.Serializable;
import java.util.List;

/**
 * Reference tab page request input object
 *
 * @author Venkat Salagrama
 */
public class ReferenceTabPageRequest implements Serializable {

    private List<Long> refIdList;

    private PageRequestInformation pageRequestInformation;

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

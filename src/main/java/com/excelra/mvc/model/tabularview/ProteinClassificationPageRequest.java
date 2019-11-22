package com.excelra.mvc.model.tabularview;

import java.io.Serializable;
import java.util.List;

/**
 * Protein Classification tab page request input object
 *
 * @author Venkat Salagrama
 */
public class ProteinClassificationPageRequest implements Serializable {

    private List<Long> stdnameIdList;

    private PageRequestInformation pageRequestInformation;

    public List<Long> getStdnameIdList() {
        return stdnameIdList;
    }

    public void setStdnameIdList(List<Long> stdnameIdList) {
        this.stdnameIdList = stdnameIdList;
    }

    public PageRequestInformation getPageRequestInformation() {
        return pageRequestInformation;
    }

    public void setPageRequestInformation(PageRequestInformation pageRequestInformation) {
        this.pageRequestInformation = pageRequestInformation;
    }
}

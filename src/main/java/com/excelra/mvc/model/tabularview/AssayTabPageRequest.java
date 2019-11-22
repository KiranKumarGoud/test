package com.excelra.mvc.model.tabularview;

import java.io.Serializable;
import java.util.List;

/**
 * Assay tab page request input object
 *
 * @author Venkat Salagrama
 */
public class AssayTabPageRequest implements Serializable {

    private List<Long> assayIdList;

    private PageRequestInformation pageRequestInformation;

    public List<Long> getAssayIdList() {
        return assayIdList;
    }

    public void setAssayIdList(List<Long> assayIdList) {
        this.assayIdList = assayIdList;
    }

    public PageRequestInformation getPageRequestInformation() {
        return pageRequestInformation;
    }

    public void setPageRequestInformation(PageRequestInformation pageRequestInformation) {
        this.pageRequestInformation = pageRequestInformation;
    }
}

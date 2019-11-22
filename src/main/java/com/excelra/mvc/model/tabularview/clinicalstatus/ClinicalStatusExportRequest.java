package com.excelra.mvc.model.tabularview.clinicalstatus;

import java.io.Serializable;
import java.util.List;

public class ClinicalStatusExportRequest implements Serializable {

    private List<Long> gvkIdList;

    private List<Long> refIdList;

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
}

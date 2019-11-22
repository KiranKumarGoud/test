package com.excelra.mvc.model.tabularview;

import java.io.Serializable;
import java.util.List;

public class TempTableDataForSession implements Serializable {

    private List<Long> actIdList;

    private List<Long> assayIdList;

    private List<Long> refIdList;

    private List<Long> gvkIdList;

    public List<Long> getActIdList() {
        return actIdList;
    }

    public void setActIdList(List<Long> actIdList) {
        this.actIdList = actIdList;
    }

    public List<Long> getAssayIdList() {
        return assayIdList;
    }

    public void setAssayIdList(List<Long> assayIdList) {
        this.assayIdList = assayIdList;
    }

    public List<Long> getRefIdList() {
        return refIdList;
    }

    public void setRefIdList(List<Long> refIdList) {
        this.refIdList = refIdList;
    }

    public List<Long> getGvkIdList() {
        return gvkIdList;
    }

    public void setGvkIdList(List<Long> gvkIdList) {
        this.gvkIdList = gvkIdList;
    }
}

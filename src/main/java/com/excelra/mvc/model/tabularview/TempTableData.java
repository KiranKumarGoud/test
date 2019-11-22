package com.excelra.mvc.model.tabularview;

import java.io.Serializable;

public class TempTableData implements Serializable {

    private Long actId;

    private Long assayId;

    private Long refId;

    private Long gvkId;

    public Long getActId() {
        return actId;
    }

    public void setActId(Long actId) {
        this.actId = actId;
    }

    public Long getAssayId() {
        return assayId;
    }

    public void setAssayId(Long assayId) {
        this.assayId = assayId;
    }

    public Long getRefId() {
        return refId;
    }

    public void setRefId(Long refId) {
        this.refId = refId;
    }

    public Long getGvkId() {
        return gvkId;
    }

    public void setGvkId(Long gvkId) {
        this.gvkId = gvkId;
    }
}

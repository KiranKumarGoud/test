package com.excelra.mvc.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class AllMappingIdsDTO implements Serializable {

    private BigDecimal actId;

    private BigDecimal gvkId;

    private BigDecimal strId;

    private BigDecimal refId;

    private BigDecimal assayId;

    private BigDecimal stdnameId;

    private BigDecimal targetId;

    private BigDecimal cellsId;

    private BigDecimal sourceId;

    private BigDecimal cellLineId;

    private BigDecimal activityValue;

    private String assayType;

    private String psm;

    public BigDecimal getActId() {
        return actId;
    }

    public void setActId(BigDecimal actId) {
        this.actId = actId;
    }

    public BigDecimal getGvkId() {
        return gvkId;
    }

    public void setGvkId(BigDecimal gvkId) {
        this.gvkId = gvkId;
    }

    public BigDecimal getStrId() {
        return strId;
    }

    public void setStrId(BigDecimal strId) {
        this.strId = strId;
    }

    public BigDecimal getRefId() {
        return refId;
    }

    public void setRefId(BigDecimal refId) {
        this.refId = refId;
    }

    public BigDecimal getAssayId() {
        return assayId;
    }

    public void setAssayId(BigDecimal assayId) {
        this.assayId = assayId;
    }

    public BigDecimal getStdnameId() {
        return stdnameId;
    }

    public void setStdnameId(BigDecimal stdnameId) {
        this.stdnameId = stdnameId;
    }

    public BigDecimal getTargetId() {
        return targetId;
    }

    public void setTargetId(BigDecimal targetId) {
        this.targetId = targetId;
    }

    public BigDecimal getCellsId() {
        return cellsId;
    }

    public void setCellsId(BigDecimal cellsId) {
        this.cellsId = cellsId;
    }

    public BigDecimal getSourceId() {
        return sourceId;
    }

    public void setSourceId(BigDecimal sourceId) {
        this.sourceId = sourceId;
    }

    public BigDecimal getCellLineId() {
        return cellLineId;
    }

    public void setCellLineId(BigDecimal cellLineId) {
        this.cellLineId = cellLineId;
    }

    public BigDecimal getActivityValue() {
        return activityValue;
    }

    public void setActivityValue(BigDecimal activityValue) {
        this.activityValue = activityValue;
    }

    public String getAssayType() {
        return assayType;
    }

    public void setAssayType(String assayType) {
        this.assayType = assayType;
    }

    public String getPsm() {
        return psm;
    }

    public void setPsm(String psm) {
        this.psm = psm;
    }
}

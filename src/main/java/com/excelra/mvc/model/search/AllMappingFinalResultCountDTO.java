package com.excelra.mvc.model.search;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * All Mapping result list count
 *
 * @author Venkat Salagrama
 */
public class AllMappingFinalResultCountDTO implements Serializable {

    private BigDecimal actIdsCount;

    private BigDecimal gvkIdsCount;

    private BigDecimal strIdsCount;

    private BigDecimal refIdsCount;

    private BigDecimal assayIdsCount;

    public BigDecimal getActIdsCount() {
        return actIdsCount;
    }

    public void setActIdsCount(BigDecimal actIdsCount) {
        this.actIdsCount = actIdsCount;
    }

    public BigDecimal getGvkIdsCount() {
        return gvkIdsCount;
    }

    public void setGvkIdsCount(BigDecimal gvkIdsCount) {
        this.gvkIdsCount = gvkIdsCount;
    }

    public BigDecimal getStrIdsCount() {
        return strIdsCount;
    }

    public void setStrIdsCount(BigDecimal strIdsCount) {
        this.strIdsCount = strIdsCount;
    }

    public BigDecimal getRefIdsCount() {
        return refIdsCount;
    }

    public void setRefIdsCount(BigDecimal refIdsCount) {
        this.refIdsCount = refIdsCount;
    }

    public BigDecimal getAssayIdsCount() {
        return assayIdsCount;
    }

    public void setAssayIdsCount(BigDecimal assayIdsCount) {
        this.assayIdsCount = assayIdsCount;
    }
}

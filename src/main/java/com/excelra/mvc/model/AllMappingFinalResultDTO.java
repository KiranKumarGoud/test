package com.excelra.mvc.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;

public class AllMappingFinalResultDTO implements Serializable {

    private Set<BigDecimal> actIdsList;
    private Set<BigDecimal> gvkIdsList;
    private Set<BigDecimal> strIdsList;
    private Set<BigDecimal> refIdsList;
    private Set<BigDecimal> assayIdsList;

    public Set<BigDecimal> getActIdsList() {
        return actIdsList;
    }

    public void setActIdsList(Set<BigDecimal> actIdsList) {
        this.actIdsList = actIdsList;
    }

    public Set<BigDecimal> getGvkIdsList() {
        return gvkIdsList;
    }

    public void setGvkIdsList(Set<BigDecimal> gvkIdsList) {
        this.gvkIdsList = gvkIdsList;
    }

    public Set<BigDecimal> getStrIdsList() {
        return strIdsList;
    }

    public void setStrIdsList(Set<BigDecimal> strIdsList) {
        this.strIdsList = strIdsList;
    }

    public Set<BigDecimal> getRefIdsList() {
        return refIdsList;
    }

    public void setRefIdsList(Set<BigDecimal> refIdsList) {
        this.refIdsList = refIdsList;
    }

    public Set<BigDecimal> getAssayIdsList() {
        return assayIdsList;
    }

    public void setAssayIdsList(Set<BigDecimal> assayIdsList) {
        this.assayIdsList = assayIdsList;
    }
}

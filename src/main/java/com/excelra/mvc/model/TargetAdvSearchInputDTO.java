package com.excelra.mvc.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class TargetAdvSearchInputDTO implements Serializable {

    private String pdbId;

    private BigDecimal stdnameId;

    private String commonName;

    private String label;

    private String value;

    private String operator;

    private String locusId;

    private String uniprotId;

    private String officalName;

    public String getPdbId() {
        return pdbId;
    }

    public void setPdbId(String pdbId) {
        this.pdbId = pdbId;
    }

    public BigDecimal getStdnameId() {
        return stdnameId;
    }

    public void setStdnameId(BigDecimal stdnameId) {
        this.stdnameId = stdnameId;
    }

    public String getCommonName() {
        return commonName;
    }

    public void setCommonName(String commonName) {
        this.commonName = commonName;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getLocusId() {
        return locusId;
    }

    public void setLocusId(String locusId) {
        this.locusId = locusId;
    }

    public String getUniprotId() {
        return uniprotId;
    }

    public void setUniprotId(String uniprotId) {
        this.uniprotId = uniprotId;
    }

    public String getOfficalName() {
        return officalName;
    }

    public void setOfficalName(String officalName) {
        this.officalName = officalName;
    }
}

package com.excelra.mvc.model;

import java.io.Serializable;

public class BibliographyIssnNoDTO implements Serializable {

    private String issnNo;

    private Long refId;

    private String label;

    private String value;

    private String operator;

    public String getIssnNo() {
        return issnNo;
    }

    public void setIssnNo(String issnNo) {
        this.issnNo = issnNo;
    }

    public Long getRefId() {
        return refId;
    }

    public void setRefId(Long refId) {
        this.refId = refId;
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
}

package com.excelra.mvc.model;

import java.io.Serializable;

public class ListCasNo implements Serializable {

    private Long strId;

    private String casNo;

    private String label;

    private String value;

    private String operator;

    public Long getStrId() {
        return strId;
    }

    public void setStrId(Long strId) {
        this.strId = strId;
    }

    public String getCasNo() {
        return casNo;
    }

    public void setCasNo(String casNo) {
        this.casNo = casNo;
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

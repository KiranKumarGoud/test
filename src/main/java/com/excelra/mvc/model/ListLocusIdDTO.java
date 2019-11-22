package com.excelra.mvc.model;

import java.io.Serializable;

public class ListLocusIdDTO  implements Serializable {

    private int locusId;

    private String label;

    private String value;

    private String operator;

    public int getLocusId() {
        return locusId;
    }

    public void setLocusId(int locusId) {
        this.locusId = locusId;
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

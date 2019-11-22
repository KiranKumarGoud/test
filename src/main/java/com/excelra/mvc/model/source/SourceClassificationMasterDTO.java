package com.excelra.mvc.model.source;

import java.io.Serializable;

public class SourceClassificationMasterDTO implements Serializable {

    private String label;

    private String value;

    private String operator;

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

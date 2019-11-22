package com.excelra.mvc.model.indication;

import java.io.Serializable;

public class ListTherapeuticUseDTO implements Serializable {

    private String therapeuticUse;

    private String label;

    private String value;

    private String operator;

    public String getTherapeuticUse() {
        return therapeuticUse;
    }

    public void setTherapeuticUse(String therapeuticUse) {
        this.therapeuticUse = therapeuticUse;
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

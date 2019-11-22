package com.excelra.mvc.model;

import java.io.Serializable;

public class ListActivityMechanismDTO implements Serializable {

    private String activityMechanism;

    private String label;

    private String value;

    private String operator;

    public String getActivityMechanism() {
        return activityMechanism;
    }

    public void setActivityMechanism(String activityMechanism) {
        this.activityMechanism = activityMechanism;
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

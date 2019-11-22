package com.excelra.mvc.model;

import java.io.Serializable;

/**
 *
 * @author venkateswarlu.s
 */
public class ListClinicalPhasesDTO implements Serializable {

    private String clinicalPhase;

    private String label;

    private String value;

    private String operator;

    public String getClinicalPhase() {
        return clinicalPhase;
    }

    public void setClinicalPhase(String clinicalPhase) {
        this.clinicalPhase = clinicalPhase;
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

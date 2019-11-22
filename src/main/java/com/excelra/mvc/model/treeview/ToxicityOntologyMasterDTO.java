package com.excelra.mvc.model.treeview;

import java.io.Serializable;

/**
 *
 * @author venkateswarlu.s
 */
public class ToxicityOntologyMasterDTO implements Serializable {

    private String toxicityName;

    private String label;

    private String value;

    private String operator;

    public String getToxicityName() {
        return toxicityName;
    }

    public void setToxicityName(String toxicityName) {
        this.toxicityName = toxicityName;
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

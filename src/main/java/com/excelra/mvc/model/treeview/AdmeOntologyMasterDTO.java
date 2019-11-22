package com.excelra.mvc.model.treeview;

import java.io.Serializable;

/**
 *
 * @author venkateswarlu.s
 */
public class AdmeOntologyMasterDTO implements Serializable {

    private String admeName;

    private String label;

    private String value;

    private String operator;

    public String getAdmeName() {
        return admeName;
    }

    public void setAdmeName(String admeName) {
        this.admeName = admeName;
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

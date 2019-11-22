package com.excelra.mvc.model.treeview;

import java.io.Serializable;

/**
 *
 * @author venkateswarlu.s
 */
public class EndpointMasterDTO implements Serializable {

    private String endpointName;

    private String label;

    private String value;

    private String operator;

    public String getEndpointName() {
        return endpointName;
    }

    public void setEndpointName(String endpointName) {
        this.endpointName = endpointName;
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

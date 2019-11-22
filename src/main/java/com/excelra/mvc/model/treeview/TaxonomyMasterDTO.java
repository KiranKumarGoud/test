package com.excelra.mvc.model.treeview;

import java.io.Serializable;

/**
 *
 * @author venkateswarlu.s
 */
public class TaxonomyMasterDTO implements Serializable {

    private String taxName;

    private String label;

    private String value;

    private String operator;

    public String getTaxName() {
        return taxName;
    }

    public void setTaxName(String taxName) {
        this.taxName = taxName;
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

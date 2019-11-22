package com.excelra.mvc.model;

import java.io.Serializable;

/**
 *
 * @author venkateswarlu.s
 */
public class ListCompoundCategoriesDTO implements Serializable {

    private String compoundCategory;

    private String label;

    private String value;

    private String operator;

    public String getCompoundCategory() {
        return compoundCategory;
    }

    public void setCompoundCategory(String compoundCategory) {
        this.compoundCategory = compoundCategory;
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

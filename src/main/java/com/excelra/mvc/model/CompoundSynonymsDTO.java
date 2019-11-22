package com.excelra.mvc.model;

import java.io.Serializable;

public class CompoundSynonymsDTO implements Serializable {

    private Long strId;

    private String compoundSynonym;

    private String cmpdType;

    private String label;

    private String value;

    private String operator;

    public Long getStrId() {
        return strId;
    }

    public void setStrId(Long strId) {
        this.strId = strId;
    }

    public String getCompoundSynonym() {
        return compoundSynonym;
    }

    public void setCompoundSynonym(String compoundSynonym) {
        this.compoundSynonym = compoundSynonym;
    }

    public String getCmpdType() {
        return cmpdType;
    }

    public void setCmpdType(String cmpdType) {
        this.cmpdType = cmpdType;
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

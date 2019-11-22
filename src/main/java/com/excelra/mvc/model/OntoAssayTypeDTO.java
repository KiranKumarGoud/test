package com.excelra.mvc.model;

import java.io.Serializable;

public class OntoAssayTypeDTO implements Serializable {

    private Integer assaytypeOntId;

    private String assayType;

    private Integer parentOntId;

    private Boolean enddataPointLeaf;

    private Integer ontLevel;

    private Integer displyOrder;

    private String assayTypeDesc;

    private String label;

    private String value;

    private String operator;

    public Integer getAssaytypeOntId() {
        return assaytypeOntId;
    }

    public void setAssaytypeOntId(Integer assaytypeOntId) {
        this.assaytypeOntId = assaytypeOntId;
    }

    public String getAssayType() {
        return assayType;
    }

    public void setAssayType(String assayType) {
        this.assayType = assayType;
    }

    public Integer getParentOntId() {
        return parentOntId;
    }

    public void setParentOntId(Integer parentOntId) {
        this.parentOntId = parentOntId;
    }

    public Boolean getEnddataPointLeaf() {
        return enddataPointLeaf;
    }

    public void setEnddataPointLeaf(Boolean enddataPointLeaf) {
        this.enddataPointLeaf = enddataPointLeaf;
    }

    public Integer getOntLevel() {
        return ontLevel;
    }

    public void setOntLevel(Integer ontLevel) {
        this.ontLevel = ontLevel;
    }

    public Integer getDisplyOrder() {
        return displyOrder;
    }

    public void setDisplyOrder(Integer displyOrder) {
        this.displyOrder = displyOrder;
    }

    public String getAssayTypeDesc() {
        return assayTypeDesc;
    }

    public void setAssayTypeDesc(String assayTypeDesc) {
        this.assayTypeDesc = assayTypeDesc;
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

package com.excelra.mvc.model;


import java.io.Serializable;
import java.math.BigDecimal;

/****
 *
 */
public class ListCasNosDTO implements Serializable {

    private BigDecimal structureId;

    private String casNo;

    private String label;

    private String value;

    private String operator;

    public String getCasNo() {
        return casNo;
    }

    public void setCasNo(String casNo) {
        this.casNo = casNo;
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

    public BigDecimal getStructureId() {
        return structureId;
    }

    public void setStructureId(BigDecimal structureId) {
        this.structureId = structureId;
    }
}

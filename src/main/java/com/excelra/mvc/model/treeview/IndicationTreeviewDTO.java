package com.excelra.mvc.model.treeview;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author venkateswarlu.s
 */
public class IndicationTreeviewDTO implements Serializable {

    private String icd10Code;

    private String therapeuticUse;

    private String label;

    private String parentIcd10Code;

    private List<IndicationTreeviewDTO> children = new ArrayList<>();

    public String getIcd10Code() {
        return icd10Code;
    }

    public void setIcd10Code(String icd10Code) {
        this.icd10Code = icd10Code;
    }

    public String getTherapeuticUse() {
        return therapeuticUse;
    }

    public void setTherapeuticUse(String therapeuticUse) {
        this.therapeuticUse = therapeuticUse;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getParentIcd10Code() {
        return parentIcd10Code;
    }

    public void setParentIcd10Code(String parentIcd10Code) {
        this.parentIcd10Code = parentIcd10Code;
    }

    public List<IndicationTreeviewDTO> getChildren() {
        return children;
    }

    public void setChildren(List<IndicationTreeviewDTO> children) {
        this.children = children;
    }
}

package com.excelra.mvc.model.search.treeview;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author venkateswarlu.s
 */
public class IndicationTreeviewParentChildNodeDTO implements Serializable {

    private String icd10Code;

    private String parentIcd10Code;

    private String therapeuticUse;

    private String label;

    public List<IndicationTreeviewParentChildNodeDTO> children;

    public Boolean lazy = Boolean.TRUE;

    public String getIcd10Code() {
        return icd10Code;
    }

    public void setIcd10Code(String icd10Code) {
        this.icd10Code = icd10Code;
    }

    public String getParentIcd10Code() {
        return parentIcd10Code;
    }

    public void setParentIcd10Code(String parentIcd10Code) {
        this.parentIcd10Code = parentIcd10Code;
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

    public List<IndicationTreeviewParentChildNodeDTO> getChildren() {
        return children;
    }

    public void setChildren(List<IndicationTreeviewParentChildNodeDTO> children) {
        this.children = children;
    }

    public Boolean getLazy() {
        return lazy;
    }

    public void setLazy(Boolean lazy) {
        this.lazy = lazy;
    }
}

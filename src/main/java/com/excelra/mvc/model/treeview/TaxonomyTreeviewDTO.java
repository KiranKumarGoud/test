package com.excelra.mvc.model.treeview;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author venkateswarlu.s
 */
public class TaxonomyTreeviewDTO implements Serializable {

    private Long taxId;

    private String taxName;

    private String label;

    private Long parentTaxId;

    private List<ToxicityTreeviewDTO> children = new ArrayList<>();

    public Long getTaxId() {
        return taxId;
    }

    public void setTaxId(Long taxId) {
        this.taxId = taxId;
    }

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

    public Long getParentTaxId() {
        return parentTaxId;
    }

    public void setParentTaxId(Long parentTaxId) {
        this.parentTaxId = parentTaxId;
    }

    public List<ToxicityTreeviewDTO> getChildren() {
        return children;
    }

    public void setChildren(List<ToxicityTreeviewDTO> children) {
        this.children = children;
    }
}

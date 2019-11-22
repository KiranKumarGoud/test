package com.excelra.mvc.model.search.treeview;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author venkateswarlu.s
 */
public class TaxonomyTreeviewParentChildNodeDTO implements Serializable {

    private Long taxId;

    private Long parentTaxId;

    private String taxName;

    private String label;

    public List<TaxonomyTreeviewParentChildNodeDTO> children;

    public Boolean lazy = Boolean.TRUE;

    public Long getTaxId() {
        return taxId;
    }

    public void setTaxId(Long taxId) {
        this.taxId = taxId;
    }

    public Long getParentTaxId() {
        return parentTaxId;
    }

    public void setParentTaxId(Long parentTaxId) {
        this.parentTaxId = parentTaxId;
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

    public List<TaxonomyTreeviewParentChildNodeDTO> getChildren() {
        return children;
    }

    public void setChildren(List<TaxonomyTreeviewParentChildNodeDTO> children) {
        this.children = children;
    }

    public Boolean getLazy() {
        return lazy;
    }

    public void setLazy(Boolean lazy) {
        this.lazy = lazy;
    }
}

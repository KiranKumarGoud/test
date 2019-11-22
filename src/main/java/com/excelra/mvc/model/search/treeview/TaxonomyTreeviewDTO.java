package com.excelra.mvc.model.search.treeview;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author venkateswarlu.s
 */
public class TaxonomyTreeviewDTO implements Serializable {

    private List<Long> taxId;

    public List<Long> getTaxId() {
        return taxId;
    }

    public void setTaxId(List<Long> taxId) {
        this.taxId = taxId;
    }
}

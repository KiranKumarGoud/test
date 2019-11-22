package com.excelra.mvc.model.search;

import java.io.Serializable;
import java.math.BigDecimal;

public class VisualizationResultDTO implements Serializable {

    private BigDecimal id;
    private String name;
    private BigDecimal tree_level;
    private BigDecimal display_order;
    private String parent_or_leaf_or_both;
    private BigDecimal parent_id;
    private BigDecimal value;

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getTree_level() {
        return tree_level;
    }

    public void setTree_level(BigDecimal tree_level) {
        this.tree_level = tree_level;
    }

    public BigDecimal getDisplay_order() {
        return display_order;
    }

    public void setDisplay_order(BigDecimal display_order) {
        this.display_order = display_order;
    }

    public String getParent_or_leaf_or_both() {
        return parent_or_leaf_or_both;
    }

    public void setParent_or_leaf_or_both(String parent_or_leaf_or_both) {
        this.parent_or_leaf_or_both = parent_or_leaf_or_both;
    }

    public BigDecimal getParent_id() {
        return parent_id;
    }

    public void setParent_id(BigDecimal parent_id) {
        this.parent_id = parent_id;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }
}

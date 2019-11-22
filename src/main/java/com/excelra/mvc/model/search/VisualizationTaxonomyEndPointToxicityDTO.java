package com.excelra.mvc.model.search;

import java.io.Serializable;
import java.math.BigDecimal;

public class VisualizationTaxonomyEndPointToxicityDTO implements Serializable {

    private BigDecimal id;
    private String name;
    private BigDecimal tree_level;
    private BigDecimal display_order;
    private String has_data;
    private String is_leaf;

    private String VisualizationType;

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

    public String getHas_data() {
        return has_data;
    }

    public void setHas_data(String has_data) {
        this.has_data = has_data;
    }

    public String getIs_leaf() {
        return is_leaf;
    }

    public void setIs_leaf(String is_leaf) {
        this.is_leaf = is_leaf;
    }

}

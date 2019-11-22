package com.excelra.mvc.model.search;

import com.excelra.mvc.model.search.treeview.TreeSearch;

import java.io.Serializable;

public class SearchCountInputDTO implements Serializable {

    private String sourceOption;

    private SimpleSearch simpleSearch;

    private AdvancedSearch advancedSearch;

    private TreeSearch treeSearch;

    private String operator;

    public String getSourceOption() {
        return sourceOption;
    }

    public void setSourceOption(String sourceOption) {
        this.sourceOption = sourceOption;
    }

    public SimpleSearch getSimpleSearch() {
        return simpleSearch;
    }

    public void setSimpleSearch(SimpleSearch simpleSearch) {
        this.simpleSearch = simpleSearch;
    }

    public AdvancedSearch getAdvancedSearch() {
        return advancedSearch;
    }

    public void setAdvancedSearch(AdvancedSearch advancedSearch) {
        this.advancedSearch = advancedSearch;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public TreeSearch getTreeSearch() {
        return treeSearch;
    }

    public void setTreeSearch(TreeSearch treeSearch) {
        this.treeSearch = treeSearch;
    }
}

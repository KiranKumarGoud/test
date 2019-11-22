package com.excelra.mvc.model.search.Bibliography;

import java.io.Serializable;

public class BibliographyAdvSearchDTO implements Serializable {

    private ListSearch listSearch;

    private CriterionSearch criterionSearch;

    private CustomSearch customSearch;

    public ListSearch getListSearch() {
        return listSearch;
    }

    public void setListSearch(ListSearch listSearch) {
        this.listSearch = listSearch;
    }

    public CriterionSearch getCriterionSearch() {
        return criterionSearch;
    }

    public void setCriterionSearch(CriterionSearch criterionSearch) {
        this.criterionSearch = criterionSearch;
    }

    public CustomSearch getCustomSearch() {
        return customSearch;
    }

    public void setCustomSearch(CustomSearch customSearch) {
        this.customSearch = customSearch;
    }
}

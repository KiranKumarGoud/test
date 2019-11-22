package com.excelra.mvc.model.search;

import com.excelra.mvc.model.search.Structure.ChemistrySearch;
import com.excelra.mvc.model.search.Structure.PropertySearch;
import com.excelra.mvc.model.search.Structure.TermSearch;

import java.io.Serializable;

public class StructureAdvSearchDTO implements Serializable {

    private ChemistrySearch chemistrySearch;

    private TermSearch termSearch;

    private PropertySearch propertySearch;

    public ChemistrySearch getChemistrySearch() {
        return chemistrySearch;
    }

    public void setChemistrySearch(ChemistrySearch chemistrySearch) {
        this.chemistrySearch = chemistrySearch;
    }

    public TermSearch getTermSearch() {
        return termSearch;
    }

    public void setTermSearch(TermSearch termSearch) {
        this.termSearch = termSearch;
    }

    public PropertySearch getPropertySearch() {
        return propertySearch;
    }

    public void setPropertySearch(PropertySearch propertySearch) {
        this.propertySearch = propertySearch;
    }
}

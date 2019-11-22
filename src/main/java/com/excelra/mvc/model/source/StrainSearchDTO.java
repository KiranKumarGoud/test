package com.excelra.mvc.model.source;

import java.io.Serializable;
import java.util.List;

public class StrainSearchDTO implements Serializable {

    private List<String> genusList;

    private List<String> speciesList;

    private List<String> strainList;

    public List<String> getGenusList() {
        return genusList;
    }

    public void setGenusList(List<String> genusList) {
        this.genusList = genusList;
    }

    public List<String> getSpeciesList() {
        return speciesList;
    }

    public void setSpeciesList(List<String> speciesList) {
        this.speciesList = speciesList;
    }

    public List<String> getStrainList() {
        return strainList;
    }

    public void setStrainList(List<String> strainList) {
        this.strainList = strainList;
    }
}

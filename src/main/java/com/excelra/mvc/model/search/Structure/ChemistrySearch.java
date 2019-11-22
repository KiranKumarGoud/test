package com.excelra.mvc.model.search.Structure;

import java.io.Serializable;
import java.util.List;

public class ChemistrySearch implements Serializable {

    private String strDraw;

    private String strCategory;

    private float strSimilarityMinValue;

    private float strSimilarityMaxValue;

    private String strReadonly;

    private List<String> strComboData;

    private List<String> strComboFileData;

    private String strFeature;

    private List<String> strFeatureOptions;

    public String getStrDraw() {
        return strDraw;
    }

    public void setStrDraw(String strDraw) {
        this.strDraw = strDraw;
    }

    public String getStrCategory() {
        return strCategory;
    }

    public void setStrCategory(String strCategory) {
        this.strCategory = strCategory;
    }

    public float getStrSimilarityMinValue() {
        return strSimilarityMinValue;
    }

    public void setStrSimilarityMinValue(float strSimilarityMinValue) {
        this.strSimilarityMinValue = strSimilarityMinValue;
    }

    public float getStrSimilarityMaxValue() {
        return strSimilarityMaxValue;
    }

    public void setStrSimilarityMaxValue(float strSimilarityMaxValue) {
        this.strSimilarityMaxValue = strSimilarityMaxValue;
    }

    public String getStrReadonly() {
        return strReadonly;
    }

    public void setStrReadonly(String strReadonly) {
        this.strReadonly = strReadonly;
    }

    public List<String> getStrComboData() {
        return strComboData;
    }

    public void setStrComboData(List<String> strComboData) {
        this.strComboData = strComboData;
    }

    public List<String> getStrComboFileData() {
        return strComboFileData;
    }

    public void setStrComboFileData(List<String> strComboFileData) {
        this.strComboFileData = strComboFileData;
    }

    public String getStrFeature() {
        return strFeature;
    }

    public void setStrFeature(String strFeature) {
        this.strFeature = strFeature;
    }

    public List<String> getStrFeatureOptions() {
        return strFeatureOptions;
    }

    public void setStrFeatureOptions(List<String> strFeatureOptions) {
        this.strFeatureOptions = strFeatureOptions;
    }
}

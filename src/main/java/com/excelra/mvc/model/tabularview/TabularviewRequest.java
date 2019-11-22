package com.excelra.mvc.model.tabularview;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class TabularviewRequest implements Serializable {

    private Map<String, List<Long>> selectedTabIds;

    private Map<String, List<Long>> unSelectedTabIds;

    private Map<String, List<String>> stringFilters;

    private Map<String, MinMaxRangeForField> numericFilters;

    private Map<String, String> freeTextFilters;

    private PageRequestInformation pageRequestInformation;

    public Map<String, List<Long>> getSelectedTabIds() {
        return selectedTabIds;
    }

    public void setSelectedTabIds(Map<String, List<Long>> selectedTabIds) {
        this.selectedTabIds = selectedTabIds;
    }

    public Map<String, List<Long>> getUnSelectedTabIds() {
        return unSelectedTabIds;
    }

    public void setUnSelectedTabIds(Map<String, List<Long>> unSelectedTabIds) {
        this.unSelectedTabIds = unSelectedTabIds;
    }

    public Map<String, List<String>> getStringFilters() {
        return stringFilters;
    }

    public void setStringFilters(Map<String, List<String>> stringFilters) {
        this.stringFilters = stringFilters;
    }

    public Map<String, MinMaxRangeForField> getNumericFilters() {
        return numericFilters;
    }

    public void setNumericFilters(Map<String, MinMaxRangeForField> numericFilters) {
        this.numericFilters = numericFilters;
    }

    public Map<String, String> getFreeTextFilters() {
        return freeTextFilters;
    }

    public void setFreeTextFilters(Map<String, String> freeTextFilters) {
        this.freeTextFilters = freeTextFilters;
    }

    public PageRequestInformation getPageRequestInformation() {
        return pageRequestInformation;
    }

    public void setPageRequestInformation(PageRequestInformation pageRequestInformation) {
        this.pageRequestInformation = pageRequestInformation;
    }
}

package com.excelra.mvc.model.tabularview.activitytab;

import com.excelra.mvc.model.tabularview.MinMaxRangeForField;
import com.excelra.mvc.model.tabularview.PageRequestInformation;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class ActivityTabRequest implements Serializable {

    private Map<String, List<Long>> selectedTabIds;

    private Map<String, List<String>> stringFilters;

    private Map<String, MinMaxRangeForField> numericFilters;

    private PageRequestInformation pageRequestInformation;

    public Map<String, List<Long>> getSelectedTabIds() {
        return selectedTabIds;
    }

    public void setSelectedTabIds(Map<String, List<Long>> selectedTabIds) {
        this.selectedTabIds = selectedTabIds;
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

    public PageRequestInformation getPageRequestInformation() {
        return pageRequestInformation;
    }

    public void setPageRequestInformation(PageRequestInformation pageRequestInformation) {
        this.pageRequestInformation = pageRequestInformation;
    }
}

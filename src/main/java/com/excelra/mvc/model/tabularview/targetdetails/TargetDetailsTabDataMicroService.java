package com.excelra.mvc.model.tabularview.targetdetails;

import com.excelra.mvc.model.tabularview.MinMaxRangeForField;
import com.excelra.mvc.model.tabularview.PageRequestInformation;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 *
 * @author venkateswarlu.s
 */
public class TargetDetailsTabDataMicroService implements Serializable {

    private List<Long> targetIdList;

    private Map<String, List<String>> stringFilters;

    private Map<String, MinMaxRangeForField> numericFilters;

    private Map<String, String> freeTextFilters;

    private PageRequestInformation pageRequestInformation;

    public List<Long> getTargetIdList() {
        return targetIdList;
    }

    public void setTargetIdList(List<Long> targetIdList) {
        this.targetIdList = targetIdList;
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

package com.excelra.mvc.model.tabularview;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class TabularviewFilterRequest implements Serializable {

    private Map<String, List<Long>> selectedTabIds;

    private Map<String, List<Long>> unSelectedTabIds;

    private Map<String, List<String>> stringFilters;

    private Map<String, MinMaxRangeForField> numericFilters;

    private String fieldName;

    private String fieldType;

    private String fieldOperator;

    private String fieldValue;

    private int pageSize;

    private int pageNumber;

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

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldType() {
        return fieldType;
    }

    public void setFieldType(String fieldType) {
        this.fieldType = fieldType;
    }

    public String getFieldOperator() {
        return fieldOperator;
    }

    public void setFieldOperator(String fieldOperator) {
        this.fieldOperator = fieldOperator;
    }

    public String getFieldValue() {
        return fieldValue;
    }

    public void setFieldValue(String fieldValue) {
        this.fieldValue = fieldValue;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }
}

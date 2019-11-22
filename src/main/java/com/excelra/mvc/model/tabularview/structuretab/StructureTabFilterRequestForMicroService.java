package com.excelra.mvc.model.tabularview.structuretab;

import com.excelra.mvc.model.tabularview.MinMaxRangeForField;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 *
 * @author venkateswarlu.s
 */
public class StructureTabFilterRequestForMicroService implements Serializable {

    private List<Long> gvkIdList;

    private Map<String, List<String>> stringFilters;

    private Map<String, MinMaxRangeForField> numericFilters;

    private String fieldName;

    private String fieldType;

    private String fieldOperator;

    private String fieldValue;

    private int pageSize;

    private int pageNumber;

    public List<Long> getGvkIdList() {
        return gvkIdList;
    }

    public void setGvkIdList(List<Long> gvkIdList) {
        this.gvkIdList = gvkIdList;
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

package com.excelra.mvc.model.tabularview;

import java.io.Serializable;

/**
 * For Pagination, sorting the data
 *
 * @author Venkat Salagrama
 */
public class PageRequestInformation implements Serializable {

    private Integer pageNumber;

    private Integer pageSize;

    private String sortField;

    private String sortType;

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getSortField() {
        return sortField;
    }

    public void setSortField(String sortField) {
        this.sortField = sortField;
    }

    public String getSortType() {
        return sortType;
    }

    public void setSortType(String sortType) {
        this.sortType = sortType;
    }
}

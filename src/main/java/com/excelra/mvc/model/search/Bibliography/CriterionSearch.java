package com.excelra.mvc.model.search.Bibliography;

import com.excelra.mvc.model.ListAuthorsDTO;
import com.excelra.mvc.model.ListCompaniesDTO;

import java.io.Serializable;
import java.util.List;

public class CriterionSearch implements Serializable {

    private List<ListAuthorsDTO> listAuthorsDTOs;

    private List<ListCompaniesDTO> listCompaniesDTOs;

    private List<String> authorsFileData;

    private List<String> companiesFileData;

    private Long fromYear;

    private Long toYear;

    private List<String> dataSource;

    public List<ListAuthorsDTO> getListAuthorsDTOs() {
        return listAuthorsDTOs;
    }

    public void setListAuthorsDTOs(List<ListAuthorsDTO> listAuthorsDTOs) {
        this.listAuthorsDTOs = listAuthorsDTOs;
    }

    public List<ListCompaniesDTO> getListCompaniesDTOs() {
        return listCompaniesDTOs;
    }

    public void setListCompaniesDTOs(List<ListCompaniesDTO> listCompaniesDTOs) {
        this.listCompaniesDTOs = listCompaniesDTOs;
    }

    public List<String> getAuthorsFileData() {
        return authorsFileData;
    }

    public void setAuthorsFileData(List<String> authorsFileData) {
        this.authorsFileData = authorsFileData;
    }

    public List<String> getCompaniesFileData() {
        return companiesFileData;
    }

    public void setCompaniesFileData(List<String> companiesFileData) {
        this.companiesFileData = companiesFileData;
    }

    public Long getFromYear() {
        return fromYear;
    }

    public void setFromYear(Long fromYear) {
        this.fromYear = fromYear;
    }

    public Long getToYear() {
        return toYear;
    }

    public void setToYear(Long toYear) {
        this.toYear = toYear;
    }

    public List<String> getDataSource() {
        return dataSource;
    }

    public void setDataSource(List<String> dataSource) {
        this.dataSource = dataSource;
    }
}

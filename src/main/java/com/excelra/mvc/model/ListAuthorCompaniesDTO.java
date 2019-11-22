package com.excelra.mvc.model;

import java.io.Serializable;

public class ListAuthorCompaniesDTO implements Serializable {

    private Long refId;

    private Long authorSNo;

    private String author;

    private Long companySNo;

    private String companyName;

    private String refType;

    private Long refYear;

    private Long refAuthorId;

    private Long refCompanyId;

    public Long getRefId() {
        return refId;
    }

    public void setRefId(Long refId) {
        this.refId = refId;
    }

    public Long getAuthorSNo() {
        return authorSNo;
    }

    public void setAuthorSNo(Long authorSNo) {
        this.authorSNo = authorSNo;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Long getCompanySNo() {
        return companySNo;
    }

    public void setCompanySNo(Long companySNo) {
        this.companySNo = companySNo;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getRefType() {
        return refType;
    }

    public void setRefType(String refType) {
        this.refType = refType;
    }

    public Long getRefYear() {
        return refYear;
    }

    public void setRefYear(Long refYear) {
        this.refYear = refYear;
    }

    public Long getRefAuthorId() {
        return refAuthorId;
    }

    public void setRefAuthorId(Long refAuthorId) {
        this.refAuthorId = refAuthorId;
    }

    public Long getRefCompanyId() {
        return refCompanyId;
    }

    public void setRefCompanyId(Long refCompanyId) {
        this.refCompanyId = refCompanyId;
    }
}

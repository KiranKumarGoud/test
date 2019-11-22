package com.excelra.mvc.model.tabularview;

import java.io.Serializable;

/**
 * Reference Tab DTO Class
 *
 * @author Venkat Salagrama
 */
public class ReferenceTabDTO implements Serializable {

    private Long refId;

    private String refType;

    private String journalPatentName;

    private String reference;

    private String year;

    private String volume;

    private String issue;

    private String startPage;

    private String endPage;

    private String doi;

    private Long pubmedId;

    private String patentNo;

    private String applicationType;

    private String issnNo;

    private String title;

    private String abstrac;

    private String authors;

    private String companyNames;

    private String companyAddresses;

    public Long getRefId() {
        return refId;
    }

    public void setRefId(Long refId) {
        this.refId = refId;
    }

    public String getRefType() {
        return refType;
    }

    public void setRefType(String refType) {
        this.refType = refType;
    }

    public String getJournalPatentName() {
        return journalPatentName;
    }

    public void setJournalPatentName(String journalPatentName) {
        this.journalPatentName = journalPatentName;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getIssue() {
        return issue;
    }

    public void setIssue(String issue) {
        this.issue = issue;
    }

    public String getStartPage() {
        return startPage;
    }

    public void setStartPage(String startPage) {
        this.startPage = startPage;
    }

    public String getEndPage() {
        return endPage;
    }

    public void setEndPage(String endPage) {
        this.endPage = endPage;
    }

    public String getDoi() {
        return doi;
    }

    public void setDoi(String doi) {
        this.doi = doi;
    }

    public Long getPubmedId() {
        return pubmedId;
    }

    public void setPubmedId(Long pubmedId) {
        this.pubmedId = pubmedId;
    }

    public String getPatentNo() {
        return patentNo;
    }

    public void setPatentNo(String patentNo) {
        this.patentNo = patentNo;
    }

    public String getApplicationType() {
        return applicationType;
    }

    public void setApplicationType(String applicationType) {
        this.applicationType = applicationType;
    }

    public String getIssnNo() {
        return issnNo;
    }

    public void setIssnNo(String issnNo) {
        this.issnNo = issnNo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAbstrac() {
        return abstrac;
    }

    public void setAbstrac(String abstrac) {
        this.abstrac = abstrac;
    }

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public String getCompanyNames() {
        return companyNames;
    }

    public void setCompanyNames(String companyNames) {
        this.companyNames = companyNames;
    }

    public String getCompanyAddresses() {
        return companyAddresses;
    }

    public void setCompanyAddresses(String companyAddresses) {
        this.companyAddresses = companyAddresses;
    }
}

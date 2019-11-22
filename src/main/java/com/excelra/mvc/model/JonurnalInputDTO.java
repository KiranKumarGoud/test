package com.excelra.mvc.model;

import java.io.Serializable;
import java.util.List;

public class JonurnalInputDTO implements Serializable {

    private String journalName;

    private List<String> year;

    private List<String> volume;

    private List<String> issue;

    private List<String> pageNo;

    public String getJournalName() {
        return journalName;
    }

    public void setJournalName(String journalName) {
        this.journalName = journalName;
    }

    public List<String> getYear() {
        return year;
    }

    public void setYear(List<String> year) {
        this.year = year;
    }

    public List<String> getVolume() {
        return volume;
    }

    public void setVolume(List<String> volume) {
        this.volume = volume;
    }

    public List<String> getIssue() {
        return issue;
    }

    public void setIssue(List<String> issue) {
        this.issue = issue;
    }

    public List<String> getPageNo() {
        return pageNo;
    }

    public void setPageNo(List<String> pageNo) {
        this.pageNo = pageNo;
    }
}

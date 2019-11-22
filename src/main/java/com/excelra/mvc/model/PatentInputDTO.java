package com.excelra.mvc.model;

import java.io.Serializable;
import java.util.List;

public class PatentInputDTO implements Serializable {

    private List<String> countryCode;

    private List<String> year;

    private List<String> patentNo;

    public List<String> getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(List<String> countryCode) {
        this.countryCode = countryCode;
    }

    public List<String> getYear() {
        return year;
    }

    public void setYear(List<String> year) {
        this.year = year;
    }

    public List<String> getPatentNo() {
        return patentNo;
    }

    public void setPatentNo(List<String> patentNo) {
        this.patentNo = patentNo;
    }
}

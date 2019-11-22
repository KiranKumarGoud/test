package com.excelra.mvc.model.search.Bibliography;

import com.excelra.mvc.model.JonurnalInputDTO;
import com.excelra.mvc.model.PatentInputDTO;

import java.io.Serializable;
import java.util.List;

public class CustomSearch implements Serializable {

    private List<JonurnalInputDTO> journalData;

    private List<PatentInputDTO> patentData;

    public List<JonurnalInputDTO> getJournalData() {
        return journalData;
    }

    public void setJournalData(List<JonurnalInputDTO> journalData) {
        this.journalData = journalData;
    }

    public List<PatentInputDTO> getPatentData() {
        return patentData;
    }

    public void setPatentData(List<PatentInputDTO> patentData) {
        this.patentData = patentData;
    }

}

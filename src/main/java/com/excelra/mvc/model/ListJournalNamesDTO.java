package com.excelra.mvc.model;

import java.io.Serializable;

public class ListJournalNamesDTO implements Serializable {

    private String journalName;

    private String label;

    private String value;

    public String getJournalName() {
        return journalName;
    }

    public void setJournalName(String journalName) {
        this.journalName = journalName;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}

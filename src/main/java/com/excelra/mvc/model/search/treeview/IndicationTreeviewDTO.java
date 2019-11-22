package com.excelra.mvc.model.search.treeview;

import java.io.Serializable;
import java.util.List;

public class IndicationTreeviewDTO implements Serializable {

    private List<String> indicationIcd10Code;

    public List<String> getIndicationIcd10Code() {
        return indicationIcd10Code;
    }

    public void setIndicationIcd10Code(List<String> indicationIcd10Code) {
        this.indicationIcd10Code = indicationIcd10Code;
    }
}

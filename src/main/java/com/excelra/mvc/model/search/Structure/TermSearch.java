package com.excelra.mvc.model.search.Structure;

import com.excelra.mvc.model.CompoundSynonymsDTO;
import com.excelra.mvc.model.ListCasNo;
import com.excelra.mvc.model.ListCasNosDTO;

import java.io.Serializable;
import java.util.List;

public class TermSearch implements Serializable {

    private String strReadonly;

    private List<String> strComboData;

    private List<String> strComboFileData;

    private List<CompoundSynonymsDTO> compoundSynonymsDTOList;

    private List<ListCasNosDTO> listCasNosDTOList;


    public String getStrReadonly() {
        return strReadonly;
    }

    public void setStrReadonly(String strReadonly) {
        this.strReadonly = strReadonly;
    }

    public List<String> getStrComboFileData() {
        return strComboFileData;
    }

    public void setStrComboFileData(List<String> strComboFileData) {
        this.strComboFileData = strComboFileData;
    }

    public List<String> getStrComboData() {
        return strComboData;
    }

    public void setStrComboData(List<String> strComboData) {
        this.strComboData = strComboData;
    }

    public List<CompoundSynonymsDTO> getCompoundSynonymsDTOList() {
        return compoundSynonymsDTOList;
    }

    public void setCompoundSynonymsDTOList(List<CompoundSynonymsDTO> compoundSynonymsDTOList) {
        this.compoundSynonymsDTOList = compoundSynonymsDTOList;
    }

    public List<ListCasNosDTO> getListCasNosDTOList() {
        return listCasNosDTOList;
    }

    public void setListCasNosDTOList(List<ListCasNosDTO> listCasNosDTOList) {
        this.listCasNosDTOList = listCasNosDTOList;
    }
}

package com.excelra.mvc.model.search.Indication;

import com.excelra.mvc.model.indication.ListIcd10CodesDTO;
import com.excelra.mvc.model.indication.ListTherapeuticUseDTO;

import java.io.Serializable;
import java.util.List;

public class IndicationAdvSearchDTO implements Serializable {

    private String option;

    private List<String> fileData;

    private List<ListTherapeuticUseDTO> therapeuticUseDTOList;

    private List<ListIcd10CodesDTO> icd10CodesDTOList;

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    public List<String> getFileData() {
        return fileData;
    }

    public void setFileData(List<String> fileData) {
        this.fileData = fileData;
    }

    public List<ListTherapeuticUseDTO> getTherapeuticUseDTOList() {
        return therapeuticUseDTOList;
    }

    public void setTherapeuticUseDTOList(List<ListTherapeuticUseDTO> therapeuticUseDTOList) {
        this.therapeuticUseDTOList = therapeuticUseDTOList;
    }

    public List<ListIcd10CodesDTO> getIcd10CodesDTOList() {
        return icd10CodesDTOList;
    }

    public void setIcd10CodesDTOList(List<ListIcd10CodesDTO> icd10CodesDTOList) {
        this.icd10CodesDTOList = icd10CodesDTOList;
    }
}

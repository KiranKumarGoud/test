package com.excelra.mvc.model.search;

import com.excelra.mvc.model.TargetAdvSearchInputDTO;
import com.excelra.mvc.model.TargetProteinMasterDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TargetAdvSearchDTO implements Serializable {

    private String option;

    // private List<TargetProteinMasterDTO> targetProteinMasterList;

    private List<TargetAdvSearchInputDTO> targetAdvSearchInputDTOList;

    private List<String> fileData;

    private List<String> targetCategory;

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    public List<TargetAdvSearchInputDTO> getTargetAdvSearchInputDTOList() {
        return targetAdvSearchInputDTOList;
    }

    public void setTargetAdvSearchInputDTOList(List<TargetAdvSearchInputDTO> targetAdvSearchInputDTOList) {
        this.targetAdvSearchInputDTOList = targetAdvSearchInputDTOList;
    }

    /*public List<TargetProteinMasterDTO> getTargetProteinMasterList() {
        return targetProteinMasterList;
    }

    public void setTargetProteinMasterList(List<TargetProteinMasterDTO> targetProteinMasterList) {
        this.targetProteinMasterList = targetProteinMasterList;
    }*/

    public List<String> getFileData() {
        return fileData;
    }

    public void setFileData(List<String> fileData) {
        this.fileData = fileData;
    }

    public List<String> getTargetCategory() {
        return targetCategory;
    }

    public void setTargetCategory(List<String> targetCategory) {
        this.targetCategory = targetCategory;
    }
}

package com.excelra.mvc.model;

import java.io.Serializable;
import java.util.List;

public class AllMappingIdsInputDTO implements Serializable {

    private List<TargetSynonymsDTO> targetSynonymsDTOList;

    private List<OntoAssayTypeDTO> ontoAssayTypeDTOList;

    private String option;

    public List<TargetSynonymsDTO> getTargetSynonymsDTOList() {
        return targetSynonymsDTOList;
    }

    public void setTargetSynonymsDTOList(List<TargetSynonymsDTO> targetSynonymsDTOList) {
        this.targetSynonymsDTOList = targetSynonymsDTOList;
    }

    public List<OntoAssayTypeDTO> getOntoAssayTypeDTOList() {
        return ontoAssayTypeDTOList;
    }

    public void setOntoAssayTypeDTOList(List<OntoAssayTypeDTO> ontoAssayTypeDTOList) {
        this.ontoAssayTypeDTOList = ontoAssayTypeDTOList;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }
}

package com.excelra.mvc.model.source;

import java.io.Serializable;
import java.util.List;

public class ListSearchDTO implements Serializable {

    private String option;

    private List<ListSourceSynonymsDTO> sourceSynonymsDTOList;

    private List<Long> sourceIdList;

    private List<ListSourceTaxIdsDTO> sourceTaxIdsDTOList;

    private List<Long> taxIdList;

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    public List<ListSourceSynonymsDTO> getSourceSynonymsDTOList() {
        return sourceSynonymsDTOList;
    }

    public void setSourceSynonymsDTOList(List<ListSourceSynonymsDTO> sourceSynonymsDTOList) {
        this.sourceSynonymsDTOList = sourceSynonymsDTOList;
    }

    public List<Long> getSourceIdList() {
        return sourceIdList;
    }

    public void setSourceIdList(List<Long> sourceIdList) {
        this.sourceIdList = sourceIdList;
    }

    public List<ListSourceTaxIdsDTO> getSourceTaxIdsDTOList() {
        return sourceTaxIdsDTOList;
    }

    public void setSourceTaxIdsDTOList(List<ListSourceTaxIdsDTO> sourceTaxIdsDTOList) {
        this.sourceTaxIdsDTOList = sourceTaxIdsDTOList;
    }

    public List<Long> getTaxIdList() {
        return taxIdList;
    }

    public void setTaxIdList(List<Long> taxIdList) {
        this.taxIdList = taxIdList;
    }
}

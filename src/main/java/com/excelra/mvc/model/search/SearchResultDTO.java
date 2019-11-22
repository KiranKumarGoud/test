package com.excelra.mvc.model.search;

import com.excelra.mvc.model.AllMappingFinalResultDTO;

import java.io.Serializable;

/**
 * All Mapping Search Result DTO
 *
 * @author Venkat Salagrama
 */
public class SearchResultDTO implements Serializable {

    private AllMappingFinalResultCountDTO counts;

    private AllMappingFinalResultDTO data;

    public AllMappingFinalResultCountDTO getCounts() {
        return counts;
    }

    public void setCounts(AllMappingFinalResultCountDTO counts) {
        this.counts = counts;
    }

    public AllMappingFinalResultDTO getData() {
        return data;
    }

    public void setData(AllMappingFinalResultDTO data) {
        this.data = data;
    }
}

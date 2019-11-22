package com.excelra.mvc.model.tabularview;

import java.io.Serializable;
import java.util.List;

public class ClinicalStatusTabPageData implements Serializable {

    private List<ClinicalStatusTabDTO> clinicalStatusTabDTOList;

    private Long count;

    public List<ClinicalStatusTabDTO> getClinicalStatusTabDTOList() {
        return clinicalStatusTabDTOList;
    }

    public void setClinicalStatusTabDTOList(List<ClinicalStatusTabDTO> clinicalStatusTabDTOList) {
        this.clinicalStatusTabDTOList = clinicalStatusTabDTOList;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}

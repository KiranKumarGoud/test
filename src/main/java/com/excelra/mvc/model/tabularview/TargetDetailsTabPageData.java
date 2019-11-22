package com.excelra.mvc.model.tabularview;

import java.io.Serializable;
import java.util.List;

/**
 * Target Details tab complete data with records count
 *
 * @author Venkat Salagrama
 */
public class TargetDetailsTabPageData implements Serializable {

    private List<TargetDetailsTabDTO> targetDetailsTabDTOList;

    private Long count;

    public List<TargetDetailsTabDTO> getTargetDetailsTabDTOList() {
        return targetDetailsTabDTOList;
    }

    public void setTargetDetailsTabDTOList(List<TargetDetailsTabDTO> targetDetailsTabDTOList) {
        this.targetDetailsTabDTOList = targetDetailsTabDTOList;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}

package com.excelra.mvc.model.tabularview;

import java.io.Serializable;
import java.util.List;

/**
 * Protein Classification tab complete data with records count
 *
 * @author Venkat Salagrama
 */
public class ProteinClassificationTabPageData implements Serializable {

    private List<ProteinClassificationTabDTO> proteinClassificationTabDTOList;

    private Long count;

    public List<ProteinClassificationTabDTO> getProteinClassificationTabDTOList() {
        return proteinClassificationTabDTOList;
    }

    public void setProteinClassificationTabDTOList(List<ProteinClassificationTabDTO> proteinClassificationTabDTOList) {
        this.proteinClassificationTabDTOList = proteinClassificationTabDTOList;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}

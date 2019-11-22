package com.excelra.mvc.model.tabularview;

import java.io.Serializable;
import java.util.List;

/**
 * Reference tab complete data with records count
 *
 * @author Venkat Salagrama
 */
public class ReferenceTabPageData implements Serializable {

    private List<ReferenceTabDTO> referenceTabDTOList;

    private Long count;

    public List<ReferenceTabDTO> getReferenceTabDTOList() {
        return referenceTabDTOList;
    }

    public void setReferenceTabDTOList(List<ReferenceTabDTO> referenceTabDTOList) {
        this.referenceTabDTOList = referenceTabDTOList;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}

package com.excelra.mvc.model.tabularview;

import java.io.Serializable;
import java.util.List;

/***
 * Assay tab complete data with records count
 *
 * @author venkateswarlu.s
 */
public class AssayTabPageData implements Serializable {

    private List<AssayTabDTO> assayTabDTOList;

    private Long count;

    public List<AssayTabDTO> getAssayTabDTOList() {
        return assayTabDTOList;
    }

    public void setAssayTabDTOList(List<AssayTabDTO> assayTabDTOList) {
        this.assayTabDTOList = assayTabDTOList;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}

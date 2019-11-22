package com.excelra.mvc.model.tabularview;

import java.io.Serializable;
import java.util.List;

/**
 * Structure Details tab complete data with records count
 *
 * @author Venkat Salagrama
 */
public class StructureDetailsTabPageData implements Serializable {

    private List<StructureDetailsTabDTO> structureDetailsTabDTOList;

    private Long count;

    public List<StructureDetailsTabDTO> getStructureDetailsTabDTOList() {
        return structureDetailsTabDTOList;
    }

    public void setStructureDetailsTabDTOList(List<StructureDetailsTabDTO> structureDetailsTabDTOList) {
        this.structureDetailsTabDTOList = structureDetailsTabDTOList;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}

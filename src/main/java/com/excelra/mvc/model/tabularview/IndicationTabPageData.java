package com.excelra.mvc.model.tabularview;

import java.io.Serializable;
import java.util.List;

/**
 * Indication tab complete data with records count
 *
 * @author Venkat Salagrama
 */
public class IndicationTabPageData implements Serializable {

    private List<IndicationTabDTO> indicationTabDTOList;

    private Long count;

    public List<IndicationTabDTO> getIndicationTabDTOList() {
        return indicationTabDTOList;
    }

    public void setIndicationTabDTOList(List<IndicationTabDTO> indicationTabDTOList) {
        this.indicationTabDTOList = indicationTabDTOList;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}

package com.excelra.mvc.model.tabularview;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author venkateswarlu.s
 */
public class Scaffold2TabPageData implements Serializable {

    private List<Scaffold2TabDTO> scaffold2TabDTOList;

    private Long count;

    public List<Scaffold2TabDTO> getScaffold2TabDTOList() {
        return scaffold2TabDTOList;
    }

    public void setScaffold2TabDTOList(List<Scaffold2TabDTO> scaffold2TabDTOList) {
        this.scaffold2TabDTOList = scaffold2TabDTOList;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}

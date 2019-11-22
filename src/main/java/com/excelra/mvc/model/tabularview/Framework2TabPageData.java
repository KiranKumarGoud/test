package com.excelra.mvc.model.tabularview;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author venkateswarlu.s
 */
public class Framework2TabPageData implements Serializable {

    private List<Framework2TabDTO> framework2TabDTOList;

    private Long count;

    public List<Framework2TabDTO> getFramework2TabDTOList() {
        return framework2TabDTOList;
    }

    public void setFramework2TabDTOList(List<Framework2TabDTO> framework2TabDTOList) {
        this.framework2TabDTOList = framework2TabDTOList;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}

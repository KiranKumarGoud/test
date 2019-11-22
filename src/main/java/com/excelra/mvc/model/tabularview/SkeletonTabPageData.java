package com.excelra.mvc.model.tabularview;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author venkateswarlu.s
 */
public class SkeletonTabPageData implements Serializable {

    private List<SkeletonTabDTO> skeletonTabDTOList;

    private Long count;

    public List<SkeletonTabDTO> getSkeletonTabDTOList() {
        return skeletonTabDTOList;
    }

    public void setSkeletonTabDTOList(List<SkeletonTabDTO> skeletonTabDTOList) {
        this.skeletonTabDTOList = skeletonTabDTOList;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}

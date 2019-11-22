package com.excelra.mvc.model.tabularview;

import java.io.Serializable;

/**
 *
 * @author venkateswarlu.s
 */
public class SkeletonTabDTO implements Serializable {

    private Long skeletonId;

    private String smiles;

    private Long gvkIdCnt;

    private Long strIdCnt;

    public Long getSkeletonId() {
        return skeletonId;
    }

    public void setSkeletonId(Long skeletonId) {
        this.skeletonId = skeletonId;
    }

    public String getSmiles() {
        return smiles;
    }

    public void setSmiles(String smiles) {
        this.smiles = smiles;
    }

    public Long getGvkIdCnt() {
        return gvkIdCnt;
    }

    public void setGvkIdCnt(Long gvkIdCnt) {
        this.gvkIdCnt = gvkIdCnt;
    }

    public Long getStrIdCnt() {
        return strIdCnt;
    }

    public void setStrIdCnt(Long strIdCnt) {
        this.strIdCnt = strIdCnt;
    }
}


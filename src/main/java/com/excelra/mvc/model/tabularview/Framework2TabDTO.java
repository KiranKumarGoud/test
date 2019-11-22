package com.excelra.mvc.model.tabularview;

import java.io.Serializable;

/**
 *
 * @author venkateswarlu.s
 */
public class Framework2TabDTO implements Serializable {

    private Long framework2Id;

    private String smiles;

    private Long gvkIdCnt;

    private Long strIdCnt;

    public Long getFramework2Id() {
        return framework2Id;
    }

    public void setFramework2Id(Long framework2Id) {
        this.framework2Id = framework2Id;
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

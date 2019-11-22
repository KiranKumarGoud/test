package com.excelra.mvc.model.tabularview;

import java.io.Serializable;

/**
 *
 * @author venkateswarlu.s
 */
public class Scaffold2TabDTO implements Serializable {

    private Long scaffold2Id;

    private String smiles;

    private Long gvkIdCnt;

    private Long strIdCnt;

    public Long getScaffold2Id() {
        return scaffold2Id;
    }

    public void setScaffold2Id(Long scaffold2Id) {
        this.scaffold2Id = scaffold2Id;
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

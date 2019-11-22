package com.excelra.mvc.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;

public class PdbIdsDTO implements Serializable {

    private String pdbId;

    private BigDecimal stdnameId;

    private String commonName;

    public String getPdbId() {
        return pdbId;
    }

    public void setPdbId(String pdbId) {
        this.pdbId = pdbId;
    }

    public BigDecimal getStdnameId() {
        return stdnameId;
    }

    public void setStdnameId(BigDecimal stdnameId) {
        this.stdnameId = stdnameId;
    }

    public String getCommonName() {
        return commonName;
    }

    public void setCommonName(String commonName) {
        this.commonName = commonName;
    }
}

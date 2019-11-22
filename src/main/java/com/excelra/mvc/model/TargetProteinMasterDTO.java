package com.excelra.mvc.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class TargetProteinMasterDTO implements Serializable {

    private Integer targetId;

    private String source;

    private BigDecimal stdNameId;

    private String standardName;

    private String commonName;

    private Integer locusId;

    private String multipleLoci;

    private String officialName;

    private String uniprotId;

    private String sciSourceName;

    private String pdbId;

    private String flag;

    private String flagNew;

    private String label;

    private String value;

    private String operator;

    public Integer getTargetId() {
        return targetId;
    }

    public void setTargetId(Integer targetId) {
        this.targetId = targetId;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getStandardName() {
        return standardName;
    }

    public void setStandardName(String standardName) {
        this.standardName = standardName;
    }

    public String getCommonName() {
        return commonName;
    }

    public void setCommonName(String commonName) {
        this.commonName = commonName;
    }

    public Integer getLocusId() {
        return locusId;
    }

    public void setLocusId(Integer locusId) {
        this.locusId = locusId;
    }

    public String getMultipleLoci() {
        return multipleLoci;
    }

    public void setMultipleLoci(String multipleLoci) {
        this.multipleLoci = multipleLoci;
    }

    public String getOfficialName() {
        return officialName;
    }

    public void setOfficialName(String officialName) {
        this.officialName = officialName;
    }

    public String getUniprotId() {
        return uniprotId;
    }

    public void setUniprotId(String uniprotId) {
        this.uniprotId = uniprotId;
    }

    public String getSciSourceName() {
        return sciSourceName;
    }

    public void setSciSourceName(String sciSourceName) {
        this.sciSourceName = sciSourceName;
    }

    public String getPdbId() {
        return pdbId;
    }

    public void setPdbId(String pdbId) {
        this.pdbId = pdbId;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getFlagNew() {
        return flagNew;
    }

    public void setFlagNew(String flagNew) {
        this.flagNew = flagNew;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public BigDecimal getStdNameId() {
        return stdNameId;
    }

    public void setStdNameId(BigDecimal stdNameId) {
        this.stdNameId = stdNameId;
    }
}

package com.excelra.mvc.model.tabularview;

import java.io.Serializable;

/**
 * Target Details Tab DTO Class
 *
 * @author Venkat Salagrama
 */
public class TargetDetailsTabDTO implements Serializable {

    private Long targetId;

    private String sourceName;

    private Long stdnameId;

    private String commonName;

    private Long locusId;

    private String multipleLoci;

    private String locusRef;

    private String officialName;

    private String uniprotId;

    private String sciSourceName;

    private String pdbId;

    private String standardName;

    private String subSubFamily;

    private String subFamily;

    private String superFamily;

    private String alias;

    public Long getTargetId() {
        return targetId;
    }

    public void setTargetId(Long targetId) {
        this.targetId = targetId;
    }

    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

    public Long getStdnameId() {
        return stdnameId;
    }

    public void setStdnameId(Long stdnameId) {
        this.stdnameId = stdnameId;
    }

    public String getCommonName() {
        return commonName;
    }

    public void setCommonName(String commonName) {
        this.commonName = commonName;
    }

    public Long getLocusId() {
        return locusId;
    }

    public void setLocusId(Long locusId) {
        this.locusId = locusId;
    }

    public String getMultipleLoci() {
        return multipleLoci;
    }

    public void setMultipleLoci(String multipleLoci) {
        this.multipleLoci = multipleLoci;
    }

    public String getLocusRef() {
        return locusRef;
    }

    public void setLocusRef(String locusRef) {
        this.locusRef = locusRef;
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

    public String getStandardName() {
        return standardName;
    }

    public void setStandardName(String standardName) {
        this.standardName = standardName;
    }

    public String getSubSubFamily() {
        return subSubFamily;
    }

    public void setSubSubFamily(String subSubFamily) {
        this.subSubFamily = subSubFamily;
    }

    public String getSubFamily() {
        return subFamily;
    }

    public void setSubFamily(String subFamily) {
        this.subFamily = subFamily;
    }

    public String getSuperFamily() {
        return superFamily;
    }

    public void setSuperFamily(String superFamily) {
        this.superFamily = superFamily;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }
}

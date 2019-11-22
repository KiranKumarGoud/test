package com.excelra.mvc.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name="target_protein_master")
public class TargetProteinMaster implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "target_id")
    private Integer targetId;

    @Column(name = "source")
    private String source;

    @Column(name = "stdname_id")
    private BigDecimal stdNameId;

    @Column(name = "standard_name")
    private String standardName;

    @Column(name = "common_name")
    private String commonName;

    @Column(name = "locus_id")
    private Integer locusId;

    @Column(name = "multipleloci")
    private String multipleLoci;

    @Column(name = "official_name")
    private String officialName;

    @Column(name = "uniprot_id")
    private String uniprotId;

    @Column(name = "sci_source_name")
    private String sciSourceName;

    @Column(name = "pdb_id")
    private String pdbId;

    @Column(name = "flag")
    private String flag;

    @Column(name = "flag_new")
    private String flagNew;

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

    public BigDecimal getStdNameId() {
        return stdNameId;
    }

    public void setStdNameId(BigDecimal stdNameId) {
        this.stdNameId = stdNameId;
    }
}


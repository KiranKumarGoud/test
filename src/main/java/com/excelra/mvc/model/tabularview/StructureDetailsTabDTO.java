package com.excelra.mvc.model.tabularview;

import java.io.Serializable;
import java.math.BigDecimal;

public class StructureDetailsTabDTO implements Serializable {

    private Long gvkId;

    private String compoundNames;

    private String subSmiles;

    private String inchiKey;

    private String originator;

    private String derivatives;

    private String bioAssay;

    private String primaryTargets;

    private String mechanisms;

    private String compoundStatus;

    private String molFormula;

    private Double  molWeight;

    public Long getGvkId() {
        return gvkId;
    }

    public void setGvkId(Long gvkId) {
        this.gvkId = gvkId;
    }

    public String getCompoundNames() {
        return compoundNames;
    }

    public void setCompoundNames(String compoundNames) {
        this.compoundNames = compoundNames;
    }

    public String getSubSmiles() {
        return subSmiles;
    }

    public void setSubSmiles(String subSmiles) {
        this.subSmiles = subSmiles;
    }

    public String getInchiKey() {
        return inchiKey;
    }

    public void setInchiKey(String inchiKey) {
        this.inchiKey = inchiKey;
    }

    public String getOriginator() {
        return originator;
    }

    public void setOriginator(String originator) {
        this.originator = originator;
    }

    public String getDerivatives() {
        return derivatives;
    }

    public void setDerivatives(String derivatives) {
        this.derivatives = derivatives;
    }

    public String getBioAssay() {
        return bioAssay;
    }

    public void setBioAssay(String bioAssay) {
        this.bioAssay = bioAssay;
    }

    public String getPrimaryTargets() {
        return primaryTargets;
    }

    public void setPrimaryTargets(String primaryTargets) {
        this.primaryTargets = primaryTargets;
    }

    public String getMechanisms() {
        return mechanisms;
    }

    public void setMechanisms(String mechanisms) {
        this.mechanisms = mechanisms;
    }

    public String getCompoundStatus() {
        return compoundStatus;
    }

    public void setCompoundStatus(String compoundStatus) {
        this.compoundStatus = compoundStatus;
    }

    public String getMolFormula() {
        return molFormula;
    }

    public void setMolFormula(String molFormula) {
        this.molFormula = molFormula;
    }

    public Double getMolWeight() {
        return molWeight;
    }

    public void setMolWeight(Double molWeight) {
        this.molWeight = molWeight;
    }
}

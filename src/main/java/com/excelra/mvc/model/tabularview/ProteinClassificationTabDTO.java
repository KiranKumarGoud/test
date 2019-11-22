package com.excelra.mvc.model.tabularview;

import java.io.Serializable;

/**
 * Protein Classification Tab DTO Class
 *
 * @author Venkat Salagrama
 */
public class ProteinClassificationTabDTO implements Serializable {

    private Long stdnameId;

    private String standardName;

    private String commonName;

    private String family;

    private String subFamily;

    private String subSubFamily;

    private String superFamily;

    private String alias;

    private String flag;

    private Long targetOntologyId;

    public Long getStdnameId() {
        return stdnameId;
    }

    public void setStdnameId(Long stdnameId) {
        this.stdnameId = stdnameId;
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

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public String getSubFamily() {
        return subFamily;
    }

    public void setSubFamily(String subFamily) {
        this.subFamily = subFamily;
    }

    public String getSubSubFamily() {
        return subSubFamily;
    }

    public void setSubSubFamily(String subSubFamily) {
        this.subSubFamily = subSubFamily;
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

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public Long getTargetOntologyId() {
        return targetOntologyId;
    }

    public void setTargetOntologyId(Long targetOntologyId) {
        this.targetOntologyId = targetOntologyId;
    }

}

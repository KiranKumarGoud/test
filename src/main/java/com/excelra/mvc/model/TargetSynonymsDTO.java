package com.excelra.mvc.model;

import java.io.Serializable;

public class TargetSynonymsDTO  implements Serializable {

    private String synonyms;

    private String commonName;

    private String label;

    private String value;

    private String operator;

    private Integer stdnameId;

    public String getSynonyms() {
        return synonyms;
    }

    public void setSynonyms(String synonyms) {
        this.synonyms = synonyms;
    }

    public String getCommonName() {
        return commonName;
    }

    public void setCommonName(String commonName) {
        this.commonName = commonName;
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

    public Integer getStdnameId() {
        return stdnameId;
    }

    public void setStdnameId(Integer stdnameId) {
        this.stdnameId = stdnameId;
    }
}

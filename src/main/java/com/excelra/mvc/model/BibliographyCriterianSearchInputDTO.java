package com.excelra.mvc.model;

import java.io.Serializable;

/***
 * <p>
 * </p>
 * @author priyanka.veidhey
 */
public class BibliographyCriterianSearchInputDTO implements Serializable {

    private String author;

    private String companyName;

    private String refType;

    private String label;

    private String value;

    private String operator;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getRefType() {
        return refType;
    }

    public void setRefType(String refType) {
        this.refType = refType;
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
}

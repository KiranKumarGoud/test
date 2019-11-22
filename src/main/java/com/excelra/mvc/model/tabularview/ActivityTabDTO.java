package com.excelra.mvc.model.tabularview;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * ActivityTab DTO
 *
 * @author Venkat Salagrama
 */
public class ActivityTabDTO implements Serializable {

    private Long actId;

    private String activityType;

    private String stdActivityType;

    private String activityUom;

    private String standardUom;

    private String activityPrefix;

    private String stdActPrefix;

    private double activityValue;

    private BigDecimal sd;

    private String activityRemarks;

    private BigDecimal  microMolarvalue;

    private String assayType;

    private String enzymeCellAssay;

    private String commonName;

    private String activityMechanism;

    private String source;

    private String cellsCelllineOrgan;

    private String measured;

    private String roa;

    private String assayMethodName;

    private String dose;

    private String doseUom;

    private String dosePrefix;

    private String activity;

    private String parameter;

    private String reference;

    public Long getActId() {
        return actId;
    }

    public void setActId(Long actId) {
        this.actId = actId;
    }

    public String getActivityType() {
        return activityType;
    }

    public void setActivityType(String activityType) {
        this.activityType = activityType;
    }

    public String getStdActivityType() {
        return stdActivityType;
    }

    public void setStdActivityType(String stdActivityType) {
        this.stdActivityType = stdActivityType;
    }

    public String getActivityUom() {
        return activityUom;
    }

    public void setActivityUom(String activityUom) {
        this.activityUom = activityUom;
    }

    public String getStandardUom() {
        return standardUom;
    }

    public void setStandardUom(String standardUom) {
        this.standardUom = standardUom;
    }

    public String getActivityPrefix() {
        return activityPrefix;
    }

    public void setActivityPrefix(String activityPrefix) {
        this.activityPrefix = activityPrefix;
    }

    public String getStdActPrefix() {
        return stdActPrefix;
    }

    public void setStdActPrefix(String stdActPrefix) {
        this.stdActPrefix = stdActPrefix;
    }

    public String getActivityRemarks() {
        return activityRemarks;
    }

    public void setActivityRemarks(String activityRemarks) {
        this.activityRemarks = activityRemarks;
    }

    public String getAssayType() {
        return assayType;
    }

    public void setAssayType(String assayType) {
        this.assayType = assayType;
    }

    public String getEnzymeCellAssay() {
        return enzymeCellAssay;
    }

    public void setEnzymeCellAssay(String enzymeCellAssay) {
        this.enzymeCellAssay = enzymeCellAssay;
    }

    public String getCommonName() {
        return commonName;
    }

    public void setCommonName(String commonName) {
        this.commonName = commonName;
    }

    public String getActivityMechanism() {
        return activityMechanism;
    }

    public void setActivityMechanism(String activityMechanism) {
        this.activityMechanism = activityMechanism;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getCellsCelllineOrgan() {
        return cellsCelllineOrgan;
    }

    public void setCellsCelllineOrgan(String cellsCelllineOrgan) {
        this.cellsCelllineOrgan = cellsCelllineOrgan;
    }

    public String getMeasured() {
        return measured;
    }

    public void setMeasured(String measured) {
        this.measured = measured;
    }

    public String getRoa() {
        return roa;
    }

    public void setRoa(String roa) {
        this.roa = roa;
    }

    public String getAssayMethodName() {
        return assayMethodName;
    }

    public void setAssayMethodName(String assayMethodName) {
        this.assayMethodName = assayMethodName;
    }

    public String getDose() {
        return dose;
    }

    public void setDose(String dose) {
        this.dose = dose;
    }

    public String getDoseUom() {
        return doseUom;
    }

    public void setDoseUom(String doseUom) {
        this.doseUom = doseUom;
    }

    public String getDosePrefix() {
        return dosePrefix;
    }

    public void setDosePrefix(String dosePrefix) {
        this.dosePrefix = dosePrefix;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public double getActivityValue() {
        return activityValue;
    }

    public void setActivityValue(double activityValue) {
        this.activityValue = activityValue;
    }

    public BigDecimal getSd() {
        return sd;
    }

    public void setSd(BigDecimal sd) {
        this.sd = sd;
    }

    public BigDecimal getMicroMolarvalue() {
        return microMolarvalue;
    }

    public void setMicroMolarvalue(BigDecimal microMolarvalue) {
        this.microMolarvalue = microMolarvalue;
    }
}

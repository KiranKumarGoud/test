package com.excelra.mvc.model.tabularview;

import java.io.Serializable;

/**
 * Clinical Status Tab DTO Class
 *
 * @author Venkat Salagrama
 */
public class ClinicalStatusTabDTO implements Serializable {

    private Long gvkId;

    private Long refId;

    private String treatment;

    private String population;

    private String studyDesign;

    private String results;

    private String conclusions;

    public Long getGvkId() {
        return gvkId;
    }

    public void setGvkId(Long gvkId) {
        this.gvkId = gvkId;
    }

    public Long getRefId() {
        return refId;
    }

    public void setRefId(Long refId) {
        this.refId = refId;
    }

    public String getTreatment() {
        return treatment;
    }

    public void setTreatment(String treatment) {
        this.treatment = treatment;
    }

    public String getPopulation() {
        return population;
    }

    public void setPopulation(String population) {
        this.population = population;
    }

    public String getStudyDesign() {
        return studyDesign;
    }

    public void setStudyDesign(String studyDesign) {
        this.studyDesign = studyDesign;
    }

    public String getResults() {
        return results;
    }

    public void setResults(String results) {
        this.results = results;
    }

    public String getConclusions() {
        return conclusions;
    }

    public void setConclusions(String conclusions) {
        this.conclusions = conclusions;
    }
}


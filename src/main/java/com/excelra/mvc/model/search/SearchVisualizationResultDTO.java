package com.excelra.mvc.model.search;

import java.io.Serializable;
import java.util.List;

public class SearchVisualizationResultDTO implements Serializable {

    private List<VisualizationResultDTO> proteinClassification;

    private List<VisualizationResultDTO> yearWiseVisualization;

    private List<VisualizationResultDTO> bibliographyVisualization;

    private List<VisualizationIndicationDTO> indicationVisualization;

    private List<VisualizationTaxonomyEndPointToxicityDTO> toxicityVisualization;

    private List<VisualizationTaxonomyEndPointToxicityDTO> taxonomyVisualization;

    private List<VisualizationTaxonomyEndPointToxicityDTO> endpointVisualization;


    public List<VisualizationTaxonomyEndPointToxicityDTO> getEndpointVisualization() {
        return endpointVisualization;
    }

    public void setEndpointVisualization(List<VisualizationTaxonomyEndPointToxicityDTO> endpointVisualization) {
        this.endpointVisualization = endpointVisualization;
    }

    public List<VisualizationTaxonomyEndPointToxicityDTO> getTaxonomyVisualization() {
        return taxonomyVisualization;
    }

    public void setTaxonomyVisualization(List<VisualizationTaxonomyEndPointToxicityDTO> taxonomyVisualization) {
        this.taxonomyVisualization = taxonomyVisualization;
    }

    public void setToxicityVisualization(List<VisualizationTaxonomyEndPointToxicityDTO> toxicityVisualization) {
        this.toxicityVisualization = toxicityVisualization;
    }

    public List<VisualizationTaxonomyEndPointToxicityDTO> getToxicityVisualization() {
        return toxicityVisualization;
    }

    public List<VisualizationResultDTO> getProteinClassification() {
        return proteinClassification;
    }

    public void setProteinClassification(List<VisualizationResultDTO> proteinClassification) {
        this.proteinClassification = proteinClassification;
    }

    public List<VisualizationResultDTO> getYearWiseVisualization() {
        return yearWiseVisualization;
    }

    public void setYearWiseVisualization(List<VisualizationResultDTO> yearWiseVisualization) {
        this.yearWiseVisualization = yearWiseVisualization;
    }

    public List<VisualizationResultDTO> getBibliographyVisualization() {
        return bibliographyVisualization;
    }

    public void setBibliographyVisualization(List<VisualizationResultDTO> bibliographyVisualization) {
        this.bibliographyVisualization = bibliographyVisualization;
    }

    public List<VisualizationIndicationDTO> getIndicationVisualization() {
        return indicationVisualization;
    }

    public void setIndicationVisualization(List<VisualizationIndicationDTO> indicationVisualization) {
        this.indicationVisualization = indicationVisualization;
    }
}

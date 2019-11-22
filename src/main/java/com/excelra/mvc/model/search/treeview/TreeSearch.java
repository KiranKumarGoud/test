package com.excelra.mvc.model.search.treeview;

import java.io.Serializable;

public class TreeSearch implements Serializable {

    private TargetTreeviewDTO targetTreeviewDTO;

    private IndicationTreeviewDTO indicationTreeviewDTO;

    private AdmeTreeviewDTO admeTreeviewDTO;

    private ToxicityTreeviewDTO toxicityTreeviewDTO;

    private TaxonomyTreeviewDTO taxonomyTreeviewDTO;

    private EndpointTreeviewDTO endpointTreeviewDTO;

    public TargetTreeviewDTO getTargetTreeviewDTO() {
        return targetTreeviewDTO;
    }

    public void setTargetTreeviewDTO(TargetTreeviewDTO targetTreeviewDTO) {
        this.targetTreeviewDTO = targetTreeviewDTO;
    }

    public IndicationTreeviewDTO getIndicationTreeviewDTO() {
        return indicationTreeviewDTO;
    }

    public void setIndicationTreeviewDTO(IndicationTreeviewDTO indicationTreeviewDTO) {
        this.indicationTreeviewDTO = indicationTreeviewDTO;
    }

    public AdmeTreeviewDTO getAdmeTreeviewDTO() {
        return admeTreeviewDTO;
    }

    public void setAdmeTreeviewDTO(AdmeTreeviewDTO admeTreeviewDTO) {
        this.admeTreeviewDTO = admeTreeviewDTO;
    }

    public ToxicityTreeviewDTO getToxicityTreeviewDTO() {
        return toxicityTreeviewDTO;
    }

    public void setToxicityTreeviewDTO(ToxicityTreeviewDTO toxicityTreeviewDTO) {
        this.toxicityTreeviewDTO = toxicityTreeviewDTO;
    }

    public TaxonomyTreeviewDTO getTaxonomyTreeviewDTO() {
        return taxonomyTreeviewDTO;
    }

    public void setTaxonomyTreeviewDTO(TaxonomyTreeviewDTO taxonomyTreeviewDTO) {
        this.taxonomyTreeviewDTO = taxonomyTreeviewDTO;
    }

    public EndpointTreeviewDTO getEndpointTreeviewDTO() {
        return endpointTreeviewDTO;
    }

    public void setEndpointTreeviewDTO(EndpointTreeviewDTO endpointTreeviewDTO) {
        this.endpointTreeviewDTO = endpointTreeviewDTO;
    }
}

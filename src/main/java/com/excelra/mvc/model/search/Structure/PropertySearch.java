package com.excelra.mvc.model.search.Structure;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class PropertySearch implements Serializable {

    private Map<String, PropertyValues> structuralProperties;

    private Map<String, PropertyValues> physicoChemicalProperties;

    public Map<String, PropertyValues> getStructuralProperties() {
        return structuralProperties;
    }

    public void setStructuralProperties(Map<String, PropertyValues> structuralProperties) {
        this.structuralProperties = structuralProperties;
    }

    public Map<String, PropertyValues> getPhysicoChemicalProperties() {
        return physicoChemicalProperties;
    }

    public void setPhysicoChemicalProperties(Map<String, PropertyValues> physicoChemicalProperties) {
        this.physicoChemicalProperties = physicoChemicalProperties;
    }
}

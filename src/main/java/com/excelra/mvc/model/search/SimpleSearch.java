package com.excelra.mvc.model.search;

import com.excelra.mvc.model.*;
import com.excelra.mvc.model.indication.ListTherapeuticUseDTO;
import com.excelra.mvc.model.source.ListSourceSynonymsDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SimpleSearch implements Serializable {

    private String option;

    private List<TargetSynonymsDTO> targetSynonymsDTOList;

    private List<OntoAssayTypeDTO> ontoAssayTypeDTOList;

    private List<CompoundSynonymsDTO> compoundSynonymsDTOList;

    private List<ReferenceMasterDTO> bibliographyReferenceMasterDToList;

    private List<ListActivityMechanismDTO> activityMechanismDTOList;

    private List<ListActivityTypesDTO> activityTypesDTOList;

    private List<ListTherapeuticUseDTO> therapeuticUseDTOList;

    private List<ListCompoundCategoriesDTO> compoundCategoriesDTOList;

    private List<ListClinicalPhasesDTO> clinicalPhasesDTOList;

    private List<ListSourceSynonymsDTO> sourceSynonymsDTOList;

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    public List<TargetSynonymsDTO> getTargetSynonymsDTOList() {
        return targetSynonymsDTOList;
    }

    public void setTargetSynonymsDTOList(List<TargetSynonymsDTO> targetSynonymsDTOList) {
        this.targetSynonymsDTOList = targetSynonymsDTOList;
    }

    public List<OntoAssayTypeDTO> getOntoAssayTypeDTOList() {
        return ontoAssayTypeDTOList;
    }

    public void setOntoAssayTypeDTOList(List<OntoAssayTypeDTO> ontoAssayTypeDTOList) {
        this.ontoAssayTypeDTOList = ontoAssayTypeDTOList;
    }

    public List<CompoundSynonymsDTO> getCompoundSynonymsDTOList() {
        return compoundSynonymsDTOList;
    }

    public void setCompoundSynonymsDTOList(List<CompoundSynonymsDTO> compoundSynonymsDTOList) {
        this.compoundSynonymsDTOList = compoundSynonymsDTOList;
    }

    public List<ReferenceMasterDTO> getBibliographyReferenceMasterDToList() {
        return bibliographyReferenceMasterDToList;
    }

    public void setBibliographyReferenceMasterDToList(List<ReferenceMasterDTO> bibliographyReferenceMasterDToList) {
        this.bibliographyReferenceMasterDToList = bibliographyReferenceMasterDToList;
    }

    public List<ListActivityMechanismDTO> getActivityMechanismDTOList() {
        return activityMechanismDTOList;
    }

    public void setActivityMechanismDTOList(List<ListActivityMechanismDTO> activityMechanismDTOList) {
        this.activityMechanismDTOList = activityMechanismDTOList;
    }

    public List<ListActivityTypesDTO> getActivityTypesDTOList() {
        return activityTypesDTOList;
    }

    public void setActivityTypesDTOList(List<ListActivityTypesDTO> activityTypesDTOList) {
        this.activityTypesDTOList = activityTypesDTOList;
    }

    public List<ListTherapeuticUseDTO> getTherapeuticUseDTOList() {
        return therapeuticUseDTOList;
    }

    public void setTherapeuticUseDTOList(List<ListTherapeuticUseDTO> therapeuticUseDTOList) {
        this.therapeuticUseDTOList = therapeuticUseDTOList;
    }

    public List<ListCompoundCategoriesDTO> getCompoundCategoriesDTOList() {
        return compoundCategoriesDTOList;
    }

    public void setCompoundCategoriesDTOList(List<ListCompoundCategoriesDTO> compoundCategoriesDTOList) {
        this.compoundCategoriesDTOList = compoundCategoriesDTOList;
    }

    public List<ListClinicalPhasesDTO> getClinicalPhasesDTOList() {
        return clinicalPhasesDTOList;
    }

    public void setClinicalPhasesDTOList(List<ListClinicalPhasesDTO> clinicalPhasesDTOList) {
        this.clinicalPhasesDTOList = clinicalPhasesDTOList;
    }

    public List<ListSourceSynonymsDTO> getSourceSynonymsDTOList() {
        return sourceSynonymsDTOList;
    }

    public void setSourceSynonymsDTOList(List<ListSourceSynonymsDTO> sourceSynonymsDTOList) {
        this.sourceSynonymsDTOList = sourceSynonymsDTOList;
    }
}

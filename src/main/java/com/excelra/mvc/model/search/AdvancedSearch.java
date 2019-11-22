package com.excelra.mvc.model.search;

import com.excelra.mvc.model.search.ActivityType.ActivityTypeAdvSearchDTO;
import com.excelra.mvc.model.search.Bibliography.BibliographyAdvSearchDTO;
import com.excelra.mvc.model.search.Indication.IndicationAdvSearchDTO;
import com.excelra.mvc.model.search.source.SourceAdvSearchDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AdvancedSearch implements Serializable {

    private TargetAdvSearchDTO targetAdvSearchDTO;

    private StructureAdvSearchDTO structureAdvSearchDTO;

    private BibliographyAdvSearchDTO bibliographyAdvSearchDTO;

    private ActivityTypeAdvSearchDTO activityTypeAdvSearchDTO;

    private IndicationAdvSearchDTO indicationAdvSearchDTO;

    private SourceAdvSearchDTO sourceAdvSearchDTO;

    public TargetAdvSearchDTO getTargetAdvSearchDTO() {
        return targetAdvSearchDTO;
    }

    public void setTargetAdvSearchDTO(TargetAdvSearchDTO targetAdvSearchDTO) {
        this.targetAdvSearchDTO = targetAdvSearchDTO;
    }

    public StructureAdvSearchDTO getStructureAdvSearchDTO() {
        return structureAdvSearchDTO;
    }

    public void setStructureAdvSearchDTO(StructureAdvSearchDTO structureAdvSearchDTO) {
        this.structureAdvSearchDTO = structureAdvSearchDTO;
    }

    public BibliographyAdvSearchDTO getBibliographyAdvSearchDTO() {
        return bibliographyAdvSearchDTO;
    }

    public void setBibliographyAdvSearchDTO(BibliographyAdvSearchDTO bibliographyAdvSearchDTO) {
        this.bibliographyAdvSearchDTO = bibliographyAdvSearchDTO;
    }

    public ActivityTypeAdvSearchDTO getActivityTypeAdvSearchDTO() {
        return activityTypeAdvSearchDTO;
    }

    public void setActivityTypeAdvSearchDTO(ActivityTypeAdvSearchDTO activityTypeAdvSearchDTO) {
        this.activityTypeAdvSearchDTO = activityTypeAdvSearchDTO;
    }

    public IndicationAdvSearchDTO getIndicationAdvSearchDTO() {
        return indicationAdvSearchDTO;
    }

    public void setIndicationAdvSearchDTO(IndicationAdvSearchDTO indicationAdvSearchDTO) {
        this.indicationAdvSearchDTO = indicationAdvSearchDTO;
    }

    public SourceAdvSearchDTO getSourceAdvSearchDTO() {
        return sourceAdvSearchDTO;
    }

    public void setSourceAdvSearchDTO(SourceAdvSearchDTO sourceAdvSearchDTO) {
        this.sourceAdvSearchDTO = sourceAdvSearchDTO;
    }
}

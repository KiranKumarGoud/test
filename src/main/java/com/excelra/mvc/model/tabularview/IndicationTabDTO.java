package com.excelra.mvc.model.tabularview;

import java.io.Serializable;

/**
 *
 * @author venkateswarlu.s
 */
public class IndicationTabDTO implements Serializable {

    private Long theraAreaId;

    private Long gvkId;

    private String databaseName;

    private String icd10Code;

    private String physiologicalUse;

    private String therapeuticUse;

    public Long getTheraAreaId() {
        return theraAreaId;
    }

    public void setTheraAreaId(Long theraAreaId) {
        this.theraAreaId = theraAreaId;
    }

    public Long getGvkId() {
        return gvkId;
    }

    public void setGvkId(Long gvkId) {
        this.gvkId = gvkId;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }

    public String getIcd10Code() {
        return icd10Code;
    }

    public void setIcd10Code(String icd10Code) {
        this.icd10Code = icd10Code;
    }

    public String getPhysiologicalUse() {
        return physiologicalUse;
    }

    public void setPhysiologicalUse(String physiologicalUse) {
        this.physiologicalUse = physiologicalUse;
    }

    public String getTherapeuticUse() {
        return therapeuticUse;
    }

    public void setTherapeuticUse(String therapeuticUse) {
        this.therapeuticUse = therapeuticUse;
    }
}

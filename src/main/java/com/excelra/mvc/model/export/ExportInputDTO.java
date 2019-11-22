package com.excelra.mvc.model.export;

import java.io.Serializable;
import java.util.List;

/***
 *<p>
 *     ExportInputDTO class for four list
 *     activity,assay,reference,structure
 *</p>
 * @author priyanka.veidhey
 */
public class ExportInputDTO implements Serializable {

    private List<Long> activity;

    private List<Long> assay;

    private List<Long> reference;

    private List<Long> structure;

    public List<Long> getActivity() {
        return activity;
    }

    public void setActivity(List<Long> activity) {
        this.activity = activity;
    }

    public List<Long> getAssay() {
        return assay;
    }

    public void setAssay(List<Long> assay) {
        this.assay = assay;
    }

    public List<Long> getReference() {
        return reference;
    }

    public void setReference(List<Long> reference) {
        this.reference = reference;
    }

    public List<Long> getStructure() {
        return structure;
    }

    public void setStructure(List<Long> structure) {
        this.structure = structure;
    }
}

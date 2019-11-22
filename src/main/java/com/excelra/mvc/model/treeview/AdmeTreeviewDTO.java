package com.excelra.mvc.model.treeview;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author venkateswarlu.s
 */
public class AdmeTreeviewDTO implements Serializable {

    private Long admeOntologyId;

    private String admeName;

    private String label;

    private Long parentAdmeOntologyId;

    private List<AdmeTreeviewDTO> children = new ArrayList<>();

    public Long getAdmeOntologyId() {
        return admeOntologyId;
    }

    public void setAdmeOntologyId(Long admeOntologyId) {
        this.admeOntologyId = admeOntologyId;
    }

    public String getAdmeName() {
        return admeName;
    }

    public void setAdmeName(String admeName) {
        this.admeName = admeName;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Long getParentAdmeOntologyId() {
        return parentAdmeOntologyId;
    }

    public void setParentAdmeOntologyId(Long parentAdmeOntologyId) {
        this.parentAdmeOntologyId = parentAdmeOntologyId;
    }

    public List<AdmeTreeviewDTO> getChildren() {
        return children;
    }

    public void setChildren(List<AdmeTreeviewDTO> children) {
        this.children = children;
    }
}

package com.excelra.mvc.model.treeview;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author venkateswarlu.s
 */
public class TargetTreeviewDTO implements Serializable {

    private Long ontologyId;

    private String targetName;

    private String label;

    private Long parentOntologyId;

    private List<TargetTreeviewDTO> children = new ArrayList<>();

    public Long getOntologyId() {
        return ontologyId;
    }

    public void setOntologyId(Long ontologyId) {
        this.ontologyId = ontologyId;
    }

    public String getTargetName() {
        return targetName;
    }

    public void setTargetName(String targetName) {
        this.targetName = targetName;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public List<TargetTreeviewDTO> getChildren() {
        return children;
    }

    public void setChildren(List<TargetTreeviewDTO> children) {
        this.children = children;
    }

    public Long getParentOntologyId() {
        return parentOntologyId;
    }

    public void setParentOntologyId(Long parentOntologyId) {
        this.parentOntologyId = parentOntologyId;
    }
}

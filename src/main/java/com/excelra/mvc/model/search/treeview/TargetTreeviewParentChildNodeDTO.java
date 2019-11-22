package com.excelra.mvc.model.search.treeview;

import java.io.Serializable;
import java.util.List;

public class TargetTreeviewParentChildNodeDTO implements Serializable {

    private Long ontologyId;

    private Long parentOntologyId;

    private String targetName;

    private String label;

    public List<TargetTreeviewParentChildNodeDTO> children;

    public Boolean lazy = Boolean.TRUE;

    public Long getOntologyId() {
        return ontologyId;
    }

    public void setOntologyId(Long ontologyId) {
        this.ontologyId = ontologyId;
    }

    public Long getParentOntologyId() {
        return parentOntologyId;
    }

    public void setParentOntologyId(Long parentOntologyId) {
        this.parentOntologyId = parentOntologyId;
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

    public List<TargetTreeviewParentChildNodeDTO> getChildren() {
        return children;
    }

    public void setChildren(List<TargetTreeviewParentChildNodeDTO> children) {
        this.children = children;
    }
}

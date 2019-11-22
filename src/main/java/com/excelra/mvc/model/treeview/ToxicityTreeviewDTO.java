package com.excelra.mvc.model.treeview;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author venkateswarlu.s
 */
public class ToxicityTreeviewDTO implements Serializable {

    private Long toxicityOntologyId;

    private String toxicityName;

    private String label;

    private Long parentToxicityOntologyId;

    private List<ToxicityTreeviewDTO> children = new ArrayList<>();

    public Long getToxicityOntologyId() {
        return toxicityOntologyId;
    }

    public void setToxicityOntologyId(Long toxicityOntologyId) {
        this.toxicityOntologyId = toxicityOntologyId;
    }

    public String getToxicityName() {
        return toxicityName;
    }

    public void setToxicityName(String toxicityName) {
        this.toxicityName = toxicityName;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Long getParentToxicityOntologyId() {
        return parentToxicityOntologyId;
    }

    public void setParentToxicityOntologyId(Long parentToxicityOntologyId) {
        this.parentToxicityOntologyId = parentToxicityOntologyId;
    }

    public List<ToxicityTreeviewDTO> getChildren() {
        return children;
    }

    public void setChildren(List<ToxicityTreeviewDTO> children) {
        this.children = children;
    }
}

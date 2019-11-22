package com.excelra.mvc.model.search.treeview;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author venkateswarlu.s
 */
public class ToxicityTreeviewParentChildNodeDTO implements Serializable {

    private Long toxicityOntologyId;

    private Long parentToxicityOntologyId;

    private String toxicityName;

    private String label;

    public List<ToxicityTreeviewParentChildNodeDTO> children;

    public Boolean lazy = Boolean.TRUE;

    public Long getToxicityOntologyId() {
        return toxicityOntologyId;
    }

    public void setToxicityOntologyId(Long toxicityOntologyId) {
        this.toxicityOntologyId = toxicityOntologyId;
    }

    public Long getParentToxicityOntologyId() {
        return parentToxicityOntologyId;
    }

    public void setParentToxicityOntologyId(Long parentToxicityOntologyId) {
        this.parentToxicityOntologyId = parentToxicityOntologyId;
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

    public List<ToxicityTreeviewParentChildNodeDTO> getChildren() {
        return children;
    }

    public void setChildren(List<ToxicityTreeviewParentChildNodeDTO> children) {
        this.children = children;
    }

    public Boolean getLazy() {
        return lazy;
    }

    public void setLazy(Boolean lazy) {
        this.lazy = lazy;
    }
}

package com.excelra.mvc.model.search.treeview;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author venkateswarlu.s
 */
public class AdmeTreeviewParentChildNodeDTO implements Serializable {

    private Long admeOntologyId;

    private Long parentAdmeOntologyId;

    private String admeName;

    private String label;

    public List<AdmeTreeviewParentChildNodeDTO> children;

    public Boolean lazy = Boolean.TRUE;

    public Long getAdmeOntologyId() {
        return admeOntologyId;
    }

    public void setAdmeOntologyId(Long admeOntologyId) {
        this.admeOntologyId = admeOntologyId;
    }

    public Long getParentAdmeOntologyId() {
        return parentAdmeOntologyId;
    }

    public void setParentAdmeOntologyId(Long parentAdmeOntologyId) {
        this.parentAdmeOntologyId = parentAdmeOntologyId;
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

    public List<AdmeTreeviewParentChildNodeDTO> getChildren() {
        return children;
    }

    public void setChildren(List<AdmeTreeviewParentChildNodeDTO> children) {
        this.children = children;
    }

    public Boolean getLazy() {
        return lazy;
    }

    public void setLazy(Boolean lazy) {
        this.lazy = lazy;
    }
}

package com.excelra.mvc.model.search.treeview;

import java.io.Serializable;
import java.util.List;

public class EndpointTreeviewParentChildNodeDTO implements Serializable {

    private Long endpointId;

    private Long parentEndpointId;

    private String endpointName;

    private String label;

    public List<EndpointTreeviewParentChildNodeDTO> children;

    public Boolean lazy = Boolean.TRUE;

    public Long getEndpointId() {
        return endpointId;
    }

    public void setEndpointId(Long endpointId) {
        this.endpointId = endpointId;
    }

    public Long getParentEndpointId() {
        return parentEndpointId;
    }

    public void setParentEndpointId(Long parentEndpointId) {
        this.parentEndpointId = parentEndpointId;
    }

    public String getEndpointName() {
        return endpointName;
    }

    public void setEndpointName(String endpointName) {
        this.endpointName = endpointName;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public List<EndpointTreeviewParentChildNodeDTO> getChildren() {
        return children;
    }

    public void setChildren(List<EndpointTreeviewParentChildNodeDTO> children) {
        this.children = children;
    }

    public Boolean getLazy() {
        return lazy;
    }

    public void setLazy(Boolean lazy) {
        this.lazy = lazy;
    }
}

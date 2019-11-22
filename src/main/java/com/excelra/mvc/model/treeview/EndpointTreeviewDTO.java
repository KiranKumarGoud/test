package com.excelra.mvc.model.treeview;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author venkateswarlu.s
 */
public class EndpointTreeviewDTO implements Serializable {

    private Long endpointId;

    private String endpointName;

    private String label;

    private Long parentEndpointId;

    private List<ToxicityTreeviewDTO> children = new ArrayList<>();

    public Long getEndpointId() {
        return endpointId;
    }

    public void setEndpointId(Long endpointId) {
        this.endpointId = endpointId;
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

    public Long getParentEndpointId() {
        return parentEndpointId;
    }

    public void setParentEndpointId(Long parentEndpointId) {
        this.parentEndpointId = parentEndpointId;
    }

    public List<ToxicityTreeviewDTO> getChildren() {
        return children;
    }

    public void setChildren(List<ToxicityTreeviewDTO> children) {
        this.children = children;
    }
}

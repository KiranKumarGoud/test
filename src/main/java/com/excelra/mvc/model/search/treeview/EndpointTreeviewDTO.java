package com.excelra.mvc.model.search.treeview;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author venkateswarlu.s
 */
public class EndpointTreeviewDTO implements Serializable {

    private List<Long> endpointId;

    public List<Long> getEndpointId() {
        return endpointId;
    }

    public void setEndpointId(List<Long> endpointId) {
        this.endpointId = endpointId;
    }
}

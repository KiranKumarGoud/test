package com.excelra.mvc.model.search.treeview;

import java.io.Serializable;
import java.util.List;

public class TargetTreeviewDTO implements Serializable {

    private List<Long> targetOntologyId;

    public List<Long> getTargetOntologyId() {
        return targetOntologyId;
    }

    public void setTargetOntologyId(List<Long> targetOntologyId) {
        this.targetOntologyId = targetOntologyId;
    }
}

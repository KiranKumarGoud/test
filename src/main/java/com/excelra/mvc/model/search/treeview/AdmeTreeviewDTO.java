package com.excelra.mvc.model.search.treeview;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author venkateswarlu.s
 */
public class AdmeTreeviewDTO implements Serializable {

    private List<Long> admeOntologyId;

    public List<Long> getAdmeOntologyId() {
        return admeOntologyId;
    }

    public void setAdmeOntologyId(List<Long> admeOntologyId) {
        this.admeOntologyId = admeOntologyId;
    }
}

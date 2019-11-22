package com.excelra.mvc.model.search.treeview;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author venkateswarlu.s
 */
public class ToxicityTreeviewDTO implements Serializable {

    private List<Long> toxicityOntologyId;

    public List<Long> getToxicityOntologyId() {
        return toxicityOntologyId;
    }

    public void setToxicityOntologyId(List<Long> toxicityOntologyId) {
        this.toxicityOntologyId = toxicityOntologyId;
    }
}

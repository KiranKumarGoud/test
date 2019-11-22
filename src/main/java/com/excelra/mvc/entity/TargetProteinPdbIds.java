package com.excelra.mvc.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name="target_protein_pdb_ids", schema = "target_search")
public class TargetProteinPdbIds implements Serializable {

    @EmbeddedId
    private TargetProteinPdbIdsEmbeddable targetPdbId;

    @Column(name = "target_id", insertable = false, updatable = false)
    private Long targetId;

    @Column(name = "stdname_id")
    private BigDecimal stdnameId;

    @Column(name = "pdb_id")
    private String pdbId;

    public TargetProteinPdbIdsEmbeddable getTargetPdbId() {
        return targetPdbId;
    }

    public void setTargetPdbId(TargetProteinPdbIdsEmbeddable targetPdbId) {
        this.targetPdbId = targetPdbId;
    }

    public BigDecimal getStdnameId() {
        return stdnameId;
    }

    public void setStdnameId(BigDecimal stdnameId) {
        this.stdnameId = stdnameId;
    }

    public String getPdbId() {
        return pdbId;
    }

    public void setPdbId(String pdbId) {
        this.pdbId = pdbId;
    }

    public Long getTargetId() {
        return targetId;
    }

    public void setTargetId(Long targetId) {
        this.targetId = targetId;
    }
}

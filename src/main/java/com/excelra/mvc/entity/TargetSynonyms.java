package com.excelra.mvc.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="target_synonyms")
public class TargetSynonyms implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "target_synonym_id")
    private Integer targetSynonymId;

    @Column(name = "synonyms")
    private String synonyms;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "target_id")
    private TargetProteinMaster target;

    @Column(name = "stdname_id")
    private Integer stdnameId;

    @Column(name = "flag")
    private String flag;

    @Column(name = "syn_type")
    private String synType;

    public Integer getTargetSynonymId() {
        return targetSynonymId;
    }

    public void setTargetSynonymId(Integer targetSynonymId) {
        this.targetSynonymId = targetSynonymId;
    }

    public String getSynonyms() {
        return synonyms;
    }

    public void setSynonyms(String synonyms) {
        this.synonyms = synonyms;
    }

    public TargetProteinMaster getTarget() {
        return target;
    }

    public void setTarget(TargetProteinMaster target) {
        this.target = target;
    }

    public Integer getStdnameId() {
        return stdnameId;
    }

    public void setStdnameId(Integer stdnameId) {
        this.stdnameId = stdnameId;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getSynType() {
        return synType;
    }

    public void setSynType(String synType) {
        this.synType = synType;
    }
}

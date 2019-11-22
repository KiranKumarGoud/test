package com.excelra.mvc.model.analyzer;

import java.io.Serializable;

public class MolecularDetails implements Serializable{
	
	private Long strId1;	
	private Float mm1;
	private String smiles1;
	private Long strId2;
	private Float mm2;
	private String smiles2;
	
	public Long getStrId1() {
		return strId1;
	}
	public void setStrId1(Long strId1) {
		this.strId1 = strId1;
	}
	
	public String getSmiles1() {
		return smiles1;
	}
	public void setSmiles1(String smiles1) {
		this.smiles1 = smiles1;
	}
	public Long getStrId2() {
		return strId2;
	}
	public void setStrId2(Long strId2) {
		this.strId2 = strId2;
	}
	
	public Float getMm1() {
		return mm1;
	}
	public void setMm1(Float mm1) {
		this.mm1 = mm1;
	}
	public Float getMm2() {
		return mm2;
	}
	public void setMm2(Float mm2) {
		this.mm2 = mm2;
	}
	public String getSmiles2() {
		return smiles2;
	}
	public void setSmiles2(String smiles2) {
		this.smiles2 = smiles2;
	}

}

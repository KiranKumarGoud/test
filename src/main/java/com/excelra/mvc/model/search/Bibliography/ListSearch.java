package com.excelra.mvc.model.search.Bibliography;

import com.excelra.mvc.model.BibliographyDoiDTO;
import com.excelra.mvc.model.BibliographyIssnNoDTO;
import com.excelra.mvc.model.BibliographyMeshDTO;
import com.excelra.mvc.model.BibliographyPubmedDTO;

import java.io.Serializable;
import java.util.List;

/***
 *
 * @author priyanka.veidhey, Venkateswarlu.s
 */
public class ListSearch implements Serializable {

    private String refReadonly;

    private List<String> refComboData;

    private List<String> refComboFileData;

    private List<BibliographyPubmedDTO> bibliographyPubmedDTOList;

    private List<BibliographyDoiDTO> bibliographyDoiDTOList;

    private List<BibliographyIssnNoDTO> bibliographyIssnNoDTOList;

    private List<BibliographyMeshDTO> bibliographyMeshDTOList;

    public String getRefReadonly() {
        return refReadonly;
    }

    public void setRefReadonly(String refReadonly) {
        this.refReadonly = refReadonly;
    }

    public List<String> getRefComboData() {
        return refComboData;
    }

    public void setRefComboData(List<String> refComboData) {
        this.refComboData = refComboData;
    }

    public List<String> getRefComboFileData() {
        return refComboFileData;
    }

    public void setRefComboFileData(List<String> refComboFileData) {
        this.refComboFileData = refComboFileData;
    }

    public List<BibliographyPubmedDTO> getBibliographyPubmedList() {
        return bibliographyPubmedDTOList;
    }

    public void setBibliographyPubmedList(List<BibliographyPubmedDTO> bibliographyPubmedDTOList) {
        this.bibliographyPubmedDTOList = bibliographyPubmedDTOList;
    }

    public List<BibliographyPubmedDTO> getBibliographyPubmedDTOList() {
        return bibliographyPubmedDTOList;
    }

    public void setBibliographyPubmedDTOList(List<BibliographyPubmedDTO> bibliographyPubmedDTOList) {
        this.bibliographyPubmedDTOList = bibliographyPubmedDTOList;
    }

    public List<BibliographyDoiDTO> getBibliographyDoiDTOList() {
        return bibliographyDoiDTOList;
    }

    public void setBibliographyDoiDTOList(List<BibliographyDoiDTO> bibliographyDoiDTOList) {
        this.bibliographyDoiDTOList = bibliographyDoiDTOList;
    }

    public List<BibliographyIssnNoDTO> getBibliographyIssnNoDTOList() {
        return bibliographyIssnNoDTOList;
    }

    public void setBibliographyIssnNoDTOList(List<BibliographyIssnNoDTO> bibliographyIssnNoDTOList) {
        this.bibliographyIssnNoDTOList = bibliographyIssnNoDTOList;
    }

    public List<BibliographyMeshDTO> getBibliographyMeshDTOList() {
        return bibliographyMeshDTOList;
    }

    public void setBibliographyMeshDTOList(List<BibliographyMeshDTO> bibliographyMeshDTOList) {
        this.bibliographyMeshDTOList = bibliographyMeshDTOList;
    }
}

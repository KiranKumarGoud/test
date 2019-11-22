package com.excelra.mvc.service;


import com.excelra.mvc.model.*;
import com.excelra.mvc.model.userjdbc.UserJdbc;
import com.excelra.mvc.repository.BibliographyDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReferenceMasterService implements IReferenceMaster {

    @Autowired
    private BibliographyDAO bibliographyDAO;

    /**
     * Reference name Simple search service for auto complete.
     *
     * @param referenceName
     * @param userJdbc
     * @return
     */
    @Override
    public List<ReferenceMasterDTO> fetchByReferenceNameContains(String referenceName, UserJdbc userJdbc) {
        return bibliographyDAO.fetchByReferenceNameContains(referenceName, userJdbc);
    }

    /****
     *
     * @param option
     * @param optionValue
     * @param userJdbcObject
     * @return
     */
    @Override
    public List<BibliographyAdvSearchInputDTO> fetchBibliographyListSearchByOptionAndValue(String option, String optionValue, UserJdbc userJdbcObject) {
        return bibliographyDAO.fetchBibliographyByOptionAndValue(option, optionValue, userJdbcObject);

    }

    /****
     *
     * @param pubmedId
     * @param userJdbc
     * @return
     */
    @Override
    public List<BibliographyPubmedDTO> findByPubmedIdContaining(String pubmedId, UserJdbc userJdbc) {
        return bibliographyDAO.findByPubmedIdContaining(pubmedId, userJdbc);

    }

    /***
     *
     * @param doi
     * @param userJdbc
     * @return
     */
    @Override
    public List<BibliographyDoiDTO> findByDoiContaining(String doi, UserJdbc userJdbc) {
        return bibliographyDAO.findByDoiRefContaining(doi, userJdbc);
    }

    @Override
    public List<BibliographyIssnNoDTO> findByRefIssnNoContaining(String issnNo, UserJdbc userJdbc) {
        return bibliographyDAO.findByRefIssnNoContaining(issnNo, userJdbc);

    }

    /***
     *
     * @param mesh
     * @param userJdbc
     * @return
     */
    @Override
    public List<BibliographyMeshDTO> findByRefMeshContaining(String mesh, UserJdbc userJdbc) {
        return bibliographyDAO.findByRefPubmedMeshContaining(mesh, userJdbc);
    }

    /***
     * <p>
     *     fetchBibliographyCriterianByOptionAndValue is for the criterian
     *     search
     * </p>
     * @param option
     * @param optionValue
     * @param userJdbcObject
     * @return
     */
    @Override
    public List<BibliographyCriterianSearchInputDTO> fetchBibliographyCriterianByOptionAndValue(String option, String optionValue, UserJdbc userJdbcObject) {
        return bibliographyDAO.fetchBibliographyCriterianSearchAdvByOptionAndValue(option, optionValue, userJdbcObject);

    }

    /***
     * <p>adde the code for the author criterion get call</p>
     * @param author
     * @param userJdbc
     * @return
     */
    @Override
    public List<ListAuthorsDTO> findByRefCriterionAuthorContaining(String author, UserJdbc userJdbc) {
        return bibliographyDAO.findByRefCriterionSeachAuthorContaining(author, userJdbc);
    }

    /***
     *
     * @param companyname
     * @param userJdbc
     * @return
     */
    @Override
    public List<ListCompaniesDTO> findByRefCriterioncompanyNameContaining(String companyname, UserJdbc userJdbc) {
        return bibliographyDAO.findByRefCriterionSearchcompanyNameContaining(companyname, userJdbc);
    }

    /**
     *
     * @param userJdbc
     * @return
     */
    @Override
    public List<ListJournalNamesDTO> fetchJournalNames(UserJdbc userJdbc) {
        return bibliographyDAO.fetchJournalNames(userJdbc);
    }

    /**
     *
     * @param jonurnalInputDTO
     * @param userJdbc
     * @return
     */
    @Override
    public List<JournalLabelValueDTO> customSearchJournalFetch(JonurnalInputDTO jonurnalInputDTO, UserJdbc userJdbc) {
        return bibliographyDAO.customSearchJournalFetch(jonurnalInputDTO, userJdbc);
    }

    /**
     *
     * @param patentInputDTO
     * @param userJdbc
     * @return
     */
    @Override
    public List<JournalLabelValueDTO> customSearchPatentFetch(PatentInputDTO patentInputDTO, UserJdbc userJdbc) {
        return bibliographyDAO.customSearchPatentFetch(patentInputDTO, userJdbc);
    }

    /**
     *
     * @return
     */
    @Override
    public List<JournalLabelValueDTO> fetchCountryCodes() {
        return bibliographyDAO.fetchCountryCodes();
    }
}

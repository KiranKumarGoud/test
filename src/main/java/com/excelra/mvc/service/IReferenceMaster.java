package com.excelra.mvc.service;

import com.excelra.mvc.model.*;
import com.excelra.mvc.model.userjdbc.UserJdbc;

import java.util.List;

/**
 * <p>
 *  Bibliography, Reference service
 * <p>
 *
 * @author venkateswarlu.s
 */
public interface IReferenceMaster {

    List<ReferenceMasterDTO> fetchByReferenceNameContains(String referenceName, UserJdbc userJdbc);

    List<BibliographyPubmedDTO> findByPubmedIdContaining(String pubmedId, UserJdbc userJdbc);

    List<BibliographyDoiDTO> findByDoiContaining(String doi , UserJdbc userJdbc);

    List<BibliographyIssnNoDTO> findByRefIssnNoContaining(String issnNo , UserJdbc userJdbc);

    List<BibliographyMeshDTO> findByRefMeshContaining(String mesh , UserJdbc userJdbc);

    List<BibliographyAdvSearchInputDTO> fetchBibliographyListSearchByOptionAndValue(String option, String optionValue, UserJdbc userJdbcObject);

    List<BibliographyCriterianSearchInputDTO> fetchBibliographyCriterianByOptionAndValue(String option, String optionValue, UserJdbc userJdbcObject);

    List<ListAuthorsDTO> findByRefCriterionAuthorContaining(String author ,UserJdbc userJdbc);

    List<ListCompaniesDTO> findByRefCriterioncompanyNameContaining(String companyname , UserJdbc userJdbc);

    List<ListJournalNamesDTO> fetchJournalNames(UserJdbc userJdbc);

    List<JournalLabelValueDTO> customSearchJournalFetch(JonurnalInputDTO jonurnalInputDTO, UserJdbc userJdbc);

    List<JournalLabelValueDTO> customSearchPatentFetch(PatentInputDTO patentInputDTO, UserJdbc userJdbc);

    List<JournalLabelValueDTO> fetchCountryCodes();
}

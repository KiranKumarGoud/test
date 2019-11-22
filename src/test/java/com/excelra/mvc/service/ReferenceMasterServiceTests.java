package com.excelra.mvc.service;

import com.excelra.mvc.model.*;
import com.excelra.mvc.model.userjdbc.UserJdbc;
import com.excelra.mvc.repository.BibliographyDAO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * Reference Master Service method JUnit test cases preparation for checking service methods healthy status
 *
 * @author venkateswarlu.s
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ReferenceMasterServiceTests {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReferenceMasterServiceTests.class);

    @Autowired
    private ReferenceMasterService referenceMasterService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private UserJdbc userJdbc;

    @Before
    public void contextLoads() {

        this.userJdbc = new UserJdbc();
        this.userJdbc.setJdbcTemplate(jdbcTemplate);
        this.userJdbc.setUserSessionId("1JldmFsdWF0ZXVzZXIiLCJhdXRoIj2");

    }

    @Test
    public void testFetchByReferenceNameContains() {

        LOGGER.info(" ------------------- Executing testFetchByReferenceNameContains Test case ------------------- ");

        List<ReferenceMasterDTO> referenceMasterDTOList = referenceMasterService.fetchByReferenceNameContains("chem", userJdbc);

        LOGGER.info(" ReferenceMasterDTO Records Count {} ", referenceMasterDTOList.size());

        Assert.assertNotNull(referenceMasterDTOList);
    }

    @Test
    public void testFetchBibliographyListSearchByOptionAndValue() {

        LOGGER.info(" ------------------- Executing testFetchByReferenceNameContains Test case ------------------- ");

        List<BibliographyAdvSearchInputDTO> bibliographyAdvSearchInputDTOS = referenceMasterService.fetchBibliographyListSearchByOptionAndValue("pubmedId", "123", userJdbc);

        LOGGER.info(" BibliographyAdvSearchInputDTO Records Count {} ", bibliographyAdvSearchInputDTOS.size());

        Assert.assertNotNull(bibliographyAdvSearchInputDTOS);

    }

    @Test
    public void testFindByPubmedIdContaining() {

        LOGGER.info(" ------------------- Executing testFindByPubmedIdContaining Test case ------------------- ");

        List<BibliographyPubmedDTO> bibliographyPubmedDTOList = referenceMasterService.findByPubmedIdContaining("600", userJdbc);

        LOGGER.info(" BibliographyPubmedDTO Records Count {} ", bibliographyPubmedDTOList.size());

        Assert.assertNotNull(bibliographyPubmedDTOList);
    }

    @Test
    public void testFindByDoiContaining() {

        LOGGER.info(" ------------------- Executing testFindByDoiContaining Test case ------------------- ");

        List<BibliographyDoiDTO> bibliographyDoiDTOList = referenceMasterService.findByDoiContaining("10", userJdbc);

        LOGGER.info(" BibliographyDoiDTO Records Count {} ", bibliographyDoiDTOList.size());

        Assert.assertNotNull(bibliographyDoiDTOList);
    }

    @Test
    public void testFindByRefIssnNoContaining() {

        LOGGER.info(" ------------------- Executing testFindByDoiContaining Test case ------------------- ");

        List<BibliographyIssnNoDTO> bibliographyIssnNoDTOList = referenceMasterService.findByRefIssnNoContaining("09", userJdbc);

        LOGGER.info(" BibliographyIssnNoDTO Records Count {} ", bibliographyIssnNoDTOList.size());

        Assert.assertNotNull(bibliographyIssnNoDTOList);
    }

    @Test
    public void testFindByRefMeshContaining() {

        LOGGER.info(" ------------------- Executing testFindByDoiContaining Test case ------------------- ");

        List<BibliographyMeshDTO> bibliographyMeshDTOList = referenceMasterService.findByRefMeshContaining("a", userJdbc);

        LOGGER.info(" BibliographyMeshDTO Records Count {} ", bibliographyMeshDTOList.size());

        Assert.assertNotNull(bibliographyMeshDTOList);

    }

    @Test
    public void testFetchBibliographyCriterianByOptionAndValue() {

        LOGGER.info(" ------------------- Executing testFindByDoiContaining Test case ------------------- ");

        List<BibliographyCriterianSearchInputDTO> bibliographyCriterianSearchInputDTOS = referenceMasterService.fetchBibliographyCriterianByOptionAndValue("author", "a", userJdbc);

        LOGGER.info(" BibliographyCriterianSearchInputDTO Records Count {} ", bibliographyCriterianSearchInputDTOS.size());

        Assert.assertNotNull(bibliographyCriterianSearchInputDTOS);

    }

    @Test
    public void testFindByRefCriterionAuthorContaining() {

        LOGGER.info(" ------------------- Executing testFindByRefCriterionAuthorContaining Test case ------------------- ");

        List<ListAuthorsDTO> listAuthorsDTOS = referenceMasterService.findByRefCriterionAuthorContaining("a", userJdbc);

        LOGGER.info(" ListAuthorsDTO Records Count {} ", listAuthorsDTOS.size());

        Assert.assertNotNull(listAuthorsDTOS);
    }

    @Test
    public void testFindByRefCriterioncompanyNameContaining() {

        LOGGER.info(" ------------------- Executing testFindByRefCriterionAuthorContaining Test case ------------------- ");

        List<ListCompaniesDTO> listCompaniesDTOS = referenceMasterService.findByRefCriterioncompanyNameContaining("a", userJdbc);

        LOGGER.info(" ListCompaniesDTO Records Count {} ", listCompaniesDTOS.size());

        Assert.assertNotNull(listCompaniesDTOS);
    }

    @Test
    public void testFetchJournalNames() {

        LOGGER.info(" ------------------- Executing testFetchJournalNames Test case ------------------- ");

        List<ListJournalNamesDTO> listJournalNamesDTOS = referenceMasterService.fetchJournalNames(userJdbc);

        LOGGER.info(" ListJournalNamesDTO Records Count {} ", listJournalNamesDTOS.size());

        Assert.assertNotNull(listJournalNamesDTOS);
    }

    @Test
    public void testCustomSearchJournalFetch() {

        LOGGER.info(" ------------------- Executing testCustomSearchJournalFetch Test case ------------------- ");

        JonurnalInputDTO jonurnalInputDTO = new JonurnalInputDTO();
        jonurnalInputDTO.setJournalName("Farmaco.");
        jonurnalInputDTO.setIssue(null);
        jonurnalInputDTO.setPageNo(null);
        jonurnalInputDTO.setVolume(null);
        jonurnalInputDTO.setYear(null);

        List<JournalLabelValueDTO> journalLabelValueDTOS = referenceMasterService.customSearchJournalFetch(jonurnalInputDTO, userJdbc);

        LOGGER.info(" JournalLabelValueDTO Records Count {} ", journalLabelValueDTOS.size());

        Assert.assertNotNull(journalLabelValueDTOS);
    }

    @Test
    public void testCustomSearchPatentFetch() {

        LOGGER.info(" ------------------- Executing testCustomSearchPatentFetch Test case ------------------- ");

        List<String> countryCodes = new ArrayList<>();
        countryCodes.add("US");

        List<String> yearCodes = new ArrayList<>();
        yearCodes.add("2019");
        yearCodes.add("2018");
        yearCodes.add("2017");
        yearCodes.add("2016");

        PatentInputDTO patentInputDTO = new PatentInputDTO();
        patentInputDTO.setCountryCode(countryCodes);
        patentInputDTO.setPatentNo(null);
        patentInputDTO.setYear(yearCodes);

        List<JournalLabelValueDTO> journalLabelValueDTOS = referenceMasterService.customSearchPatentFetch(patentInputDTO, userJdbc);

        LOGGER.info(" JournalLabelValueDTO Records Count {} ", journalLabelValueDTOS.size());

        Assert.assertNotNull(journalLabelValueDTOS);

    }

    @Test
    public void testFetchCountryCodes() {

        LOGGER.info(" ------------------- Executing testFetchCountryCodes Test case ------------------- ");

        List<JournalLabelValueDTO> journalLabelValueDTOS = referenceMasterService.fetchCountryCodes();

        LOGGER.info(" JournalLabelValueDTO Records Count {} ", journalLabelValueDTOS.size());

        Assert.assertNotNull(journalLabelValueDTOS);
    }
}

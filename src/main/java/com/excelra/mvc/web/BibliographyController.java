package com.excelra.mvc.web;

import com.excelra.mvc.config.CustomJdbcConnection;
import com.excelra.mvc.exception.ServiceException;
import com.excelra.mvc.model.*;

import com.excelra.mvc.service.IReferenceMaster;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author venkateswarlu.s
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
@RequestMapping(value = "/security/bibliography")
public class BibliographyController {

    private static final Logger LOGGER = LoggerFactory.getLogger(BibliographyController.class);

    @Autowired
    private IReferenceMaster iReferenceMaster;

    @Autowired
    private CustomJdbcConnection customJdbcConnection;

    /**
     * Reference name Simple search service for auto complete.
     *
     * @param referenceName
     * @param userSessionId
     * @param httpSession
     * @return
     * @throws ServiceException
     */
    @RequestMapping(value = "/referencename/contains/{referenceName}", method = RequestMethod.GET, produces = {"application/json"})
    @ResponseBody
    public ResponseEntity<?> fetchByReferenceNameContains(@PathVariable String referenceName, @RequestHeader("userSessionId") String userSessionId, HttpSession httpSession) throws ServiceException {
        LOGGER.info("Bibliography Reference name fetch service");

        List<ReferenceMasterDTO> referenceMasterDtoList = iReferenceMaster.fetchByReferenceNameContains(referenceName, customJdbcConnection.getUserJdbcObject(httpSession, userSessionId));

        return new ResponseEntity<>(referenceMasterDtoList, HttpStatus.OK);
    }


    /****
     *
     * @param pubmedId
     * @param userSessionId
     * @param httpSession
     * @return
     * @throws ServiceException
     */
    @RequestMapping(value = "/refpubmed/contains/{pubmedId}", method = RequestMethod.GET, produces = {"application/json"})
    @ResponseBody
    public ResponseEntity<?> fetchByPubmedIdContains(@PathVariable String pubmedId, @RequestHeader("userSessionId") String userSessionId, HttpSession httpSession) throws ServiceException {
        LOGGER.info("PubmedId fetch service");

        List<BibliographyPubmedDTO> bibliographyPubmedDTOList = iReferenceMaster.findByPubmedIdContaining(pubmedId, customJdbcConnection.getUserJdbcObject(httpSession, userSessionId));

        return new ResponseEntity<>(bibliographyPubmedDTOList, HttpStatus.OK);
    }

    /****
     *
     * @param doi
     * @param userSessionId
     * @param httpSession
     * @return
     * @throws ServiceException
     */
    @RequestMapping(value = "/refdoi/contains/{doi}", method = RequestMethod.GET, produces = {"application/json"})
    @ResponseBody
    public ResponseEntity<?> fetchByRefDoiContains(@PathVariable String doi, @RequestHeader("userSessionId") String userSessionId, HttpSession httpSession) throws ServiceException {
        LOGGER.info("Doi fetch service");

        List<BibliographyDoiDTO> bibliographyDoiDTOList = iReferenceMaster.findByDoiContaining(doi, customJdbcConnection.getUserJdbcObject(httpSession, userSessionId));

        return new ResponseEntity<>(bibliographyDoiDTOList, HttpStatus.OK);
    }

    /****
     *
     * @param issnNo
     * @param userSessionId
     * @param httpSession
     * @return
     * @throws ServiceException
     */
    @RequestMapping(value = "/refissno/contains/{issnNo}", method = RequestMethod.GET, produces = {"application/json"})
    @ResponseBody
    public ResponseEntity<?> fetchByIssnNoContains(@PathVariable String issnNo, @RequestHeader("userSessionId") String userSessionId, HttpSession httpSession) throws ServiceException {
        LOGGER.info("IssnNo fetch service");

        List<BibliographyIssnNoDTO> bibliographyIssnNoDTOList = iReferenceMaster.findByRefIssnNoContaining(issnNo, customJdbcConnection.getUserJdbcObject(httpSession, userSessionId));

        return new ResponseEntity<>(bibliographyIssnNoDTOList, HttpStatus.OK);
    }

    /****
     *
     * @param mesh
     * @param userSessionId
     * @param httpSession
     * @return
     * @throws ServiceException
     */
    @RequestMapping(value = "/refmesh/contains/{mesh}", method = RequestMethod.GET, produces = {"application/json"})
    @ResponseBody
    public ResponseEntity<?> fetchByMeshContains(@PathVariable String mesh, @RequestHeader("userSessionId") String userSessionId, HttpSession httpSession) throws ServiceException {
        LOGGER.info("Mesh fetch service");

        List<BibliographyMeshDTO> bibliographyMeshDTOList = iReferenceMaster.findByRefMeshContaining(mesh, customJdbcConnection.getUserJdbcObject(httpSession, userSessionId));

        return new ResponseEntity<>(bibliographyMeshDTOList, HttpStatus.OK);
    }

    /****
     *
     * @param option
     * @param optionValue
     * @param userSessionId
     * @param httpSession
     * @return
     * @throws ServiceException
     */

    @RequestMapping(value = "/bibliographylistsearch/{option}/{optionValue}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<?> fetchBibliographyByOptionAndValue(@PathVariable String option, @PathVariable String optionValue, @RequestHeader("userSessionId") String userSessionId, HttpSession httpSession) throws ServiceException {
        LOGGER.info("BiblioGraphy fetch service");

        List<BibliographyAdvSearchInputDTO> bibliographyAdvSearchInputDTOList = iReferenceMaster.fetchBibliographyListSearchByOptionAndValue(option, optionValue, customJdbcConnection.getUserJdbcObject(httpSession, userSessionId));

        return new ResponseEntity<>(bibliographyAdvSearchInputDTOList, HttpStatus.OK);
    }

    /**
     *
     * @param bibliographyOptionValueDTO
     * @param userSessionId
     * @param httpSession
     * @return
     * @throws ServiceException
     */
    @RequestMapping(value = "/bibliographylistsearch/optionvaluepost", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<?> fetchBibliographyByOptionAndValuePost(@RequestBody BibliographyOptionValueDTO bibliographyOptionValueDTO, @RequestHeader("userSessionId") String userSessionId, HttpSession httpSession) throws ServiceException {
        LOGGER.info("BiblioGraphy fetch service");

        List<BibliographyAdvSearchInputDTO> bibliographyAdvSearchInputDTOList = iReferenceMaster.fetchBibliographyListSearchByOptionAndValue(bibliographyOptionValueDTO.getOption(), bibliographyOptionValueDTO.getValue(), customJdbcConnection.getUserJdbcObject(httpSession, userSessionId));

        return new ResponseEntity<>(bibliographyAdvSearchInputDTOList, HttpStatus.OK);
    }

    /****
     *
     * @param option
     * @param optionValue
     * @param userSessionId
     * @param httpSession
     * @return
     * @throws ServiceException
     */
    @RequestMapping(value = "/bibliographycriteriansearch/{option}/{optionValue}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<?> fetchBibliographyCriterianSearchByOptionAndValue(@PathVariable String option, @PathVariable String optionValue, @RequestHeader("userSessionId") String userSessionId, HttpSession httpSession) throws ServiceException {
        LOGGER.info("BiblioGraphy criterian search fetch service");

        List<BibliographyCriterianSearchInputDTO> bibliographyCriterianSearchInputDTOList = iReferenceMaster.fetchBibliographyCriterianByOptionAndValue(option, optionValue, customJdbcConnection.getUserJdbcObject(httpSession, userSessionId));

        return new ResponseEntity<>(bibliographyCriterianSearchInputDTOList, HttpStatus.OK);
    }

    /****
     *
     * @param author
     * @param userSessionId
     * @param httpSession
     * @return
     * @throws ServiceException
     */
    @RequestMapping(value = "/refcriterianauthor/contains/{author}", method = RequestMethod.GET, produces = {"application/json"})
    @ResponseBody
    public ResponseEntity<?> fetchByCriterionAuthorContains(@PathVariable String author, @RequestHeader("userSessionId") String userSessionId, HttpSession httpSession) throws ServiceException {
        LOGGER.info("author fetch service");

        List<ListAuthorsDTO> listAuthorsDTOList = iReferenceMaster.findByRefCriterionAuthorContaining(author, customJdbcConnection.getUserJdbcObject(httpSession, userSessionId));

        return new ResponseEntity<>(listAuthorsDTOList, HttpStatus.OK);
    }


    /****
     * <></>
     * @param companyname
     * @param userSessionId
     * @param httpSession
     * @return
     * @throws ServiceException
     */
    @RequestMapping(value = "/refcriteriancompanyname/contains/{companyname}", method = RequestMethod.GET, produces = {"application/json"})
    @ResponseBody
    public ResponseEntity<?> fetchByCriterionCompanyNameContains(@PathVariable String companyname, @RequestHeader("userSessionId") String userSessionId, HttpSession httpSession) throws ServiceException {
        LOGGER.info("companyname fetch service");

        List<ListCompaniesDTO> listCompaniesDTOList = iReferenceMaster.findByRefCriterioncompanyNameContaining(companyname, customJdbcConnection.getUserJdbcObject(httpSession, userSessionId));

        return new ResponseEntity<>(listCompaniesDTOList, HttpStatus.OK);
    }


    /**
     *
     * @param userSessionId
     * @param httpSession
     * @return
     * @throws ServiceException
     */
    @RequestMapping(value = "/fetchjournalnames", method = RequestMethod.GET, produces = {"application/json"})
    @ResponseBody
    public ResponseEntity<?> fetchJournalNames(@RequestHeader("userSessionId") String userSessionId, HttpSession httpSession) throws ServiceException {
        LOGGER.info("fetchJournalNames fetch service");

        List<ListJournalNamesDTO> journalNamesDTOList = iReferenceMaster.fetchJournalNames(customJdbcConnection.getUserJdbcObject(httpSession, userSessionId));

        return new ResponseEntity<>(journalNamesDTOList, HttpStatus.OK);
    }

    /**
     *
     * @param jonurnalInputDTO
     * @param userSessionId
     * @param httpSession
     * @return
     * @throws ServiceException
     */
    @RequestMapping(value = "/customsearch/journal/fetch", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<?> customSearchJournalFetch(@RequestBody JonurnalInputDTO jonurnalInputDTO, @RequestHeader("userSessionId") String userSessionId, HttpSession httpSession) throws ServiceException {
        LOGGER.info("companyname fetch service");

        List<JournalLabelValueDTO> journalOutputDTOs = iReferenceMaster.customSearchJournalFetch(jonurnalInputDTO, customJdbcConnection.getUserJdbcObject(httpSession, userSessionId));

        return new ResponseEntity<>(journalOutputDTOs, HttpStatus.OK);
    }

    /**
     *
     * @param patentInputDTO
     * @param userSessionId
     * @param httpSession
     * @return
     * @throws ServiceException
     */
    @RequestMapping(value = "/customsearch/patent/fetch", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<?> customSearchPatentFetch(@RequestBody PatentInputDTO patentInputDTO, @RequestHeader("userSessionId") String userSessionId, HttpSession httpSession) throws ServiceException {
        LOGGER.info("companyname fetch service");

        List<JournalLabelValueDTO> journalOutputDTOs = iReferenceMaster.customSearchPatentFetch(patentInputDTO, customJdbcConnection.getUserJdbcObject(httpSession, userSessionId));

        return new ResponseEntity<>(journalOutputDTOs, HttpStatus.OK);
    }


    /**
     *
     * @param userSessionId
     * @param httpSession
     * @return
     * @throws ServiceException
     */
    @RequestMapping(value = "/fetch/country/codes", method = RequestMethod.GET, produces = {"application/json"})
    @ResponseBody
    public ResponseEntity<?> fetchCountryCodes(@RequestHeader("userSessionId") String userSessionId, HttpSession httpSession) throws ServiceException {
        LOGGER.info("fetchCountryCodes fetch service");

        List<JournalLabelValueDTO> journalLabelValueDTOs = iReferenceMaster.fetchCountryCodes();

        return new ResponseEntity<>(journalLabelValueDTOs, HttpStatus.OK);
    }
}

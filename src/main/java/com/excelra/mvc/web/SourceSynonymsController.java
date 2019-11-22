package com.excelra.mvc.web;

import com.excelra.mvc.config.CustomJdbcConnection;
import com.excelra.mvc.exception.ServiceException;
import com.excelra.mvc.model.source.ListSourceSynonymsDTO;
import com.excelra.mvc.model.source.ListSourceTaxIdsDTO;
import com.excelra.mvc.model.source.SourceClassificationMasterDTO;
import com.excelra.mvc.service.IListSourceSynonyms;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Source synonyms Controller for
 * @author venkateswarlu.s
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
@RequestMapping(value = "/security/source")
public class SourceSynonymsController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TargetController.class);

    @Autowired
    private IListSourceSynonyms iListSourceSynonyms;

    @Autowired
    private CustomJdbcConnection customJdbcConnection;

    /**
     *
     * @param sourceName
     * @param httpSession
     * @param userSessionId
     * @return
     * @throws ServiceException
     */
    @RequestMapping(value = "/bysourcename/{sourceName}", method = RequestMethod.GET, produces = { "application/json" })
    @ResponseBody
    public ResponseEntity<?> fetchBySourceName(@PathVariable String sourceName, HttpSession httpSession, @RequestHeader("userSessionId") String userSessionId) throws ServiceException {
        LOGGER.info("Source Synonym fetch service");

        System.out.println("===========> "+userSessionId);
        List<ListSourceSynonymsDTO> listSourceSynonymsDTOS = iListSourceSynonyms.findBySourcenameContaining(sourceName, customJdbcConnection.getUserJdbcObject(httpSession, userSessionId));

        return new ResponseEntity<>(listSourceSynonymsDTOS, HttpStatus.OK);
    }

    /**
     *
     * @param synonym
     * @param httpSession
     * @param userSessionId
     * @return
     * @throws ServiceException
     */
    @RequestMapping(value = "/bysynonym/{synonym}", method = RequestMethod.GET, produces = { "application/json" })
    @ResponseBody
    public ResponseEntity<?> fetchBySynonyms(@PathVariable String synonym, HttpSession httpSession, @RequestHeader("userSessionId") String userSessionId) throws ServiceException {
        LOGGER.info("Source Synonym fetch service");

        System.out.println("===========> "+userSessionId);
        List<ListSourceSynonymsDTO> listSourceSynonymsDTOS = iListSourceSynonyms.findBySynonymContaining(synonym, customJdbcConnection.getUserJdbcObject(httpSession, userSessionId));

        return new ResponseEntity<>(listSourceSynonymsDTOS, HttpStatus.OK);
    }

    /**
     *
     * @param taxId
     * @param httpSession
     * @param userSessionId
     * @return
     * @throws ServiceException
     */
    @RequestMapping(value = "/bytaxid/{taxId}", method = RequestMethod.GET, produces = { "application/json" })
    @ResponseBody
    public ResponseEntity<?> fetchByTaxIds(@PathVariable String taxId, HttpSession httpSession, @RequestHeader("userSessionId") String userSessionId) throws ServiceException {
        LOGGER.info("Source Synonym fetch service");

        System.out.println("===========> "+userSessionId);
        List<ListSourceTaxIdsDTO> listSourceTaxIdsDTOS = iListSourceSynonyms.findByTaxIdContaining(taxId, customJdbcConnection.getUserJdbcObject(httpSession, userSessionId));

        return new ResponseEntity<>(listSourceTaxIdsDTOS, HttpStatus.OK);
    }

    /**
     *
     * @param genus
     * @param httpSession
     * @param userSessionId
     * @return
     * @throws ServiceException
     */
    @RequestMapping(value = "/strainsearch/forgenus/{genus}", method = RequestMethod.GET, produces = { "application/json" })
    @ResponseBody
    public ResponseEntity<?> strainSearchByGenusContaining(@PathVariable String genus, HttpSession httpSession, @RequestHeader("userSessionId") String userSessionId) throws ServiceException {
        LOGGER.info("Source Strain search by genus");

        System.out.println("===========> "+userSessionId);
        List<SourceClassificationMasterDTO> sourceClassificationMasterDTOList = iListSourceSynonyms.findByGenusContaining(genus, customJdbcConnection.getUserJdbcObject(httpSession, userSessionId));

        return new ResponseEntity<>(sourceClassificationMasterDTOList, HttpStatus.OK);
    }

    /**
     *
     * @param genus
     * @param species
     * @param httpSession
     * @param userSessionId
     * @return
     * @throws ServiceException
     */
    @RequestMapping(value = "/strainsearch/forspecies/{genus}/{species}", method = RequestMethod.GET, produces = { "application/json" })
    @ResponseBody
    public ResponseEntity<?> strainSearchBySpeciesContaining(@PathVariable String genus, @PathVariable String species, HttpSession httpSession, @RequestHeader("userSessionId") String userSessionId) throws ServiceException {
        LOGGER.info("Source Strain search by Species");

        System.out.println("===========> "+userSessionId);
        List<SourceClassificationMasterDTO> sourceClassificationMasterDTOList = iListSourceSynonyms.findBySpeciesContaining(genus, species, customJdbcConnection.getUserJdbcObject(httpSession, userSessionId));

        return new ResponseEntity<>(sourceClassificationMasterDTOList, HttpStatus.OK);
    }

    /**
     *
     * @param genus
     * @param species
     * @param strain
     * @param httpSession
     * @param userSessionId
     * @return
     * @throws ServiceException
     */
    @RequestMapping(value = "/strainsearch/forstrain/{genus}/{species}/{strain}", method = RequestMethod.GET, produces = { "application/json" })
    @ResponseBody
    public ResponseEntity<?> strainSearchByStrainContaining(@PathVariable String genus, @PathVariable String species, @PathVariable String strain, HttpSession httpSession, @RequestHeader("userSessionId") String userSessionId) throws ServiceException {
        LOGGER.info("Source Strain search by Strain");

        System.out.println("===========> "+userSessionId);
        List<SourceClassificationMasterDTO> sourceClassificationMasterDTOList = iListSourceSynonyms.findByStrainContaining(genus, species, strain, customJdbcConnection.getUserJdbcObject(httpSession, userSessionId));

        return new ResponseEntity<>(sourceClassificationMasterDTOList, HttpStatus.OK);
    }

}

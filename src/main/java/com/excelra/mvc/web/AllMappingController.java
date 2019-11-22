package com.excelra.mvc.web;

import com.excelra.mvc.config.CustomJdbcConnection;
import com.excelra.mvc.exception.ServiceException;
import com.excelra.mvc.model.AllMappingFinalResultDTO;
import com.excelra.mvc.model.AllMappingIdsInputDTO;
import com.excelra.mvc.model.search.*;
import com.excelra.mvc.model.tabularview.TabularviewRequest;
import com.excelra.mvc.service.IAllMappingIds;
import com.excelra.mvc.service.tabularview.IActivityTab;
import com.excelra.mvc.service.tabularview.IAllTab;
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
 * <p>
 *  All Mapping Controller
 * <p>
 *
 * @author venkateswarlu.s
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
@RequestMapping(value = "/security/allmapping")
public class AllMappingController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AllMappingController.class);

    @Autowired
    private IAllMappingIds allMappingIdsService;

    @Autowired
    private CustomJdbcConnection customJdbcConnection;

    @Autowired
    private IAllTab iAllTab;

    /**
     * Create temp tables for storing Search results and get the Visualizations data
     *
     * @return
     */
    @RequestMapping(value = "/create/temptables", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<String> createTempTables(@RequestHeader("userSessionId") String userSessionId, HttpSession httpSession) throws ServiceException {

        LOGGER.info("Create Temp tables for Search Results {} ");

        String creationStatus = allMappingIdsService.createTempTables(customJdbcConnection.getUserJdbcObject(httpSession, userSessionId));

        // LOGGER.info("AllMappingController", "Create Temp tables for Search Results Response received {} ", creationStatus);

        return new ResponseEntity<>(creationStatus, HttpStatus.OK);

    }

    /**
     * Search for Visualization data based on Simple and Advanced Search Inputs.
     *
     * @param searchCountInputDTOList
     * @return
     */
    @RequestMapping(value = "/search/visualization", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<SearchVisualizationResultDTO> searchWithVisualizationData(@RequestBody List<SearchCountInputDTO> searchCountInputDTOList, @RequestHeader("userSessionId") String userSessionId, HttpSession httpSession) throws ServiceException {

        LOGGER.info("Search All MappingIds For Visualizations Data {} ", searchCountInputDTOList);

        SearchVisualizationResultDTO searchVisualizationResultDTO = allMappingIdsService.searchWithVisualizationData(searchCountInputDTOList, customJdbcConnection.getUserJdbcObject(httpSession, userSessionId));

        // LOGGER.info("AllMappingController", "Response received {} ", searchVisualizationResultDTO);

        return new ResponseEntity<>(searchVisualizationResultDTO, HttpStatus.OK);

    }

    /**
     * Find the counts for Search results on ActId, GvkId, StrId, RefId and AssayId
     *
     * @param searchCountInputDTOList
     * @return
     */
    @RequestMapping(value = "/search/count", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<AllMappingFinalResultCountDTO> searchForCounts(@RequestBody List<SearchCountInputDTO> searchCountInputDTOList, @RequestHeader("userSessionId") String userSessionId, HttpSession httpSession) throws ServiceException {

        LOGGER.info("Search All MappingIds For Visualizations Data {} ", searchCountInputDTOList);

        AllMappingFinalResultCountDTO allMappingFinalResultCountDTO = allMappingIdsService.searchForCounts(searchCountInputDTOList, customJdbcConnection.getUserJdbcObject(httpSession, userSessionId));

        LOGGER.info("AllMappingController", "Response received {} ", allMappingFinalResultCountDTO);

        return new ResponseEntity<>(allMappingFinalResultCountDTO, HttpStatus.OK);

    }


    /**
     * Find the Default counts for ActId, GvkId, StrId, RefId and AssayId
     * Default Counts when loading the page, this count will fetch from default table from existing schema.
     *
     * @param userSessionId
     * @return
     */
    @RequestMapping(value = "/search/default/count", method = RequestMethod.GET, consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<AllMappingFinalResultCountDTO> searchForDefaultCounts(@RequestHeader("userSessionId") String userSessionId, HttpSession httpSession) throws ServiceException {

        LOGGER.info("Default Counts for All MappingIds For Visualizations Data for session {} ", userSessionId);

        AllMappingFinalResultCountDTO allMappingFinalResultCountDTO = allMappingIdsService.searchForDefaultCounts(customJdbcConnection.getUserJdbcObject(httpSession, userSessionId));

        LOGGER.info("AllMappingController", "Response received {} ", allMappingFinalResultCountDTO);

        return new ResponseEntity<>(allMappingFinalResultCountDTO, HttpStatus.OK);

    }


    /**
     * Filtered visualizations data.
     *
     * @param filterInputVisualDTOList
     * @return
     */
    @RequestMapping(value = "/search/filteredvisualization", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<AllMappingFinalResultCountDTO> searchWithFilterVisualizationData(@RequestBody List<FilterInputVisualizationDTO> filterInputVisualDTOList, @RequestHeader("userSessionId") String userSessionId, HttpSession httpSession) throws ServiceException {

        LOGGER.info("All filtered Visualizations Data {} ", filterInputVisualDTOList);

        AllMappingFinalResultCountDTO allMappingFinalResultCountDTO = allMappingIdsService.searchWithFilterVisualizationData(filterInputVisualDTOList, customJdbcConnection.getUserJdbcObject(httpSession, userSessionId));

        LOGGER.info("AllMappingController", "allMappingFinalResultCountDTO Response received {} ", allMappingFinalResultCountDTO);

        return new ResponseEntity<>(allMappingFinalResultCountDTO, HttpStatus.OK);
    }


    /**
     * Visualization data for ProteinClassification
     *
     * @param userSessionId
     * @param httpSession
     * @return
     */
    @RequestMapping(value = "/search/visualization/classification", method = RequestMethod.GET, consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<List<VisualizationResultDTO>> fetchForProteinClassification(@RequestHeader("userSessionId") String userSessionId, HttpSession httpSession) throws ServiceException {

        LOGGER.info("Search All MappingIds For Visualizations Data For ProteinClassification and session {} ", userSessionId);

        List<VisualizationResultDTO> searchVisualizationResultDTO = allMappingIdsService.visualizationForProteinClassification(customJdbcConnection.getUserJdbcObject(httpSession, userSessionId));

        return new ResponseEntity<>(searchVisualizationResultDTO, HttpStatus.OK);

    }

    /**
     * Visualization data for YearWise
     *
     * @param userSessionId
     * @param httpSession
     * @return
     */
    @RequestMapping(value = "/search/visualization/yearwise", method = RequestMethod.GET, consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<List<VisualizationResultDTO>> fetchForYearWise(@RequestHeader("userSessionId") String userSessionId, HttpSession httpSession) throws ServiceException {

        LOGGER.info("Search All MappingIds For Visualizations Data For Yearwise and session {} ", userSessionId);

        List<VisualizationResultDTO> searchVisualizationResultDTO = allMappingIdsService.visualizationForYearWiseVisualization(customJdbcConnection.getUserJdbcObject(httpSession, userSessionId));

        return new ResponseEntity<>(searchVisualizationResultDTO, HttpStatus.OK);

    }

    /**
     * Visualization data for Bibliography
     *
     * @param userSessionId
     * @param httpSession
     * @return
     */
    @RequestMapping(value = "/search/visualization/bibliography", method = RequestMethod.GET, consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<List<VisualizationResultDTO>> fetchForBibliography(@RequestHeader("userSessionId") String userSessionId, HttpSession httpSession) throws ServiceException {

        LOGGER.info("Search All MappingIds For Visualizations Data For Bibliography and session {} ", userSessionId);

        List<VisualizationResultDTO> searchVisualizationResultDTO = allMappingIdsService.visualizationForBibliographyVisualization(customJdbcConnection.getUserJdbcObject(httpSession, userSessionId));

        return new ResponseEntity<>(searchVisualizationResultDTO, HttpStatus.OK);

    }

    /**
     * Visualization data for Indication
     *
     * @param userSessionId
     * @param httpSession
     * @return
     */
    @RequestMapping(value = "/search/visualization/indication", method = RequestMethod.GET, consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<List<VisualizationIndicationDTO>> fetchForIndication(@RequestHeader("userSessionId") String userSessionId, HttpSession httpSession) throws ServiceException {

        LOGGER.info("Search All MappingIds For Visualizations Data For Indication and session {} ", userSessionId);

        List<VisualizationIndicationDTO> searchVisualizationIndicationResultDTO = allMappingIdsService.visualizationForIndicationVisualization(customJdbcConnection.getUserJdbcObject(httpSession, userSessionId));

        return new ResponseEntity<>(searchVisualizationIndicationResultDTO, HttpStatus.OK);

    }

    /**
     * Visualization data for Toxicity
     *
     * @param userSessionId
     * @param httpSession
     * @return
     */
    @RequestMapping(value = "/search/visualization/toxicity", method = RequestMethod.GET, consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<List<VisualizationTaxonomyEndPointToxicityDTO>> fetchForToxicity(@RequestHeader("userSessionId") String userSessionId, HttpSession httpSession) throws ServiceException {

        LOGGER.info("Search All MappingIds For Visualizations Data For Toxicity and session {} ", userSessionId);

        List<VisualizationTaxonomyEndPointToxicityDTO> searchVisualizationToxicityResultDTO = allMappingIdsService.visualizationForToxicityVisualization(customJdbcConnection.getUserJdbcObject(httpSession, userSessionId));

        return new ResponseEntity<>(searchVisualizationToxicityResultDTO, HttpStatus.OK);

    }

    /**
     * Visualization data for Taxonomy
     *
     * @param userSessionId
     * @param httpSession
     * @return
     */
    @RequestMapping(value = "/search/visualization/taxonomy", method = RequestMethod.GET, consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<List<VisualizationTaxonomyEndPointToxicityDTO>> fetchForTaxonomy(@RequestHeader("userSessionId") String userSessionId, HttpSession httpSession) throws ServiceException {

        LOGGER.info("Search All MappingIds For Visualizations Data For Taxonomy and session {} ", userSessionId);

        List<VisualizationTaxonomyEndPointToxicityDTO> searchVisualizationTaxonomyResultDTO = allMappingIdsService.visualizationForTaxonomyVisualization(customJdbcConnection.getUserJdbcObject(httpSession, userSessionId));

        return new ResponseEntity<>(searchVisualizationTaxonomyResultDTO, HttpStatus.OK);

    }

    /**
     * Visualization data for Endpoints
     *
     * @param userSessionId
     * @param httpSession
     * @return
     */
    @RequestMapping(value = "/search/visualization/endpoint", method = RequestMethod.GET, consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<List<VisualizationTaxonomyEndPointToxicityDTO>> fetchForEndpoint(@RequestHeader("userSessionId") String userSessionId, HttpSession httpSession) throws ServiceException {

        LOGGER.info("Search All MappingIds For Visualizations Data For Endpoint and session {} ", userSessionId);

        List<VisualizationTaxonomyEndPointToxicityDTO> searchVisualizationEndpointResultDTO = allMappingIdsService.visualizationForEndpointVisualization(customJdbcConnection.getUserJdbcObject(httpSession, userSessionId));

        return new ResponseEntity<>(searchVisualizationEndpointResultDTO, HttpStatus.OK);

    }

    /**
     * Visualization data for Reload
     *
     * @param userSessionId
     * @param httpSession
     * @return
     */
    @RequestMapping(value = "/search/visualization/reload", method = RequestMethod.GET, consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<String> reloadVisualization(@RequestHeader("userSessionId") String userSessionId, HttpSession httpSession) throws ServiceException {

        LOGGER.info("Search All MappingIds For Visualizations Data For Reload and session {} ", userSessionId);

        allMappingIdsService.reloadVisualization(customJdbcConnection.getUserJdbcObject(httpSession, userSessionId));

        return new ResponseEntity<>("Reloaded successfully", HttpStatus.OK);

    }


    /**
     * Search for Visualization data based on Simple and Advanced Search Inputs - Store request state for reset to initial state.
     *
     * @param searchRequestPayload
     * @return
     */
    @RequestMapping(value = "/search/visualization/storerequest", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<?> searchWithVisualizationDataStoreRequestState(@RequestBody String searchRequestPayload, @RequestHeader("userSessionId") String userSessionId, HttpSession httpSession) throws ServiceException {

        LOGGER.info("Search All MappingIds For Visualizations Data {} ", searchRequestPayload);

        httpSession.setAttribute(userSessionId+"_initialsearch", searchRequestPayload);

        return new ResponseEntity<>(searchRequestPayload, HttpStatus.OK);

    }

    /**
     * Search for Visualization data based on Simple and Advanced Search Inputs reset information return for initial load.
     *
     * @param userSessionId
     * @param httpSession
     * @return
     * @throws ServiceException
     */
    @RequestMapping(value = "/search/visualization/fetchrequest", method = RequestMethod.GET, consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<?> searchWithVisualizationDataFetchRequest(@RequestHeader("userSessionId") String userSessionId, HttpSession httpSession) throws ServiceException {

        LOGGER.info("Search All MappingIds For Visualizations Data Reset for Session {} ", userSessionId);

        return new ResponseEntity<>(httpSession.getAttribute(userSessionId+"_initialsearch"), HttpStatus.OK);

    }

    /**
     * Count after filtered data from Visual Results Table to display in Visualization and Tabularview pages
     *
     * @param userSessionId
     * @param httpSession
     * @return
     * @throws ServiceException
     */
    @RequestMapping(value = "/search/visualresults/count", method = RequestMethod.GET, consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<AllMappingFinalResultCountDTO> searchCountsForVisualResults(@RequestHeader("userSessionId") String userSessionId, HttpSession httpSession) throws ServiceException {

        LOGGER.info("Search Counts For Visual results template table {} ", userSessionId);

        AllMappingFinalResultCountDTO allMappingFinalResultCountDTO = allMappingIdsService.searchCountsForVisualResults(customJdbcConnection.getUserJdbcObject(httpSession, userSessionId));

        // LOGGER.info("AllMappingController", "Response received {} ", allMappingFinalResultCountDTO);

        return new ResponseEntity<>(allMappingFinalResultCountDTO, HttpStatus.OK);

    }

    /**
     *
     *
     * @param tabularviewRequest
     * @return
     */
    @RequestMapping(value = "/search/tabularview/count", method = RequestMethod.POST)
    public ResponseEntity<?> searchCountsForTabularResults(@RequestBody TabularviewRequest tabularviewRequest, @RequestHeader("userSessionId") String userSessionId, HttpSession httpSession) {

        String currentSessionUserToken = (String) httpSession.getAttribute(userSessionId+"_token");

        AllMappingFinalResultCountDTO allMappingFinalResultCountDTO = iAllTab.searchCountsForTabularResults(tabularviewRequest, customJdbcConnection.getUserJdbcObject(httpSession, userSessionId), currentSessionUserToken);

        // LOGGER.info("AllMappingController findActivityTabData ", "Response received {} ", allMappingFinalResultCountDTO);

        return new ResponseEntity<>(allMappingFinalResultCountDTO, HttpStatus.OK);
    }

    /**
     * All Visualizations from AllMappingIds with existing Search Results GVKIDs
     *
     * @param userSessionId
     * @param httpSession
     * @return
     * @throws ServiceException
     */
    @RequestMapping(value = "/search/all/visualization", method = RequestMethod.GET, consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<AllMappingFinalResultCountDTO> searchAllVisualizationData(@RequestHeader("userSessionId") String userSessionId, HttpSession httpSession) throws ServiceException {

        LOGGER.info("All Visualizations from AllMappingIds with existing Search Results GVKIDs Data {} ", userSessionId);

        AllMappingFinalResultCountDTO allMappingFinalResultCountDTO = allMappingIdsService.searchAllVisualizationData(customJdbcConnection.getUserJdbcObject(httpSession, userSessionId));

        LOGGER.info("AllMappingController", "searchAllVisualizationData allMappingFinalResultCountDTO Response received {} ", allMappingFinalResultCountDTO);

        return new ResponseEntity<>(allMappingFinalResultCountDTO, HttpStatus.OK);
    }

}

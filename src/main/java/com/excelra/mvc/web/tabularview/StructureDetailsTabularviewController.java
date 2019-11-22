package com.excelra.mvc.web.tabularview;


import com.excelra.mvc.config.CustomJdbcConnection;
import com.excelra.mvc.exception.TabularServiceException;
import com.excelra.mvc.model.tabularview.*;
import com.excelra.mvc.service.tabularview.IStructureDetailsTab;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * This class is used to get the Tabular data for
 * Structure Details tab information
 *
 * @author Venkat Salagrama
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/security/tabularview")
public class StructureDetailsTabularviewController {

    private static final Logger LOGGER = LoggerFactory.getLogger(StructureDetailsTabularviewController.class);

    @Autowired
    private IStructureDetailsTab structureDetailsTab;

    @Autowired
    private CustomJdbcConnection customJdbcConnection;

    /**
     *
     * @param tabularviewFilterRequest
     * @param userSessionId
     * @param httpSession
     * @return
     */
    @RequestMapping(value = "/structuretab/filterfield", method = RequestMethod.POST)
    public ResponseEntity<?> findStructureTabFilterFieldByGvkIds(@RequestBody TabularviewFilterRequest tabularviewFilterRequest, @RequestHeader("userSessionId") String userSessionId, HttpSession httpSession) throws TabularServiceException {

        String currentSessionUserToken = (String) httpSession.getAttribute(userSessionId+"_token");

        List<StructureDetailsTabDTO> structureTabFilterDto = structureDetailsTab.findStructureTabFilterFieldByGvkIds(tabularviewFilterRequest, customJdbcConnection.getUserJdbcObject(httpSession, userSessionId), currentSessionUserToken);

        LOGGER.debug("Tabular View filter field for Structure Tab - structureTabFilterDto - Response received {} ", structureTabFilterDto);

        return new ResponseEntity<>(structureTabFilterDto, HttpStatus.OK);
    }

    /**
     * To Get Structure Tab data by StructureIds from Search Criteria data available in Temp table.
     *
     * @param tabularviewRequest
     * @return
     */
    @RequestMapping(value = "/structuretab", method = RequestMethod.POST)
    public ResponseEntity<?> findStructureTabData(@RequestBody TabularviewRequest tabularviewRequest, @RequestHeader("userSessionId") String userSessionId, HttpSession httpSession) throws TabularServiceException {

        String currentSessionUserToken = (String) httpSession.getAttribute(userSessionId+"_token");

        StructureDetailsTabPageData structureTabPageData = structureDetailsTab.findStructureTabData(tabularviewRequest, customJdbcConnection.getUserJdbcObject(httpSession, userSessionId), currentSessionUserToken);

        LOGGER.debug("Tabular View for Structure Tab - findStructureTabData - Response received {} ", structureTabPageData);

        return new ResponseEntity<>(structureTabPageData, HttpStatus.OK);
    }

}

package com.excelra.mvc.web.tabularview;

import com.excelra.mvc.config.CustomJdbcConnection;
import com.excelra.mvc.exception.TabularServiceException;
import com.excelra.mvc.model.tabularview.*;
import com.excelra.mvc.service.tabularview.IReferenceTab;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * This class is used to get the Tabular data for
 * Reference tab information
 *
 * @author Venkat Salagrama
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/security/tabularview")
public class ReferenceTabularviewController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReferenceTabularviewController.class);

    @Autowired
    private IReferenceTab referenceTab;

    @Autowired
    private CustomJdbcConnection customJdbcConnection;

    /**
     *
     * @param tabularviewFilterRequest
     * @param userSessionId
     * @param httpSession
     * @return
     */
    @RequestMapping(value = "/referencetab/filterfield", method = RequestMethod.POST)
    public ResponseEntity<?> findReferenceTabFilterFieldByRefIds(@RequestBody TabularviewFilterRequest tabularviewFilterRequest, @RequestHeader("userSessionId") String userSessionId, HttpSession httpSession) throws TabularServiceException {

        String currentSessionUserToken = (String) httpSession.getAttribute(userSessionId+"_token");

        List<ReferenceTabDTO> referenceTabFilterDto = referenceTab.findReferenceTabFilterFieldByRefIds(tabularviewFilterRequest, customJdbcConnection.getUserJdbcObject(httpSession, userSessionId), currentSessionUserToken);

        LOGGER.debug("Tabular View filter field for Reference Tab - referenceTabFilterDto - Response received {} ", referenceTabFilterDto);

        return new ResponseEntity<>(referenceTabFilterDto, HttpStatus.OK);
    }

    /**
     * To Get Reference Tab data by ReferenceIds from Search Criteria data available in Temp table.
     *
     * @param tabularviewRequest
     * @return
     */
    @RequestMapping(value = "/referencetab", method = RequestMethod.POST)
    public ResponseEntity<?> findReferenceTabData(@RequestBody TabularviewRequest tabularviewRequest, @RequestHeader("userSessionId") String userSessionId, HttpSession httpSession) throws TabularServiceException {

        String currentSessionUserToken = (String) httpSession.getAttribute(userSessionId+"_token");

        ReferenceTabPageData referenceTabPageData = referenceTab.findReferenceTabData(tabularviewRequest, customJdbcConnection.getUserJdbcObject(httpSession, userSessionId), currentSessionUserToken);

        LOGGER.debug("Tabular View for Reference Tab - findReferenceTabData - Response received {} ", referenceTabPageData);

        return new ResponseEntity<>(referenceTabPageData, HttpStatus.OK);
    }

}

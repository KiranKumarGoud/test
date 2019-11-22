package com.excelra.mvc.web.tabularview;


import com.excelra.mvc.config.CustomJdbcConnection;
import com.excelra.mvc.exception.TabularServiceException;
import com.excelra.mvc.model.tabularview.*;
import com.excelra.mvc.service.tabularview.IAssayTab;
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
 * AssayTab Controller for assay
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/security/tabularview")
public class AssayTabularviewController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ActivityTabularviewController.class);

    @Autowired
    private IAssayTab assayTab;

    @Autowired
    private CustomJdbcConnection customJdbcConnection;


    /**
     *
     * @param tabularviewFilterRequest
     * @param userSessionId
     * @param httpSession
     * @return
     */
    @RequestMapping(value = "/assaytab/filterfield", method = RequestMethod.POST)
    public ResponseEntity<?> findAssayTabFilterFieldByAssayIds(@RequestBody TabularviewFilterRequest tabularviewFilterRequest, @RequestHeader("userSessionId") String userSessionId, HttpSession httpSession) throws TabularServiceException {

        String currentSessionUserToken = (String) httpSession.getAttribute(userSessionId+"_token");

        List<AssayTabDTO> activityTabFilterDto = assayTab.findAssayTabFilterFieldByAssayIds(tabularviewFilterRequest, customJdbcConnection.getUserJdbcObject(httpSession, userSessionId), currentSessionUserToken);

        LOGGER.debug("Tabular View filter field for Activity Tab - activityTabFilterDto - Response received {} ", activityTabFilterDto);

        return new ResponseEntity<>(activityTabFilterDto, HttpStatus.OK);
    }

    /**
     * To Get Assay Tab data by AssayIds from Search Criteria data available in Temp table.
     *
     * @param tabularviewRequest
     * @return
     */
    @RequestMapping(value = "/assaytab", method = RequestMethod.POST)
    public ResponseEntity<?> findAssayTabData(@RequestBody TabularviewRequest tabularviewRequest, @RequestHeader("userSessionId") String userSessionId, HttpSession httpSession) throws TabularServiceException {

        String currentSessionUserToken = (String) httpSession.getAttribute(userSessionId+"_token");

        AssayTabPageData assayTabPageData = assayTab.findAssayTabData(tabularviewRequest, customJdbcConnection.getUserJdbcObject(httpSession, userSessionId), currentSessionUserToken);

        LOGGER.debug("Tabular View for Assay Tab - findAssayTabData - Response received {} ", assayTabPageData);

        return new ResponseEntity<>(assayTabPageData, HttpStatus.OK);
    }
}

package com.excelra.mvc.web.tabularview;

import com.excelra.mvc.config.CustomJdbcConnection;
import com.excelra.mvc.exception.TabularServiceException;
import com.excelra.mvc.model.tabularview.ClinicalStatusTabDTO;
import com.excelra.mvc.model.tabularview.ClinicalStatusTabPageData;
import com.excelra.mvc.model.tabularview.TabularviewFilterRequest;
import com.excelra.mvc.model.tabularview.TabularviewRequest;
import com.excelra.mvc.service.tabularview.IClinicalStatusTab;
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
 * Clinical Status tab information
 *
 * @author venkateswarlu.s
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/security/tabularview")
public class ClinicalStatusTabularviewController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClinicalStatusTabularviewController.class);

    @Autowired
    private IClinicalStatusTab iClinicalStatusTab;

    @Autowired
    private CustomJdbcConnection customJdbcConnection;

    /**
     *
     * @param tabularviewFilterRequest
     * @param userSessionId
     * @param httpSession
     * @return
     * @throws TabularServiceException
     */
    @RequestMapping(value = "/clinicalstatustab/filterfield", method = RequestMethod.POST)
    public ResponseEntity<?> findClinicalStatusTabFilterFieldByGvkRefIds(@RequestBody TabularviewFilterRequest tabularviewFilterRequest, @RequestHeader("userSessionId") String userSessionId, HttpSession httpSession) throws TabularServiceException {

        String currentSessionUserToken = (String) httpSession.getAttribute(userSessionId+"_token");

        List<ClinicalStatusTabDTO> clinicalStatusTabFilterDto = iClinicalStatusTab.findClinicalStatusTabFilterFieldByGvkRefIds(tabularviewFilterRequest, customJdbcConnection.getUserJdbcObject(httpSession, userSessionId), currentSessionUserToken);

        LOGGER.debug("Tabular View filter field for ClinicalStatus Tab - clinicalStatusTabFilterDto - Response received {} ", clinicalStatusTabFilterDto);

        return new ResponseEntity<>(clinicalStatusTabFilterDto, HttpStatus.OK);
    }

    /**
     *
     * @param tabularviewRequest
     * @param userSessionId
     * @param httpSession
     * @return
     * @throws TabularServiceException
     */
    @RequestMapping(value = "/clinicalstatustab", method = RequestMethod.POST)
    public ResponseEntity<?> findClinicalStatusTabData(@RequestBody TabularviewRequest tabularviewRequest, @RequestHeader("userSessionId") String userSessionId, HttpSession httpSession) throws TabularServiceException {

        String currentSessionUserToken = (String) httpSession.getAttribute(userSessionId+"_token");

        ClinicalStatusTabPageData clinicalStatusTabPageData = iClinicalStatusTab.findClinicalStatusTabData(tabularviewRequest, customJdbcConnection.getUserJdbcObject(httpSession, userSessionId), currentSessionUserToken);

        LOGGER.debug("Tabular View for ClinicalStatus Tab - findClinicalStatusTabData - Response received {} ", clinicalStatusTabPageData);

        return new ResponseEntity<>(clinicalStatusTabPageData, HttpStatus.OK);
    }
}

package com.excelra.mvc.web.tabularview;

import com.excelra.mvc.config.CustomJdbcConnection;
import com.excelra.mvc.exception.TabularServiceException;
import com.excelra.mvc.model.tabularview.TabularviewFilterRequest;
import com.excelra.mvc.model.tabularview.TabularviewRequest;
import com.excelra.mvc.model.tabularview.TargetDetailsTabDTO;
import com.excelra.mvc.model.tabularview.TargetDetailsTabPageData;
import com.excelra.mvc.service.tabularview.ITargetDetailsTab;
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
 * Target Details tab information
 *
 * @author venkateswarlu.s
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/security/tabularview")
public class TargetDetailsTabularviewController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TargetDetailsTabularviewController.class);

    @Autowired
    private ITargetDetailsTab iTargetDetailsTab;

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
    @RequestMapping(value = "/targetdetailstab/filterfield", method = RequestMethod.POST)
    public ResponseEntity<?> findTargetDetailsTabFilterFieldByTargetIds(@RequestBody TabularviewFilterRequest tabularviewFilterRequest, @RequestHeader("userSessionId") String userSessionId, HttpSession httpSession) throws TabularServiceException {

        String currentSessionUserToken = (String) httpSession.getAttribute(userSessionId+"_token");

        List<TargetDetailsTabDTO> targetDetailsTabFilterDto = iTargetDetailsTab.findTargetDetailsTabFilterFieldByTargetIds(tabularviewFilterRequest, customJdbcConnection.getUserJdbcObject(httpSession, userSessionId), currentSessionUserToken);

        LOGGER.debug("Tabular View filter field for TargetDetails Tab - targetDetailsTabFilterDto - Response received {} ", targetDetailsTabFilterDto);

        return new ResponseEntity<>(targetDetailsTabFilterDto, HttpStatus.OK);
    }

    /**
     *
     * @param tabularviewRequest
     * @param userSessionId
     * @param httpSession
     * @return
     * @throws TabularServiceException
     */
    @RequestMapping(value = "/targetdetailstab", method = RequestMethod.POST)
    public ResponseEntity<?> findTargetDetailsTabData(@RequestBody TabularviewRequest tabularviewRequest, @RequestHeader("userSessionId") String userSessionId, HttpSession httpSession) throws TabularServiceException {

        String currentSessionUserToken = (String) httpSession.getAttribute(userSessionId+"_token");

        TargetDetailsTabPageData targetDetailsTabPageData = iTargetDetailsTab.findTargetDetailsTabData(tabularviewRequest, customJdbcConnection.getUserJdbcObject(httpSession, userSessionId), currentSessionUserToken);

        LOGGER.debug("Tabular View for TargetDetails Tab - findTargetDetailsTabData - Response received {} ", targetDetailsTabPageData);

        return new ResponseEntity<>(targetDetailsTabPageData, HttpStatus.OK);
    }
}

package com.excelra.mvc.web.tabularview;

import com.excelra.mvc.config.CustomJdbcConnection;
import com.excelra.mvc.exception.TabularServiceException;
import com.excelra.mvc.model.tabularview.IndicationTabDTO;
import com.excelra.mvc.model.tabularview.IndicationTabPageData;
import com.excelra.mvc.model.tabularview.TabularviewFilterRequest;
import com.excelra.mvc.model.tabularview.TabularviewRequest;
import com.excelra.mvc.service.tabularview.IIndicationTab;
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
 * Indication tab information
 *
 * @author venkateswarlu.s
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/security/tabularview")
public class IndicationTabularviewController {

    private static final Logger LOGGER = LoggerFactory.getLogger(IndicationTabularviewController.class);

    @Autowired
    private IIndicationTab iIndicationTab;

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
    @RequestMapping(value = "/indicationtab/filterfield", method = RequestMethod.POST)
    public ResponseEntity<?> findIndicationTabFilterFieldByGvkIds(@RequestBody TabularviewFilterRequest tabularviewFilterRequest, @RequestHeader("userSessionId") String userSessionId, HttpSession httpSession) throws TabularServiceException {

        String currentSessionUserToken = (String) httpSession.getAttribute(userSessionId+"_token");

        List<IndicationTabDTO> indicationTabFilterDto = iIndicationTab.findIndicationTabFilterFieldByGvkIds(tabularviewFilterRequest, customJdbcConnection.getUserJdbcObject(httpSession, userSessionId), currentSessionUserToken);

        LOGGER.debug("Tabular View filter field for Indication Tab - indicationTabFilterDto - Response received {} ", indicationTabFilterDto);

        return new ResponseEntity<>(indicationTabFilterDto, HttpStatus.OK);
    }

    /**
     *
     * @param tabularviewRequest
     * @param userSessionId
     * @param httpSession
     * @return
     * @throws TabularServiceException
     */
    @RequestMapping(value = "/indicationtab", method = RequestMethod.POST)
    public ResponseEntity<?> findIndicationTabData(@RequestBody TabularviewRequest tabularviewRequest, @RequestHeader("userSessionId") String userSessionId, HttpSession httpSession) throws TabularServiceException {

        String currentSessionUserToken = (String) httpSession.getAttribute(userSessionId+"_token");

        IndicationTabPageData indicationTabPageData = iIndicationTab.findIndicationTabData(tabularviewRequest, customJdbcConnection.getUserJdbcObject(httpSession, userSessionId), currentSessionUserToken);

        LOGGER.debug("Tabular View for Indication Tab - findIndicationTabData - Response received {} ", indicationTabPageData);

        return new ResponseEntity<>(indicationTabPageData, HttpStatus.OK);
    }
}

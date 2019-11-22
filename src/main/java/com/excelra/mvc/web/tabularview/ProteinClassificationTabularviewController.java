package com.excelra.mvc.web.tabularview;

import com.excelra.mvc.config.CustomJdbcConnection;
import com.excelra.mvc.exception.TabularServiceException;
import com.excelra.mvc.model.tabularview.ProteinClassificationTabDTO;
import com.excelra.mvc.model.tabularview.ProteinClassificationTabPageData;
import com.excelra.mvc.model.tabularview.TabularviewFilterRequest;
import com.excelra.mvc.model.tabularview.TabularviewRequest;
import com.excelra.mvc.service.tabularview.IProteinClassificationTab;
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
 * Protein Classification tab information
 *
 * @author venkateswarlu.s
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/security/tabularview")
public class ProteinClassificationTabularviewController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProteinClassificationTabularviewController.class);

    @Autowired
    private IProteinClassificationTab iProteinClassificationTab;

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
    @RequestMapping(value = "/proteinclassificationtab/filterfield", method = RequestMethod.POST)
    public ResponseEntity<?> findProteinClassificationTabFilterFieldByStdnameIds(@RequestBody TabularviewFilterRequest tabularviewFilterRequest, @RequestHeader("userSessionId") String userSessionId, HttpSession httpSession) throws TabularServiceException {

        String currentSessionUserToken = (String) httpSession.getAttribute(userSessionId+"_token");

        List<ProteinClassificationTabDTO> proteinClassificationTabFilterDto = iProteinClassificationTab.findProteinClassificationTabFilterFieldByStdnameIds(tabularviewFilterRequest, customJdbcConnection.getUserJdbcObject(httpSession, userSessionId), currentSessionUserToken);

        LOGGER.debug("Tabular View filter field for ProteinClassification Tab - proteinClassificationTabFilterDto - Response received {} ", proteinClassificationTabFilterDto);

        return new ResponseEntity<>(proteinClassificationTabFilterDto, HttpStatus.OK);
    }

    /**
     *
     * @param tabularviewRequest
     * @param userSessionId
     * @param httpSession
     * @return
     * @throws TabularServiceException
     */
    @RequestMapping(value = "/proteinclassificationtab", method = RequestMethod.POST)
    public ResponseEntity<?> findProteinClassificationTabData(@RequestBody TabularviewRequest tabularviewRequest, @RequestHeader("userSessionId") String userSessionId, HttpSession httpSession) throws TabularServiceException {

        String currentSessionUserToken = (String) httpSession.getAttribute(userSessionId+"_token");

        ProteinClassificationTabPageData proteinClassificationTabPageData = iProteinClassificationTab.findProteinClassificationTabData(tabularviewRequest, customJdbcConnection.getUserJdbcObject(httpSession, userSessionId), currentSessionUserToken);

        LOGGER.debug("Tabular View for ProteinClassification Tab - findProteinClassificationTabData - Response received {} ", proteinClassificationTabPageData);

        return new ResponseEntity<>(proteinClassificationTabPageData, HttpStatus.OK);
    }
}

package com.excelra.mvc.web.tabularview;

import com.excelra.mvc.config.CustomJdbcConnection;
import com.excelra.mvc.exception.TabularServiceException;
import com.excelra.mvc.model.tabularview.Scaffold2TabDTO;
import com.excelra.mvc.model.tabularview.Scaffold2TabPageData;
import com.excelra.mvc.model.tabularview.TabularviewFilterRequest;
import com.excelra.mvc.model.tabularview.TabularviewRequest;
import com.excelra.mvc.service.tabularview.IScaffold2Tab;
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
 * Scaffold2 tab information
 *
 * @author Venkat Salagrama
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/security/tabularview")
public class Scaffold2TabularviewController {

    private static final Logger LOGGER = LoggerFactory.getLogger(Scaffold2TabularviewController.class);

    @Autowired
    private IScaffold2Tab iScaffold2Tab;

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
    @RequestMapping(value = "/scaffold2tab/filterfield", method = RequestMethod.POST)
    public ResponseEntity<?> findScaffold2TabFilterFieldByGvkIds(@RequestBody TabularviewFilterRequest tabularviewFilterRequest, @RequestHeader("userSessionId") String userSessionId, HttpSession httpSession) throws TabularServiceException {

        String currentSessionUserToken = (String) httpSession.getAttribute(userSessionId+"_token");

        List<Scaffold2TabDTO> scaffold2TabDTOList = iScaffold2Tab.findScaffold2TabFilterFieldByGvkIds(tabularviewFilterRequest, customJdbcConnection.getUserJdbcObject(httpSession, userSessionId), currentSessionUserToken);

        LOGGER.debug("Tabular View filter field for Scaffold2 Tab - scaffold2TabDTOList - Response received {} ", scaffold2TabDTOList);

        return new ResponseEntity<>(scaffold2TabDTOList, HttpStatus.OK);
    }

    /**
     *
     * @param tabularviewRequest
     * @param userSessionId
     * @param httpSession
     * @return
     * @throws TabularServiceException
     */
    @RequestMapping(value = "/scaffold2tab", method = RequestMethod.POST)
    public ResponseEntity<?> findScaffold2TabData(@RequestBody TabularviewRequest tabularviewRequest, @RequestHeader("userSessionId") String userSessionId, HttpSession httpSession) throws TabularServiceException {

        String currentSessionUserToken = (String) httpSession.getAttribute(userSessionId+"_token");

        Scaffold2TabPageData scaffold2TabPageData = iScaffold2Tab.findScaffold2TabData(tabularviewRequest, customJdbcConnection.getUserJdbcObject(httpSession, userSessionId), currentSessionUserToken);

        LOGGER.debug("Tabular View for Scaffold2 Tab - scaffold2TabPageData - Response received {} ", scaffold2TabPageData);

        return new ResponseEntity<>(scaffold2TabPageData, HttpStatus.OK);
    }
}

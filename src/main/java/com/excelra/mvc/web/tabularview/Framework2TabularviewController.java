package com.excelra.mvc.web.tabularview;

import com.excelra.mvc.config.CustomJdbcConnection;
import com.excelra.mvc.exception.TabularServiceException;
import com.excelra.mvc.model.tabularview.Framework2TabDTO;
import com.excelra.mvc.model.tabularview.Framework2TabPageData;
import com.excelra.mvc.model.tabularview.TabularviewFilterRequest;
import com.excelra.mvc.model.tabularview.TabularviewRequest;
import com.excelra.mvc.service.tabularview.IFramework2Tab;
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
 * Framework2 tab information
 *
 * @author Venkat Salagrama
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/security/tabularview")
public class Framework2TabularviewController {

    private static final Logger LOGGER = LoggerFactory.getLogger(Framework2TabularviewController.class);

    @Autowired
    private IFramework2Tab iFramework2Tab;

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
    @RequestMapping(value = "/framework2tab/filterfield", method = RequestMethod.POST)
    public ResponseEntity<?> findFramework2TabFilterFieldByGvkIds(@RequestBody TabularviewFilterRequest tabularviewFilterRequest, @RequestHeader("userSessionId") String userSessionId, HttpSession httpSession) throws TabularServiceException {

        String currentSessionUserToken = (String) httpSession.getAttribute(userSessionId+"_token");

        List<Framework2TabDTO> framework2TabDTOList = iFramework2Tab.findFramework2TabFilterFieldByGvkIds(tabularviewFilterRequest, customJdbcConnection.getUserJdbcObject(httpSession, userSessionId), currentSessionUserToken);

        LOGGER.debug("Tabular View filter field for Framework2 Tab - framework2TabDTOList - Response received {} ", framework2TabDTOList);

        return new ResponseEntity<>(framework2TabDTOList, HttpStatus.OK);
    }

    /**
     *
     * @param tabularviewRequest
     * @param userSessionId
     * @param httpSession
     * @return
     * @throws TabularServiceException
     */
    @RequestMapping(value = "/framework2tab", method = RequestMethod.POST)
    public ResponseEntity<?> findFramework2TabData(@RequestBody TabularviewRequest tabularviewRequest, @RequestHeader("userSessionId") String userSessionId, HttpSession httpSession) throws TabularServiceException {

        String currentSessionUserToken = (String) httpSession.getAttribute(userSessionId+"_token");

        Framework2TabPageData framework2TabPageData = iFramework2Tab.findFramework2TabData(tabularviewRequest, customJdbcConnection.getUserJdbcObject(httpSession, userSessionId), currentSessionUserToken);

        LOGGER.debug("Tabular View for Framework2 Tab - framework2TabPageData - Response received {} ", framework2TabPageData);

        return new ResponseEntity<>(framework2TabPageData, HttpStatus.OK);
    }
}

package com.excelra.mvc.web.tabularview;

import com.excelra.mvc.config.CustomJdbcConnection;
import com.excelra.mvc.exception.TabularServiceException;
import com.excelra.mvc.model.tabularview.*;
import com.excelra.mvc.service.tabularview.IActivityTab;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * This class is used to get the Tabular data for Activity
 * tab information
 *
 * @author Venkat Salagrama
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/security/tabularview")
public class ActivityTabularviewController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ActivityTabularviewController.class);

    @Autowired
    private IActivityTab activityTabDAO;

    @Autowired
    private CustomJdbcConnection customJdbcConnection;

    /**
     * To Get Filter field autofill based on ActIds data.
     *
     * @param tabularviewFilterRequest
     * @return
     */
    @RequestMapping(value = "/activitytab/filterfield", method = RequestMethod.POST)
    public ResponseEntity<?> findActivityTabFilterFieldByActIds(@RequestBody TabularviewFilterRequest tabularviewFilterRequest, @RequestHeader("userSessionId") String userSessionId, HttpSession httpSession) throws TabularServiceException  {

        String currentSessionUserToken = (String) httpSession.getAttribute(userSessionId+"_token");

        List<ActivityTabDTO> activityTabFilterDto = activityTabDAO.findActivityTabFilterFieldByActIds(tabularviewFilterRequest, customJdbcConnection.getUserJdbcObject(httpSession, userSessionId), currentSessionUserToken);

        LOGGER.debug("Tabular View filter field for Activity Tab - activityTabFilterDto - Response received {} ", activityTabFilterDto);

        return new ResponseEntity<>(activityTabFilterDto, HttpStatus.OK);
    }

    /**
     * To Get Activity Tab data by ActIds from Search Criteria data available in Temp table.
     *
     * @param tabularviewRequest
     * @return
     */
    @RequestMapping(value = "/activitytab", method = RequestMethod.POST)
    public ResponseEntity<?> findActivityTabData(@RequestBody TabularviewRequest tabularviewRequest, @RequestHeader("userSessionId") String userSessionId, HttpSession httpSession) throws TabularServiceException {

        String currentSessionUserToken = (String) httpSession.getAttribute(userSessionId+"_token");

        ActivityTabPageData activityTabPageData = activityTabDAO.findActivityTabData(tabularviewRequest, customJdbcConnection.getUserJdbcObject(httpSession, userSessionId), currentSessionUserToken);

        LOGGER.debug("Tabular View for Activity Tab - findActivityTabData - Response received {} ", activityTabPageData);

        return new ResponseEntity<>(activityTabPageData, HttpStatus.OK);
    }

}

package com.excelra.mvc.web.tabularview;

import com.excelra.mvc.config.CustomJdbcConnection;
import com.excelra.mvc.exception.TabularServiceException;
import com.excelra.mvc.model.tabularview.SkeletonTabDTO;
import com.excelra.mvc.model.tabularview.SkeletonTabPageData;
import com.excelra.mvc.model.tabularview.TabularviewFilterRequest;
import com.excelra.mvc.model.tabularview.TabularviewRequest;
import com.excelra.mvc.service.tabularview.ISkeletonTab;
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
public class SkeletonTabularviewController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SkeletonTabularviewController.class);

    @Autowired
    private ISkeletonTab iSkeletonTab;

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
    @RequestMapping(value = "/skeletontab/filterfield", method = RequestMethod.POST)
    public ResponseEntity<?> findSkeletonTabFilterFieldByGvkIds(@RequestBody TabularviewFilterRequest tabularviewFilterRequest, @RequestHeader("userSessionId") String userSessionId, HttpSession httpSession) throws TabularServiceException {

        String currentSessionUserToken = (String) httpSession.getAttribute(userSessionId+"_token");

        List<SkeletonTabDTO> skeletonTabDTOList = iSkeletonTab.findSkeletonTabFilterFieldByGvkIds(tabularviewFilterRequest, customJdbcConnection.getUserJdbcObject(httpSession, userSessionId), currentSessionUserToken);

        LOGGER.debug("Tabular View filter field for Skeleton Tab - skeletonTabDTOList - Response received {} ", skeletonTabDTOList);

        return new ResponseEntity<>(skeletonTabDTOList, HttpStatus.OK);
    }

    /**
     *
     * @param tabularviewRequest
     * @param userSessionId
     * @param httpSession
     * @return
     * @throws TabularServiceException
     */
    @RequestMapping(value = "/skeletontab", method = RequestMethod.POST)
    public ResponseEntity<?> findSkeletonTabData(@RequestBody TabularviewRequest tabularviewRequest, @RequestHeader("userSessionId") String userSessionId, HttpSession httpSession) throws TabularServiceException {

        String currentSessionUserToken = (String) httpSession.getAttribute(userSessionId+"_token");

        SkeletonTabPageData skeletonTabPageData = iSkeletonTab.findSkeletonTabData(tabularviewRequest, customJdbcConnection.getUserJdbcObject(httpSession, userSessionId), currentSessionUserToken);

        LOGGER.debug("Tabular View for Skeleton Tab - skeletonTabPageData - Response received {} ", skeletonTabPageData);

        return new ResponseEntity<>(skeletonTabPageData, HttpStatus.OK);
    }
}

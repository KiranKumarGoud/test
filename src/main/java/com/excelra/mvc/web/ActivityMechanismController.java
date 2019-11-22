package com.excelra.mvc.web;

import com.excelra.mvc.config.CustomJdbcConnection;
import com.excelra.mvc.exception.ServiceException;
import com.excelra.mvc.model.ListActivityMechanismDTO;
import com.excelra.mvc.service.IActivityMechanism;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * <p>
 *  Activity Mechanism Controller
 * <p>
 *
 * @author venkateswarlu.s
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
@RequestMapping(value = "/security/activity/mechanism")
public class ActivityMechanismController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ActivityMechanismController.class);

    @Autowired
    private CustomJdbcConnection customJdbcConnection;

    @Autowired
    private IActivityMechanism iActivityMechanism;

    /**
     *
     * @param userSessionId
     * @param httpSession
     * @return
     * @throws ServiceException
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = {"application/json"})
    @ResponseBody
    public ResponseEntity<?> fetchActivityMechanism(@RequestHeader("userSessionId") String userSessionId, HttpSession httpSession) throws ServiceException {
        LOGGER.info("fetch ActivityMechanism service");

        List<ListActivityMechanismDTO> listActivityMechanismDTOs = iActivityMechanism.fetchActivityMechanism(customJdbcConnection.getUserJdbcObject(httpSession, userSessionId));

        return new ResponseEntity<>(listActivityMechanismDTOs, HttpStatus.OK);
    }
}

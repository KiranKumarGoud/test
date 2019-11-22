package com.excelra.mvc.web;

import com.excelra.mvc.config.CustomJdbcConnection;
import com.excelra.mvc.exception.ServiceException;
import com.excelra.mvc.model.ActivityLabelValueDTO;
import com.excelra.mvc.model.ListActivityTypesDTO;
import com.excelra.mvc.service.IActivityTypes;
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
 *  Activity Type Controller
 * <p>
 *
 * @author venkateswarlu.s
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
@RequestMapping(value = "/security/activity/type")
public class ActivityTypeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ActivityTypeController.class);

    @Autowired
    private IActivityTypes iActivityTypes;

    @Autowired
    private CustomJdbcConnection customJdbcConnection;

    /**
     *
     * @param activityType
     * @param userSessionId
     * @param httpSession
     * @return
     * @throws ServiceException
     */
    @RequestMapping(value = "/byactivitytype/{activityType}", method = RequestMethod.GET, produces = {"application/json"})
    @ResponseBody
    public ResponseEntity<?> fetchActivityTypeContaining(@PathVariable String activityType, @RequestHeader("userSessionId") String userSessionId, HttpSession httpSession) throws ServiceException {
        LOGGER.info("fetch ActivityType service");

        List<ListActivityTypesDTO> listActivityTypesDTOs = iActivityTypes.fetchActivityTypeContaining(activityType, customJdbcConnection.getUserJdbcObject(httpSession, userSessionId));

        return new ResponseEntity<>(listActivityTypesDTOs, HttpStatus.OK);
    }

    /**
     *
     * @param activityType
     * @param userSessionId
     * @param httpSession
     * @return
     * @throws ServiceException
     */
    @RequestMapping(value = "/bylabelvalue/type/{activityType}", method = RequestMethod.GET, produces = {"application/json"})
    @ResponseBody
    public ResponseEntity<?> fetchActivityTypeLabelValue(@PathVariable String activityType, @RequestHeader("userSessionId") String userSessionId, HttpSession httpSession) throws ServiceException {
        LOGGER.info("fetch ActivityType service");

        List<ActivityLabelValueDTO> activityLabelValueDTOs = iActivityTypes.fetchActivityTypeLabelValue(activityType, customJdbcConnection.getUserJdbcObject(httpSession, userSessionId));

        return new ResponseEntity<>(activityLabelValueDTOs, HttpStatus.OK);
    }

    /**
     *
     * @param activityType
     * @param userSessionId
     * @param httpSession
     * @return
     * @throws ServiceException
     */
    @RequestMapping(value = "/bylabelvalue/uom/{activityType}", method = RequestMethod.GET, produces = {"application/json"})
    @ResponseBody
    public ResponseEntity<?> fetchActivityUomLabelValue(@PathVariable String activityType, @RequestHeader("userSessionId") String userSessionId, HttpSession httpSession) throws ServiceException {
        LOGGER.info("fetch ActivityType service");

        List<ActivityLabelValueDTO> activityLabelValueDTOs = iActivityTypes.fetchActivityUomLabelValue(activityType, customJdbcConnection.getUserJdbcObject(httpSession, userSessionId));

        return new ResponseEntity<>(activityLabelValueDTOs, HttpStatus.OK);
    }

    /**
     *
     * @param activityType
     * @param userSessionId
     * @param httpSession
     * @return
     * @throws ServiceException
     */
    @RequestMapping(value = "/bylabelvalue/prefix/{activityType}", method = RequestMethod.GET, produces = {"application/json"})
    @ResponseBody
    public ResponseEntity<?> fetchActivityPrefixLabelValue(@PathVariable String activityType, @RequestHeader("userSessionId") String userSessionId, HttpSession httpSession) throws ServiceException {
        LOGGER.info("fetch ActivityType service");

        List<ActivityLabelValueDTO> activityLabelValueDTOs = iActivityTypes.fetchActivityPrefixLabelValue(activityType, customJdbcConnection.getUserJdbcObject(httpSession, userSessionId));

        return new ResponseEntity<>(activityLabelValueDTOs, HttpStatus.OK);
    }

    /**
     *
     * @param userSessionId
     * @param httpSession
     * @return
     * @throws ServiceException
     */
    @RequestMapping(value = "/bylabelvalue/allprefixes", method = RequestMethod.GET, produces = {"application/json"})
    @ResponseBody
    public ResponseEntity<?> fetchActivityPrefixValues(@RequestHeader("userSessionId") String userSessionId, HttpSession httpSession) throws ServiceException {
        LOGGER.info("fetch ActivityType service");

        List<ActivityLabelValueDTO> activityLabelValueDTOs = iActivityTypes.fetchActivityPrefixValues();

        return new ResponseEntity<>(activityLabelValueDTOs, HttpStatus.OK);
    }
}

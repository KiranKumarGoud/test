package com.excelra.mvc.web.treeview;

import com.excelra.mvc.config.CustomJdbcConnection;
import com.excelra.mvc.exception.ServiceException;
import com.excelra.mvc.model.search.treeview.EndpointTreeviewParentChildNodeDTO;
import com.excelra.mvc.model.treeview.EndpointTreeviewDTO;
import com.excelra.mvc.model.treeview.EndpointMasterDTO;
import com.excelra.mvc.service.treeview.IEndpointTreeview;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * <p>
 *  Activity Endpoint Treeview Controller
 * <p>
 *
 * @author venkateswarlu.s
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
@RequestMapping(value = "/security/treeview")
public class EndpointTreeviewController {

    private static final Logger LOGGER = LoggerFactory.getLogger(EndpointTreeviewController.class);

    @Autowired
    private IEndpointTreeview iEndpointTreeview;

    @Autowired
    private CustomJdbcConnection customJdbcConnection;

    /**
     *
     * @param endpointId
     * @param httpSession
     * @param userSessionId
     * @return
     * @throws ServiceException
     */
    @RequestMapping(value = "/activityendpoint/{endpointId}", method = RequestMethod.GET, produces = { "application/json" })
    @ResponseBody
    public ResponseEntity<?> prepareTreeviewForNode(@PathVariable Long endpointId, HttpSession httpSession, @RequestHeader("userSessionId") String userSessionId) throws ServiceException {
        LOGGER.info("ActivityEndpoint :: prepareTreeviewForNode");

        List<EndpointTreeviewDTO> endpointTreeviewDTOList = iEndpointTreeview.prepareTreeviewForNode(endpointId, customJdbcConnection.getUserJdbcObject(httpSession, userSessionId));

        return new ResponseEntity<>(endpointTreeviewDTOList, HttpStatus.OK);
    }

    /**
     *
     * @param endpointName
     * @param httpSession
     * @param userSessionId
     * @return
     * @throws ServiceException
     */
    @RequestMapping(value = "/activityendpoint/search/{endpointName}", method = RequestMethod.GET, produces = { "application/json" })
    @ResponseBody
    public ResponseEntity<?> prepareTreeviewForEndpointName(@PathVariable String endpointName, HttpSession httpSession, @RequestHeader("userSessionId") String userSessionId) throws ServiceException {
        LOGGER.info("prepareTreeviewForEndpointName");

        List<EndpointTreeviewParentChildNodeDTO> endpointTreeviewParentChildNodeDTOs = iEndpointTreeview.prepareTreeviewForEndpointName(endpointName, customJdbcConnection.getUserJdbcObject(httpSession, userSessionId));

        return new ResponseEntity<>(endpointTreeviewParentChildNodeDTOs, HttpStatus.OK);
    }

    /**
     *
     * @param httpSession
     * @param userSessionId
     * @return
     * @throws ServiceException
     */
    @RequestMapping(value = "/activityendpoint/parentlevel", method = RequestMethod.GET, produces = { "application/json" })
    @ResponseBody
    public ResponseEntity<?> prepareTreeviewForParentLevel(HttpSession httpSession, @RequestHeader("userSessionId") String userSessionId) throws ServiceException {
        LOGGER.info("prepareTreeviewForParentLevel");

        List<EndpointTreeviewParentChildNodeDTO> endpointTreeviewParentChildNodeDTOs = iEndpointTreeview.prepareTreeviewForParentLevel(customJdbcConnection.getUserJdbcObject(httpSession, userSessionId));

        return new ResponseEntity<>(endpointTreeviewParentChildNodeDTOs, HttpStatus.OK);
    }

    /**
     *
     * @param endpointName
     * @param httpSession
     * @param userSessionId
     * @return
     * @throws ServiceException
     */
    @RequestMapping(value = "/activityendpoint/searchbyendpointname/{endpointName}", method = RequestMethod.GET, produces = { "application/json" })
    @ResponseBody
    public ResponseEntity<?> fetchEndpointEndpointNameByContaining(@PathVariable String endpointName, HttpSession httpSession, @RequestHeader("userSessionId") String userSessionId) throws ServiceException {
        LOGGER.info("fetchEndpointEndpointNameByContaining fetch service");

        List<EndpointMasterDTO> endpointMasterDTOList = iEndpointTreeview.fetchEndpointEndpointNameByContaining(endpointName, customJdbcConnection.getUserJdbcObject(httpSession, userSessionId));

        return new ResponseEntity<>(endpointMasterDTOList, HttpStatus.OK);
    }

    /**
     *
     * @param endpointNameList
     * @param httpSession
     * @param userSessionId
     * @return
     * @throws ServiceException
     */
    @RequestMapping(value = "/activityendpoint/byendpointnamelist", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<?> prepareTreeviewForSelectedEndpointNames(@RequestBody List<String> endpointNameList, HttpSession httpSession, @RequestHeader("userSessionId") String userSessionId) throws ServiceException
    {
        LOGGER.info("prepareTreeviewForSelectedEndpointNames {} ", endpointNameList.size());

        System.out.println("===========> "+userSessionId);
        List<EndpointTreeviewParentChildNodeDTO> endpointTreeviewParentChildNodeDTOS = iEndpointTreeview.prepareTreeviewForSelectedEndpointNames(endpointNameList, customJdbcConnection.getUserJdbcObject(httpSession, userSessionId));

        return new ResponseEntity<>(endpointTreeviewParentChildNodeDTOS, HttpStatus.OK);
    }
}

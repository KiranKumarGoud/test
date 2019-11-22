package com.excelra.mvc.web.treeview;

import com.excelra.mvc.config.CustomJdbcConnection;
import com.excelra.mvc.exception.ServiceException;
import com.excelra.mvc.model.search.treeview.TargetTreeviewParentChildNodeDTO;
import com.excelra.mvc.model.treeview.TargetOntologyMasterDTO;
import com.excelra.mvc.model.treeview.TargetTreeviewDTO;
import com.excelra.mvc.service.treeview.ITargetTreeview;
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
 *  Target Treeview Controller
 * <p>
 *
 * @author venkateswarlu.s
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
@RequestMapping(value = "/security/treeview")
public class TargetTreeviewController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TargetTreeviewController.class);

    @Autowired
    private ITargetTreeview iTargetTreeview;

    @Autowired
    private CustomJdbcConnection customJdbcConnection;

    /**
     *
     * @param ontologyId
     * @param httpSession
     * @param userSessionId
     * @return
     * @throws ServiceException
     */
    @RequestMapping(value = "/target/{ontologyId}", method = RequestMethod.GET, produces = { "application/json" })
    @ResponseBody
    public ResponseEntity<?> prepareTreeviewForNode(@PathVariable Long ontologyId, HttpSession httpSession, @RequestHeader("userSessionId") String userSessionId) throws ServiceException {
        LOGGER.info("Target fetch service");

        List<TargetTreeviewDTO> targetTreeviewDTOList = iTargetTreeview.prepareTreeviewForNode(ontologyId, customJdbcConnection.getUserJdbcObject(httpSession, userSessionId));

        return new ResponseEntity<>(targetTreeviewDTOList, HttpStatus.OK);
    }

    /**
     *
     * @param targetName
     * @param httpSession
     * @param userSessionId
     * @return
     * @throws ServiceException
     */
    @RequestMapping(value = "/target/search/{targetName}", method = RequestMethod.GET, produces = { "application/json" })
    @ResponseBody
    public ResponseEntity<?> prepareTreeviewForTargetName(@PathVariable String targetName, HttpSession httpSession, @RequestHeader("userSessionId") String userSessionId) throws ServiceException {
        LOGGER.info("Target fetch service");

        List<TargetTreeviewParentChildNodeDTO> targetTreeviewParentChildNodeDTOs = iTargetTreeview.prepareTreeviewForTargetName(targetName, customJdbcConnection.getUserJdbcObject(httpSession, userSessionId));

        return new ResponseEntity<>(targetTreeviewParentChildNodeDTOs, HttpStatus.OK);
    }

    /**
     *
     * @param httpSession
     * @param userSessionId
     * @return
     * @throws ServiceException
     */
    @RequestMapping(value = "/target/parentlevel", method = RequestMethod.GET, produces = { "application/json" })
    @ResponseBody
    public ResponseEntity<?> prepareTreeviewForParentLevel(HttpSession httpSession, @RequestHeader("userSessionId") String userSessionId) throws ServiceException {
        LOGGER.info("Target fetch service");

        List<TargetTreeviewParentChildNodeDTO> targetTreeviewParentChildNodeDTOs = iTargetTreeview.prepareTreeviewForParentLevel(customJdbcConnection.getUserJdbcObject(httpSession, userSessionId));

        return new ResponseEntity<>(targetTreeviewParentChildNodeDTOs, HttpStatus.OK);
    }

    /**
     *
     * @param targetName
     * @param httpSession
     * @param userSessionId
     * @return
     * @throws ServiceException
     */
    @RequestMapping(value = "/target/searchbytargetname/{targetName}", method = RequestMethod.GET, produces = { "application/json" })
    @ResponseBody
    public ResponseEntity<?> fetchTargetOntologyTargetNameByContaining(@PathVariable String targetName, HttpSession httpSession, @RequestHeader("userSessionId") String userSessionId) throws ServiceException {
        LOGGER.info("TargetOntologyMasterDTO fetch service");

        List<TargetOntologyMasterDTO> targetOntologyMasterDTOList = iTargetTreeview.fetchTargetOntologyTargetNameByContaining(targetName, customJdbcConnection.getUserJdbcObject(httpSession, userSessionId));

        return new ResponseEntity<>(targetOntologyMasterDTOList, HttpStatus.OK);
    }


    /**
     *
     * @param targetNameList
     * @param httpSession
     * @param userSessionId
     * @return
     * @throws ServiceException
     */
    @RequestMapping(value = "/target/bytargetnamelist", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<?> prepareTreeviewForSelectedTargetNames(@RequestBody List<String> targetNameList, HttpSession httpSession, @RequestHeader("userSessionId") String userSessionId) throws ServiceException
    {
        LOGGER.info("prepareTreeviewForSelectedTargetNames {} ", targetNameList.size());

        System.out.println("===========> "+userSessionId);
        List<TargetTreeviewParentChildNodeDTO> targetTreeviewParentChildNodeDTOS = iTargetTreeview.prepareTreeviewForSelectedTargetNames(targetNameList, customJdbcConnection.getUserJdbcObject(httpSession, userSessionId));

        return new ResponseEntity<>(targetTreeviewParentChildNodeDTOS, HttpStatus.OK);
    }
}

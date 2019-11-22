package com.excelra.mvc.web.treeview;

import com.excelra.mvc.config.CustomJdbcConnection;
import com.excelra.mvc.exception.ServiceException;
import com.excelra.mvc.model.search.treeview.AdmeTreeviewParentChildNodeDTO;
import com.excelra.mvc.model.treeview.AdmeOntologyMasterDTO;
import com.excelra.mvc.model.treeview.AdmeTreeviewDTO;
import com.excelra.mvc.service.treeview.IAdmeTreeview;
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
 *  Adme Treeview Controller
 * <p>
 *
 * @author venkateswarlu.s
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
@RequestMapping(value = "/security/treeview")
public class AdmeTreeviewController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AdmeTreeviewController.class);

    @Autowired
    private IAdmeTreeview iAdmeTreeview;

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
    @RequestMapping(value = "/adme/{ontologyId}", method = RequestMethod.GET, produces = { "application/json" })
    @ResponseBody
    public ResponseEntity<?> prepareTreeviewForNode(@PathVariable Long ontologyId, HttpSession httpSession, @RequestHeader("userSessionId") String userSessionId) throws ServiceException {
        LOGGER.info("Adme fetch service");

        List<AdmeTreeviewDTO> admeTreeviewDTOList = iAdmeTreeview.prepareTreeviewForNode(ontologyId, customJdbcConnection.getUserJdbcObject(httpSession, userSessionId));

        return new ResponseEntity<>(admeTreeviewDTOList, HttpStatus.OK);
    }

    /**
     *
     * @param admeName
     * @param httpSession
     * @param userSessionId
     * @return
     * @throws ServiceException
     */
    @RequestMapping(value = "/adme/search/{admeName}", method = RequestMethod.GET, produces = { "application/json" })
    @ResponseBody
    public ResponseEntity<?> prepareTreeviewForAdmeName(@PathVariable String admeName, HttpSession httpSession, @RequestHeader("userSessionId") String userSessionId) throws ServiceException {
        LOGGER.info("Adme fetch service");

        List<AdmeTreeviewParentChildNodeDTO> admeTreeviewParentChildNodeDTOs = iAdmeTreeview.prepareTreeviewForAdmeName(admeName, customJdbcConnection.getUserJdbcObject(httpSession, userSessionId));

        return new ResponseEntity<>(admeTreeviewParentChildNodeDTOs, HttpStatus.OK);
    }

    /**
     *
     * @param httpSession
     * @param userSessionId
     * @return
     * @throws ServiceException
     */
    @RequestMapping(value = "/adme/parentlevel", method = RequestMethod.GET, produces = { "application/json" })
    @ResponseBody
    public ResponseEntity<?> prepareTreeviewForParentLevel(HttpSession httpSession, @RequestHeader("userSessionId") String userSessionId) throws ServiceException {
        LOGGER.info("Adme fetch service");

        List<AdmeTreeviewParentChildNodeDTO> admeTreeviewParentChildNodeDTOs = iAdmeTreeview.prepareTreeviewForParentLevel(customJdbcConnection.getUserJdbcObject(httpSession, userSessionId));

        return new ResponseEntity<>(admeTreeviewParentChildNodeDTOs, HttpStatus.OK);
    }

    /**
     *
     * @param admeName
     * @param httpSession
     * @param userSessionId
     * @return
     * @throws ServiceException
     */
    @RequestMapping(value = "/adme/searchbyadmename/{admeName}", method = RequestMethod.GET, produces = { "application/json" })
    @ResponseBody
    public ResponseEntity<?> fetchAdmeOntologyAdmeNameByContaining(@PathVariable String admeName, HttpSession httpSession, @RequestHeader("userSessionId") String userSessionId) throws ServiceException {
        LOGGER.info("fetchAdmeOntologyAdmeNameByContaining fetch service");

        List<AdmeOntologyMasterDTO> admeOntologyMasterDTOList = iAdmeTreeview.fetchAdmeOntologyAdmeNameByContaining(admeName, customJdbcConnection.getUserJdbcObject(httpSession, userSessionId));

        return new ResponseEntity<>(admeOntologyMasterDTOList, HttpStatus.OK);
    }

    /**
     *
     * @param admeNameList
     * @param httpSession
     * @param userSessionId
     * @return
     * @throws ServiceException
     */
    @RequestMapping(value = "/adme/byadmenamelist", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<?> prepareTreeviewForSelectedAdmeNames(@RequestBody List<String> admeNameList, HttpSession httpSession, @RequestHeader("userSessionId") String userSessionId) throws ServiceException
    {
        LOGGER.info("prepareTreeviewForSelectedAdmeNames {} ", admeNameList.size());

        System.out.println("===========> "+userSessionId);
        List<AdmeTreeviewParentChildNodeDTO> admeTreeviewParentChildNodeDTOS = iAdmeTreeview.prepareTreeviewForSelectedAdmeNames(admeNameList, customJdbcConnection.getUserJdbcObject(httpSession, userSessionId));

        return new ResponseEntity<>(admeTreeviewParentChildNodeDTOS, HttpStatus.OK);
    }
}

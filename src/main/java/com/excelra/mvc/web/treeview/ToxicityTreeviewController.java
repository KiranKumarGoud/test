package com.excelra.mvc.web.treeview;

import com.excelra.mvc.config.CustomJdbcConnection;
import com.excelra.mvc.exception.ServiceException;
import com.excelra.mvc.model.search.treeview.ToxicityTreeviewParentChildNodeDTO;
import com.excelra.mvc.model.treeview.ToxicityOntologyMasterDTO;
import com.excelra.mvc.model.treeview.ToxicityTreeviewDTO;
import com.excelra.mvc.service.treeview.IToxicityTreeview;
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
 *  Toxicity Treeview Controller
 * <p>
 *
 * @author venkateswarlu.s
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
@RequestMapping(value = "/security/treeview")
public class ToxicityTreeviewController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ToxicityTreeviewController.class);

    @Autowired
    private IToxicityTreeview iToxicityTreeview;

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
    @RequestMapping(value = "/toxicity/{ontologyId}", method = RequestMethod.GET, produces = { "application/json" })
    @ResponseBody
    public ResponseEntity<?> prepareTreeviewForNode(@PathVariable Long ontologyId, HttpSession httpSession, @RequestHeader("userSessionId") String userSessionId) throws ServiceException {
        LOGGER.info("Toxicity fetch service");

        List<ToxicityTreeviewDTO> toxicityTreeviewDTOList = iToxicityTreeview.prepareTreeviewForNode(ontologyId, customJdbcConnection.getUserJdbcObject(httpSession, userSessionId));

        return new ResponseEntity<>(toxicityTreeviewDTOList, HttpStatus.OK);
    }

    /**
     *
     * @param toxicityName
     * @param httpSession
     * @param userSessionId
     * @return
     * @throws ServiceException
     */
    @RequestMapping(value = "/toxicity/search/{toxicityName}", method = RequestMethod.GET, produces = { "application/json" })
    @ResponseBody
    public ResponseEntity<?> prepareTreeviewForToxicityName(@PathVariable String toxicityName, HttpSession httpSession, @RequestHeader("userSessionId") String userSessionId) throws ServiceException {
        LOGGER.info("Toxicity fetch service");

        List<ToxicityTreeviewParentChildNodeDTO> toxicityTreeviewParentChildNodeDTOs = iToxicityTreeview.prepareTreeviewForToxicityName(toxicityName, customJdbcConnection.getUserJdbcObject(httpSession, userSessionId));

        return new ResponseEntity<>(toxicityTreeviewParentChildNodeDTOs, HttpStatus.OK);
    }

    /**
     *
     * @param httpSession
     * @param userSessionId
     * @return
     * @throws ServiceException
     */
    @RequestMapping(value = "/toxicity/parentlevel", method = RequestMethod.GET, produces = { "application/json" })
    @ResponseBody
    public ResponseEntity<?> prepareTreeviewForParentLevel(HttpSession httpSession, @RequestHeader("userSessionId") String userSessionId) throws ServiceException {
        LOGGER.info("Toxicity fetch service");

        List<ToxicityTreeviewParentChildNodeDTO> toxicityTreeviewParentChildNodeDTOs = iToxicityTreeview.prepareTreeviewForParentLevel(customJdbcConnection.getUserJdbcObject(httpSession, userSessionId));

        return new ResponseEntity<>(toxicityTreeviewParentChildNodeDTOs, HttpStatus.OK);
    }

    /**
     *
     * @param toxicityName
     * @param httpSession
     * @param userSessionId
     * @return
     * @throws ServiceException
     */
    @RequestMapping(value = "/toxicity/searchbytoxicityname/{toxicityName}", method = RequestMethod.GET, produces = { "application/json" })
    @ResponseBody
    public ResponseEntity<?> fetchToxicityOntologyToxicityNameByContaining(@PathVariable String toxicityName, HttpSession httpSession, @RequestHeader("userSessionId") String userSessionId) throws ServiceException {
        LOGGER.info("fetchToxicityOntologyToxicityNameByContaining fetch service");

        List<ToxicityOntologyMasterDTO> toxicityOntologyMasterDTOList = iToxicityTreeview.fetchToxicityOntologyToxicityNameByContaining(toxicityName, customJdbcConnection.getUserJdbcObject(httpSession, userSessionId));

        return new ResponseEntity<>(toxicityOntologyMasterDTOList, HttpStatus.OK);
    }

    /**
     *
     * @param toxicityNameList
     * @param httpSession
     * @param userSessionId
     * @return
     * @throws ServiceException
     */
    @RequestMapping(value = "/toxicity/bytoxicitynamelist", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<?> prepareTreeviewForSelectedToxicityNames(@RequestBody List<String> toxicityNameList, HttpSession httpSession, @RequestHeader("userSessionId") String userSessionId) throws ServiceException
    {
        LOGGER.info("prepareTreeviewForSelectedToxicityNames {} ", toxicityNameList.size());

        System.out.println("===========> "+userSessionId);
        List<ToxicityTreeviewParentChildNodeDTO> toxicityTreeviewParentChildNodeDTOS = iToxicityTreeview.prepareTreeviewForSelectedToxicityNames(toxicityNameList, customJdbcConnection.getUserJdbcObject(httpSession, userSessionId));

        return new ResponseEntity<>(toxicityTreeviewParentChildNodeDTOS, HttpStatus.OK);
    }
}

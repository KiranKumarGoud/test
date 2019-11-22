package com.excelra.mvc.web.treeview;

import com.excelra.mvc.config.CustomJdbcConnection;
import com.excelra.mvc.exception.ServiceException;
import com.excelra.mvc.model.search.treeview.IndicationTreeviewParentChildNodeDTO;
import com.excelra.mvc.model.treeview.IndicationOntologyMasterDTO;
import com.excelra.mvc.model.treeview.IndicationTreeviewDTO;
import com.excelra.mvc.service.treeview.IIndicationTreeview;
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
 *  Indication Treeview Controller
 * <p>
 *
 * @author venkateswarlu.s
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
@RequestMapping(value = "/security/treeview")
public class IndicationTreeviewController {

    private static final Logger LOGGER = LoggerFactory.getLogger(IndicationTreeviewController.class);

    @Autowired
    private IIndicationTreeview iIndicationTreeview;

    @Autowired
    private CustomJdbcConnection customJdbcConnection;

    /**
     *
     * @param icd10Code
     * @param httpSession
     * @param userSessionId
     * @return
     * @throws ServiceException
     */
    @RequestMapping(value = "/indication/{icd10Code}", method = RequestMethod.GET, produces = { "application/json" })
    @ResponseBody
    public ResponseEntity<?> prepareTreeviewForNode(@PathVariable String icd10Code, HttpSession httpSession, @RequestHeader("userSessionId") String userSessionId) throws ServiceException {
        LOGGER.info("Indication fetch service");

        List<IndicationTreeviewDTO> indicationTreeviewDTOList = iIndicationTreeview.prepareTreeviewForNode(icd10Code, customJdbcConnection.getUserJdbcObject(httpSession, userSessionId));

        return new ResponseEntity<>(indicationTreeviewDTOList, HttpStatus.OK);
    }

    /**
     *
     * @param therapeuticUse
     * @param httpSession
     * @param userSessionId
     * @return
     * @throws ServiceException
     */
    @RequestMapping(value = "/indication/search/{therapeuticUse}", method = RequestMethod.GET, produces = { "application/json" })
    @ResponseBody
    public ResponseEntity<?> prepareTreeviewForTherapeuticUse(@PathVariable String therapeuticUse, HttpSession httpSession, @RequestHeader("userSessionId") String userSessionId) throws ServiceException {
        LOGGER.info("Target fetch service");

        List<IndicationTreeviewParentChildNodeDTO> indicationTreeviewParentChildNodeDTOs = iIndicationTreeview.prepareTreeviewForTherapeuticUse(therapeuticUse, customJdbcConnection.getUserJdbcObject(httpSession, userSessionId));

        return new ResponseEntity<>(indicationTreeviewParentChildNodeDTOs, HttpStatus.OK);
    }

    /**
     *
     * @param httpSession
     * @param userSessionId
     * @return
     * @throws ServiceException
     */
    @RequestMapping(value = "/indication/parentlevel", method = RequestMethod.GET, produces = { "application/json" })
    @ResponseBody
    public ResponseEntity<?> prepareTreeviewForParentLevel(HttpSession httpSession, @RequestHeader("userSessionId") String userSessionId) throws ServiceException {
        LOGGER.info("Target fetch service");

        List<IndicationTreeviewParentChildNodeDTO> indicationTreeviewParentChildNodeDTOs = iIndicationTreeview.prepareTreeviewForParentLevel(customJdbcConnection.getUserJdbcObject(httpSession, userSessionId));

        return new ResponseEntity<>(indicationTreeviewParentChildNodeDTOs, HttpStatus.OK);
    }

    /**
     *
     * @param therapeuticUse
     * @param httpSession
     * @param userSessionId
     * @return
     * @throws ServiceException
     */
    @RequestMapping(value = "/indication/searchbytherapeuticuse/{therapeuticUse}", method = RequestMethod.GET, produces = { "application/json" })
    @ResponseBody
    public ResponseEntity<?> fetchIndicationOntologyTherapeuticUseByContaining(@PathVariable String therapeuticUse, HttpSession httpSession, @RequestHeader("userSessionId") String userSessionId) throws ServiceException {
        LOGGER.info("TargetOntologyMasterDTO fetch service");

        List<IndicationOntologyMasterDTO> indicationOntologyMasterDTOList = iIndicationTreeview.fetchIndicationOntologyTherapeuticUseByContaining(therapeuticUse, customJdbcConnection.getUserJdbcObject(httpSession, userSessionId));

        return new ResponseEntity<>(indicationOntologyMasterDTOList, HttpStatus.OK);
    }

    /**
     *
     * @param therapeuticUseList
     * @param httpSession
     * @param userSessionId
     * @return
     * @throws ServiceException
     */
    @RequestMapping(value = "/indication/bytherapeuticuselist", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<?> prepareTreeviewForSelectedTherapeuticUses(@RequestBody List<String> therapeuticUseList, HttpSession httpSession, @RequestHeader("userSessionId") String userSessionId) throws ServiceException
    {
        LOGGER.info("prepareTreeviewForSelectedTherapeuticUses {} ", therapeuticUseList.size());

        List<IndicationTreeviewParentChildNodeDTO> indicationTreeviewParentChildNodeDTOS = iIndicationTreeview.prepareTreeviewForSelectedTherapeuticUses(therapeuticUseList, customJdbcConnection.getUserJdbcObject(httpSession, userSessionId));

        return new ResponseEntity<>(indicationTreeviewParentChildNodeDTOS, HttpStatus.OK);
    }
}

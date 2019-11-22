package com.excelra.mvc.web.treeview;

import com.excelra.mvc.config.CustomJdbcConnection;
import com.excelra.mvc.exception.ServiceException;
import com.excelra.mvc.model.search.treeview.TaxonomyTreeviewParentChildNodeDTO;
import com.excelra.mvc.model.treeview.TaxonomyMasterDTO;
import com.excelra.mvc.model.treeview.TaxonomyTreeviewDTO;
import com.excelra.mvc.service.treeview.ITaxonomyTreeview;
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
 *  Taxonomy Treeview Controller
 * <p>
 *
 * @author venkateswarlu.s
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
@RequestMapping(value = "/security/treeview")
public class TaxonomyTreeviewController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TaxonomyTreeviewController.class);

    @Autowired
    private ITaxonomyTreeview iTaxonomyTreeview;

    @Autowired
    private CustomJdbcConnection customJdbcConnection;

    /**
     *
     * @param taxId
     * @param httpSession
     * @param userSessionId
     * @return
     * @throws ServiceException
     */
    @RequestMapping(value = "/taxonomy/{taxId}", method = RequestMethod.GET, produces = { "application/json" })
    @ResponseBody
    public ResponseEntity<?> prepareTreeviewForNode(@PathVariable Long taxId, HttpSession httpSession, @RequestHeader("userSessionId") String userSessionId) throws ServiceException {
        LOGGER.info("prepareTreeviewForNode");

        List<TaxonomyTreeviewDTO> taxonomyTreeviewDTOList = iTaxonomyTreeview.prepareTreeviewForNode(taxId, customJdbcConnection.getUserJdbcObject(httpSession, userSessionId));

        return new ResponseEntity<>(taxonomyTreeviewDTOList, HttpStatus.OK);
    }

    /**
     *
     * @param taxName
     * @param httpSession
     * @param userSessionId
     * @return
     * @throws ServiceException
     */
    @RequestMapping(value = "/taxonomy/search/{taxName}", method = RequestMethod.GET, produces = { "application/json" })
    @ResponseBody
    public ResponseEntity<?> prepareTreeviewForTaxName(@PathVariable String taxName, HttpSession httpSession, @RequestHeader("userSessionId") String userSessionId) throws ServiceException {
        LOGGER.info("prepareTreeviewForTaxName");

        List<TaxonomyTreeviewParentChildNodeDTO> taxonomyTreeviewParentChildNodeDTOs = iTaxonomyTreeview.prepareTreeviewForTaxName(taxName, customJdbcConnection.getUserJdbcObject(httpSession, userSessionId));

        return new ResponseEntity<>(taxonomyTreeviewParentChildNodeDTOs, HttpStatus.OK);
    }

    /**
     *
     * @param httpSession
     * @param userSessionId
     * @return
     * @throws ServiceException
     */
    @RequestMapping(value = "/taxonomy/parentlevel", method = RequestMethod.GET, produces = { "application/json" })
    @ResponseBody
    public ResponseEntity<?> prepareTreeviewForParentLevel(HttpSession httpSession, @RequestHeader("userSessionId") String userSessionId) throws ServiceException {
        LOGGER.info("prepareTreeviewForParentLevel");

        List<TaxonomyTreeviewParentChildNodeDTO> taxonomyTreeviewParentChildNodeDTOs = iTaxonomyTreeview.prepareTreeviewForParentLevel(customJdbcConnection.getUserJdbcObject(httpSession, userSessionId));

        return new ResponseEntity<>(taxonomyTreeviewParentChildNodeDTOs, HttpStatus.OK);
    }

    /**
     *
     * @param taxName
     * @param httpSession
     * @param userSessionId
     * @return
     * @throws ServiceException
     */
    @RequestMapping(value = "/taxonomy/searchbytaxname/{taxName}", method = RequestMethod.GET, produces = { "application/json" })
    @ResponseBody
    public ResponseEntity<?> fetchTaxonomyTaxNameByContaining(@PathVariable String taxName, HttpSession httpSession, @RequestHeader("userSessionId") String userSessionId) throws ServiceException {
        LOGGER.info("fetchTaxonomyTaxNameByContaining fetch service");

        List<TaxonomyMasterDTO> taxonomyMasterDTOList = iTaxonomyTreeview.fetchTaxonomyTaxNameByContaining(taxName, customJdbcConnection.getUserJdbcObject(httpSession, userSessionId));

        return new ResponseEntity<>(taxonomyMasterDTOList, HttpStatus.OK);
    }

    /**
     *
     * @param taxNameList
     * @param httpSession
     * @param userSessionId
     * @return
     * @throws ServiceException
     */
    @RequestMapping(value = "/taxonomy/bytaxnamelist", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<?> prepareTreeviewForSelectedTaxNames(@RequestBody List<String> taxNameList, HttpSession httpSession, @RequestHeader("userSessionId") String userSessionId) throws ServiceException
    {
        LOGGER.info("prepareTreeviewForSelectedTaxNames {} ", taxNameList.size());

        System.out.println("===========> "+userSessionId);
        List<TaxonomyTreeviewParentChildNodeDTO> taxonomyTreeviewParentChildNodeDTOS = iTaxonomyTreeview.prepareTreeviewForSelectedTaxNames(taxNameList, customJdbcConnection.getUserJdbcObject(httpSession, userSessionId));

        return new ResponseEntity<>(taxonomyTreeviewParentChildNodeDTOS, HttpStatus.OK);
    }

}

package com.excelra.mvc.web;

import com.excelra.mvc.config.CustomJdbcConnection;
import com.excelra.mvc.exception.ServiceException;
import com.excelra.mvc.model.OntoAssayTypeDTO;
import com.excelra.mvc.service.IOntoAssayType;
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
import java.util.Objects;

/**
 * <p>
 *  Onto Assaytype Controller
 * <p>
 *
 * @author venkateswarlu.s
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
@RequestMapping(value = "/security/ontoassay")
public class OntoAssayTypeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(OntoAssayTypeController.class);

    @Autowired
    private IOntoAssayType iOntoAssayType;

    @Autowired
    private CustomJdbcConnection customJdbcConnection;

    /**
     *
     * @param enddataPointLeaf
     * @return
     * @throws ServiceException
     */
    @RequestMapping(value = "/byenddataPointLeaf/{enddataPointLeaf}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<?> fetchByEnddataPointLeaf(@PathVariable Boolean enddataPointLeaf, @RequestHeader("userSessionId") String userSessionId, HttpSession httpSession) throws ServiceException {

        if(Objects.isNull(enddataPointLeaf)) enddataPointLeaf = false;

        LOGGER.info("OntoAssayType fetch service");

        List<OntoAssayTypeDTO> ontoAssayTypeDTOList = iOntoAssayType.findByEnddataPointLeaf(enddataPointLeaf, customJdbcConnection.getUserJdbcObject(httpSession, userSessionId));
        return new ResponseEntity<>(ontoAssayTypeDTOList, HttpStatus.OK);
    }

    /**
     *
     * @return
     * @throws ServiceException
     */
    @RequestMapping(value = "/fetchall", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<?> fetchAll(@RequestHeader("userSessionId") String userSessionId, HttpSession httpSession) throws ServiceException {

        LOGGER.info("OntoAssayType fetch service");

        List<OntoAssayTypeDTO> ontoAssayTypeDTOList = iOntoAssayType.fetchAll(customJdbcConnection.getUserJdbcObject(httpSession, userSessionId));

        return new ResponseEntity<>(ontoAssayTypeDTOList, HttpStatus.OK);
    }
}

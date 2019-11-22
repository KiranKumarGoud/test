package com.excelra.mvc.web;

import com.excelra.mvc.config.CustomJdbcConnection;
import com.excelra.mvc.exception.ServiceException;
import com.excelra.mvc.model.ListClinicalPhasesDTO;
import com.excelra.mvc.service.IClinicalPhases;
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
 *  Clinical Phases Controller
 * <p>
 *
 * @author venkateswarlu.s
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
@RequestMapping(value = "/security/clinicalphases")
public class ClinicalPhasesController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClinicalPhasesController.class);

    @Autowired
    private IClinicalPhases iClinicalPhases;

    @Autowired
    private CustomJdbcConnection customJdbcConnection;

    /**
     *
     * @param userSessionId
     * @param httpSession
     * @return
     * @throws ServiceException
     */
    @RequestMapping(value = "/fetchall", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<?> fetchAll(@RequestHeader("userSessionId") String userSessionId, HttpSession httpSession) throws ServiceException {

        LOGGER.info("CompoundPhases fetch service");

        List<ListClinicalPhasesDTO> listClinicalPhasesDTOS = iClinicalPhases.fetchClinicalPhasesList(customJdbcConnection.getUserJdbcObject(httpSession, userSessionId));

        return new ResponseEntity<>(listClinicalPhasesDTOS, HttpStatus.OK);
    }
}

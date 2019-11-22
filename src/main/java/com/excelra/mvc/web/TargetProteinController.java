package com.excelra.mvc.web;

import com.excelra.mvc.config.CustomJdbcConnection;
import com.excelra.mvc.exception.ServiceException;
import com.excelra.mvc.model.TargetProteinMasterDTO;
import com.excelra.mvc.model.userjdbc.UserJdbc;
import com.excelra.mvc.service.ITargetProteinMaster;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * <p>
 *  Target Protein master Controller
 * <p>
 *
 * @author venkateswarlu.s
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
@RequestMapping(value = "/security/targetprotein")
public class TargetProteinController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TargetProteinController.class);

    @Autowired
    private ITargetProteinMaster targetProteinMaster;

    @Autowired
    private CustomJdbcConnection customJdbcConnection;

    /**
     *
     * @param commonName
     * @return
     * @throws ServiceException
     */
    @RequestMapping(value = "/bycommonname/{commonName}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<?> fetchByCommonName(@PathVariable String commonName, @RequestHeader("userSessionId") String userSessionId, HttpSession httpSession) throws ServiceException {
        LOGGER.info("Target fetch service");

        List<TargetProteinMasterDTO> targetProteinMasterDTOS = targetProteinMaster.findByCommonName(commonName, customJdbcConnection.getUserJdbcObject(httpSession, userSessionId));

        return new ResponseEntity<>(targetProteinMasterDTOS, HttpStatus.OK);
    }

}

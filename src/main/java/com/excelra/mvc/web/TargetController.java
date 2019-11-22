package com.excelra.mvc.web;

import com.excelra.mvc.config.CustomJdbcConnection;
import com.excelra.mvc.exception.ServiceException;
import com.excelra.mvc.model.TargetAdvSearchInputDTO;
import com.excelra.mvc.model.TargetSynonymsDTO;
import com.excelra.mvc.model.userjdbc.UserJdbc;
import com.excelra.mvc.service.ITarget;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
@RequestMapping(value = "/security/target")
public class TargetController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TargetController.class);

    @Autowired
    private ITarget targetService;

    @Autowired
    private CustomJdbcConnection customJdbcConnection;

    /**
     *
     * @return
     * @throws ServiceException
     */
    @RequestMapping(value = "/", method = RequestMethod.GET, produces = { "application/json" })
    @ResponseBody
    public ResponseEntity<?> fetch(HttpSession httpSession, @RequestHeader("userSessionId") String userSessionId) throws ServiceException {
        LOGGER.info("Target fetch service");

        System.out.println("===========> "+userSessionId);
        List<TargetSynonymsDTO> targetSynonymsDtoList = targetService.findAll(customJdbcConnection.getUserJdbcObject(httpSession, userSessionId));

        return new ResponseEntity<>(targetSynonymsDtoList, HttpStatus.OK);
    }

    /**
     *
     * @param synonyms
     * @return
     * @throws ServiceException
     */
    @RequestMapping(value = "/bysynonyms/{synonyms}", method = RequestMethod.GET, produces = { "application/json" })
    @ResponseBody
    public ResponseEntity<?> fetchBySynonyms(@PathVariable String synonyms, HttpSession httpSession, @RequestHeader("userSessionId") String userSessionId) throws ServiceException {
        LOGGER.info("Target fetch service");

        System.out.println("===========> "+userSessionId);
        List<TargetSynonymsDTO> targetSynonymsDtoList = targetService.findBySynonyms(synonyms, customJdbcConnection.getUserJdbcObject(httpSession, userSessionId));

        return new ResponseEntity<>(targetSynonymsDtoList, HttpStatus.OK);
    }

    /**
     *
     * @param synonyms
     * @return
     * @throws ServiceException
     */
    @RequestMapping(value = "/contains/{synonyms}", method = RequestMethod.GET, produces = { "application/json" })
    @ResponseBody
    public ResponseEntity<?> fetchBySynonymContains(@PathVariable String synonyms, HttpSession httpSession, @RequestHeader("userSessionId") String userSessionId) throws ServiceException {
        LOGGER.info("Target fetch service");

        System.out.println("===========> "+userSessionId);
        List<TargetSynonymsDTO> targetSynonymsDtoList = targetService.findBySynonymsContaining(synonyms, customJdbcConnection.getUserJdbcObject(httpSession, userSessionId));

        return new ResponseEntity<>(targetSynonymsDtoList, HttpStatus.OK);
    }

    /**
     *
     * @param targetSynonymsDTOList
     * @return
     */
    @RequestMapping(value = "/getstdname", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<List<Integer>> fetchStdnameIdBySynonymsList(@RequestBody List<TargetSynonymsDTO> targetSynonymsDTOList, HttpSession httpSession, @RequestHeader("userSessionId") String userSessionId) throws ServiceException
    {
        LOGGER.info("fetchStdnameIdBySynonymsList {} ", targetSynonymsDTOList.size());

        System.out.println("===========> "+userSessionId);
        List<Integer> finalStdnameIdList = targetService.findStdnameIdBySynonymsList(targetSynonymsDTOList, customJdbcConnection.getUserJdbcObject(httpSession, userSessionId));

        // LOGGER.info("Response received as findStdnameIdBySynonymsList {} ", finalStdnameIdList);

        return new ResponseEntity<>(finalStdnameIdList, HttpStatus.OK);
    }

    @RequestMapping(value = "/targetadvsearch/{option}/{optionValue}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<?> fetchTargetByOptionAndValue(@PathVariable String option, @PathVariable String optionValue, @RequestHeader("userSessionId") String userSessionId, HttpSession httpSession) throws ServiceException {
        LOGGER.info("Target fetch service");

        List<TargetAdvSearchInputDTO> targetProteinMasterDTOS = targetService.fetchTargetByOptionAndValue(option, optionValue, customJdbcConnection.getUserJdbcObject(httpSession, userSessionId));

        return new ResponseEntity<>(targetProteinMasterDTOS, HttpStatus.OK);
    }
}

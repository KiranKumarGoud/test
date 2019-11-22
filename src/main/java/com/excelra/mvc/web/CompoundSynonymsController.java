package com.excelra.mvc.web;

import com.excelra.mvc.config.CustomJdbcConnection;
import com.excelra.mvc.exception.ServiceException;
import com.excelra.mvc.model.CompoundSynonymsDTO;
import com.excelra.mvc.model.ListCasNo;
import com.excelra.mvc.model.StructuralPropertiesDTO;
import com.excelra.mvc.service.ICompoundSynonyms;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 *
 * @author venkateswarlu.s
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
@RequestMapping(value = "/security/structure")
public class CompoundSynonymsController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CompoundSynonymsController.class);

    @Autowired
    private ICompoundSynonyms iCompoundSynonyms;

    @Autowired
    private CustomJdbcConnection customJdbcConnection;

    /**
     *
     * @param compoundSynonyms
     * @param userSessionId
     * @param httpSession
     * @return
     * @throws ServiceException
     */
    @RequestMapping(value = "/compoundsynonyms/contains/{compoundSynonyms}", method = RequestMethod.GET, produces = { "application/json" })
    @ResponseBody
    public ResponseEntity<?> fetchBySynonymContains(@PathVariable String compoundSynonyms, @RequestHeader("userSessionId") String userSessionId, HttpSession httpSession) throws ServiceException {
        LOGGER.info("Compound Synonyms fetch service");

        List<CompoundSynonymsDTO> compoundSynonymsDtoList = iCompoundSynonyms.findByCompoundSynonymsContaining(compoundSynonyms, customJdbcConnection.getUserJdbcObject(httpSession, userSessionId));

        return new ResponseEntity<>(compoundSynonymsDtoList, HttpStatus.OK);
    }

    /**
     *
     * @param casNo
     * @param userSessionId
     * @param httpSession
     * @return
     * @throws ServiceException
     */
    @RequestMapping(value = "/casno/contains/{casNo}", method = RequestMethod.GET, produces = { "application/json" })
    @ResponseBody
    public ResponseEntity<?> fetchByCasNoContains(@PathVariable String casNo, @RequestHeader("userSessionId") String userSessionId, HttpSession httpSession) throws ServiceException {
        LOGGER.info("Compound Synonyms fetch service");

        List<ListCasNo> listCasNos = iCompoundSynonyms.fetchByCasNoContains(casNo, customJdbcConnection.getUserJdbcObject(httpSession, userSessionId));

        return new ResponseEntity<>(listCasNos, HttpStatus.OK);
    }

    /**
     *
     * @param userSessionId
     * @param httpSession
     * @return
     * @throws ServiceException
     */
    @RequestMapping(value = "/structural/properties/minmax", method = RequestMethod.GET, produces = { "application/json" })
    @ResponseBody
    public ResponseEntity<?> structuralPropertiesMinMax(@RequestHeader("userSessionId") String userSessionId, HttpSession httpSession) throws ServiceException {
        LOGGER.info("Compound Synonyms fetch service");

        StructuralPropertiesDTO structuralPropertiesDTO = iCompoundSynonyms.structuralPropertiesMinMax(customJdbcConnection.getUserJdbcObject(httpSession, userSessionId));

        return new ResponseEntity<>(structuralPropertiesDTO, HttpStatus.OK);
    }
}

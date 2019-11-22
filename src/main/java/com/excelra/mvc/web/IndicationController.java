package com.excelra.mvc.web;

import com.excelra.mvc.config.CustomJdbcConnection;
import com.excelra.mvc.exception.ServiceException;
import com.excelra.mvc.model.indication.ListIcd10CodesDTO;
import com.excelra.mvc.model.indication.ListTherapeuticUseDTO;
import com.excelra.mvc.service.IIndication;
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
 * <p>
 *  Indication Controller
 * <p>
 *
 * @author venkateswarlu.s
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
@RequestMapping(value = "/security/indication")
public class IndicationController {

    private static final Logger LOGGER = LoggerFactory.getLogger(IndicationController.class);

    @Autowired
    private IIndication iIndication;

    @Autowired
    private CustomJdbcConnection customJdbcConnection;

    /**
     *
     * @param therapeuic
     * @param userSessionId
     * @param httpSession
     * @return
     * @throws ServiceException
     */
    @RequestMapping(value = "/bytherapuic/{therapeuic}", method = RequestMethod.GET, produces = {"application/json"})
    @ResponseBody
    public ResponseEntity<?> fetchTherapeuicListByContaining(@PathVariable String therapeuic, @RequestHeader("userSessionId") String userSessionId, HttpSession httpSession) throws ServiceException {
        LOGGER.info("fetch Therapeuic List By Containing service");

        List<ListTherapeuticUseDTO> listTherapeuticUseDTOList = iIndication.fetchTherapeuicListByContaining(therapeuic, customJdbcConnection.getUserJdbcObject(httpSession, userSessionId));

        return new ResponseEntity<>(listTherapeuticUseDTOList, HttpStatus.OK);
    }

    /**
     *
     * @param icd10Code
     * @param userSessionId
     * @param httpSession
     * @return
     * @throws ServiceException
     */
    @RequestMapping(value = "/byicd10code/{icd10Code}", method = RequestMethod.GET, produces = {"application/json"})
    @ResponseBody
    public ResponseEntity<?> fetchIcd10CodesListByContaining(@PathVariable String icd10Code, @RequestHeader("userSessionId") String userSessionId, HttpSession httpSession) throws ServiceException {
        LOGGER.info("fetch Icd10Codes List By Containing service");

        List<ListIcd10CodesDTO> listIcd10CodesDTOList = iIndication.fetchIcd10CodesListByContaining(icd10Code, customJdbcConnection.getUserJdbcObject(httpSession, userSessionId));

        return new ResponseEntity<>(listIcd10CodesDTOList, HttpStatus.OK);
    }
}

package com.excelra.mvc.web;

import com.excelra.mvc.config.CustomJdbcConnection;
import com.excelra.mvc.exception.ServiceException;
import com.excelra.mvc.model.ListPdbIdsDTO;
import com.excelra.mvc.model.userjdbc.UserJdbc;
import com.excelra.mvc.service.IListPdbIds;
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
 *  Pdb Controller
 * <p>
 *
 * @author venkateswarlu.s
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
@RequestMapping(value = "/security/pdb")
public class PdbController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PdbController.class);

    @Autowired
    private IListPdbIds iListPdbIds;

    @Autowired
    private CustomJdbcConnection customJdbcConnection;

    /**
     *
     * @param pdbId
     * @return
     * @throws ServiceException
     */
    @RequestMapping(value = "/bypdbid/{pdbId}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<?> fetchByPdbId(@PathVariable String pdbId, @RequestHeader("userSessionId") String userSessionId, HttpSession httpSession) throws ServiceException {
        LOGGER.info("Target fetch service");

        List<ListPdbIdsDTO> pdbIdsDtoList = iListPdbIds.findByPdbId(pdbId, customJdbcConnection.getUserJdbcObject(httpSession, userSessionId));

        return new ResponseEntity<>(pdbIdsDtoList, HttpStatus.OK);
    }
}

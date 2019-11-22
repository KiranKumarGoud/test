package com.excelra.mvc.web;

import com.excelra.mvc.config.CustomJdbcConnection;
import com.excelra.mvc.exception.ServiceException;
import com.excelra.mvc.model.ListUniprotIdDTO;
import com.excelra.mvc.service.IListUniprotId;
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
 *  Uniprot Controller
 * <p>
 *
 * @author venkateswarlu.s
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
@RequestMapping(value = "/security/uniprot")
public class UniprotController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TargetController.class);

    @Autowired
    private IListUniprotId iListUniprotId;

    @Autowired
    private CustomJdbcConnection customJdbcConnection;

    /**
     *
     * @param uniprotId
     * @return
     * @throws ServiceException
     */
    @RequestMapping(value = "/byuniprotid/{uniprotId}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<?> fetchByUniprotId(@PathVariable String uniprotId, @RequestHeader("userSessionId") String userSessionId, HttpSession httpSession) throws ServiceException {
        LOGGER.info("UniprotId fetch service");

        List<ListUniprotIdDTO> uniprotIdList = iListUniprotId.findByUniprotId(uniprotId, customJdbcConnection.getUserJdbcObject(httpSession, userSessionId));

        return new ResponseEntity<>(uniprotIdList, HttpStatus.OK);
    }
}

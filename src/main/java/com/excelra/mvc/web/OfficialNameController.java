package com.excelra.mvc.web;

import com.excelra.mvc.config.CustomJdbcConnection;
import com.excelra.mvc.exception.ServiceException;
import com.excelra.mvc.model.ListOfficialNameDTO;
import com.excelra.mvc.service.IListOfficialName;
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
 *  Official name Controller
 * <p>
 *
 * @author venkateswarlu.s
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
@RequestMapping(value = "/security/officialname")
public class OfficialNameController {

    private static final Logger LOGGER = LoggerFactory.getLogger(OfficialNameController.class);

    @Autowired
    private IListOfficialName listOfficialNameService;

    @Autowired
    private CustomJdbcConnection customJdbcConnection;

    /**
     *
     * @param officialName
     * @return
     * @throws ServiceException
     */
    @RequestMapping(value = "/byofficialname/{officialName}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<?> fetchByLocusId(@PathVariable String officialName, @RequestHeader("userSessionId") String userSessionId, HttpSession httpSession) throws ServiceException {
        LOGGER.info("Official name fetch service");

        List<ListOfficialNameDTO> officialNameList = listOfficialNameService.findByOfficialName(officialName, customJdbcConnection.getUserJdbcObject(httpSession, userSessionId));

        return new ResponseEntity<>(officialNameList, HttpStatus.OK);
    }

}

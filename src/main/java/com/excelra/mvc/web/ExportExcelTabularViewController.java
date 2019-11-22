package com.excelra.mvc.web;

import com.excelra.mvc.config.CustomJdbcConnection;
import com.excelra.mvc.model.export.ExportInputDTO;
import com.excelra.mvc.model.tabularview.*;
import com.excelra.mvc.service.tabularview.IActivityTab;
import com.excelra.mvc.service.tabularview.IAssayTab;
import com.excelra.mvc.util.ExcelGenerator;
import com.excelra.mvc.util.WriteDataToCSV;
import org.apache.commons.io.IOUtils;
import org.apache.poi.ss.usermodel.Header;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpHeaders;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.zip.ZipEntry;

/***
 * <p>
 *     this controller class is used to get the Tabular data for Activity
 *    tab information,Assay Tab information and Gvk Tab information,Structure tab
 *
 * </p>
 * @author priyanka.veidhey
 */

@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
@RequestMapping(value = "/security/tabularview/excel_fromat/export")
public class ExportExcelTabularViewController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExportExcelTabularViewController.class);

    @Autowired
    private IActivityTab activityTabDAO;

    @Autowired
    private IAssayTab assayTabDAO;

    @Autowired
    private CustomJdbcConnection customJdbcConnection;

    private static final String ACTIVITY = "Activity";

    @RequestMapping(value = "/download/excel/fromtempdata/filename", method = RequestMethod.POST )
    public ResponseEntity<InputStreamResource> exportExcelFileForAllTabularViewFromTempTable(@RequestBody TabularviewExportRequest tabularviewExportRequest, HttpServletResponse response, @RequestHeader("userSessionId") String userSessionId, HttpSession httpSession) throws IOException {


        LOGGER.debug("Tabularview excel format export - exportExcelFileForAllTabularViewFromTempTable ");

        HttpHeaders headers = new HttpHeaders();

        headers.add("Content-Disposition", "attachment; filename=check.xlsx");

        ZipEntry e = new ZipEntry("GOSTAR_ACTIVITY.xlsx");

        headers.setContentType(MediaType.valueOf("application/octet-stream"));


        String currentSessionUserToken = (String) httpSession.getAttribute(userSessionId + "_token");

        Boolean activityTabStatus = Boolean.TRUE;
        Boolean assayTabStatus = Boolean.TRUE;
        Boolean strTabStatus = Boolean.TRUE;
        Boolean referenceTabStatus = Boolean.TRUE;

        List<ActivityTabDTO> activityTabDToList = new ArrayList<>();

        //ActivityTAb
        if (activityTabStatus)
            activityTabDToList = activityTabDAO.findActivityTabDataForExport(tabularviewExportRequest, customJdbcConnection.getUserJdbcObject(httpSession, userSessionId), currentSessionUserToken);

        LOGGER.info(" exportExcelFileForAllTabularViewFromTempTable : activityTabDToList size is {} " + activityTabDToList.size());

        //AssayTab

        List<AssayTabDTO> assayTabDToList = new ArrayList<>();

        if(assayTabStatus)

            assayTabDToList = assayTabDAO.findAssayTabDataForExport(tabularviewExportRequest, customJdbcConnection.getUserJdbcObject(httpSession, userSessionId), currentSessionUserToken);

        LOGGER.info(" exportExcelFileForAllTabularViewFromTempTable : assayTabDToList size is {} "+assayTabDToList.size());


        ByteArrayInputStream byteArrayInputStream = ExcelGenerator.ActivityTabDataToExcel(activityTabDToList , assayTabDToList);


        return ResponseEntity.ok()
                .headers(headers)
                .body(new InputStreamResource(byteArrayInputStream));

    }

}

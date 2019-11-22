package com.excelra.mvc.web;

import com.excelra.mvc.config.CustomJdbcConnection;
import com.excelra.mvc.model.tabularview.*;
import com.excelra.mvc.service.tabularview.IActivityTab;
import com.excelra.mvc.service.tabularview.IAssayTab;
import com.excelra.mvc.service.tabularview.IReferenceTab;
import com.excelra.mvc.service.tabularview.IStructureDetailsTab;
import com.excelra.mvc.util.WriteDataToCSV;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/***
 *<p>
 *   this class is used to get the Tabular data for Activity ,assay, reference
 *   Structure
 *   tab information in sdf file format
 *</p>
 * @author priyanka.veidhey
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
@RequestMapping(value = "/security/tabularview/export/sdf")
public class ExportSDFTabularController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExportCSVTabularviewController.class);

    @Autowired
    private IActivityTab activityTabDAO;

    @Autowired
    private IAssayTab assayTabDAO;

    @Autowired
    private IReferenceTab referenceTabDAO;

    @Autowired
    private IStructureDetailsTab structureDetailsTabDAO;

    @Autowired
    private CustomJdbcConnection customJdbcConnection;

    private static final String ASSAY = "Assay";

    private static final String REFERENCE = "Reference";

    private static final String STRUCTURE = "Structure";

    private static final String ACTIVITY = "Activity";

    /***
     * <p>
     *     Api call for  for the Structure data collection
     *     for the SDF file format
     * </p>
     */
    @RequestMapping(value = "/download/sdf_format/structure/fromtempdata", method = RequestMethod.POST)
    public  void exportSDFFileForStructureTabularViewWithDifferentFromTempTable(@RequestBody TabularviewExportRequest tabularviewExportRequest, HttpServletResponse response, @RequestHeader("userSessionId") String userSessionId, HttpSession httpSession)throws IOException
    {
        LOGGER.debug("Tabular View for Structure Tab - exportSDFFileForStructureTabularViewWithDifferentFromTempTable ");

        String currentSessionUserToken = (String) httpSession.getAttribute(userSessionId+"_token");

        response.setContentType("text/plain");

        response.setHeader("Content-Disposition", "attachment; filename=GOSTAR_STRUCTURE.sdf");

        Boolean activityTabStatus = Boolean.TRUE;
        Boolean assayTabStatus = Boolean.TRUE;
        Boolean strTabStatus = Boolean.TRUE;
        Boolean referenceTabStatus = Boolean.TRUE;


        List<ActivityTabDTO> activityTabDToList = new ArrayList<>();

        if(activityTabStatus)
            activityTabDToList =  activityTabDAO.findActivityTabDataForExport(tabularviewExportRequest, customJdbcConnection.getUserJdbcObject(httpSession, userSessionId), currentSessionUserToken);

        LOGGER.info(" exportCSVFileForAllTabularViewFromTempTable : activityTabDToList size is {} "+activityTabDToList.size());



        List<AssayTabDTO> assayTabDToList = new ArrayList<>();

        if(assayTabStatus)
            assayTabDToList = assayTabDAO.findAssayTabDataForExport(tabularviewExportRequest, customJdbcConnection.getUserJdbcObject(httpSession, userSessionId), currentSessionUserToken);

        LOGGER.info(" exportSDFFileForStructureTabularViewWithDifferentFromTempTable : assayTabDToList size is {} "+assayTabDToList.size());


        List<ReferenceTabDTO> referenceTabDTOList = new ArrayList<>();

        if(referenceTabStatus)
            referenceTabDTOList = referenceTabDAO.findReferenceTabDataForExport(tabularviewExportRequest, customJdbcConnection.getUserJdbcObject(httpSession, userSessionId), currentSessionUserToken);

        LOGGER.info(" exportSDFFileForStructureTabularViewWithDifferentFromTempTable : referenceTabDToList size is {} "+referenceTabDTOList.size());


        List<StructureDetailsTabDTO> structureDetailsTabDTOList = new ArrayList<>();

        if(strTabStatus)
            structureDetailsTabDTOList = structureDetailsTabDAO.findStructureTabDataForExport(tabularviewExportRequest, customJdbcConnection.getUserJdbcObject(httpSession, userSessionId), currentSessionUserToken);

        LOGGER.info(" exportSDFFileForStructureTabularViewWithDifferentFromTempTable : structureDetailsTabDTOList size is {} "+structureDetailsTabDTOList.size());

        WriteDataToCSV.writeDtaToSdf(response.getWriter(),structureDetailsTabDTOList, referenceTabDTOList , assayTabDToList , activityTabDToList);
    }

}

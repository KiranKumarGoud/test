package com.excelra.mvc.web;

import com.excelra.mvc.config.CustomJdbcConnection;
import com.excelra.mvc.model.export.ExportInputDTO;
import com.excelra.mvc.model.tabularview.*;
import com.excelra.mvc.service.tabularview.IActivityTab;
import com.excelra.mvc.service.tabularview.IAssayTab;
import com.excelra.mvc.service.tabularview.IReferenceTab;
import com.excelra.mvc.service.tabularview.IStructureDetailsTab;
import com.excelra.mvc.util.WriteDataToCSV;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/***
 *<p>
 *   this class is used to get the Tabular data for Activity
 *   tab information
 *</p>
 * @author priyanka.veidhey
 */

@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
@RequestMapping(value = "/security/tabularview/export")
public class ExportCSVTabularviewController {

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
     *
     * @param exportInputDto
     * @param response
     * @param userSessionId
     * @param httpSession
     * @throws IOException
     */
    @RequestMapping(value = "/download/filename", method = RequestMethod.POST)
    public  void exportCSVFileForAllTabularView(@RequestBody ExportInputDTO exportInputDto, HttpServletResponse response, @RequestHeader("userSessionId") String userSessionId, HttpSession httpSession)throws IOException
    {
        LOGGER.debug("Tabular View for Activity Tab - findActivityTabByActIds {} ", exportInputDto);

      //  response.setContentType("text/csv");
        response.setContentType("text/tab-separated-values");

        response.setHeader("Content-Disposition", "attachment; filename=export.tsv");

        List<Long> actIdList = exportInputDto.getActivity();

        List<Long> assayIdList = exportInputDto.getAssay();

        List<Long> refIdList = exportInputDto.getReference();

        List<Long> gvkIdList = exportInputDto.getStructure();

        List<ActivityTabDTO> activityTabDToList =  (!actIdList.isEmpty()) ? new ArrayList<>() : new ArrayList<>();

        List<AssayTabDTO> assayTabDToList = (!assayIdList.isEmpty()) ? new ArrayList<>() : new ArrayList<>();

        List<ReferenceTabDTO> referenceTabDTOList = (!refIdList.isEmpty()) ? new ArrayList<>() : new ArrayList<>();

        List<StructureDetailsTabDTO> structureDetailsTabDTOList = (!gvkIdList.isEmpty()) ? new ArrayList<>() : new ArrayList<>();

        WriteDataToCSV.writeDataToCsvUsingStringArray(response.getWriter(),activityTabDToList,assayTabDToList,referenceTabDTOList,structureDetailsTabDTOList);

    }

    /***
     *
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/download/fromtempdata", method = RequestMethod.POST)
    public  void exportCSVFileForAllTabularViewFromTempTable(@RequestBody TabularviewExportRequest tabularviewExportRequest, HttpServletResponse response, @RequestHeader("userSessionId") String userSessionId, HttpSession httpSession)throws  IOException
    {
        LOGGER.debug("Tabular View for Activity Tab - findActivityTabByActIds ");

        String currentSessionUserToken = (String) httpSession.getAttribute(userSessionId+"_token");

        response.setContentType("text/tab-separated-values");

        response.setHeader("Content-Disposition", "attachment; filename=export.tsv");

        /*
        * --- THIS WILL BE UNCOMMENTED ONCE CLARIFICATION RECEIVED FROM BUSINESS HOLDERS ----
        Boolean activityTabStatus = Boolean.FALSE;
        Boolean assayTabStatus = Boolean.FALSE;
        Boolean strTabStatus = Boolean.FALSE;
        Boolean referenceTabStatus = Boolean.FALSE;

        if(tabularviewExportRequest.getSelectedTabIds().isEmpty() && tabularviewExportRequest.getUnSelectedTabIds().isEmpty()) {
            activityTabStatus = Boolean.TRUE;
            assayTabStatus = Boolean.TRUE;
            strTabStatus = Boolean.TRUE;
            referenceTabStatus = Boolean.TRUE;
        } else if(!tabularviewExportRequest.getSelectedTabIds().isEmpty() && tabularviewExportRequest.getUnSelectedTabIds().isEmpty()) {
            for (Map.Entry<String, List<Long>> selectedEntry : tabularviewExportRequest.getSelectedTabIds().entrySet()) {
                switch (selectedEntry.getKey()) {
                    case ASSAY:
                        if (!selectedEntry.getValue().isEmpty()) assayTabStatus = Boolean.TRUE;
                        break;
                    case REFERENCE:
                        if (!selectedEntry.getValue().isEmpty()) referenceTabStatus = Boolean.TRUE;
                        break;
                    case STRUCTURE:
                        if (!selectedEntry.getValue().isEmpty()) strTabStatus = Boolean.TRUE;
                        break;
                    case ACTIVITY:
                        if (!selectedEntry.getValue().isEmpty()) activityTabStatus = Boolean.TRUE;
                        break;

                    default:
                        LOGGER.info(" No Proper key ");
                }
            }
        } else if(tabularviewExportRequest.getSelectedTabIds().isEmpty() && !tabularviewExportRequest.getUnSelectedTabIds().isEmpty()) {
            for (Map.Entry<String, List<Long>> unselectedEntry : tabularviewExportRequest.getUnSelectedTabIds().entrySet()) {

                switch (unselectedEntry.getKey()) {
                    case ASSAY:
                        if (!unselectedEntry.getValue().isEmpty()) assayTabStatus = Boolean.TRUE;
                        break;
                    case REFERENCE:
                        if (!unselectedEntry.getValue().isEmpty()) referenceTabStatus = Boolean.TRUE;
                        break;
                    case STRUCTURE:
                        if (!unselectedEntry.getValue().isEmpty()) strTabStatus = Boolean.TRUE;
                        break;
                    case ACTIVITY:
                        if (!unselectedEntry.getValue().isEmpty()) activityTabStatus = Boolean.TRUE;
                        break;

                    default:
                        LOGGER.info(" No Proper key ");
                }
            }
        }*/

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

        LOGGER.info(" exportCSVFileForAllTabularViewFromTempTable : assayTabDToList size is {} "+assayTabDToList.size());

        List<ReferenceTabDTO> referenceTabDTOList = new ArrayList<>();

        if(referenceTabStatus)
            referenceTabDTOList = referenceTabDAO.findReferenceTabDataForExport(tabularviewExportRequest, customJdbcConnection.getUserJdbcObject(httpSession, userSessionId), currentSessionUserToken);

        LOGGER.info(" exportCSVFileForAllTabularViewFromTempTable : referenceTabDToList size is {} "+referenceTabDTOList.size());

        List<StructureDetailsTabDTO> structureDetailsTabDTOList = new ArrayList<>();

        if(strTabStatus)
            structureDetailsTabDTOList = structureDetailsTabDAO.findStructureTabDataForExport(tabularviewExportRequest, customJdbcConnection.getUserJdbcObject(httpSession, userSessionId), currentSessionUserToken);

        LOGGER.info(" exportCSVFileForAllTabularViewFromTempTable : structureDetailsTabDTOList size is {} "+structureDetailsTabDTOList.size());

        //for all the option download like ActivityTab,AssayTab,StrutureTab,ReferenceTab
       WriteDataToCSV.writeDataToCsvUsingStringArray(response.getWriter(),activityTabDToList,assayTabDToList,referenceTabDTOList,structureDetailsTabDTOList);


    }

    /***
     *<p>Api call for the Activity collection</p>
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/download/activity/fromtempdata", method = RequestMethod.POST)
    public  void exportCSVFileForActivityTabularViewWithDifferentFromTempTable(@RequestBody TabularviewExportRequest tabularviewExportRequest, HttpServletResponse response, @RequestHeader("userSessionId") String userSessionId, HttpSession httpSession)throws  IOException
    {
        LOGGER.debug("Tabular View for Activity Tab - findActivityTabByActIds ");

        String currentSessionUserToken = (String) httpSession.getAttribute(userSessionId+"_token");

        response.setContentType("text/csv");

        response.setHeader("Content-Disposition", "attachment; filename=GOSTAR_ACTIVITY.csv");

        Boolean activityTabStatus = Boolean.TRUE;

        Boolean assayTabStatus = Boolean.TRUE;

        Boolean strTabStatus = Boolean.TRUE;

        Boolean referenceTabStatus = Boolean.TRUE;

        List<ActivityTabDTO> activityTabDToList = new ArrayList<>();

        if(activityTabStatus)
            activityTabDToList =  activityTabDAO.findActivityTabDataForExport(tabularviewExportRequest, customJdbcConnection.getUserJdbcObject(httpSession, userSessionId), currentSessionUserToken);

        LOGGER.info(" exportCSVFileForActivityTabularViewWithDifferentFromTempTable : activityTabDToList size is {} "+activityTabDToList.size());


        List<AssayTabDTO> assayTabDToList = new ArrayList<>();

        if(assayTabStatus)
            assayTabDToList = assayTabDAO.findAssayTabDataForExport(tabularviewExportRequest, customJdbcConnection.getUserJdbcObject(httpSession, userSessionId), currentSessionUserToken);

        LOGGER.info(" exportCSVFileForActivityTabularViewWithDifferentFromTempTable : assayTabDToList size is {} "+assayTabDToList.size());

        List<ReferenceTabDTO> referenceTabDTOList = new ArrayList<>();

        if(referenceTabStatus)
            referenceTabDTOList = referenceTabDAO.findReferenceTabDataForExport(tabularviewExportRequest, customJdbcConnection.getUserJdbcObject(httpSession, userSessionId), currentSessionUserToken);

        LOGGER.info(" exportCSVFileForActivityTabularViewWithDifferentFromTempTable : referenceTabDToList size is {} "+referenceTabDTOList.size());


        List<StructureDetailsTabDTO> structureDetailsTabDTOList = new ArrayList<>();

        if(strTabStatus)
            structureDetailsTabDTOList = structureDetailsTabDAO.findStructureTabDataForExport(tabularviewExportRequest, customJdbcConnection.getUserJdbcObject(httpSession, userSessionId), currentSessionUserToken);

        LOGGER.info(" exportCSVFileForActivityTabularViewWithDifferentFromTempTable : structureDetailsTabDTOList size is {} "+structureDetailsTabDTOList.size());


        WriteDataToCSV.writeDataToCsvUsingStringArrayForActivity(response.getWriter(), activityTabDToList , assayTabDToList , referenceTabDTOList , structureDetailsTabDTOList);
    }

    /***
     *<p>Api call for  for the Assay data collection</p>
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/download/assay/fromtempdata", method = RequestMethod.POST)
    public  void exportCSVFileForAssayTabularViewWithDifferentFromTempTable(@RequestBody TabularviewExportRequest tabularviewExportRequest, HttpServletResponse response, @RequestHeader("userSessionId") String userSessionId, HttpSession httpSession)throws  IOException
    {
        LOGGER.debug("Tabular View for Activity Tab - findActivityTabByActIds ");

        String currentSessionUserToken = (String) httpSession.getAttribute(userSessionId+"_token");

        response.setContentType("text/csv");

        response.setHeader("Content-Disposition", "attachment; filename=GOSTAR_ASSAY.csv");

        Boolean assayTabStatus = Boolean.TRUE;

        Boolean strTabStatus = Boolean.TRUE;

        Boolean referenceTabStatus = Boolean.TRUE;

        List<AssayTabDTO> assayTabDToList = new ArrayList<>();

        if(assayTabStatus)
            assayTabDToList = assayTabDAO.findAssayTabDataForExport(tabularviewExportRequest, customJdbcConnection.getUserJdbcObject(httpSession, userSessionId), currentSessionUserToken);

        LOGGER.info(" exportCSVFileForAssayTabularViewWithDifferentFromTempTable : assayTabDToList size is {} "+assayTabDToList.size());

        List<ReferenceTabDTO> referenceTabDTOList = new ArrayList<>();

        if(referenceTabStatus)
            referenceTabDTOList = referenceTabDAO.findReferenceTabDataForExport(tabularviewExportRequest, customJdbcConnection.getUserJdbcObject(httpSession, userSessionId), currentSessionUserToken);

        LOGGER.info(" exportCSVFileForAssayTabularViewWithDifferentFromTempTable : referenceTabDToList size is {} "+referenceTabDTOList.size());


        List<StructureDetailsTabDTO> structureDetailsTabDTOList = new ArrayList<>();

        if(strTabStatus)
            structureDetailsTabDTOList = structureDetailsTabDAO.findStructureTabDataForExport(tabularviewExportRequest, customJdbcConnection.getUserJdbcObject(httpSession, userSessionId), currentSessionUserToken);

        LOGGER.info(" exportCSVFileForAssayTabularViewWithDifferentFromTempTable : structureDetailsTabDTOList size is {} "+structureDetailsTabDTOList.size());


        WriteDataToCSV.writeDataToCsvUsingStringArrayForAssay(response.getWriter(),assayTabDToList , referenceTabDTOList , structureDetailsTabDTOList);
    }

    /***
     * <p>
     *     Api call for  for the Structure data collection
     * </p>
     */
    @RequestMapping(value = "/download/structure/fromtempdata", method = RequestMethod.POST)
    public  void exportCSVFileForStructureTabularViewWithDifferentFromTempTable(@RequestBody TabularviewExportRequest tabularviewExportRequest, HttpServletResponse response, @RequestHeader("userSessionId") String userSessionId, HttpSession httpSession)throws  IOException
    {
        LOGGER.debug("Tabular View for Activity Tab - findActivityTabByActIds ");

        String currentSessionUserToken = (String) httpSession.getAttribute(userSessionId+"_token");

        response.setContentType("text/csv");

        response.setHeader("Content-Disposition", "attachment; filename=GOSTAR_STRUCTURE.csv");

        Boolean strTabStatus = Boolean.TRUE;

        Boolean referenceTabStatus = Boolean.TRUE;

        List<ReferenceTabDTO> referenceTabDTOList = new ArrayList<>();

        if(referenceTabStatus)
            referenceTabDTOList = referenceTabDAO.findReferenceTabDataForExport(tabularviewExportRequest, customJdbcConnection.getUserJdbcObject(httpSession, userSessionId), currentSessionUserToken);

        LOGGER.info(" exportCSVFileForReferenceTabularViewWithDifferentFromTempTable : referenceTabDToList size is {} "+referenceTabDTOList.size());


        List<StructureDetailsTabDTO> structureDetailsTabDTOList = new ArrayList<>();

        if(strTabStatus)
            structureDetailsTabDTOList = structureDetailsTabDAO.findStructureTabDataForExport(tabularviewExportRequest, customJdbcConnection.getUserJdbcObject(httpSession, userSessionId), currentSessionUserToken);

        LOGGER.info(" exportCSVFileForStructureTabularViewWithDifferentFromTempTable : structureDetailsTabDTOList size is {} "+structureDetailsTabDTOList.size());

        WriteDataToCSV.writeDataToCsvUsingStringArrayForStructure(response.getWriter(),structureDetailsTabDTOList , referenceTabDTOList);
    }

    /***
     * <p>
     *     Api call for  for the Reference data collection
     * </p>
     */
    @RequestMapping(value = "/download/reference/fromtempdata", method = RequestMethod.POST)
    public  void exportCSVFileForReferenceTabularViewWithDifferentFromTempTable(@RequestBody TabularviewExportRequest tabularviewExportRequest, HttpServletResponse response, @RequestHeader("userSessionId") String userSessionId, HttpSession httpSession)throws  IOException
    {
        LOGGER.debug("Tabular View for Activity Tab - findActivityTabByActIds ");

        String currentSessionUserToken = (String) httpSession.getAttribute(userSessionId+"_token");

        response.setContentType("text/csv");

        response.setHeader("Content-Disposition", "attachment; filename=GOSTAR_REFERENCE.csv");

        Boolean referenceTabStatus = Boolean.TRUE;

        List<ReferenceTabDTO> referenceTabDTOList = new ArrayList<>();

        if(referenceTabStatus)
            referenceTabDTOList = referenceTabDAO.findReferenceTabDataForExport(tabularviewExportRequest, customJdbcConnection.getUserJdbcObject(httpSession, userSessionId), currentSessionUserToken);

        LOGGER.info(" exportCSVFileForReferenceTabularViewWithDifferentFromTempTable : referenceTabDToList size is {} "+referenceTabDTOList.size());

        WriteDataToCSV.writeDataToCsvUsingStringArrayForReference(response.getWriter(),referenceTabDTOList);
    }


    /***
     * <p>
     *     Api call for  for the Structure data collection
     *     Testing the image generation
     * </p>
     */
    @RequestMapping(value = "/download/png", method = RequestMethod.GET , produces = MediaType.IMAGE_PNG_VALUE)
    public  void test(HttpServletResponse response, @RequestHeader("userSessionId") String userSessionId, HttpSession httpSession)throws  IOException
    {

        String currentSessionUserToken = (String) httpSession.getAttribute(userSessionId+"_token");

        response.setContentType("image/png");

        response.setHeader("Content-Disposition", "attachment; filename=test.png");

        WriteDataToCSV.convertToPng(response.getWriter());
    }

    /****
     * <p>
     *     This controller is used for theto generate the image of smiles of
     *     Structure Tab
     * </p>
     * @param tabularviewExportRequest
     * @param response
     * @param userSessionId
     * @param httpSession
     * @throws IOException
     */

    @RequestMapping(value = "/generate_image", method = RequestMethod.POST  ,  produces = MediaType.IMAGE_PNG_VALUE)
    public  void imagegeratonFileForStructureTabularSmilesData(@RequestBody TabularviewExportRequest tabularviewExportRequest, HttpServletResponse response, @RequestHeader("userSessionId") String userSessionId, HttpSession httpSession)throws  IOException
    {
        LOGGER.debug("Tabular View for Strcuture Tab - imagegeratonFileForStructureTabularSmilesData ");

        String currentSessionUserToken = (String) httpSession.getAttribute(userSessionId+"_token");

        response.setContentType("image/png");

        response.setHeader("Content-Disposition", "attachment; filename=test.png");

        Boolean strTabStatus = Boolean.TRUE;

        List<StructureDetailsTabDTO> structureDetailsTabDTOList = new ArrayList<>();

        if(strTabStatus)
            structureDetailsTabDTOList = structureDetailsTabDAO.findStructureTabDataForExport(tabularviewExportRequest, customJdbcConnection.getUserJdbcObject(httpSession, userSessionId), currentSessionUserToken);

        LOGGER.info(" imagegeratonFileForStructureTabularSmilesData : structureDetailsTabDTOList size is {} "+structureDetailsTabDTOList.size());

        WriteDataToCSV.generateImagesForStrcuture(response.getWriter(),structureDetailsTabDTOList);
    }


    /***
     * <p>
     *     Api call for  for the Reference data collection
     *     for the flatfile test
     * </p>
     */
    @RequestMapping(value = "/download/flatfile_data/reference/fromtempdata", method = RequestMethod.GET)
    public  void exportFlatFileForReferenceTabularViewWithDifferentFromTempTable(HttpServletResponse response, @RequestHeader("userSessionId") String userSessionId, HttpSession httpSession)throws  IOException
    {
        LOGGER.debug("Tabular View for Reference Tab - findActivityTabByActIds ");

        String currentSessionUserToken = (String) httpSession.getAttribute(userSessionId+"_token");

        response.setContentType("text/tab-separated-values");

        response.setHeader("Content-Disposition", "attachment; filename=flatfile.tsv");

        String fileName = "export.csv";

        WriteDataToCSV.writeDataToFlatFileUsingStringArrayForReference(response.getWriter() ,fileName);
    }

}

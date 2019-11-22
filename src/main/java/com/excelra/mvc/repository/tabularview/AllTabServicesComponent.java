package com.excelra.mvc.repository.tabularview;

import chemaxon.license.Base64;
import com.excelra.mvc.exception.GoStarRestTemplateException;
import com.excelra.mvc.exception.GoStarSqlException;
import com.excelra.mvc.model.tabularview.*;
import com.excelra.mvc.model.tabularview.activitytab.ActivityTabDataMicroService;
import com.excelra.mvc.model.tabularview.activitytab.ActivityTabFilterRequestForMicroService;
import com.excelra.mvc.model.tabularview.assaytab.AssayTabDataMicroService;
import com.excelra.mvc.model.tabularview.assaytab.AssayTabFilterRequestForMicroService;
import com.excelra.mvc.model.tabularview.clinicalstatus.ClinicalStatusExportRequest;
import com.excelra.mvc.model.tabularview.clinicalstatus.ClinicalStatusTabDataMicroService;
import com.excelra.mvc.model.tabularview.clinicalstatus.ClinicalStatusTabFilterRequestForMicroService;
import com.excelra.mvc.model.tabularview.indication.IndicationTabDataMicroService;
import com.excelra.mvc.model.tabularview.indication.IndicationTabFilterRequestForMicroService;
import com.excelra.mvc.model.tabularview.proteinclassificationtab.ProteinClassificationTabDataMicroService;
import com.excelra.mvc.model.tabularview.proteinclassificationtab.ProteinClassificationTabFilterRequestForMicroService;
import com.excelra.mvc.model.tabularview.referencetab.ReferenceTabDataMicroService;
import com.excelra.mvc.model.tabularview.referencetab.ReferenceTabFilterRequestForMicroService;
import com.excelra.mvc.model.tabularview.structuretab.StructureTabDataMicroService;
import com.excelra.mvc.model.tabularview.structuretab.StructureTabFilterRequestForMicroService;
import com.excelra.mvc.model.tabularview.targetdetails.TargetDetailsTabDataMicroService;
import com.excelra.mvc.model.tabularview.targetdetails.TargetDetailsTabFilterRequestForMicroService;
import com.excelra.mvc.model.userjdbc.UserJdbc;
import com.excelra.mvc.repository.AllMappingIdsDAO;
import com.excelra.mvc.utility.AllTabServicesUtility;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * All Tab services component is used for method which are related to all Tabs DAO services.
 * Also methods includes to all respective Microservices and returns the respective object to serve like component.
 *
 * @author venkateswarlu.s
 */
@Component
public class AllTabServicesComponent {

    private static final Logger LOGGER = LoggerFactory.getLogger(AllTabServicesComponent.class);

    @Autowired
    private ServletContext servletContext;

    @Value("${microservices.url}")
    private String microServiceUrl;

    @Value("${microservice.activitytab.fieldfilter.service}")
    private String activityTabFieldFilterService;

    @Value("${microservice.activitytab.data.service}")
    private String activityTabDataService;

    @Value("${microservice.activitytab.data.export.service}")
    private String activityTabDataExportService;

    @Value("${microservice.assaytab.fieldfilter.service}")
    private String assayTabFieldFilterService;

    @Value("${microservice.assaytab.data.service}")
    private String assayTabDataService;

    @Value("${microservice.assaytab.data.export.service}")
    private String assayTabDataExportService;

    @Value("${microservice.reftab.fieldfilter.service}")
    private String refTabFieldFilterService;

    @Value("${microservice.reftab.data.service}")
    private String refTabDataService;

    @Value("${microservice.reftab.data.export.service}")
    private String refTabDataExportService;

    @Value("${microservice.strtab.fieldfilter.service}")
    private String strTabFieldFilterService;

    @Value("${microservice.strtab.data.service}")
    private String strTabDataService;

    @Value("${microservice.strtab.data.export.service}")
    private String strTabDataExportService;

    @Value("${microservice.proteinclassificationtab.fieldfilter.service}")
    private String proteinClassificationTabFieldFilterService;

    @Value("${microservice.proteinclassificationtab.data.service}")
    private String proteinClassificationTabDataService;

    @Value("${microservice.proteinclassificationtab.data.export.service}")
    private String proteinClassificationTabDataExportService;

    @Value("${microservice.targetdetailstab.fieldfilter.service}")
    private String targetDetailsTabFieldFilterService;

    @Value("${microservice.targetdetailstab.data.service}")
    private String targetDetailsTabDataService;

    @Value("${microservice.targetdetailstab.data.export.service}")
    private String targetDetailsTabDataExportService;

    @Value("${microservice.clinicalstatustab.fieldfilter.service}")
    private String clinicalStatusTabFieldFilterService;

    @Value("${microservice.clinicalstatustab.data.service}")
    private String clinicalStatusTabDataService;

    @Value("${microservice.clinicalstatustab.data.export.service}")
    private String clinicalStatusTabDataExportService;

    @Value("${microservice.indicationtab.fieldfilter.service}")
    private String indicationTabFieldFilterService;

    @Value("${microservice.indicationtab.data.service}")
    private String indicationTabDataService;

    @Value("${microservice.indicationtab.data.export.service}")
    private String indicationTabDataExportService;

    @Value("${microservice.framework2tab.fieldfilter.service}")
    private String framework2TabFieldFilterService;

    @Value("${microservice.framework2tab.data.service}")
    private String framework2TabDataService;

    @Value("${microservice.framework2tab.data.export.service}")
    private String framework2TabDataExportService;

    @Value("${microservice.scaffold2tab.fieldfilter.service}")
    private String scaffold2TabFieldFilterService;

    @Value("${microservice.scaffold2tab.data.service}")
    private String scaffold2TabDataService;

    @Value("${microservice.scaffold2tab.data.export.service}")
    private String scaffold2TabDataExportService;

    @Value("${microservice.skeletontab.fieldfilter.service}")
    private String skeletonTabFieldFilterService;

    @Value("${microservice.skeletontab.data.service}")
    private String skeletonTabDataService;

    @Value("${microservice.skeletontab.data.export.service}")
    private String skeletonTabDataExportService;

    private static final String RESPONSE_ACTIVITY_TAB_DTO_LIST = "activityTabDTOList";

    private static final String RESPONSE_ASSAY_TAB_DTO_LIST = "assayTabDTOList";

    private static final String RESPONSE_REFERENCE_TAB_DTO_LIST = "referenceTabDTOList";

    private static final String RESPONSE_STRUCTURE_DETAILS_TAB_DTO_LIST = "structureDetailsTabDTOList";

    private static final String RESPONSE_PROTEINCLASSIFICATION_TAB_DTO_LIST = "proteinClassificationTabDTOList";

    private static final String RESPONSE_TARGETDETAILS_TAB_DTO_LIST = "targetDetailsTabDTOList";

    private static final String RESPONSE_CLINICALSTATUS_TAB_DTO_LIST = "clinicalStatusTabDTOList";

    private static final String RESPONSE_INDICATION_TAB_DTO_LIST = "indicationTabDTOList";

    private static final String RESPONSE_FRAMEWORK2_TAB_DTO_LIST = "framework2TabDTOList";

    private static final String RESPONSE_SCAFFOLD2_TAB_DTO_LIST = "scaffold2TabDTOList";

    private static final String RESPONSE_SKELETON_TAB_DTO_LIST = "skeletonTabDTOList";

    private static final String RESPONSE_COUNT = "count";

    private static final String ASSAY = "Assay";

    private static final String REFERENCE = "Reference";

    private static final String STRUCTURE = "Structure";

    private static final String ACTIVITY = "Activity";

    private static final String PROTEINCLASSIFICATION = "ProteinClassification";

    private static final String TARGETDETAILS = "TargetDetails";

    private static final String CLINICALSTATUS = "ClinicalStatus";

    private static final String INDICATION = "Indication";

    private static final String FRAMEWORK2 = "Framework2";

    private static final String SCAFFOLD2 = "Scaffold2";

    private static final String SKELETON = "Skeleton";

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private AllTabServicesUtility allTabServicesUtility;

    @Autowired
    private AllMappingIdsDAO allMappingIdsDAO;


    /**
     * To get activity specific Jdbc Connection to fetch data from Specific connection Temp table data
     *
     * @param userJdbc
     * @return
     */
    public JdbcTemplate getActivitySpecificJdbcConnection(UserJdbc userJdbc) {
        return allMappingIdsDAO.getActivitySpecificJdbcConnection(userJdbc, "ProteinClassification");
    }

    /**
     * @param selectedTabIds
     * @param specificJdbc
     * @return
     */
    public List<Long> fetchIdsFromTempTable(Map<String, List<Long>> selectedTabIds, Map<String, List<Long>> unSelectedTabIds, JdbcTemplate specificJdbc, String fieldSpecificQuery) throws GoStarRestTemplateException {

        try {
            String specificIdListFromVisualTemp = fieldSpecificQuery;

            if (selectedTabIds.isEmpty() && unSelectedTabIds.isEmpty()) {
                LOGGER.info(" FetchIdsFrmTempTable - Both Selected and UnSelected List Empty ");
                LOGGER.info(" Query without Combinations -  {} ", specificIdListFromVisualTemp);

            } else if (!selectedTabIds.isEmpty() && !unSelectedTabIds.isEmpty()) {

                specificIdListFromVisualTemp = selectedIdsQueryBuild(selectedTabIds, specificIdListFromVisualTemp);
                specificIdListFromVisualTemp = unSelectedIdsQueryBuild(unSelectedTabIds, specificIdListFromVisualTemp);
                LOGGER.info(" Query with Select and UnSelected Combinations -  {} ", specificIdListFromVisualTemp);

                specificIdListFromVisualTemp = specificIdListFromVisualTemp.replaceAll("\\)\\s+where", ") and ");
            } else if (selectedTabIds.isEmpty() && !unSelectedTabIds.isEmpty()) {
                LOGGER.info(" FetchIdsFrmTempTable - Only UnSelectedTabIds - {} ", unSelectedTabIds.size());

                specificIdListFromVisualTemp = unSelectedIdsQueryBuild(unSelectedTabIds, specificIdListFromVisualTemp);
                LOGGER.info(" Query with UnSelected Combinations -  {} ", specificIdListFromVisualTemp);

            } else if (!selectedTabIds.isEmpty() && unSelectedTabIds.isEmpty()) {
                LOGGER.info(" FetchIdsFrmTempTable - Only SelectedTabIds - {} ", selectedTabIds.size());

                specificIdListFromVisualTemp = selectedIdsQueryBuild(selectedTabIds, specificIdListFromVisualTemp);

                LOGGER.info(" Query with Selected Combinations -  {} ", specificIdListFromVisualTemp);

            }

            return getIdsFromVisualTempTable(specificJdbc, specificIdListFromVisualTemp);
        } catch (Exception e) {

            LOGGER.error(" Error while processing fetchIdsFromTempTable service method, error is {} ", e.getMessage());
            throw new GoStarSqlException(" Error while processing fetchIdsFromTempTable service method, error is {} ", e);

        }
    }

    /**
     * @param selectedTabIds
     * @param specificIdListFromVisualTemp
     * @return
     */
    public String selectedIdsQueryBuild(Map<String, List<Long>> selectedTabIds, String specificIdListFromVisualTemp) {

        for (Map.Entry<String, List<Long>> entry : selectedTabIds.entrySet()) {

            String inQueryValues = StringUtils.join(entry.getValue(), ",");

            switch (entry.getKey()) {

                case ASSAY:
                    if (!entry.getValue().isEmpty())
                        specificIdListFromVisualTemp = specificIdListFromVisualTemp + ((selectedTabIds.entrySet().stream().findFirst().get().getKey().equalsIgnoreCase(ASSAY)) ? " where " : " and ") + " assay_id in (" + inQueryValues + ") ";
                    break;
                case REFERENCE:
                    if (!entry.getValue().isEmpty())
                        specificIdListFromVisualTemp = specificIdListFromVisualTemp + ((selectedTabIds.entrySet().stream().findFirst().get().getKey().equalsIgnoreCase(REFERENCE)) ? " where " : " and ") + " ref_id in (" + inQueryValues + ") ";
                    break;
                case STRUCTURE:
                    if (!entry.getValue().isEmpty())
                        specificIdListFromVisualTemp = specificIdListFromVisualTemp + ((selectedTabIds.entrySet().stream().findFirst().get().getKey().equalsIgnoreCase(STRUCTURE)) ? " where " : " and ") + " gvk_id in (" + inQueryValues + ") ";
                    break;
                case ACTIVITY:
                    if (!entry.getValue().isEmpty())
                        specificIdListFromVisualTemp = specificIdListFromVisualTemp + ((selectedTabIds.entrySet().stream().findFirst().get().getKey().equalsIgnoreCase(ACTIVITY)) ? " where " : " and ") + " act_id in (" + inQueryValues + ") ";
                    break;
                case PROTEINCLASSIFICATION:
                    if (!entry.getValue().isEmpty())
                        specificIdListFromVisualTemp = specificIdListFromVisualTemp + ((selectedTabIds.entrySet().stream().findFirst().get().getKey().equalsIgnoreCase(PROTEINCLASSIFICATION)) ? " where " : " and ") + " stdname_id in (" + inQueryValues + ") ";
                    break;
                case TARGETDETAILS:
                    if (!entry.getValue().isEmpty())
                        specificIdListFromVisualTemp = specificIdListFromVisualTemp + ((selectedTabIds.entrySet().stream().findFirst().get().getKey().equalsIgnoreCase(TARGETDETAILS)) ? " where " : " and ") + " target_id in (" + inQueryValues + ") ";
                    break;
                case CLINICALSTATUS:
                    if (!entry.getValue().isEmpty())
                        specificIdListFromVisualTemp = specificIdListFromVisualTemp + ((selectedTabIds.entrySet().stream().findFirst().get().getKey().equalsIgnoreCase(CLINICALSTATUS)) ? " where " : " and ") + " gvk_id in (" + inQueryValues + ") ";
                    break;
                case INDICATION:
                    if (!entry.getValue().isEmpty())
                        specificIdListFromVisualTemp = specificIdListFromVisualTemp + ((selectedTabIds.entrySet().stream().findFirst().get().getKey().equalsIgnoreCase(INDICATION)) ? " where " : " and ") + " gvk_id in (" + inQueryValues + ") ";
                    break;

                default:
                    // System.out.println("No Proper key");
                    LOGGER.info(" No Proper key ");

            }
        }

        return specificIdListFromVisualTemp;
    }

    /**
     * @param unSelectedTabIds
     * @param specificIdListFromVisualTemp
     * @return
     */
    public String unSelectedIdsQueryBuild(Map<String, List<Long>> unSelectedTabIds, String specificIdListFromVisualTemp) {
        for (Map.Entry<String, List<Long>> entry : unSelectedTabIds.entrySet()) {

            String outQueryValues = StringUtils.join(entry.getValue(), ",");

            switch (entry.getKey()) {

                case ASSAY:
                    if (!entry.getValue().isEmpty())
                        specificIdListFromVisualTemp = specificIdListFromVisualTemp + ((unSelectedTabIds.entrySet().stream().findFirst().get().getKey().equalsIgnoreCase(ASSAY)) ? " where " : " and ") + " assay_id not in (" + outQueryValues + ") ";
                    break;
                case REFERENCE:
                    if (!entry.getValue().isEmpty())
                        specificIdListFromVisualTemp = specificIdListFromVisualTemp + ((unSelectedTabIds.entrySet().stream().findFirst().get().getKey().equalsIgnoreCase(REFERENCE)) ? " where " : " and ") + " ref_id not in (" + outQueryValues + ") ";
                    break;
                case STRUCTURE:
                    if (!entry.getValue().isEmpty())
                        specificIdListFromVisualTemp = specificIdListFromVisualTemp + ((unSelectedTabIds.entrySet().stream().findFirst().get().getKey().equalsIgnoreCase(STRUCTURE)) ? " where " : " and ") + " gvk_id not in (" + outQueryValues + ") ";
                    break;
                case ACTIVITY:
                    if (!entry.getValue().isEmpty())
                        specificIdListFromVisualTemp = specificIdListFromVisualTemp + ((unSelectedTabIds.entrySet().stream().findFirst().get().getKey().equalsIgnoreCase(ACTIVITY)) ? " where " : " and ") + " act_id not in (" + outQueryValues + ") ";
                    break;
                case PROTEINCLASSIFICATION:
                    if (!entry.getValue().isEmpty())
                        specificIdListFromVisualTemp = specificIdListFromVisualTemp + ((unSelectedTabIds.entrySet().stream().findFirst().get().getKey().equalsIgnoreCase(PROTEINCLASSIFICATION)) ? " where " : " and ") + " stdname_id not in (" + outQueryValues + ") ";
                    break;
                case TARGETDETAILS:
                    if (!entry.getValue().isEmpty())
                        specificIdListFromVisualTemp = specificIdListFromVisualTemp + ((unSelectedTabIds.entrySet().stream().findFirst().get().getKey().equalsIgnoreCase(TARGETDETAILS)) ? " where " : " and ") + " target_id not in (" + outQueryValues + ") ";
                    break;
                case CLINICALSTATUS:
                    if (!entry.getValue().isEmpty())
                        specificIdListFromVisualTemp = specificIdListFromVisualTemp + ((unSelectedTabIds.entrySet().stream().findFirst().get().getKey().equalsIgnoreCase(CLINICALSTATUS)) ? " where " : " and ") + " gvk_id not in (" + outQueryValues + ") ";
                    break;
                case INDICATION:
                    if (!entry.getValue().isEmpty())
                        specificIdListFromVisualTemp = specificIdListFromVisualTemp + ((unSelectedTabIds.entrySet().stream().findFirst().get().getKey().equalsIgnoreCase(INDICATION)) ? " where " : " and ") + " gvk_id not in (" + outQueryValues + ") ";
                    break;
                default:
                    // System.out.println("No Proper key");
                    LOGGER.info(" No Proper key ");

            }
        }

        return specificIdListFromVisualTemp;
    }

    /**
     *
     * @param specificJdbc
     * @param visualTempTableQuery
     * @return
     * @throws GoStarSqlException
     */
    public List<TempTableData> getTempTableData(JdbcTemplate specificJdbc, String visualTempTableQuery) throws GoStarSqlException {

        try {
            List<TempTableData> tempTabDataList = specificJdbc.query(visualTempTableQuery, new BeanPropertyRowMapper<>(TempTableData.class));

            return tempTabDataList;
        } catch (Exception e) {
            LOGGER.error(" Error while processing getTempTableData service method, error is {} ", e.getMessage());
            throw new GoStarSqlException(" Error while processing getTempTableData service method, error is {} ", e);
        }

    }

    /**
     * To get actIds list from Visual temp table.
     *
     * @return
     */
    public synchronized List<Long> getIdsFromVisualTempTable(JdbcTemplate jdbcTemplate, String visualResultsQuery) throws GoStarSqlException {

        List<Long> idsList = new ArrayList<>();
        try {

            // System.out.println("Final Prepared VisualResults Query "+visualResultsQuery);
            LOGGER.info(" Final Prepared VisualResults Query -  {} ", visualResultsQuery);

            NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);

            List<BigInteger> resultData = namedParameterJdbcTemplate.query(visualResultsQuery,
                    new BigIntegerDataMapper()
            );

            for (int i = 0; i < resultData.size(); i++) {
                idsList.add(((Objects.nonNull(resultData.get(i))) ? resultData.get(i).longValue() : null));
            }

        } catch (Exception e) {
            // e.printStackTrace();
            LOGGER.error("Error while executing Temp table query {} ", e.getMessage());
            throw new GoStarSqlException(" Error while executing Temp table query {} ", e);
        }
        return idsList;
    }

    /**
     * Activity Filter Microservice RestTemplate
     *
     * @param tabularviewFilterRequest
     * @param actIdList
     * @return
     */
    public List<ActivityTabDTO> getActivityFieldFilteredData(TabularviewFilterRequest tabularviewFilterRequest, List<Long> actIdList, String currentSessionUserToken) throws GoStarRestTemplateException {

        try {
            final String baseUrl = microServiceUrl + activityTabFieldFilterService;
            URI uri = new URI(baseUrl);

            LOGGER.info(" In getActivityFieldFilteredData method  - {} ", actIdList);

            ActivityTabFilterRequestForMicroService activityTabFilterRequestForMicroService = allTabServicesUtility.prepareActivityTabFilterRequestForMicroServiceObject(tabularviewFilterRequest, actIdList);
            HttpHeaders headers = allTabServicesUtility.prepareHttpHeaders(currentSessionUserToken);

            HttpEntity<ActivityTabFilterRequestForMicroService> request = new HttpEntity<>(activityTabFilterRequestForMicroService, headers);

            ResponseEntity<String> responseEntity = restTemplate.postForEntity(uri, request, String.class);
            JSONArray activityJson = new JSONArray(responseEntity.getBody());

            LOGGER.info(" In getActivityFieldFilteredData method RestTemplate Response JSON - {} ", activityJson.length());

            ObjectMapper mapper = new ObjectMapper();
            List<ActivityTabDTO> activityTabDTOS = new ArrayList<>();
            if (Objects.nonNull(activityJson)) {
                activityTabDTOS = mapper.reader()
                        .forType(new TypeReference<Object>() {
                        })
                        .readValue(activityJson.toString());
            }

            LOGGER.info(" AllTabServicesComponent :: getActivityFieldFilteredData - Received Results count {} ", activityTabDTOS.size());
            return activityTabDTOS;

        } catch (URISyntaxException ex) {
            LOGGER.error(" AllTabServicesComponent :: getActivityFieldFilteredData - URISyntaxException {} ", ex.getMessage());
            throw new GoStarRestTemplateException(" Error while processing getActivityFieldFilteredData service method, error is {} ", ex);

        } catch (Exception ex) {
            LOGGER.error(" AllTabServicesComponent :: getActivityFieldFilteredData - Exception {} ", ex.getMessage());
            throw new GoStarRestTemplateException(" Error while processing getActivityFieldFilteredData service method, error is {} ", ex);

        }

    }

    /**
     * Activity data Microservice RestTemplate
     *
     * @param tabularviewRequest
     * @param actIdList
     * @return
     */
    public ActivityTabPageData getActivityData(TabularviewRequest tabularviewRequest, List<Long> actIdList, String currentSessionUserToken) throws GoStarRestTemplateException {

        try {
            final String baseUrl = microServiceUrl + activityTabDataService;
            URI uri = new URI(baseUrl);

            ActivityTabDataMicroService activityTabDataMicroService = allTabServicesUtility.prepareActivityTabDataMicroServiceObject(tabularviewRequest, actIdList);
            HttpHeaders headers = allTabServicesUtility.prepareHttpHeaders(currentSessionUserToken);

            HttpEntity<ActivityTabDataMicroService> request = new HttpEntity<>(activityTabDataMicroService, headers);

            ResponseEntity<String> responseEntity = restTemplate.postForEntity(uri, request, String.class);

            JSONObject activityJson = new JSONObject(responseEntity.getBody());

            LOGGER.info(" In getActivityData method RestTemplate Response JSON - {} ", activityJson);

            ObjectMapper mapper = new ObjectMapper();

            List<ActivityTabDTO> activityTabDTOS = new ArrayList<>();
            if (Objects.nonNull(activityJson.getJSONArray(RESPONSE_ACTIVITY_TAB_DTO_LIST))) {
                activityTabDTOS = mapper.reader()
                        .forType(new TypeReference<Object>() {
                        })
                        .readValue(activityJson.getJSONArray(RESPONSE_ACTIVITY_TAB_DTO_LIST).toString());
            }

            Long count = 0L;
            if (Objects.nonNull(activityJson.get(RESPONSE_COUNT)))
                count = Long.parseLong(activityJson.get(RESPONSE_COUNT).toString());

            ActivityTabPageData activityTabPageData = new ActivityTabPageData();
            activityTabPageData.setActivityTabDTOList(activityTabDTOS);
            activityTabPageData.setCount(count);

            LOGGER.info(" AllTabServicesComponent :: getActivityData - Received Results count {} ", activityTabPageData.getActivityTabDTOList().size());

            return activityTabPageData;
        } catch (URISyntaxException ex) {
            ex.printStackTrace();
            LOGGER.error(" AllTabServicesComponent :: getActivityData - URISyntaxException {} ", ex.getMessage());
            ActivityTabPageData activityTabPageData = new ActivityTabPageData();
            activityTabPageData.setActivityTabDTOList(new ArrayList<>());
            activityTabPageData.setCount(0L);

            return activityTabPageData;
        } catch (Exception ex) {
            ex.printStackTrace();
            LOGGER.error(" AllTabServicesComponent :: getActivityFieldFilteredData - Exception {} ", ex.getMessage());

            ActivityTabPageData activityTabPageData = new ActivityTabPageData();
            activityTabPageData.setActivityTabDTOList(new ArrayList<>());
            activityTabPageData.setCount(0L);

            return activityTabPageData;
        }
    }

    /**
     * Activity data to Export Microservice RestTemplate
     *
     * @param actIdList
     * @return
     */
    public List<ActivityTabDTO> getActivityDataForExport(List<Long> actIdList, String currentSessionUserToken) throws GoStarRestTemplateException {

        try {
            final String baseUrl = microServiceUrl + activityTabDataExportService;
            System.out.println("----> URL " + baseUrl);
            URI uri = new URI(baseUrl);

            HttpHeaders headers = allTabServicesUtility.prepareHttpHeaders(currentSessionUserToken);

            HttpEntity<List<Long>> request = new HttpEntity<>(actIdList, headers);

            ResponseEntity<String> responseEntity = restTemplate.postForEntity(uri, request, String.class);

            JSONArray activityJson = new JSONArray(responseEntity.getBody());
            LOGGER.info(" In getActivityData method RestTemplate Response JSON - {} ", activityJson.length());
            ObjectMapper mapper = new ObjectMapper();

            List<ActivityTabDTO> activityTabDTOS = new ArrayList<>();
            if (Objects.nonNull(activityJson)) {
                activityTabDTOS = mapper.readValue(activityJson.toString(), new TypeReference<List<ActivityTabDTO>>() {
                });
            }

            LOGGER.info(" AllTabServicesComponent :: getActivityData - Received Results count {} ", activityTabDTOS.size());

            return activityTabDTOS;
        } catch (URISyntaxException ex) {
            ex.printStackTrace();
            LOGGER.error(" AllTabServicesComponent :: getActivityData - URISyntaxException {} ", ex.getMessage());
            return new ArrayList<>();
        } catch (Exception ex) {
            ex.printStackTrace();
            LOGGER.error(" AllTabServicesComponent :: getActivityFieldFilteredData - Exception {} ", ex.getMessage());
            return new ArrayList<>();
        }
    }

    /**
     * Assay data to Export Microservice RestTemplate
     *
     * @param assayIdList
     * @return
     */
    public List<AssayTabDTO> getAssayDataForExport(List<Long> assayIdList, String currentSessionUserToken) throws GoStarRestTemplateException {

        try {
            final String baseUrl = microServiceUrl + assayTabDataExportService;
            URI uri = new URI(baseUrl);

            HttpHeaders headers = allTabServicesUtility.prepareHttpHeaders(currentSessionUserToken);

            HttpEntity<List<Long>> request = new HttpEntity<>(assayIdList, headers);

            ResponseEntity<String> responseEntity = restTemplate.postForEntity(uri, request, String.class);

            JSONArray assayJson = new JSONArray(responseEntity.getBody());
            LOGGER.info(" In getAssayDataForExport method RestTemplate Response JSON - {} ", assayJson.length());
            ObjectMapper mapper = new ObjectMapper();

            List<AssayTabDTO> assayTabDTOS = new ArrayList<>();
            if (Objects.nonNull(assayJson)) {
                assayTabDTOS = mapper.readValue(assayJson.toString(), new TypeReference<List<AssayTabDTO>>() {
                });
            }

            LOGGER.info(" AllTabServicesComponent :: getAssayDataForExport - Received Results count {} ", assayTabDTOS.size());

            return assayTabDTOS;
        } catch (URISyntaxException ex) {
            ex.printStackTrace();
            LOGGER.error(" AllTabServicesComponent :: getAssayDataForExport - URISyntaxException {} ", ex.getMessage());
            return new ArrayList<>();
        } catch (Exception ex) {
            ex.printStackTrace();
            LOGGER.error(" AllTabServicesComponent :: getAssayDataForExport - Exception {} ", ex.getMessage());
            return new ArrayList<>();
        }
    }

    /**
     * Reference data to Export Microservice RestTemplate
     *
     * @param refIdList
     * @return
     */
    public List<ReferenceTabDTO> getReferenceDataForExport(List<Long> refIdList, String currentSessionUserToken) throws GoStarRestTemplateException {

        try {
            final String baseUrl = microServiceUrl + refTabDataExportService;
            URI uri = new URI(baseUrl);

            HttpHeaders headers = allTabServicesUtility.prepareHttpHeaders(currentSessionUserToken);

            HttpEntity<List<Long>> request = new HttpEntity<>(refIdList, headers);

            ResponseEntity<String> responseEntity = restTemplate.postForEntity(uri, request, String.class);

            JSONArray refJson = new JSONArray(responseEntity.getBody());
            LOGGER.info(" In getReferenceDataForExport method RestTemplate Response JSON - {} ", refJson.length());
            ObjectMapper mapper = new ObjectMapper();

            List<ReferenceTabDTO> refTabDTOS = new ArrayList<>();
            if (Objects.nonNull(refJson)) {
                refTabDTOS = mapper.readValue(refJson.toString(), new TypeReference<List<ReferenceTabDTO>>() {
                });
            }

            LOGGER.info(" AllTabServicesComponent :: getReferenceDataForExport - Received Results count {} ", refTabDTOS.size());

            return refTabDTOS;
        } catch (URISyntaxException ex) {
            ex.printStackTrace();
            LOGGER.error(" AllTabServicesComponent :: getAssayDataForExport - URISyntaxException {} ", ex.getMessage());
            return new ArrayList<>();
        } catch (Exception ex) {
            ex.printStackTrace();
            LOGGER.error(" AllTabServicesComponent :: getAssayDataForExport - Exception {} ", ex.getMessage());
            return new ArrayList<>();
        }
    }

    /**
     *
     * @param stdnameIdList
     * @param currentSessionUserToken
     * @return
     * @throws GoStarRestTemplateException
     */
    public List<ProteinClassificationTabDTO> getProteinClassificationDataForExport(List<Long> stdnameIdList, String currentSessionUserToken) throws GoStarRestTemplateException {

        try {
            final String baseUrl = microServiceUrl + proteinClassificationTabDataExportService;
            URI uri = new URI(baseUrl);

            HttpHeaders headers = allTabServicesUtility.prepareHttpHeaders(currentSessionUserToken);

            HttpEntity<List<Long>> request = new HttpEntity<>(stdnameIdList, headers);

            ResponseEntity<String> responseEntity = restTemplate.postForEntity(uri, request, String.class);

            JSONArray proteinClassificationJson = new JSONArray(responseEntity.getBody());
            LOGGER.info(" In getProteinClassificationDataForExport method RestTemplate Response JSON - {} ", proteinClassificationJson.length());
            ObjectMapper mapper = new ObjectMapper();

            List<ProteinClassificationTabDTO> proteinClassificationTabDTOS = new ArrayList<>();
            if (Objects.nonNull(proteinClassificationJson)) {
                proteinClassificationTabDTOS = mapper.readValue(proteinClassificationJson.toString(), new TypeReference<List<ProteinClassificationTabDTO>>() {
                });
            }

            LOGGER.info(" AllTabServicesComponent :: getProteinClassificationDataForExport - Received Results count {} ", proteinClassificationTabDTOS.size());

            return proteinClassificationTabDTOS;
        } catch (URISyntaxException ex) {
            ex.printStackTrace();
            LOGGER.error(" AllTabServicesComponent :: getProteinClassificationDataForExport - URISyntaxException {} ", ex.getMessage());
            return new ArrayList<>();
        } catch (Exception ex) {
            ex.printStackTrace();
            LOGGER.error(" AllTabServicesComponent :: getProteinClassificationDataForExport - Exception {} ", ex.getMessage());
            return new ArrayList<>();
        }
    }

    /**
     *
     * @param targetIdList
     * @param currentSessionUserToken
     * @return
     * @throws GoStarRestTemplateException
     */
    public List<TargetDetailsTabDTO> getTargetDetailsDataForExport(List<Long> targetIdList, String currentSessionUserToken) throws GoStarRestTemplateException {

        try {
            final String baseUrl = microServiceUrl + targetDetailsTabDataExportService;
            URI uri = new URI(baseUrl);

            HttpHeaders headers = allTabServicesUtility.prepareHttpHeaders(currentSessionUserToken);

            HttpEntity<List<Long>> request = new HttpEntity<>(targetIdList, headers);

            ResponseEntity<String> responseEntity = restTemplate.postForEntity(uri, request, String.class);

            JSONArray targetDetailsJson = new JSONArray(responseEntity.getBody());
            LOGGER.info(" In getTargetDetailsDataForExport method RestTemplate Response JSON - {} ", targetDetailsJson.length());
            ObjectMapper mapper = new ObjectMapper();

            List<TargetDetailsTabDTO> targetDetailsTabDTOS = new ArrayList<>();
            if (Objects.nonNull(targetDetailsJson)) {
                targetDetailsTabDTOS = mapper.readValue(targetDetailsJson.toString(), new TypeReference<List<TargetDetailsTabDTO>>() {
                });
            }

            LOGGER.info(" AllTabServicesComponent :: getTargetDetailsDataForExport - Received Results count {} ", targetDetailsTabDTOS.size());

            return targetDetailsTabDTOS;
        } catch (URISyntaxException ex) {
            ex.printStackTrace();
            LOGGER.error(" AllTabServicesComponent :: getTargetDetailsDataForExport - URISyntaxException {} ", ex.getMessage());
            return new ArrayList<>();
        } catch (Exception ex) {
            ex.printStackTrace();
            LOGGER.error(" AllTabServicesComponent :: getTargetDetailsDataForExport - Exception {} ", ex.getMessage());
            return new ArrayList<>();
        }
    }

    /**
     *
     * @param gvkIdList
     * @param currentSessionUserToken
     * @return
     * @throws GoStarRestTemplateException
     */
    public List<IndicationTabDTO> getIndicationDataForExport(List<Long> gvkIdList, String currentSessionUserToken) throws GoStarRestTemplateException {

        try {
            final String baseUrl = microServiceUrl + indicationTabDataExportService;
            URI uri = new URI(baseUrl);

            HttpHeaders headers = allTabServicesUtility.prepareHttpHeaders(currentSessionUserToken);

            HttpEntity<List<Long>> request = new HttpEntity<>(gvkIdList, headers);

            ResponseEntity<String> responseEntity = restTemplate.postForEntity(uri, request, String.class);

            JSONArray indicationJson = new JSONArray(responseEntity.getBody());
            LOGGER.info(" In getIndicationDataForExport method RestTemplate Response JSON - {} ", indicationJson.length());
            ObjectMapper mapper = new ObjectMapper();

            List<IndicationTabDTO> indicationTabDTOS = new ArrayList<>();
            if (Objects.nonNull(indicationJson)) {
                indicationTabDTOS = mapper.readValue(indicationJson.toString(), new TypeReference<List<IndicationTabDTO>>() {
                });
            }

            LOGGER.info(" AllTabServicesComponent :: getIndicationDataForExport - Received Results count {} ", indicationTabDTOS.size());

            return indicationTabDTOS;
        } catch (URISyntaxException ex) {
            ex.printStackTrace();
            LOGGER.error(" AllTabServicesComponent :: getIndicationDataForExport - URISyntaxException {} ", ex.getMessage());
            return new ArrayList<>();
        } catch (Exception ex) {
            ex.printStackTrace();
            LOGGER.error(" AllTabServicesComponent :: getIndicationDataForExport - Exception {} ", ex.getMessage());
            return new ArrayList<>();
        }
    }

    /**
     *
     * @param gvkIdList
     * @param refIdList
     * @param currentSessionUserToken
     * @return
     * @throws GoStarRestTemplateException
     */
    public List<ClinicalStatusTabDTO> getClinicalStatusDataForExport(List<Long> gvkIdList, List<Long> refIdList, String currentSessionUserToken) throws GoStarRestTemplateException {

        try {
            final String baseUrl = microServiceUrl + clinicalStatusTabDataExportService;
            URI uri = new URI(baseUrl);

            HttpHeaders headers = allTabServicesUtility.prepareHttpHeaders(currentSessionUserToken);

            ClinicalStatusExportRequest clinicalStatusExportRequest = new ClinicalStatusExportRequest();
            clinicalStatusExportRequest.setGvkIdList(gvkIdList);
            clinicalStatusExportRequest.setRefIdList(refIdList);

            HttpEntity<ClinicalStatusExportRequest> request = new HttpEntity<>(clinicalStatusExportRequest, headers);

            ResponseEntity<String> responseEntity = restTemplate.postForEntity(uri, request, String.class);

            JSONArray clinicalStatusJson = new JSONArray(responseEntity.getBody());
            LOGGER.info(" In getClinicalStatusDataForExport method RestTemplate Response JSON - {} ", clinicalStatusJson.length());
            ObjectMapper mapper = new ObjectMapper();

            List<ClinicalStatusTabDTO> clinicalStatusTabDTOS = new ArrayList<>();
            if (Objects.nonNull(clinicalStatusJson)) {
                clinicalStatusTabDTOS = mapper.readValue(clinicalStatusJson.toString(), new TypeReference<List<ClinicalStatusTabDTO>>() {
                });
            }

            LOGGER.info(" AllTabServicesComponent :: getClinicalStatusDataForExport - Received Results count {} ", clinicalStatusTabDTOS.size());

            return clinicalStatusTabDTOS;
        } catch (URISyntaxException ex) {
            ex.printStackTrace();
            LOGGER.error(" AllTabServicesComponent :: getClinicalStatusDataForExport - URISyntaxException {} ", ex.getMessage());
            return new ArrayList<>();
        } catch (Exception ex) {
            ex.printStackTrace();
            LOGGER.error(" AllTabServicesComponent :: getClinicalStatusDataForExport - Exception {} ", ex.getMessage());
            return new ArrayList<>();
        }
    }



    /**
     * Structure data to Export Microservice RestTemplate
     *
     * @param gvkIdList
     * @return
     */
    public List<StructureDetailsTabDTO> getStructureDataForExport(List<Long> gvkIdList, String currentSessionUserToken) throws GoStarRestTemplateException {

        try {
            final String baseUrl = microServiceUrl + strTabDataExportService;
            URI uri = new URI(baseUrl);

            HttpHeaders headers = allTabServicesUtility.prepareHttpHeaders(currentSessionUserToken);

            HttpEntity<List<Long>> request = new HttpEntity<>(gvkIdList, headers);

            ResponseEntity<String> responseEntity = restTemplate.postForEntity(uri, request, String.class);

            JSONArray gvkJson = new JSONArray(responseEntity.getBody());
            LOGGER.info(" In getStructureDataForExport method RestTemplate Response JSON - {} ", gvkJson.length());
            ObjectMapper mapper = new ObjectMapper();

            List<StructureDetailsTabDTO> structureTabDTOS = new ArrayList<>();
            if (Objects.nonNull(gvkJson)) {
                structureTabDTOS = mapper.readValue(gvkJson.toString(), new TypeReference<List<StructureDetailsTabDTO>>() {
                });
            }

            LOGGER.info(" AllTabServicesComponent :: getStructureDataForExport - Received Results count {} ", structureTabDTOS.size());

            return structureTabDTOS;
        } catch (URISyntaxException ex) {
            ex.printStackTrace();
            LOGGER.error(" AllTabServicesComponent :: getStructureDataForExport - URISyntaxException {} ", ex.getMessage());
            return new ArrayList<>();
        } catch (Exception ex) {
            ex.printStackTrace();
            LOGGER.error(" AllTabServicesComponent :: getStructureDataForExport - Exception {} ", ex.getMessage());
            return new ArrayList<>();
        }
    }


    /**
     * Framework2 data to Export Microservice RestTemplate
     *
     * @param gvkIdList
     * @return
     */
    public List<Framework2TabDTO> getFramework2DataForExport(List<Long> gvkIdList, String currentSessionUserToken) throws GoStarRestTemplateException {

        try {
            final String baseUrl = microServiceUrl + framework2TabDataExportService;
            URI uri = new URI(baseUrl);

            HttpHeaders headers = allTabServicesUtility.prepareHttpHeaders(currentSessionUserToken);

            HttpEntity<List<Long>> request = new HttpEntity<>(gvkIdList, headers);

            ResponseEntity<String> responseEntity = restTemplate.postForEntity(uri, request, String.class);

            JSONArray gvkJson = new JSONArray(responseEntity.getBody());
            LOGGER.info(" In getFramework2DataForExport method RestTemplate Response JSON - {} ", gvkJson.length());
            ObjectMapper mapper = new ObjectMapper();

            List<Framework2TabDTO> framework2TabDTOS = new ArrayList<>();
            if (Objects.nonNull(gvkJson)) {
                framework2TabDTOS = mapper.readValue(gvkJson.toString(), new TypeReference<List<Framework2TabDTO>>() {
                });
            }

            LOGGER.info(" AllTabServicesComponent :: getFramework2DataForExport - Received Results count {} ", framework2TabDTOS.size());

            return framework2TabDTOS;
        } catch (URISyntaxException ex) {
            ex.printStackTrace();
            LOGGER.error(" AllTabServicesComponent :: getFramework2DataForExport - URISyntaxException {} ", ex.getMessage());
            return new ArrayList<>();
        } catch (Exception ex) {
            ex.printStackTrace();
            LOGGER.error(" AllTabServicesComponent :: getFramework2DataForExport - Exception {} ", ex.getMessage());
            return new ArrayList<>();
        }
    }

    /**
     * Scaffold2 data to Export Microservice RestTemplate
     *
     * @param gvkIdList
     * @return
     */
    public List<Scaffold2TabDTO> getScaffold2DataForExport(List<Long> gvkIdList, String currentSessionUserToken) throws GoStarRestTemplateException {

        try {
            final String baseUrl = microServiceUrl + scaffold2TabDataExportService;
            URI uri = new URI(baseUrl);

            HttpHeaders headers = allTabServicesUtility.prepareHttpHeaders(currentSessionUserToken);

            HttpEntity<List<Long>> request = new HttpEntity<>(gvkIdList, headers);

            ResponseEntity<String> responseEntity = restTemplate.postForEntity(uri, request, String.class);

            JSONArray gvkJson = new JSONArray(responseEntity.getBody());
            LOGGER.info(" In getScaffold2DataForExport method RestTemplate Response JSON - {} ", gvkJson.length());
            ObjectMapper mapper = new ObjectMapper();

            List<Scaffold2TabDTO> scaffold2TabDTOS = new ArrayList<>();
            if (Objects.nonNull(gvkJson)) {
                scaffold2TabDTOS = mapper.readValue(gvkJson.toString(), new TypeReference<List<Scaffold2TabDTO>>() {
                });
            }

            LOGGER.info(" AllTabServicesComponent :: getScaffold2DataForExport - Received Results count {} ", scaffold2TabDTOS.size());

            return scaffold2TabDTOS;
        } catch (URISyntaxException ex) {
            ex.printStackTrace();
            LOGGER.error(" AllTabServicesComponent :: getScaffold2DataForExport - URISyntaxException {} ", ex.getMessage());
            return new ArrayList<>();
        } catch (Exception ex) {
            ex.printStackTrace();
            LOGGER.error(" AllTabServicesComponent :: getScaffold2DataForExport - Exception {} ", ex.getMessage());
            return new ArrayList<>();
        }
    }

    /**
     * Skeleton data to Export Microservice RestTemplate
     *
     * @param gvkIdList
     * @return
     */
    public List<SkeletonTabDTO> getSkeletonDataForExport(List<Long> gvkIdList, String currentSessionUserToken) throws GoStarRestTemplateException {

        try {
            final String baseUrl = microServiceUrl + skeletonTabDataExportService;
            URI uri = new URI(baseUrl);

            HttpHeaders headers = allTabServicesUtility.prepareHttpHeaders(currentSessionUserToken);

            HttpEntity<List<Long>> request = new HttpEntity<>(gvkIdList, headers);

            ResponseEntity<String> responseEntity = restTemplate.postForEntity(uri, request, String.class);

            JSONArray gvkJson = new JSONArray(responseEntity.getBody());
            LOGGER.info(" In getSkeletonDataForExport method RestTemplate Response JSON - {} ", gvkJson.length());
            ObjectMapper mapper = new ObjectMapper();

            List<SkeletonTabDTO> skeletonTabDTOS = new ArrayList<>();
            if (Objects.nonNull(gvkJson)) {
                skeletonTabDTOS = mapper.readValue(gvkJson.toString(), new TypeReference<List<SkeletonTabDTO>>() {
                });
            }

            LOGGER.info(" AllTabServicesComponent :: getSkeletonDataForExport - Received Results count {} ", skeletonTabDTOS.size());

            return skeletonTabDTOS;
        } catch (URISyntaxException ex) {
            ex.printStackTrace();
            LOGGER.error(" AllTabServicesComponent :: getSkeletonDataForExport - URISyntaxException {} ", ex.getMessage());
            return new ArrayList<>();
        } catch (Exception ex) {
            ex.printStackTrace();
            LOGGER.error(" AllTabServicesComponent :: getSkeletonDataForExport - Exception {} ", ex.getMessage());
            return new ArrayList<>();
        }
    }

    /**
     * @param tabularviewFilterRequest
     * @param assayIdList
     * @param currentSessionUserToken
     * @return
     */
    public List<AssayTabDTO> getAssayFieldFilteredData(TabularviewFilterRequest tabularviewFilterRequest, List<Long> assayIdList, String currentSessionUserToken) throws GoStarRestTemplateException {

        try {
            final String baseUrl = microServiceUrl + assayTabFieldFilterService;
            URI uri = new URI(baseUrl);

            AssayTabFilterRequestForMicroService assayTabFilterRequestForMicroService = allTabServicesUtility.prepareAssayTabFilterRequestForMicroServiceObject(tabularviewFilterRequest, assayIdList);
            HttpHeaders headers = allTabServicesUtility.prepareHttpHeaders(currentSessionUserToken);

            HttpEntity<AssayTabFilterRequestForMicroService> request = new HttpEntity<>(assayTabFilterRequestForMicroService, headers);

            ResponseEntity<String> responseEntity = restTemplate.postForEntity(uri, request, String.class);
            JSONArray assayJson = new JSONArray(responseEntity.getBody());

            LOGGER.info(" In getAssayFieldFilteredData method RestTemplate Response JSON - {} ", assayJson.length());

            ObjectMapper mapper = new ObjectMapper();
            List<AssayTabDTO> assayTabDTOS = new ArrayList<>();
            if (Objects.nonNull(assayJson)) {
                assayTabDTOS = mapper.reader()
                        .forType(new TypeReference<Object>() {
                        })
                        .readValue(assayJson.toString());
            }

            LOGGER.info(" AllTabServicesComponent :: getAssayFieldFilteredData - Received Results count {} ", assayTabDTOS.size());
            return assayTabDTOS;

        } catch (URISyntaxException ex) {
            ex.printStackTrace();
            LOGGER.error(" AllTabServicesComponent :: getAssayFieldFilteredData - URISyntaxException {} ", ex.getMessage());
            return new ArrayList<>();
        } catch (Exception ex) {
            ex.printStackTrace();
            LOGGER.error(" AllTabServicesComponent :: getAssayFieldFilteredData - Exception {} ", ex.getMessage());
            return new ArrayList<>();
        }
    }

    /**
     * @param tabularviewRequest
     * @param assayIdList
     * @param currentSessionUserToken
     * @return
     */
    public AssayTabPageData getAssayData(TabularviewRequest tabularviewRequest, List<Long> assayIdList, String currentSessionUserToken) throws GoStarRestTemplateException {

        try {
            final String baseUrl = microServiceUrl + assayTabDataService;
            URI uri = new URI(baseUrl);

            AssayTabDataMicroService assayTabDataMicroService = allTabServicesUtility.prepareAssayTabDataMicroServiceObject(tabularviewRequest, assayIdList);
            HttpHeaders headers = allTabServicesUtility.prepareHttpHeaders(currentSessionUserToken);

            HttpEntity<AssayTabDataMicroService> request = new HttpEntity<>(assayTabDataMicroService, headers);

            ResponseEntity<String> responseEntity = restTemplate.postForEntity(uri, request, String.class);

            JSONObject assayJson = new JSONObject(responseEntity.getBody());

            LOGGER.info(" In getAssayData method RestTemplate Response JSON - {} ", assayJson.length());

            ObjectMapper mapper = new ObjectMapper();

            List<AssayTabDTO> assayTabDTOS = new ArrayList<>();
            if (Objects.nonNull(assayJson.getJSONArray(RESPONSE_ASSAY_TAB_DTO_LIST))) {
                assayTabDTOS = mapper.reader()
                        .forType(new TypeReference<Object>() {
                        })
                        .readValue(assayJson.getJSONArray(RESPONSE_ASSAY_TAB_DTO_LIST).toString());
            }

            Long count = 0L;
            if (Objects.nonNull(assayJson.get(RESPONSE_COUNT)))
                count = Long.parseLong(assayJson.get(RESPONSE_COUNT).toString());

            AssayTabPageData assayTabPageData = new AssayTabPageData();
            assayTabPageData.setAssayTabDTOList(assayTabDTOS);
            assayTabPageData.setCount(count);

            LOGGER.info(" AllTabServicesComponent :: getAssayData - Received Results count {} ", assayTabPageData.getAssayTabDTOList().size());

            return assayTabPageData;
        } catch (URISyntaxException ex) {
            ex.printStackTrace();
            LOGGER.error(" AllTabServicesComponent :: getAssayData - URISyntaxException {} ", ex.getMessage());
            AssayTabPageData assayTabPageData = new AssayTabPageData();
            assayTabPageData.setAssayTabDTOList(new ArrayList<>());
            assayTabPageData.setCount(0L);

            return assayTabPageData;
        } catch (Exception ex) {
            ex.printStackTrace();
            LOGGER.error(" AllTabServicesComponent :: getAssayFieldFilteredData - Exception {} ", ex.getMessage());
            AssayTabPageData assayTabPageData = new AssayTabPageData();
            assayTabPageData.setAssayTabDTOList(new ArrayList<>());
            assayTabPageData.setCount(0L);

            return assayTabPageData;
        }
    }

    /**
     * @param tabularviewFilterRequest
     * @param refIdList
     * @param currentSessionUserToken
     * @return
     */
    public List<ReferenceTabDTO> getReferenceFieldFilteredData(TabularviewFilterRequest tabularviewFilterRequest, List<Long> refIdList, String currentSessionUserToken) throws GoStarRestTemplateException {

        try {
            final String baseUrl = microServiceUrl + refTabFieldFilterService;
            URI uri = new URI(baseUrl);

            ReferenceTabFilterRequestForMicroService refTabFilterRequestForMicroService = allTabServicesUtility.prepareReferenceTabFilterRequestForMicroServiceObject(tabularviewFilterRequest, refIdList);
            HttpHeaders headers = allTabServicesUtility.prepareHttpHeaders(currentSessionUserToken);

            HttpEntity<ReferenceTabFilterRequestForMicroService> request = new HttpEntity<>(refTabFilterRequestForMicroService, headers);

            ResponseEntity<String> responseEntity = restTemplate.postForEntity(uri, request, String.class);
            JSONArray refJson = new JSONArray(responseEntity.getBody());

            LOGGER.info(" In getReferenceFieldFilteredData method RestTemplate Response JSON - {} ", refJson.length());

            ObjectMapper mapper = new ObjectMapper();
            List<ReferenceTabDTO> refTabDTOS = new ArrayList<>();
            if (Objects.nonNull(refJson)) {
                refTabDTOS = mapper.reader()
                        .forType(new TypeReference<Object>() {
                        })
                        .readValue(refJson.toString());
            }

            LOGGER.info(" AllTabServicesComponent :: getRefFieldFilteredData - Received Results count {} ", refTabDTOS.size());
            return refTabDTOS;

        } catch (URISyntaxException ex) {
            ex.printStackTrace();
            LOGGER.error(" AllTabServicesComponent :: getRefFieldFilteredData - URISyntaxException {} ", ex.getMessage());
            return new ArrayList<>();
        } catch (Exception ex) {
            ex.printStackTrace();
            LOGGER.error(" AllTabServicesComponent :: getRefFieldFilteredData - Exception {} ", ex.getMessage());
            return new ArrayList<>();
        }
    }

    /**
     * @param tabularviewRequest
     * @param refIdList
     * @param currentSessionUserToken
     * @return
     */
    public ReferenceTabPageData getReferenceData(TabularviewRequest tabularviewRequest, List<Long> refIdList, String currentSessionUserToken) throws GoStarRestTemplateException {

        try {
            final String baseUrl = microServiceUrl + refTabDataService;
            URI uri = new URI(baseUrl);

            ReferenceTabDataMicroService referenceTabDataMicroService = allTabServicesUtility.prepareReferenceTabDataMicroServiceObject(tabularviewRequest, refIdList);
            HttpHeaders headers = allTabServicesUtility.prepareHttpHeaders(currentSessionUserToken);

            HttpEntity<ReferenceTabDataMicroService> request = new HttpEntity<>(referenceTabDataMicroService, headers);

            ResponseEntity<String> responseEntity = restTemplate.postForEntity(uri, request, String.class);

            JSONObject refJson = new JSONObject(responseEntity.getBody());

            LOGGER.info(" In getReferenceData method RestTemplate Response JSON - {} ", refJson.length());

            ObjectMapper mapper = new ObjectMapper();

            List<ReferenceTabDTO> refTabDTOS = new ArrayList<>();
            if (Objects.nonNull(refJson.getJSONArray(RESPONSE_REFERENCE_TAB_DTO_LIST))) {
                refTabDTOS = mapper.reader()
                        .forType(new TypeReference<Object>() {
                        })
                        .readValue(refJson.getJSONArray(RESPONSE_REFERENCE_TAB_DTO_LIST).toString());
            }

            Long count = 0L;
            if (Objects.nonNull(refJson.get(RESPONSE_COUNT)))
                count = Long.parseLong(refJson.get(RESPONSE_COUNT).toString());

            ReferenceTabPageData refTabPageData = new ReferenceTabPageData();
            refTabPageData.setReferenceTabDTOList(refTabDTOS);
            refTabPageData.setCount(count);

            LOGGER.info(" AllTabServicesComponent :: getReferenceData - Received Results count {} ", refTabPageData.getReferenceTabDTOList().size());

            return refTabPageData;
        } catch (URISyntaxException ex) {
            ex.printStackTrace();
            LOGGER.error(" AllTabServicesComponent :: getReferenceData - URISyntaxException {} ", ex.getMessage());
            ReferenceTabPageData referenceTabPageData = new ReferenceTabPageData();
            referenceTabPageData.setReferenceTabDTOList(new ArrayList<>());
            referenceTabPageData.setCount(0L);

            return referenceTabPageData;
        } catch (Exception ex) {
            ex.printStackTrace();
            LOGGER.error(" AllTabServicesComponent :: getReferenceFieldFilteredData - Exception {} ", ex.getMessage());
            ReferenceTabPageData referenceTabPageData = new ReferenceTabPageData();
            referenceTabPageData.setReferenceTabDTOList(new ArrayList<>());
            referenceTabPageData.setCount(0L);

            return referenceTabPageData;
        }
    }

    /**
     *
     * @param tabularviewFilterRequest
     * @param stdnameIdList
     * @param currentSessionUserToken
     * @return
     * @throws GoStarRestTemplateException
     */
    public List<ProteinClassificationTabDTO> getProteinClassificationFieldFilteredData(TabularviewFilterRequest tabularviewFilterRequest, List<Long> stdnameIdList, String currentSessionUserToken) throws GoStarRestTemplateException {

        try {
            final String baseUrl = microServiceUrl + proteinClassificationTabFieldFilterService;
            URI uri = new URI(baseUrl);

            ProteinClassificationTabFilterRequestForMicroService proteinClassificationTabFilterRequestForMicroService = allTabServicesUtility.prepareProteinClassificationTabFilterRequestForMicroServiceObject(tabularviewFilterRequest, stdnameIdList);
            HttpHeaders headers = allTabServicesUtility.prepareHttpHeaders(currentSessionUserToken);

            HttpEntity<ProteinClassificationTabFilterRequestForMicroService> request = new HttpEntity<>(proteinClassificationTabFilterRequestForMicroService, headers);

            ResponseEntity<String> responseEntity = restTemplate.postForEntity(uri, request, String.class);
            JSONArray proteinClassificationJson = new JSONArray(responseEntity.getBody());

            LOGGER.info(" In getProteinClassificationFieldFilteredData method RestTemplate Response JSON - {} ", proteinClassificationJson.length());

            ObjectMapper mapper = new ObjectMapper();
            List<ProteinClassificationTabDTO> proteinClassificationTabDTOS = new ArrayList<>();
            if (Objects.nonNull(proteinClassificationJson)) {
                proteinClassificationTabDTOS = mapper.reader()
                        .forType(new TypeReference<Object>() {
                        })
                        .readValue(proteinClassificationJson.toString());
            }

            LOGGER.info(" AllTabServicesComponent :: getProteinClassificationFieldFilteredData - Received Results count {} ", proteinClassificationTabDTOS.size());
            return proteinClassificationTabDTOS;

        } catch (URISyntaxException ex) {
            ex.printStackTrace();
            LOGGER.error(" AllTabServicesComponent :: getProteinClassificationFieldFilteredData - URISyntaxException {} ", ex.getMessage());
            return new ArrayList<>();
        } catch (Exception ex) {
            ex.printStackTrace();
            LOGGER.error(" AllTabServicesComponent :: getProteinClassificationFieldFilteredData - Exception {} ", ex.getMessage());
            return new ArrayList<>();
        }
    }

    /**
     *
     * @param tabularviewRequest
     * @param stdnameIdList
     * @param currentSessionUserToken
     * @return
     * @throws GoStarRestTemplateException
     */
    public ProteinClassificationTabPageData getProteinClassificationData(TabularviewRequest tabularviewRequest, List<Long> stdnameIdList, String currentSessionUserToken) throws GoStarRestTemplateException {

        try {
            final String baseUrl = microServiceUrl + proteinClassificationTabDataService;
            URI uri = new URI(baseUrl);

            ProteinClassificationTabDataMicroService proteinClassificationTabDataMicroService = allTabServicesUtility.prepareProteinClassificationTabDataMicroServiceObject(tabularviewRequest, stdnameIdList);
            HttpHeaders headers = allTabServicesUtility.prepareHttpHeaders(currentSessionUserToken);

            HttpEntity<ProteinClassificationTabDataMicroService> request = new HttpEntity<>(proteinClassificationTabDataMicroService, headers);

            ResponseEntity<String> responseEntity = restTemplate.postForEntity(uri, request, String.class);

            JSONObject proteinClassificationJson = new JSONObject(responseEntity.getBody());

            LOGGER.info(" In getProteinClassificationData method RestTemplate Response JSON - {} ", proteinClassificationJson.length());

            ObjectMapper mapper = new ObjectMapper();

            List<ProteinClassificationTabDTO> proteinClassificationTabDTOS = new ArrayList<>();
            if (Objects.nonNull(proteinClassificationJson.getJSONArray(RESPONSE_PROTEINCLASSIFICATION_TAB_DTO_LIST))) {
                proteinClassificationTabDTOS = mapper.reader()
                        .forType(new TypeReference<Object>() {
                        })
                        .readValue(proteinClassificationJson.getJSONArray(RESPONSE_PROTEINCLASSIFICATION_TAB_DTO_LIST).toString());
            }

            Long count = 0L;
            if (Objects.nonNull(proteinClassificationJson.get(RESPONSE_COUNT)))
                count = Long.parseLong(proteinClassificationJson.get(RESPONSE_COUNT).toString());

            ProteinClassificationTabPageData proteinClassificationTabPageData = new ProteinClassificationTabPageData();
            proteinClassificationTabPageData.setProteinClassificationTabDTOList(proteinClassificationTabDTOS);
            proteinClassificationTabPageData.setCount(count);

            LOGGER.info(" AllTabServicesComponent :: getProteinClassificationData - Received Results count {} ", proteinClassificationTabPageData.getProteinClassificationTabDTOList().size());

            return proteinClassificationTabPageData;
        } catch (URISyntaxException ex) {
            ex.printStackTrace();
            LOGGER.error(" AllTabServicesComponent :: getProteinClassificationData - URISyntaxException {} ", ex.getMessage());
            ProteinClassificationTabPageData proteinClassificationTabPageData = new ProteinClassificationTabPageData();
            proteinClassificationTabPageData.setProteinClassificationTabDTOList(new ArrayList<>());
            proteinClassificationTabPageData.setCount(0L);

            return proteinClassificationTabPageData;
        } catch (Exception ex) {
            ex.printStackTrace();
            LOGGER.error(" AllTabServicesComponent :: getProteinClassificationFieldFilteredData - Exception {} ", ex.getMessage());
            ProteinClassificationTabPageData proteinClassificationTabPageData = new ProteinClassificationTabPageData();
            proteinClassificationTabPageData.setProteinClassificationTabDTOList(new ArrayList<>());
            proteinClassificationTabPageData.setCount(0L);

            return proteinClassificationTabPageData;
        }
    }

    /**
     *
     * @param tabularviewFilterRequest
     * @param targetIdList
     * @param currentSessionUserToken
     * @return
     * @throws GoStarRestTemplateException
     */
    public List<TargetDetailsTabDTO> getTargetDetailsFieldFilteredData(TabularviewFilterRequest tabularviewFilterRequest, List<Long> targetIdList, String currentSessionUserToken) throws GoStarRestTemplateException {

        try {
            final String baseUrl = microServiceUrl + targetDetailsTabFieldFilterService;
            URI uri = new URI(baseUrl);

            TargetDetailsTabFilterRequestForMicroService targetDetailsTabFilterRequestForMicroService = allTabServicesUtility.prepareTargetDetailsTabFilterRequestForMicroServiceObject(tabularviewFilterRequest, targetIdList);
            HttpHeaders headers = allTabServicesUtility.prepareHttpHeaders(currentSessionUserToken);

            HttpEntity<TargetDetailsTabFilterRequestForMicroService> request = new HttpEntity<>(targetDetailsTabFilterRequestForMicroService, headers);

            ResponseEntity<String> responseEntity = restTemplate.postForEntity(uri, request, String.class);
            JSONArray targetDetailsJson = new JSONArray(responseEntity.getBody());

            LOGGER.info(" In getTargetDetailsFieldFilteredData method RestTemplate Response JSON - {} ", targetDetailsJson.length());

            ObjectMapper mapper = new ObjectMapper();
            List<TargetDetailsTabDTO> targetDetailsTabDTOS = new ArrayList<>();
            if (Objects.nonNull(targetDetailsJson)) {
                targetDetailsTabDTOS = mapper.reader()
                        .forType(new TypeReference<Object>() {
                        })
                        .readValue(targetDetailsJson.toString());
            }

            LOGGER.info(" AllTabServicesComponent :: getTargetDetailsFieldFilteredData - Received Results count {} ", targetDetailsTabDTOS.size());
            return targetDetailsTabDTOS;

        } catch (URISyntaxException ex) {
            ex.printStackTrace();
            LOGGER.error(" AllTabServicesComponent :: getTargetDetailsFieldFilteredData - URISyntaxException {} ", ex.getMessage());
            return new ArrayList<>();
        } catch (Exception ex) {
            ex.printStackTrace();
            LOGGER.error(" AllTabServicesComponent :: getTargetDetailsFieldFilteredData - Exception {} ", ex.getMessage());
            return new ArrayList<>();
        }
    }

    /**
     *
     * @param tabularviewRequest
     * @param targetIdList
     * @param currentSessionUserToken
     * @return
     * @throws GoStarRestTemplateException
     */
    public TargetDetailsTabPageData getTargetDetailsData(TabularviewRequest tabularviewRequest, List<Long> targetIdList, String currentSessionUserToken) throws GoStarRestTemplateException {

        try {
            final String baseUrl = microServiceUrl + targetDetailsTabDataService;
            URI uri = new URI(baseUrl);

            TargetDetailsTabDataMicroService targetDetailsTabDataMicroService = allTabServicesUtility.prepareTargetDetailsTabDataMicroServiceObject(tabularviewRequest, targetIdList);
            HttpHeaders headers = allTabServicesUtility.prepareHttpHeaders(currentSessionUserToken);

            HttpEntity<TargetDetailsTabDataMicroService> request = new HttpEntity<>(targetDetailsTabDataMicroService, headers);

            ResponseEntity<String> responseEntity = restTemplate.postForEntity(uri, request, String.class);

            JSONObject targetDetailsJson = new JSONObject(responseEntity.getBody());

            LOGGER.info(" In getTargetDetailsData method RestTemplate Response JSON - {} ", targetDetailsJson.length());

            ObjectMapper mapper = new ObjectMapper();

            List<TargetDetailsTabDTO> targetDetailsTabDTOS = new ArrayList<>();
            if (Objects.nonNull(targetDetailsJson.getJSONArray(RESPONSE_TARGETDETAILS_TAB_DTO_LIST))) {
                targetDetailsTabDTOS = mapper.reader()
                        .forType(new TypeReference<Object>() {
                        })
                        .readValue(targetDetailsJson.getJSONArray(RESPONSE_TARGETDETAILS_TAB_DTO_LIST).toString());
            }

            Long count = 0L;
            if (Objects.nonNull(targetDetailsJson.get(RESPONSE_COUNT)))
                count = Long.parseLong(targetDetailsJson.get(RESPONSE_COUNT).toString());

            TargetDetailsTabPageData targetDetailsTabPageData = new TargetDetailsTabPageData();
            targetDetailsTabPageData.setTargetDetailsTabDTOList(targetDetailsTabDTOS);
            targetDetailsTabPageData.setCount(count);

            LOGGER.info(" AllTabServicesComponent :: getTargetDetailsData - Received Results count {} ", targetDetailsTabPageData.getTargetDetailsTabDTOList().size());

            return targetDetailsTabPageData;
        } catch (URISyntaxException ex) {
            ex.printStackTrace();
            LOGGER.error(" AllTabServicesComponent :: getTargetDetailsData - URISyntaxException {} ", ex.getMessage());
            TargetDetailsTabPageData targetDetailsTabPageData = new TargetDetailsTabPageData();
            targetDetailsTabPageData.setTargetDetailsTabDTOList(new ArrayList<>());
            targetDetailsTabPageData.setCount(0L);

            return targetDetailsTabPageData;
        } catch (Exception ex) {
            ex.printStackTrace();
            LOGGER.error(" AllTabServicesComponent :: getTargetDetailsFieldFilteredData - Exception {} ", ex.getMessage());
            TargetDetailsTabPageData targetDetailsTabPageData = new TargetDetailsTabPageData();
            targetDetailsTabPageData.setTargetDetailsTabDTOList(new ArrayList<>());
            targetDetailsTabPageData.setCount(0L);

            return targetDetailsTabPageData;
        }
    }

    /**
     *
     * @param tabularviewFilterRequest
     * @param gvkIdList
     * @param currentSessionUserToken
     * @return
     * @throws GoStarRestTemplateException
     */
    public List<IndicationTabDTO> getIndicationFieldFilteredData(TabularviewFilterRequest tabularviewFilterRequest, List<Long> gvkIdList, String currentSessionUserToken) throws GoStarRestTemplateException {

        try {
            final String baseUrl = microServiceUrl + indicationTabFieldFilterService;
            URI uri = new URI(baseUrl);

            IndicationTabFilterRequestForMicroService indicationTabFilterRequestForMicroService = allTabServicesUtility.prepareIndicationTabFilterRequestForMicroServiceObject(tabularviewFilterRequest, gvkIdList);
            HttpHeaders headers = allTabServicesUtility.prepareHttpHeaders(currentSessionUserToken);

            HttpEntity<IndicationTabFilterRequestForMicroService> request = new HttpEntity<>(indicationTabFilterRequestForMicroService, headers);

            ResponseEntity<String> responseEntity = restTemplate.postForEntity(uri, request, String.class);
            JSONArray indicationJson = new JSONArray(responseEntity.getBody());

            LOGGER.info(" In getIndicationFieldFilteredData method RestTemplate Response JSON - {} ", indicationJson.length());

            ObjectMapper mapper = new ObjectMapper();
            List<IndicationTabDTO> indicationTabDTOS = new ArrayList<>();
            if (Objects.nonNull(indicationJson)) {
                indicationTabDTOS = mapper.reader()
                        .forType(new TypeReference<Object>() {
                        })
                        .readValue(indicationJson.toString());
            }

            LOGGER.info(" AllTabServicesComponent :: getIndicationFieldFilteredData - Received Results count {} ", indicationTabDTOS.size());
            return indicationTabDTOS;

        } catch (URISyntaxException ex) {
            ex.printStackTrace();
            LOGGER.error(" AllTabServicesComponent :: getIndicationFieldFilteredData - URISyntaxException {} ", ex.getMessage());
            return new ArrayList<>();
        } catch (Exception ex) {
            ex.printStackTrace();
            LOGGER.error(" AllTabServicesComponent :: getIndicationFieldFilteredData - Exception {} ", ex.getMessage());
            return new ArrayList<>();
        }
    }

    /**
     *
     * @param tabularviewRequest
     * @param gvkIdList
     * @param currentSessionUserToken
     * @return
     * @throws GoStarRestTemplateException
     */
    public IndicationTabPageData getIndicationData(TabularviewRequest tabularviewRequest, List<Long> gvkIdList, String currentSessionUserToken) throws GoStarRestTemplateException {

        try {
            final String baseUrl = microServiceUrl + indicationTabDataService;
            URI uri = new URI(baseUrl);

            IndicationTabDataMicroService indicationTabDataMicroService = allTabServicesUtility.prepareIndicationTabDataMicroServiceObject(tabularviewRequest, gvkIdList);
            HttpHeaders headers = allTabServicesUtility.prepareHttpHeaders(currentSessionUserToken);

            HttpEntity<IndicationTabDataMicroService> request = new HttpEntity<>(indicationTabDataMicroService, headers);

            ResponseEntity<String> responseEntity = restTemplate.postForEntity(uri, request, String.class);

            JSONObject indicationJson = new JSONObject(responseEntity.getBody());

            LOGGER.info(" In getIndicationData method RestTemplate Response JSON - {} ", indicationJson.length());

            ObjectMapper mapper = new ObjectMapper();

            List<IndicationTabDTO> indicationTabDTOS = new ArrayList<>();
            if (Objects.nonNull(indicationJson.getJSONArray(RESPONSE_INDICATION_TAB_DTO_LIST))) {
                indicationTabDTOS = mapper.reader()
                        .forType(new TypeReference<Object>() {
                        })
                        .readValue(indicationJson.getJSONArray(RESPONSE_INDICATION_TAB_DTO_LIST).toString());
            }

            Long count = 0L;
            if (Objects.nonNull(indicationJson.get(RESPONSE_COUNT)))
                count = Long.parseLong(indicationJson.get(RESPONSE_COUNT).toString());

            IndicationTabPageData indicationTabPageData = new IndicationTabPageData();
            indicationTabPageData.setIndicationTabDTOList(indicationTabDTOS);
            indicationTabPageData.setCount(count);

            LOGGER.info(" AllTabServicesComponent :: getIndicationData - Received Results count {} ", indicationTabPageData.getIndicationTabDTOList().size());

            return indicationTabPageData;
        } catch (URISyntaxException ex) {
            ex.printStackTrace();
            LOGGER.error(" AllTabServicesComponent :: getTargetDetailsData - URISyntaxException {} ", ex.getMessage());
            IndicationTabPageData indicationTabPageData = new IndicationTabPageData();
            indicationTabPageData.setIndicationTabDTOList(new ArrayList<>());
            indicationTabPageData.setCount(0L);

            return indicationTabPageData;
        } catch (Exception ex) {
            ex.printStackTrace();
            LOGGER.error(" AllTabServicesComponent :: getIndicationFieldFilteredData - Exception {} ", ex.getMessage());
            IndicationTabPageData indicationTabPageData = new IndicationTabPageData();
            indicationTabPageData.setIndicationTabDTOList(new ArrayList<>());
            indicationTabPageData.setCount(0L);

            return indicationTabPageData;
        }
    }


    /**
     *
     * @param tabularviewFilterRequest
     * @param gvkIdList
     * @param refIdList
     * @param currentSessionUserToken
     * @return
     * @throws GoStarRestTemplateException
     */
    public List<ClinicalStatusTabDTO> getClinicalStatusFieldFilteredData(TabularviewFilterRequest tabularviewFilterRequest, List<Long> gvkIdList, List<Long> refIdList, String currentSessionUserToken) throws GoStarRestTemplateException {

        try {
            final String baseUrl = microServiceUrl + clinicalStatusTabFieldFilterService;
            URI uri = new URI(baseUrl);

            ClinicalStatusTabFilterRequestForMicroService clinicalStatusTabFilterRequestForMicroService = allTabServicesUtility.prepareClinicalStatusTabFilterRequestForMicroServiceObject(tabularviewFilterRequest, gvkIdList, refIdList);
            HttpHeaders headers = allTabServicesUtility.prepareHttpHeaders(currentSessionUserToken);

            HttpEntity<ClinicalStatusTabFilterRequestForMicroService> request = new HttpEntity<>(clinicalStatusTabFilterRequestForMicroService, headers);

            ResponseEntity<String> responseEntity = restTemplate.postForEntity(uri, request, String.class);
            JSONArray clinicalStatusJson = new JSONArray(responseEntity.getBody());

            LOGGER.info(" In getClinicalStatusFieldFilteredData method RestTemplate Response JSON - {} ", clinicalStatusJson.length());

            ObjectMapper mapper = new ObjectMapper();
            List<ClinicalStatusTabDTO> clinicalStatusTabDTOS = new ArrayList<>();
            if (Objects.nonNull(clinicalStatusJson)) {
                clinicalStatusTabDTOS = mapper.reader()
                        .forType(new TypeReference<Object>() {
                        })
                        .readValue(clinicalStatusJson.toString());
            }

            LOGGER.info(" AllTabServicesComponent :: getClinicalStatusFieldFilteredData - Received Results count {} ", clinicalStatusTabDTOS.size());
            return clinicalStatusTabDTOS;

        } catch (URISyntaxException ex) {
            ex.printStackTrace();
            LOGGER.error(" AllTabServicesComponent :: getClinicalStatusFieldFilteredData - URISyntaxException {} ", ex.getMessage());
            return new ArrayList<>();
        } catch (Exception ex) {
            ex.printStackTrace();
            LOGGER.error(" AllTabServicesComponent :: getClinicalStatusFieldFilteredData - Exception {} ", ex.getMessage());
            return new ArrayList<>();
        }
    }

    /**
     *      *
     * @param tabularviewRequest
     * @param gvkIdList
     * @param refIdList
     * @param currentSessionUserToken
     * @return
     * @throws GoStarRestTemplateException
     */
    public ClinicalStatusTabPageData getClinicalStatusData(TabularviewRequest tabularviewRequest, List<Long> gvkIdList, List<Long> refIdList, String currentSessionUserToken) throws GoStarRestTemplateException {

        try {
            final String baseUrl = microServiceUrl + clinicalStatusTabDataService;
            URI uri = new URI(baseUrl);

            ClinicalStatusTabDataMicroService clinicalStatusTabDataMicroService = allTabServicesUtility.prepareClinicalStatusTabDataMicroServiceObject(tabularviewRequest, gvkIdList, refIdList);
            HttpHeaders headers = allTabServicesUtility.prepareHttpHeaders(currentSessionUserToken);

            HttpEntity<ClinicalStatusTabDataMicroService> request = new HttpEntity<>(clinicalStatusTabDataMicroService, headers);

            ResponseEntity<String> responseEntity = restTemplate.postForEntity(uri, request, String.class);

            JSONObject clinicalStatusJson = new JSONObject(responseEntity.getBody());

            LOGGER.info(" In getClinicalStatusData method RestTemplate Response JSON - {} ", clinicalStatusJson.length());

            ObjectMapper mapper = new ObjectMapper();

            List<ClinicalStatusTabDTO> clinicalStatusTabDTOS = new ArrayList<>();
            if (Objects.nonNull(clinicalStatusJson.getJSONArray(RESPONSE_CLINICALSTATUS_TAB_DTO_LIST))) {
                clinicalStatusTabDTOS = mapper.reader()
                        .forType(new TypeReference<Object>() {
                        })
                        .readValue(clinicalStatusJson.getJSONArray(RESPONSE_CLINICALSTATUS_TAB_DTO_LIST).toString());
            }

            Long count = 0L;
            if (Objects.nonNull(clinicalStatusJson.get(RESPONSE_COUNT)))
                count = Long.parseLong(clinicalStatusJson.get(RESPONSE_COUNT).toString());

            ClinicalStatusTabPageData clinicalStatusTabPageData = new ClinicalStatusTabPageData();
            clinicalStatusTabPageData.setClinicalStatusTabDTOList(clinicalStatusTabDTOS);
            clinicalStatusTabPageData.setCount(count);

            LOGGER.info(" AllTabServicesComponent :: getClinicalStatusData - Received Results count {} ", clinicalStatusTabPageData.getClinicalStatusTabDTOList().size());

            return clinicalStatusTabPageData;
        } catch (URISyntaxException ex) {
            ex.printStackTrace();
            LOGGER.error(" AllTabServicesComponent :: getClinicalStatusData - URISyntaxException {} ", ex.getMessage());
            ClinicalStatusTabPageData clinicalStatusTabPageData = new ClinicalStatusTabPageData();
            clinicalStatusTabPageData.setClinicalStatusTabDTOList(new ArrayList<>());
            clinicalStatusTabPageData.setCount(0L);

            return clinicalStatusTabPageData;
        } catch (Exception ex) {
            ex.printStackTrace();
            LOGGER.error(" AllTabServicesComponent :: getClinicalStatusFieldFilteredData - Exception {} ", ex.getMessage());
            ClinicalStatusTabPageData clinicalStatusTabPageData = new ClinicalStatusTabPageData();
            clinicalStatusTabPageData.setClinicalStatusTabDTOList(new ArrayList<>());
            clinicalStatusTabPageData.setCount(0L);

            return clinicalStatusTabPageData;
        }
    }

    /**
     * @param tabularviewFilterRequest
     * @param gvkIdList
     * @param currentSessionUserToken
     * @return
     */
    public List<StructureDetailsTabDTO> getStructureFieldFilteredData(TabularviewFilterRequest tabularviewFilterRequest, List<Long> gvkIdList, String currentSessionUserToken) throws GoStarRestTemplateException {

        try {
            final String baseUrl = microServiceUrl + strTabFieldFilterService;
            URI uri = new URI(baseUrl);

            StructureTabFilterRequestForMicroService strTabFilterRequestForMicroService = allTabServicesUtility.prepareStructureTabFilterRequestForMicroServiceObject(tabularviewFilterRequest, gvkIdList);
            HttpHeaders headers = allTabServicesUtility.prepareHttpHeaders(currentSessionUserToken);

            HttpEntity<StructureTabFilterRequestForMicroService> request = new HttpEntity<>(strTabFilterRequestForMicroService, headers);

            ResponseEntity<String> responseEntity = restTemplate.postForEntity(uri, request, String.class);
            JSONArray strJson = new JSONArray(responseEntity.getBody());

            LOGGER.info(" In getStructureFieldFilteredData method RestTemplate Response JSON - {} ", strJson.length());

            ObjectMapper mapper = new ObjectMapper();
            List<StructureDetailsTabDTO> strTabDTOS = new ArrayList<>();
            if (Objects.nonNull(strJson)) {
                strTabDTOS = mapper.reader()
                        .forType(new TypeReference<Object>() {
                        })
                        .readValue(strJson.toString());
            }

            LOGGER.info(" AllTabServicesComponent :: getStructureFieldFilteredData - Received Results count {} ", strTabDTOS.size());
            return strTabDTOS;

        } catch (URISyntaxException ex) {
            ex.printStackTrace();
            LOGGER.error(" AllTabServicesComponent :: getStructureFieldFilteredData - URISyntaxException {} ", ex.getMessage());
            return new ArrayList<>();
        } catch (Exception ex) {
            ex.printStackTrace();
            LOGGER.error(" AllTabServicesComponent :: getStructureFieldFilteredData - Exception {} ", ex.getMessage());
            return new ArrayList<>();
        }
    }

    /**
     * @param tabularviewRequest
     * @param gvkIdList
     * @param currentSessionUserToken
     * @return
     */
    public StructureDetailsTabPageData getStructureData(TabularviewRequest tabularviewRequest, List<Long> gvkIdList, String currentSessionUserToken) throws GoStarRestTemplateException {

        try {
            final String baseUrl = microServiceUrl + strTabDataService;
            URI uri = new URI(baseUrl);

            StructureTabDataMicroService structureTabDataMicroService = allTabServicesUtility.prepareStructureTabDataMicroServiceObject(tabularviewRequest, gvkIdList);
            HttpHeaders headers = allTabServicesUtility.prepareHttpHeaders(currentSessionUserToken);

            HttpEntity<StructureTabDataMicroService> request = new HttpEntity<>(structureTabDataMicroService, headers);

            ResponseEntity<String> responseEntity = restTemplate.postForEntity(uri, request, String.class);

            JSONObject strJson = new JSONObject(responseEntity.getBody());

            // System.out.println("json check" + strJson.getJSONArray("structureDetailsTabDTOList").getJSONObject(0).getString("subSmiles"));

            LOGGER.info(" In getStructureData method RestTemplate Response JSON - {} ", strJson.length());

            ObjectMapper mapper = new ObjectMapper();

            List<StructureDetailsTabDTO> strTabDTOS = new ArrayList<>();


            if (Objects.nonNull(strJson.getJSONArray(RESPONSE_STRUCTURE_DETAILS_TAB_DTO_LIST))) {
                strJson.getJSONArray("structureDetailsTabDTOList").getJSONObject(0).getString("subSmiles");
                System.out.println("inside for loop ");
                strTabDTOS = mapper.reader()
                        .forType(new TypeReference<Object>() {
                        })
                        .readValue(strJson.getJSONArray(RESPONSE_STRUCTURE_DETAILS_TAB_DTO_LIST).toString());

            }
            //for loop for to test the subsmiles

            if (Objects.nonNull(strJson.getJSONArray(RESPONSE_STRUCTURE_DETAILS_TAB_DTO_LIST).getJSONObject(0).get("subSmiles"))) {

                JSONObject obj = new JSONObject();
                System.out.println(strTabDTOS);

                strTabDTOS = mapper.reader()
                        .forType(new TypeReference<Object>() {
                        })
                        .readValue(strJson.getJSONArray(RESPONSE_STRUCTURE_DETAILS_TAB_DTO_LIST).toString());

               Object smilesTest =  strJson.getJSONArray(RESPONSE_STRUCTURE_DETAILS_TAB_DTO_LIST);

                Iterator itr = strTabDTOS.iterator();

                while (itr.hasNext()) {
                    smilesTest =  itr.next();

                    for(int i=0 ;  i < strTabDTOS.size(); i++) {
                        StructureDetailsTabDTO subsmilesImages = mapper.convertValue(strTabDTOS.get(i), StructureDetailsTabDTO.class);
                        chemaxon.util.MolHandler mh1 = new chemaxon.util.MolHandler(subsmilesImages.getSubSmiles());

                        mh1.aromatize();
                        chemaxon.struc.Molecule mol = mh1.getMolecule();
                        mol.clean(2, null);
                        mol.dearomatize();
                        byte[] s1 = mol.toBinFormat("png:w250,h250,b32");

                        FileOutputStream out =new  FileOutputStream(servletContext.getRealPath("") +"assets/images/smileimages/"+ subsmilesImages.getGvkId()+".png");

                        out.write(s1);
                        out.close();
                        out = null;
                    }

                }

            }

            Long count = 0L;
            if (Objects.nonNull(strJson.get(RESPONSE_COUNT)))
                count = Long.parseLong(strJson.get(RESPONSE_COUNT).toString());


            StructureDetailsTabPageData structureTabPageData = new StructureDetailsTabPageData();

            structureTabPageData.setStructureDetailsTabDTOList(strTabDTOS);

            structureTabPageData.setCount(count);

            LOGGER.info(" AllTabServicesComponent :: getStructureData - Received Results count {} ", structureTabPageData.getStructureDetailsTabDTOList().size());

            return structureTabPageData;
        } catch (URISyntaxException ex) {
            ex.printStackTrace();
            LOGGER.error(" AllTabServicesComponent :: getAssayData - URISyntaxException {} ", ex.getMessage());
            StructureDetailsTabPageData structureDetailsTabPageData = new StructureDetailsTabPageData();
            structureDetailsTabPageData.setStructureDetailsTabDTOList(new ArrayList<>());
            structureDetailsTabPageData.setCount(0L);

            return new StructureDetailsTabPageData();
        } catch (Exception ex) {
            ex.printStackTrace();
            LOGGER.error(" AllTabServicesComponent :: getAssayFieldFilteredData - Exception {} ", ex.getMessage());
            StructureDetailsTabPageData structureDetailsTabPageData = new StructureDetailsTabPageData();
            structureDetailsTabPageData.setStructureDetailsTabDTOList(new ArrayList<>());
            structureDetailsTabPageData.setCount(0L);

            return new StructureDetailsTabPageData();
        }
    }


    /**
     *
     * @param tabularviewFilterRequest
     * @param gvkIdList
     * @param currentSessionUserToken
     * @return
     * @throws GoStarRestTemplateException
     */
    public List<Framework2TabDTO> getFramework2FieldFilteredData(TabularviewFilterRequest tabularviewFilterRequest, List<Long> gvkIdList, String currentSessionUserToken) throws GoStarRestTemplateException {

        try {
            final String baseUrl = microServiceUrl + framework2TabFieldFilterService;
            URI uri = new URI(baseUrl);

            StructureTabFilterRequestForMicroService strTabFilterRequestForMicroService = allTabServicesUtility.prepareStructureTabFilterRequestForMicroServiceObject(tabularviewFilterRequest, gvkIdList);
            HttpHeaders headers = allTabServicesUtility.prepareHttpHeaders(currentSessionUserToken);

            HttpEntity<StructureTabFilterRequestForMicroService> request = new HttpEntity<>(strTabFilterRequestForMicroService, headers);

            ResponseEntity<String> responseEntity = restTemplate.postForEntity(uri, request, String.class);
            JSONArray strJson = new JSONArray(responseEntity.getBody());

            LOGGER.info(" In getFramework2FieldFilteredData method RestTemplate Response JSON - {} ", strJson.length());

            ObjectMapper mapper = new ObjectMapper();
            List<Framework2TabDTO> framework2TabDTOS = new ArrayList<>();
            if (Objects.nonNull(strJson)) {
                framework2TabDTOS = mapper.reader()
                        .forType(new TypeReference<Object>() {
                        })
                        .readValue(strJson.toString());
            }

            LOGGER.info(" AllTabServicesComponent :: getFramework2FieldFilteredData - Received Results count {} ", framework2TabDTOS.size());
            return framework2TabDTOS;

        } catch (URISyntaxException ex) {
            ex.printStackTrace();
            LOGGER.error(" AllTabServicesComponent :: getFramework2FieldFilteredData - URISyntaxException {} ", ex.getMessage());
            return new ArrayList<>();
        } catch (Exception ex) {
            ex.printStackTrace();
            LOGGER.error(" AllTabServicesComponent :: getFramework2FieldFilteredData - Exception {} ", ex.getMessage());
            return new ArrayList<>();
        }
    }


    /**
     *
     * @param tabularviewRequest
     * @param gvkIdList
     * @param currentSessionUserToken
     * @return
     * @throws GoStarRestTemplateException
     */
    public Framework2TabPageData getFramework2Data(TabularviewRequest tabularviewRequest, List<Long> gvkIdList, String currentSessionUserToken) throws GoStarRestTemplateException {

        try {
            final String baseUrl = microServiceUrl + framework2TabDataService;
            URI uri = new URI(baseUrl);

            StructureTabDataMicroService structureTabDataMicroService = allTabServicesUtility.prepareStructureTabDataMicroServiceObject(tabularviewRequest, gvkIdList);
            HttpHeaders headers = allTabServicesUtility.prepareHttpHeaders(currentSessionUserToken);

            HttpEntity<StructureTabDataMicroService> request = new HttpEntity<>(structureTabDataMicroService, headers);

            ResponseEntity<String> responseEntity = restTemplate.postForEntity(uri, request, String.class);

            JSONObject strJson = new JSONObject(responseEntity.getBody());

            LOGGER.info(" In getFramework2Data method RestTemplate Response JSON - {} ", strJson.length());

            ObjectMapper mapper = new ObjectMapper();

            List<Framework2TabDTO> framework2TabDTOS = new ArrayList<>();


            if (Objects.nonNull(strJson.getJSONArray(RESPONSE_FRAMEWORK2_TAB_DTO_LIST))) {
                strJson.getJSONArray("framework2TabDTOList").getJSONObject(0).getString("smiles");
                framework2TabDTOS = mapper.reader()
                        .forType(new TypeReference<Object>() {
                        })
                        .readValue(strJson.getJSONArray(RESPONSE_FRAMEWORK2_TAB_DTO_LIST).toString());

            }

            //for loop for to test the Smiles
            if (Objects.nonNull(strJson.getJSONArray(RESPONSE_FRAMEWORK2_TAB_DTO_LIST).getJSONObject(0).get("smiles"))) {

                JSONObject obj = new JSONObject();

                framework2TabDTOS = mapper.reader()
                        .forType(new TypeReference<Object>() {
                        })
                        .readValue(strJson.getJSONArray(RESPONSE_FRAMEWORK2_TAB_DTO_LIST).toString());

                Object smilesTest =  strJson.getJSONArray(RESPONSE_FRAMEWORK2_TAB_DTO_LIST);

                Iterator itr = framework2TabDTOS.iterator();

                while (itr.hasNext()) {
                    smilesTest =  itr.next();

                    for(int i=0 ;  i < framework2TabDTOS.size(); i++) {
                        Framework2TabDTO smileImages = mapper.convertValue(framework2TabDTOS.get(i), Framework2TabDTO.class);
                        chemaxon.util.MolHandler mh1 = new chemaxon.util.MolHandler(smileImages.getSmiles());

                        mh1.aromatize();
                        chemaxon.struc.Molecule mol = mh1.getMolecule();
                        mol.clean(2, null);
                        mol.dearomatize();
                        byte[] s1 = mol.toBinFormat("png:w250,h250,b32");

                        FileOutputStream out =new  FileOutputStream(servletContext.getRealPath("") +"assets/images/smileimages/"+ smileImages.getFramework2Id()+".png");

                        out.write(s1);
                        out.close();
                        out = null;

                    }

                }

            }

            Long count = 0L;
            if (Objects.nonNull(strJson.get(RESPONSE_COUNT)))
                count = Long.parseLong(strJson.get(RESPONSE_COUNT).toString());


            Framework2TabPageData framework2TabPageData = new Framework2TabPageData();

            framework2TabPageData.setFramework2TabDTOList(framework2TabDTOS);

            framework2TabPageData.setCount(count);

            LOGGER.info(" AllTabServicesComponent :: getFramework2Data - Received Results count {} ", framework2TabPageData.getFramework2TabDTOList().size());

            return framework2TabPageData;
        } catch (URISyntaxException ex) {
            ex.printStackTrace();
            LOGGER.error(" AllTabServicesComponent :: getFramework2Data - URISyntaxException {} ", ex.getMessage());
            Framework2TabPageData framework2TabPageData = new Framework2TabPageData();
            framework2TabPageData.setFramework2TabDTOList(new ArrayList<>());
            framework2TabPageData.setCount(0L);

            return framework2TabPageData;
        } catch (Exception ex) {
            ex.printStackTrace();
            LOGGER.error(" AllTabServicesComponent :: getFramework2Data - Exception {} ", ex.getMessage());
            Framework2TabPageData framework2TabPageData = new Framework2TabPageData();
            framework2TabPageData.setFramework2TabDTOList(new ArrayList<>());
            framework2TabPageData.setCount(0L);

            return framework2TabPageData;
        }
    }


    /**
     *
     * @param tabularviewFilterRequest
     * @param gvkIdList
     * @param currentSessionUserToken
     * @return
     * @throws GoStarRestTemplateException
     */
    public List<Scaffold2TabDTO> getScaffold2FieldFilteredData(TabularviewFilterRequest tabularviewFilterRequest, List<Long> gvkIdList, String currentSessionUserToken) throws GoStarRestTemplateException {

        try {
            final String baseUrl = microServiceUrl + scaffold2TabFieldFilterService;
            URI uri = new URI(baseUrl);

            StructureTabFilterRequestForMicroService strTabFilterRequestForMicroService = allTabServicesUtility.prepareStructureTabFilterRequestForMicroServiceObject(tabularviewFilterRequest, gvkIdList);
            HttpHeaders headers = allTabServicesUtility.prepareHttpHeaders(currentSessionUserToken);

            HttpEntity<StructureTabFilterRequestForMicroService> request = new HttpEntity<>(strTabFilterRequestForMicroService, headers);

            ResponseEntity<String> responseEntity = restTemplate.postForEntity(uri, request, String.class);
            JSONArray strJson = new JSONArray(responseEntity.getBody());

            LOGGER.info(" In getScaffold2FieldFilteredData method RestTemplate Response JSON - {} ", strJson.length());

            ObjectMapper mapper = new ObjectMapper();
            List<Scaffold2TabDTO> scaffold2TabDTOS = new ArrayList<>();
            if (Objects.nonNull(strJson)) {
                scaffold2TabDTOS = mapper.reader()
                        .forType(new TypeReference<Object>() {
                        })
                        .readValue(strJson.toString());
            }

            LOGGER.info(" AllTabServicesComponent :: getScaffold2FieldFilteredData - Received Results count {} ", scaffold2TabDTOS.size());
            return scaffold2TabDTOS;

        } catch (URISyntaxException ex) {
            ex.printStackTrace();
            LOGGER.error(" AllTabServicesComponent :: getScaffold2FieldFilteredData - URISyntaxException {} ", ex.getMessage());
            return new ArrayList<>();
        } catch (Exception ex) {
            ex.printStackTrace();
            LOGGER.error(" AllTabServicesComponent :: getScaffold2FieldFilteredData - Exception {} ", ex.getMessage());
            return new ArrayList<>();
        }
    }


    /**
     *
     * @param tabularviewRequest
     * @param gvkIdList
     * @param currentSessionUserToken
     * @return
     * @throws GoStarRestTemplateException
     */
    public Scaffold2TabPageData getScaffold2Data(TabularviewRequest tabularviewRequest, List<Long> gvkIdList, String currentSessionUserToken) throws GoStarRestTemplateException {

        try {
            final String baseUrl = microServiceUrl + scaffold2TabDataService;
            URI uri = new URI(baseUrl);

            StructureTabDataMicroService structureTabDataMicroService = allTabServicesUtility.prepareStructureTabDataMicroServiceObject(tabularviewRequest, gvkIdList);
            HttpHeaders headers = allTabServicesUtility.prepareHttpHeaders(currentSessionUserToken);

            HttpEntity<StructureTabDataMicroService> request = new HttpEntity<>(structureTabDataMicroService, headers);

            ResponseEntity<String> responseEntity = restTemplate.postForEntity(uri, request, String.class);

            JSONObject strJson = new JSONObject(responseEntity.getBody());

            LOGGER.info(" In getScaffold2Data method RestTemplate Response JSON - {} ", strJson.length());

            ObjectMapper mapper = new ObjectMapper();

            List<Scaffold2TabDTO> scaffold2TabDTOS = new ArrayList<>();


            if (Objects.nonNull(strJson.getJSONArray(RESPONSE_SCAFFOLD2_TAB_DTO_LIST))) {
                strJson.getJSONArray("scaffold2TabDTOList").getJSONObject(0).getString("smiles");
                scaffold2TabDTOS = mapper.reader()
                        .forType(new TypeReference<Object>() {
                        })
                        .readValue(strJson.getJSONArray(RESPONSE_SCAFFOLD2_TAB_DTO_LIST).toString());

            }

            //for loop for to test the Smiles
            if (Objects.nonNull(strJson.getJSONArray(RESPONSE_SCAFFOLD2_TAB_DTO_LIST).getJSONObject(0).get("smiles"))) {

                JSONObject obj = new JSONObject();

                scaffold2TabDTOS = mapper.reader()
                        .forType(new TypeReference<Object>() {
                        })
                        .readValue(strJson.getJSONArray(RESPONSE_SCAFFOLD2_TAB_DTO_LIST).toString());

                Object smilesTest =  strJson.getJSONArray(RESPONSE_SCAFFOLD2_TAB_DTO_LIST);

                Iterator itr = scaffold2TabDTOS.iterator();

                while (itr.hasNext()) {
                    smilesTest =  itr.next();

                    for(int i=0 ;  i < scaffold2TabDTOS.size(); i++) {
                        Scaffold2TabDTO smileImages = mapper.convertValue(scaffold2TabDTOS.get(i), Scaffold2TabDTO.class);
                        chemaxon.util.MolHandler mh1 = new chemaxon.util.MolHandler(smileImages.getSmiles());

                        mh1.aromatize();
                        chemaxon.struc.Molecule mol = mh1.getMolecule();
                        mol.clean(2, null);
                        mol.dearomatize();
                        byte[] s1 = mol.toBinFormat("png:w250,h250,b32");

                        FileOutputStream out =new  FileOutputStream(servletContext.getRealPath("") +"assets/images/smileimages/"+ smileImages.getScaffold2Id()+".png");

                        out.write(s1);
                        out.close();
                        out = null;

                    }

                }

            }

            Long count = 0L;
            if (Objects.nonNull(strJson.get(RESPONSE_COUNT)))
                count = Long.parseLong(strJson.get(RESPONSE_COUNT).toString());


            Scaffold2TabPageData scaffold2TabPageData = new Scaffold2TabPageData();

            scaffold2TabPageData.setScaffold2TabDTOList(scaffold2TabDTOS);

            scaffold2TabPageData.setCount(count);

            LOGGER.info(" AllTabServicesComponent :: getScaffold2Data - Received Results count {} ", scaffold2TabPageData.getScaffold2TabDTOList().size());

            return scaffold2TabPageData;
        } catch (URISyntaxException ex) {
            ex.printStackTrace();
            LOGGER.error(" AllTabServicesComponent :: getScaffold2Data - URISyntaxException {} ", ex.getMessage());
            Scaffold2TabPageData scaffold2TabPageData = new Scaffold2TabPageData();
            scaffold2TabPageData.setScaffold2TabDTOList(new ArrayList<>());
            scaffold2TabPageData.setCount(0L);

            return scaffold2TabPageData;
        } catch (Exception ex) {
            ex.printStackTrace();
            LOGGER.error(" AllTabServicesComponent :: getScaffold2Data - Exception {} ", ex.getMessage());
            Scaffold2TabPageData scaffold2TabPageData = new Scaffold2TabPageData();
            scaffold2TabPageData.setScaffold2TabDTOList(new ArrayList<>());
            scaffold2TabPageData.setCount(0L);

            return scaffold2TabPageData;
        }
    }


    /**
     *
     * @param tabularviewFilterRequest
     * @param gvkIdList
     * @param currentSessionUserToken
     * @return
     * @throws GoStarRestTemplateException
     */
    public List<SkeletonTabDTO> getSkeletonFieldFilteredData(TabularviewFilterRequest tabularviewFilterRequest, List<Long> gvkIdList, String currentSessionUserToken) throws GoStarRestTemplateException {

        try {
            final String baseUrl = microServiceUrl + skeletonTabFieldFilterService;
            URI uri = new URI(baseUrl);

            StructureTabFilterRequestForMicroService strTabFilterRequestForMicroService = allTabServicesUtility.prepareStructureTabFilterRequestForMicroServiceObject(tabularviewFilterRequest, gvkIdList);
            HttpHeaders headers = allTabServicesUtility.prepareHttpHeaders(currentSessionUserToken);

            HttpEntity<StructureTabFilterRequestForMicroService> request = new HttpEntity<>(strTabFilterRequestForMicroService, headers);

            ResponseEntity<String> responseEntity = restTemplate.postForEntity(uri, request, String.class);
            JSONArray strJson = new JSONArray(responseEntity.getBody());

            LOGGER.info(" In getSkeletonFieldFilteredData method RestTemplate Response JSON - {} ", strJson.length());

            ObjectMapper mapper = new ObjectMapper();
            List<SkeletonTabDTO> skeletonTabDTOS = new ArrayList<>();
            if (Objects.nonNull(strJson)) {
                skeletonTabDTOS = mapper.reader()
                        .forType(new TypeReference<Object>() {
                        })
                        .readValue(strJson.toString());
            }

            LOGGER.info(" AllTabServicesComponent :: getSkeletonFieldFilteredData - Received Results count {} ", skeletonTabDTOS.size());
            return skeletonTabDTOS;

        } catch (URISyntaxException ex) {
            ex.printStackTrace();
            LOGGER.error(" AllTabServicesComponent :: getSkeletonFieldFilteredData - URISyntaxException {} ", ex.getMessage());
            return new ArrayList<>();
        } catch (Exception ex) {
            ex.printStackTrace();
            LOGGER.error(" AllTabServicesComponent :: getSkeletonFieldFilteredData - Exception {} ", ex.getMessage());
            return new ArrayList<>();
        }
    }


    /**
     *
     * @param tabularviewRequest
     * @param gvkIdList
     * @param currentSessionUserToken
     * @return
     * @throws GoStarRestTemplateException
     */
    public SkeletonTabPageData getSkeletonData(TabularviewRequest tabularviewRequest, List<Long> gvkIdList, String currentSessionUserToken) throws GoStarRestTemplateException {

        try {
            final String baseUrl = microServiceUrl + skeletonTabDataService;
            URI uri = new URI(baseUrl);

            StructureTabDataMicroService structureTabDataMicroService = allTabServicesUtility.prepareStructureTabDataMicroServiceObject(tabularviewRequest, gvkIdList);
            HttpHeaders headers = allTabServicesUtility.prepareHttpHeaders(currentSessionUserToken);

            HttpEntity<StructureTabDataMicroService> request = new HttpEntity<>(structureTabDataMicroService, headers);

            ResponseEntity<String> responseEntity = restTemplate.postForEntity(uri, request, String.class);

            JSONObject strJson = new JSONObject(responseEntity.getBody());

            LOGGER.info(" In getScaffold2Data method RestTemplate Response JSON - {} ", strJson.length());

            ObjectMapper mapper = new ObjectMapper();

            List<SkeletonTabDTO> skeletonTabDTOS = new ArrayList<>();


            if (Objects.nonNull(strJson.getJSONArray(RESPONSE_SKELETON_TAB_DTO_LIST))) {
                strJson.getJSONArray("skeletonTabDTOList").getJSONObject(0).getString("smiles");
                skeletonTabDTOS = mapper.reader()
                        .forType(new TypeReference<Object>() {
                        })
                        .readValue(strJson.getJSONArray(RESPONSE_SKELETON_TAB_DTO_LIST).toString());

            }

            //for loop for to test the Smiles
            if (Objects.nonNull(strJson.getJSONArray(RESPONSE_SKELETON_TAB_DTO_LIST).getJSONObject(0).get("smiles"))) {

                JSONObject obj = new JSONObject();

                skeletonTabDTOS = mapper.reader()
                        .forType(new TypeReference<Object>() {
                        })
                        .readValue(strJson.getJSONArray(RESPONSE_SKELETON_TAB_DTO_LIST).toString());

                Object smilesTest =  strJson.getJSONArray(RESPONSE_SKELETON_TAB_DTO_LIST);

                Iterator itr = skeletonTabDTOS.iterator();

                while (itr.hasNext()) {
                    smilesTest =  itr.next();

                    for(int i=0 ;  i < skeletonTabDTOS.size(); i++) {
                        SkeletonTabDTO smileImages = mapper.convertValue(skeletonTabDTOS.get(i), SkeletonTabDTO.class);
                        chemaxon.util.MolHandler mh1 = new chemaxon.util.MolHandler(smileImages.getSmiles());

                        mh1.aromatize();
                        chemaxon.struc.Molecule mol = mh1.getMolecule();
                        mol.clean(2, null);
                        mol.dearomatize();
                        byte[] s1 = mol.toBinFormat("png:w250,h250,b32");

                        FileOutputStream out =new  FileOutputStream(servletContext.getRealPath("") +"assets/images/smileimages/"+ smileImages.getSkeletonId()+".png");

                        out.write(s1);
                        out.close();
                        out = null;

                    }

                }

            }

            Long count = 0L;
            if (Objects.nonNull(strJson.get(RESPONSE_COUNT)))
                count = Long.parseLong(strJson.get(RESPONSE_COUNT).toString());


            SkeletonTabPageData skeletonTabPageData = new SkeletonTabPageData();

            skeletonTabPageData.setSkeletonTabDTOList(skeletonTabDTOS);

            skeletonTabPageData.setCount(count);

            LOGGER.info(" AllTabServicesComponent :: getScaffold2Data - Received Results count {} ", skeletonTabPageData.getSkeletonTabDTOList().size());

            return skeletonTabPageData;
        } catch (URISyntaxException ex) {
            ex.printStackTrace();
            LOGGER.error(" AllTabServicesComponent :: getScaffold2Data - URISyntaxException {} ", ex.getMessage());
            SkeletonTabPageData skeletonTabPageData = new SkeletonTabPageData();
            skeletonTabPageData.setSkeletonTabDTOList(new ArrayList<>());
            skeletonTabPageData.setCount(0L);

            return skeletonTabPageData;
        } catch (Exception ex) {
            ex.printStackTrace();
            LOGGER.error(" AllTabServicesComponent :: getScaffold2Data - Exception {} ", ex.getMessage());
            SkeletonTabPageData skeletonTabPageData = new SkeletonTabPageData();
            skeletonTabPageData.setSkeletonTabDTOList(new ArrayList<>());
            skeletonTabPageData.setCount(0L);

            return skeletonTabPageData;
        }
    }

    /**
     *
     */
    public static final class BigIntegerDataMapper implements RowMapper<BigInteger> {

        private static final Logger LOGGER_INNER = LoggerFactory.getLogger(BigIntegerDataMapper.class);

        public BigInteger mapRow(ResultSet rs, int rowNum) throws SQLException {
            BigInteger columnValue = null;
            if (doesColumnExist("act_id", rs))
                columnValue = new BigInteger(Integer.valueOf(rs.getInt("act_id")).toString());
            else if (doesColumnExist("assay_id", rs))
                columnValue = new BigInteger(Integer.valueOf(rs.getInt("assay_id")).toString());
            else if (doesColumnExist("ref_id", rs))
                columnValue = new BigInteger(Integer.valueOf(rs.getInt("ref_id")).toString());
            else if (doesColumnExist("gvk_id", rs))
                columnValue = new BigInteger(Integer.valueOf(rs.getInt("gvk_id")).toString());
            else if (doesColumnExist("stdname_id", rs))
                columnValue = new BigInteger(Integer.valueOf(rs.getInt("stdname_id")).toString());
            else if (doesColumnExist("target_id", rs))
                columnValue = new BigInteger(Integer.valueOf(rs.getInt("target_id")).toString());

            return columnValue;
        }

        /**
         * Check for Field exists or not
         *
         * @param columnName
         * @param rs
         * @return
         * @throws SQLException
         */
        public static boolean doesColumnExist(String columnName, ResultSet rs) throws SQLException {
            ResultSetMetaData meta = rs.getMetaData();

            int numCol = meta.getColumnCount();

            for (int i = 1; i <= numCol; i++) {
                if (meta.getColumnName(i).equalsIgnoreCase(columnName)) {
                    LOGGER_INNER.debug("Column Exists ");
                    return true;
                }
            }

            return false;
        }
    }


    /**
     * Activity data Microservice RestTemplate
     *
     * @param tabularviewRequest
     * @param actIdList
     * @return
     */
    public Long getActivityDataCountsAfterFilters(TabularviewRequest tabularviewRequest, List<Long> actIdList, String currentSessionUserToken) {

        PageRequestInformation pageRequestInformation = new PageRequestInformation();
        pageRequestInformation.setPageNumber(0);
        pageRequestInformation.setPageSize(1);
        pageRequestInformation.setSortField("actId");
        pageRequestInformation.setSortType("DESC");
        tabularviewRequest.setPageRequestInformation(pageRequestInformation);

        ActivityTabPageData activityTabPageData = getActivityData(tabularviewRequest, actIdList, currentSessionUserToken);

        return activityTabPageData.getCount();

    }

    /**
     * Assay data Microservice RestTemplate
     *
     * @param tabularviewRequest
     * @param assayIdList
     * @return
     */
    public Long getAssayDataCountsAfterFilters(TabularviewRequest tabularviewRequest, List<Long> assayIdList, String currentSessionUserToken) {

        PageRequestInformation pageRequestInformation = new PageRequestInformation();
        pageRequestInformation.setPageNumber(0);
        pageRequestInformation.setPageSize(1);
        pageRequestInformation.setSortField("assayId");
        pageRequestInformation.setSortType("DESC");
        tabularviewRequest.setPageRequestInformation(pageRequestInformation);

        AssayTabPageData assayTabPageData = getAssayData(tabularviewRequest, assayIdList, currentSessionUserToken);

        return assayTabPageData.getCount();

    }

    /**
     * Reference data Microservice RestTemplate
     *
     * @param tabularviewRequest
     * @param refIdList
     * @return
     */
    public Long getReferenceDataCountsAfterFilters(TabularviewRequest tabularviewRequest, List<Long> refIdList, String currentSessionUserToken) {

        PageRequestInformation pageRequestInformation = new PageRequestInformation();
        pageRequestInformation.setPageNumber(0);
        pageRequestInformation.setPageSize(1);
        pageRequestInformation.setSortField("refId");
        pageRequestInformation.setSortType("DESC");
        tabularviewRequest.setPageRequestInformation(pageRequestInformation);

        ReferenceTabPageData refTabPageData = getReferenceData(tabularviewRequest, refIdList, currentSessionUserToken);

        return refTabPageData.getCount();

    }

    /**
     * Structure data Microservice RestTemplate
     *
     * @param tabularviewRequest
     * @param gvkIdList
     * @return
     */
    public Long getStructureDataCountsAfterFilters(TabularviewRequest tabularviewRequest, List<Long> gvkIdList, String currentSessionUserToken) {

        PageRequestInformation pageRequestInformation = new PageRequestInformation();
        pageRequestInformation.setPageNumber(0);
        pageRequestInformation.setPageSize(1);
        pageRequestInformation.setSortField("gvkId");
        pageRequestInformation.setSortType("DESC");
        tabularviewRequest.setPageRequestInformation(pageRequestInformation);

        StructureDetailsTabPageData structureDetailsTabPageData = getStructureData(tabularviewRequest, gvkIdList, currentSessionUserToken);

        return structureDetailsTabPageData.getCount();

    }

}

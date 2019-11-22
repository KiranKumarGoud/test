package com.excelra.mvc.utility;

import com.excelra.mvc.model.tabularview.TabularviewFilterRequest;
import com.excelra.mvc.model.tabularview.TabularviewRequest;
import com.excelra.mvc.model.tabularview.activitytab.ActivityTabDataMicroService;
import com.excelra.mvc.model.tabularview.activitytab.ActivityTabFilterRequestForMicroService;
import com.excelra.mvc.model.tabularview.assaytab.AssayTabDataMicroService;
import com.excelra.mvc.model.tabularview.assaytab.AssayTabFilterRequestForMicroService;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * All Tab Services Utility methods like object creation, preparing object etc.
 *
 * @author venkateswarlu.s
 */
@Component
public class AllTabServicesUtility {

    private static final Logger LOGGER = LoggerFactory.getLogger(AllTabServicesUtility.class);

    /**
     *
     * @param currentSessionUserToken
     * @return
     */
    public HttpHeaders prepareHttpHeaders(String currentSessionUserToken) {

        HttpHeaders headers = new HttpHeaders();

        LOGGER.info(" RestTemplate - Microservice user Token is {} ", currentSessionUserToken);

        headers.set("Authorization", "Bearer "+currentSessionUserToken);
        headers.set("Content-Type", "application/json");

        return headers;
    }

    /**
     *
     * @param tabularviewFilterRequest
     * @param actIdList
     * @return
     */
    public ActivityTabFilterRequestForMicroService prepareActivityTabFilterRequestForMicroServiceObject(TabularviewFilterRequest tabularviewFilterRequest, List<Long> actIdList) {

        LOGGER.info(" Preparing the Object for ActivityTabFilterRequestForMicroService tabularviewFilterRequest - {}, actIdList - {} ", tabularviewFilterRequest, actIdList);

        ActivityTabFilterRequestForMicroService activityTabFilterRequestForMicroService = new ActivityTabFilterRequestForMicroService();

        activityTabFilterRequestForMicroService.setActIdList(actIdList);
        activityTabFilterRequestForMicroService.setStringFilters(tabularviewFilterRequest.getStringFilters());
        activityTabFilterRequestForMicroService.setNumericFilters(tabularviewFilterRequest.getNumericFilters());
        activityTabFilterRequestForMicroService.setFieldName(tabularviewFilterRequest.getFieldName());
        activityTabFilterRequestForMicroService.setFieldType(tabularviewFilterRequest.getFieldType());
        activityTabFilterRequestForMicroService.setFieldOperator(tabularviewFilterRequest.getFieldOperator());
        activityTabFilterRequestForMicroService.setFieldValue(tabularviewFilterRequest.getFieldValue());
        activityTabFilterRequestForMicroService.setPageSize(tabularviewFilterRequest.getPageSize());
        activityTabFilterRequestForMicroService.setPageNumber(tabularviewFilterRequest.getPageNumber());

        return activityTabFilterRequestForMicroService;
    }

    /**
     *
     * @param tabularviewRequest
     * @param actIdList
     * @return
     */
    public ActivityTabDataMicroService prepareActivityTabDataMicroServiceObject(TabularviewRequest tabularviewRequest, List<Long> actIdList) {

        LOGGER.info(" Preparing the Object for ActivityTabDataMicroService tabularviewRequest - {}, actIdList - {} ", tabularviewRequest, actIdList);

        ActivityTabDataMicroService activityTabDataMicroService = new ActivityTabDataMicroService();

        activityTabDataMicroService.setActIdList(actIdList);
        activityTabDataMicroService.setNumericFilters(tabularviewRequest.getNumericFilters());
        activityTabDataMicroService.setStringFilters(tabularviewRequest.getStringFilters());
        activityTabDataMicroService.setFreeTextFilters(tabularviewRequest.getFreeTextFilters());
        activityTabDataMicroService.setPageRequestInformation(tabularviewRequest.getPageRequestInformation());

        return activityTabDataMicroService;

    }

    /**
     *
     * @param tabularviewFilterRequest
     * @param assayIdList
     * @return
     */
    public AssayTabFilterRequestForMicroService prepareAssayTabFilterRequestForMicroServiceObject(TabularviewFilterRequest tabularviewFilterRequest, List<Long> assayIdList) {

        LOGGER.info(" Preparing the Object for AssayTabFilterRequestForMicroService tabularviewFilterRequest - {}, assayIdList - {} ", tabularviewFilterRequest, assayIdList);

        AssayTabFilterRequestForMicroService assayTabFilterRequestForMicroService = new AssayTabFilterRequestForMicroService();

        assayTabFilterRequestForMicroService.setAssayIdList(assayIdList);
        assayTabFilterRequestForMicroService.setNumericFilters(tabularviewFilterRequest.getNumericFilters());
        assayTabFilterRequestForMicroService.setStringFilters(tabularviewFilterRequest.getStringFilters());
        assayTabFilterRequestForMicroService.setFieldName(tabularviewFilterRequest.getFieldName());
        assayTabFilterRequestForMicroService.setFieldOperator(tabularviewFilterRequest.getFieldOperator());
        assayTabFilterRequestForMicroService.setFieldType(tabularviewFilterRequest.getFieldType());
        assayTabFilterRequestForMicroService.setFieldValue(tabularviewFilterRequest.getFieldValue());
        assayTabFilterRequestForMicroService.setPageNumber(tabularviewFilterRequest.getPageNumber());
        assayTabFilterRequestForMicroService.setPageSize(tabularviewFilterRequest.getPageSize());

        return assayTabFilterRequestForMicroService;
    }

    /**
     *
     * @param tabularviewRequest
     * @param assayIdList
     * @return
     */
    public AssayTabDataMicroService prepareAssayTabDataMicroServiceObject(TabularviewRequest tabularviewRequest, List<Long> assayIdList) {

        LOGGER.info(" Preparing the Object for AssayTabDataMicroService tabularviewRequest - {}, assayIdList - {} ", tabularviewRequest, assayIdList);

        AssayTabDataMicroService assayTabDataMicroService = new AssayTabDataMicroService();

        assayTabDataMicroService.setAssayIdList(assayIdList);
        assayTabDataMicroService.setNumericFilters(tabularviewRequest.getNumericFilters());
        assayTabDataMicroService.setStringFilters(tabularviewRequest.getStringFilters());
        assayTabDataMicroService.setFreeTextFilters(tabularviewRequest.getFreeTextFilters());
        assayTabDataMicroService.setPageRequestInformation(tabularviewRequest.getPageRequestInformation());

        return assayTabDataMicroService;

    }

    /**
     *
     * @param tabularviewFilterRequest
     * @param refIdList
     * @return
     */
    public ReferenceTabFilterRequestForMicroService prepareReferenceTabFilterRequestForMicroServiceObject(TabularviewFilterRequest tabularviewFilterRequest, List<Long> refIdList) {

        LOGGER.info(" Preparing the Object for ReferenceTabFilterRequestForMicroService tabularviewFilterRequest - {}, refIdList - {} ", tabularviewFilterRequest, refIdList);

        ReferenceTabFilterRequestForMicroService refTabFilterRequestForMicroService = new ReferenceTabFilterRequestForMicroService();

        refTabFilterRequestForMicroService.setRefIdList(refIdList);
        refTabFilterRequestForMicroService.setNumericFilters(tabularviewFilterRequest.getNumericFilters());
        refTabFilterRequestForMicroService.setStringFilters(tabularviewFilterRequest.getStringFilters());
        refTabFilterRequestForMicroService.setFieldName(tabularviewFilterRequest.getFieldName());
        refTabFilterRequestForMicroService.setFieldOperator(tabularviewFilterRequest.getFieldOperator());
        refTabFilterRequestForMicroService.setFieldType(tabularviewFilterRequest.getFieldType());
        refTabFilterRequestForMicroService.setFieldValue(tabularviewFilterRequest.getFieldValue());
        refTabFilterRequestForMicroService.setPageNumber(tabularviewFilterRequest.getPageNumber());
        refTabFilterRequestForMicroService.setPageSize(tabularviewFilterRequest.getPageSize());

        return refTabFilterRequestForMicroService;
    }

    /**
     *
     * @param tabularviewRequest
     * @param refIdList
     * @return
     */
    public ReferenceTabDataMicroService prepareReferenceTabDataMicroServiceObject(TabularviewRequest tabularviewRequest, List<Long> refIdList) {

        LOGGER.info(" Preparing the Object for ReferenceTabDataMicroService tabularviewRequest - {}, refIdList - {} ", tabularviewRequest, refIdList);

        ReferenceTabDataMicroService referenceTabDataMicroService = new ReferenceTabDataMicroService();

        referenceTabDataMicroService.setRefIdList(refIdList);
        referenceTabDataMicroService.setNumericFilters(tabularviewRequest.getNumericFilters());
        referenceTabDataMicroService.setStringFilters(tabularviewRequest.getStringFilters());
        referenceTabDataMicroService.setFreeTextFilters(tabularviewRequest.getFreeTextFilters());
        referenceTabDataMicroService.setPageRequestInformation(tabularviewRequest.getPageRequestInformation());

        return referenceTabDataMicroService;
    }

    /**
     *
     * @param tabularviewFilterRequest
     * @param stdnameIdList
     * @return
     */
    public ProteinClassificationTabFilterRequestForMicroService prepareProteinClassificationTabFilterRequestForMicroServiceObject(TabularviewFilterRequest tabularviewFilterRequest, List<Long> stdnameIdList) {

        LOGGER.info(" Preparing the Object for ProteinClassificationTabFilterRequestForMicroService tabularviewFilterRequest - {}, stdnameIdList - {} ", tabularviewFilterRequest, stdnameIdList);

        ProteinClassificationTabFilterRequestForMicroService proteinClassificationTabFilterRequestForMicroService = new ProteinClassificationTabFilterRequestForMicroService();

        proteinClassificationTabFilterRequestForMicroService.setStdnameIdList(stdnameIdList);
        proteinClassificationTabFilterRequestForMicroService.setNumericFilters(tabularviewFilterRequest.getNumericFilters());
        proteinClassificationTabFilterRequestForMicroService.setStringFilters(tabularviewFilterRequest.getStringFilters());
        proteinClassificationTabFilterRequestForMicroService.setFieldName(tabularviewFilterRequest.getFieldName());
        proteinClassificationTabFilterRequestForMicroService.setFieldOperator(tabularviewFilterRequest.getFieldOperator());
        proteinClassificationTabFilterRequestForMicroService.setFieldType(tabularviewFilterRequest.getFieldType());
        proteinClassificationTabFilterRequestForMicroService.setFieldValue(tabularviewFilterRequest.getFieldValue());
        proteinClassificationTabFilterRequestForMicroService.setPageNumber(tabularviewFilterRequest.getPageNumber());
        proteinClassificationTabFilterRequestForMicroService.setPageSize(tabularviewFilterRequest.getPageSize());

        return proteinClassificationTabFilterRequestForMicroService;
    }

    /**
     *
     * @param tabularviewRequest
     * @param stdnameIdList
     * @return
     */
    public ProteinClassificationTabDataMicroService prepareProteinClassificationTabDataMicroServiceObject(TabularviewRequest tabularviewRequest, List<Long> stdnameIdList) {

        LOGGER.info(" Preparing the Object for ProteinClassificationTabDataMicroService tabularviewRequest - {}, stdnameIdList - {} ", tabularviewRequest, stdnameIdList);

        ProteinClassificationTabDataMicroService proteinClassificationTabDataMicroService = new ProteinClassificationTabDataMicroService();

        proteinClassificationTabDataMicroService.setStdnameIdList(stdnameIdList);
        proteinClassificationTabDataMicroService.setNumericFilters(tabularviewRequest.getNumericFilters());
        proteinClassificationTabDataMicroService.setStringFilters(tabularviewRequest.getStringFilters());
        proteinClassificationTabDataMicroService.setFreeTextFilters(tabularviewRequest.getFreeTextFilters());
        proteinClassificationTabDataMicroService.setPageRequestInformation(tabularviewRequest.getPageRequestInformation());

        return proteinClassificationTabDataMicroService;
    }

    /**
     *
     * @param tabularviewFilterRequest
     * @param gvkIdList
     * @return
     */
    public StructureTabFilterRequestForMicroService prepareStructureTabFilterRequestForMicroServiceObject(TabularviewFilterRequest tabularviewFilterRequest, List<Long> gvkIdList) {

        LOGGER.info(" Preparing the Object for StructureTabFilterRequestForMicroService tabularviewFilterRequest - {}, gvkIdList - {} ", tabularviewFilterRequest, gvkIdList);

        StructureTabFilterRequestForMicroService strTabFilterRequestForMicroService = new StructureTabFilterRequestForMicroService();

        strTabFilterRequestForMicroService.setGvkIdList(gvkIdList);
        strTabFilterRequestForMicroService.setNumericFilters(tabularviewFilterRequest.getNumericFilters());
        strTabFilterRequestForMicroService.setStringFilters(tabularviewFilterRequest.getStringFilters());
        strTabFilterRequestForMicroService.setFieldName(tabularviewFilterRequest.getFieldName());
        strTabFilterRequestForMicroService.setFieldOperator(tabularviewFilterRequest.getFieldOperator());
        strTabFilterRequestForMicroService.setFieldType(tabularviewFilterRequest.getFieldType());
        strTabFilterRequestForMicroService.setFieldValue(tabularviewFilterRequest.getFieldValue());
        strTabFilterRequestForMicroService.setPageNumber(tabularviewFilterRequest.getPageNumber());
        strTabFilterRequestForMicroService.setPageSize(tabularviewFilterRequest.getPageSize());

        return strTabFilterRequestForMicroService;
    }

    /**
     *
     * @param tabularviewRequest
     * @param gvkIdList
     * @return
     */
    public StructureTabDataMicroService prepareStructureTabDataMicroServiceObject(TabularviewRequest tabularviewRequest, List<Long> gvkIdList) {

        LOGGER.info(" Preparing the Object for StructureTabDataMicroService tabularviewRequest - {}, gvkIdList - {} ", tabularviewRequest, gvkIdList);

        StructureTabDataMicroService structureTabDataMicroService = new StructureTabDataMicroService();

        structureTabDataMicroService.setGvkIdList(gvkIdList);
        structureTabDataMicroService.setNumericFilters(tabularviewRequest.getNumericFilters());
        structureTabDataMicroService.setStringFilters(tabularviewRequest.getStringFilters());
        structureTabDataMicroService.setFreeTextFilters(tabularviewRequest.getFreeTextFilters());
        structureTabDataMicroService.setPageRequestInformation(tabularviewRequest.getPageRequestInformation());

        return structureTabDataMicroService;
    }

    /**
     *
     * @param tabularviewFilterRequest
     * @param targetIdList
     * @return
     */
    public TargetDetailsTabFilterRequestForMicroService prepareTargetDetailsTabFilterRequestForMicroServiceObject(TabularviewFilterRequest tabularviewFilterRequest, List<Long> targetIdList) {

        LOGGER.info(" Preparing the Object for TargetDetailsTabFilterRequestForMicroService tabularviewFilterRequest - {}, targetIdList - {} ", tabularviewFilterRequest, targetIdList);

        TargetDetailsTabFilterRequestForMicroService targetDetailsTabFilterRequestForMicroService = new TargetDetailsTabFilterRequestForMicroService();

        targetDetailsTabFilterRequestForMicroService.setTargetIdList(targetIdList);
        targetDetailsTabFilterRequestForMicroService.setNumericFilters(tabularviewFilterRequest.getNumericFilters());
        targetDetailsTabFilterRequestForMicroService.setStringFilters(tabularviewFilterRequest.getStringFilters());
        targetDetailsTabFilterRequestForMicroService.setFieldName(tabularviewFilterRequest.getFieldName());
        targetDetailsTabFilterRequestForMicroService.setFieldOperator(tabularviewFilterRequest.getFieldOperator());
        targetDetailsTabFilterRequestForMicroService.setFieldType(tabularviewFilterRequest.getFieldType());
        targetDetailsTabFilterRequestForMicroService.setFieldValue(tabularviewFilterRequest.getFieldValue());
        targetDetailsTabFilterRequestForMicroService.setPageNumber(tabularviewFilterRequest.getPageNumber());
        targetDetailsTabFilterRequestForMicroService.setPageSize(tabularviewFilterRequest.getPageSize());

        return targetDetailsTabFilterRequestForMicroService;
    }

    /**
     *
     * @param tabularviewRequest
     * @param targetIdList
     * @return
     */
    public TargetDetailsTabDataMicroService prepareTargetDetailsTabDataMicroServiceObject(TabularviewRequest tabularviewRequest, List<Long> targetIdList) {

        LOGGER.info(" Preparing the Object for TargetDetailsTabDataMicroService tabularviewRequest - {}, targetIdList - {} ", tabularviewRequest, targetIdList);

        TargetDetailsTabDataMicroService targetDetailsTabDataMicroService = new TargetDetailsTabDataMicroService();

        targetDetailsTabDataMicroService.setTargetIdList(targetIdList);
        targetDetailsTabDataMicroService.setNumericFilters(tabularviewRequest.getNumericFilters());
        targetDetailsTabDataMicroService.setStringFilters(tabularviewRequest.getStringFilters());
        targetDetailsTabDataMicroService.setFreeTextFilters(tabularviewRequest.getFreeTextFilters());
        targetDetailsTabDataMicroService.setPageRequestInformation(tabularviewRequest.getPageRequestInformation());

        return targetDetailsTabDataMicroService;
    }

    /**
     *
     * @param tabularviewFilterRequest
     * @param gvkIdList
     * @return
     */
    public IndicationTabFilterRequestForMicroService prepareIndicationTabFilterRequestForMicroServiceObject(TabularviewFilterRequest tabularviewFilterRequest, List<Long> gvkIdList) {

        LOGGER.info(" Preparing the Object for IndicationTabFilterRequestForMicroService tabularviewFilterRequest - {}, gvkIdList - {} ", tabularviewFilterRequest, gvkIdList);

        IndicationTabFilterRequestForMicroService indicationTabFilterRequestForMicroService = new IndicationTabFilterRequestForMicroService();

        indicationTabFilterRequestForMicroService.setGvkIdList(gvkIdList);
        indicationTabFilterRequestForMicroService.setNumericFilters(tabularviewFilterRequest.getNumericFilters());
        indicationTabFilterRequestForMicroService.setStringFilters(tabularviewFilterRequest.getStringFilters());
        indicationTabFilterRequestForMicroService.setFieldName(tabularviewFilterRequest.getFieldName());
        indicationTabFilterRequestForMicroService.setFieldOperator(tabularviewFilterRequest.getFieldOperator());
        indicationTabFilterRequestForMicroService.setFieldType(tabularviewFilterRequest.getFieldType());
        indicationTabFilterRequestForMicroService.setFieldValue(tabularviewFilterRequest.getFieldValue());
        indicationTabFilterRequestForMicroService.setPageNumber(tabularviewFilterRequest.getPageNumber());
        indicationTabFilterRequestForMicroService.setPageSize(tabularviewFilterRequest.getPageSize());

        return indicationTabFilterRequestForMicroService;
    }

    /**
     *
     * @param tabularviewRequest
     * @param gvkIdList
     * @return
     */
    public IndicationTabDataMicroService prepareIndicationTabDataMicroServiceObject(TabularviewRequest tabularviewRequest, List<Long> gvkIdList) {

        LOGGER.info(" Preparing the Object for IndicationTabDataMicroService tabularviewRequest - {}, gvkIdList - {} ", tabularviewRequest, gvkIdList);

        IndicationTabDataMicroService indicationTabDataMicroService = new IndicationTabDataMicroService();

        indicationTabDataMicroService.setGvkIdList(gvkIdList);
        indicationTabDataMicroService.setNumericFilters(tabularviewRequest.getNumericFilters());
        indicationTabDataMicroService.setStringFilters(tabularviewRequest.getStringFilters());
        indicationTabDataMicroService.setFreeTextFilters(tabularviewRequest.getFreeTextFilters());
        indicationTabDataMicroService.setPageRequestInformation(tabularviewRequest.getPageRequestInformation());

        return indicationTabDataMicroService;
    }


    /**
     *
     * @param tabularviewFilterRequest
     * @param gvkIdList
     * @param refIdList
     * @return
     */
    public ClinicalStatusTabFilterRequestForMicroService prepareClinicalStatusTabFilterRequestForMicroServiceObject(TabularviewFilterRequest tabularviewFilterRequest, List<Long> gvkIdList, List<Long> refIdList) {

        LOGGER.info(" Preparing the Object for ClinicalStatusTabFilterRequestForMicroService tabularviewFilterRequest - {}, gvkIdList - {}, refIdList - {} ", tabularviewFilterRequest, gvkIdList, refIdList);

        ClinicalStatusTabFilterRequestForMicroService clinicalStatusTabFilterRequestForMicroService = new ClinicalStatusTabFilterRequestForMicroService();

        clinicalStatusTabFilterRequestForMicroService.setGvkIdList(gvkIdList);
        clinicalStatusTabFilterRequestForMicroService.setRefIdList(refIdList);
        clinicalStatusTabFilterRequestForMicroService.setNumericFilters(tabularviewFilterRequest.getNumericFilters());
        clinicalStatusTabFilterRequestForMicroService.setStringFilters(tabularviewFilterRequest.getStringFilters());
        clinicalStatusTabFilterRequestForMicroService.setFieldName(tabularviewFilterRequest.getFieldName());
        clinicalStatusTabFilterRequestForMicroService.setFieldOperator(tabularviewFilterRequest.getFieldOperator());
        clinicalStatusTabFilterRequestForMicroService.setFieldType(tabularviewFilterRequest.getFieldType());
        clinicalStatusTabFilterRequestForMicroService.setFieldValue(tabularviewFilterRequest.getFieldValue());
        clinicalStatusTabFilterRequestForMicroService.setPageNumber(tabularviewFilterRequest.getPageNumber());
        clinicalStatusTabFilterRequestForMicroService.setPageSize(tabularviewFilterRequest.getPageSize());

        return clinicalStatusTabFilterRequestForMicroService;
    }

    /**
     *
     * @param tabularviewRequest
     * @param gvkIdList
     * @param refIdList
     * @return
     */
    public ClinicalStatusTabDataMicroService prepareClinicalStatusTabDataMicroServiceObject(TabularviewRequest tabularviewRequest, List<Long> gvkIdList, List<Long> refIdList) {

        LOGGER.info(" Preparing the Object for ClinicalStatusTabDataMicroService tabularviewRequest - {}, gvkIdList - {}, refIdList - {} ", tabularviewRequest, gvkIdList, refIdList);

        ClinicalStatusTabDataMicroService clinicalStatusTabDataMicroService = new ClinicalStatusTabDataMicroService();

        clinicalStatusTabDataMicroService.setGvkIdList(gvkIdList);
        clinicalStatusTabDataMicroService.setRefIdList(refIdList);
        clinicalStatusTabDataMicroService.setNumericFilters(tabularviewRequest.getNumericFilters());
        clinicalStatusTabDataMicroService.setStringFilters(tabularviewRequest.getStringFilters());
        clinicalStatusTabDataMicroService.setFreeTextFilters(tabularviewRequest.getFreeTextFilters());
        clinicalStatusTabDataMicroService.setPageRequestInformation(tabularviewRequest.getPageRequestInformation());

        return clinicalStatusTabDataMicroService;
    }
}
